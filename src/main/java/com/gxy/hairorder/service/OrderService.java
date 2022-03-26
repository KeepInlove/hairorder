package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.*;
import com.gxy.hairorder.repository.*;
import com.gxy.hairorder.resp.OrderResp;
import com.gxy.hairorder.utils.CopyUtil;
import com.gxy.hairorder.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/27 0:23
 * @Classname OrderService
 * @Description TODO
 */
@Slf4j
@Service
@Transactional
public class OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private HairRepository hairRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    public CartRepository cartRepository;
    @Autowired
    private SnowFlake snowFlake;

    //app下单
    public void saveOrder(List<Long> cartIdList){
        for (long cartId:cartIdList){
            Cart cart = cartRepository.findByCartId(cartId);
            //增加服务量
            Barber barber = barberRepository.findByBarberId(cart.getBarberId());
            barber.setOrderCount(barber.getOrderCount()+1);
            barberRepository.save(barber);
            //增加销售量
            Hair hair=hairRepository.findByHairId(cart.getHairId());
            hair.setOrderCount(hair.getOrderCount()+1);
            hairRepository.save(hair);

            Order order = CopyUtil.copy(cart, Order.class);
            order.setOrderId(snowFlake.nextId());
            order.setOrderStatus(0);
            Optional<User> user = userRepository.findById(cart.getUserId());
            String phone = user.get().getPhone();
            order.setPhone(phone);
            order.setCreateTime(new Date(System.currentTimeMillis()));
            orderRepository.save(order);
            //删除购物车
            cartRepository.deleteById(cartId);
        }
    }

    public List<OrderResp> userOrder(Long userId) {
        List<Order> orderList = orderRepository.findByUserId(userId);
        List<OrderResp> orderRespList = CopyUtil.copyList(orderList, OrderResp.class);
        for (OrderResp orderResp : orderRespList) {

            Barber barber = barberRepository.findByBarberId(orderResp.getBarberId());
            Hair hair = hairRepository.findByHairId(orderResp.getHairId());

            orderResp.setBarberName(barber.getBarberName());
            orderResp.setHairName(hair.getHairName());
            orderResp.setHairImg(hair.getHairImg());
        }
        return orderRespList;
    }
}
