package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.*;
import com.gxy.hairorder.form.OrderForm;
import com.gxy.hairorder.repository.*;
import com.gxy.hairorder.req.OrderReq;
import com.gxy.hairorder.resp.OrderResp;
import com.gxy.hairorder.resp.PageResp;
import com.gxy.hairorder.utils.CopyUtil;
import com.gxy.hairorder.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void saveOrder(List<Long> cartIdList) {
        for (long cartId : cartIdList) {
            Cart cart = cartRepository.findByCartId(cartId);
            //增加服务量
            Barber barber = barberRepository.findByBarberId(cart.getBarberId());
            barber.setOrderCount(barber.getOrderCount() + 1);
            barberRepository.save(barber);
            //增加销售量
            Hair hair = hairRepository.findByHairId(cart.getHairId());
            hair.setOrderCount(hair.getOrderCount() + 1);
            hairRepository.save(hair);

            Order order = CopyUtil.copy(cart, Order.class);
            order.setOrderId(snowFlake.nextId());
            order.setOrderStatus(0);
            Optional<User> user = userRepository.findById(cart.getUserId());
            String phone = user.get().getPhone();
            //添加积分
            Integer integral = cart.getPrice().intValue();
            user.get().setIntegral(user.get().getIntegral() + integral);
            order.setPhone(phone);
            order.setCreateTime(new Date(System.currentTimeMillis()));
            orderRepository.save(order);
            //删除购物车
            cartRepository.deleteById(cartId);
        }
    }

    //查询已确认或已拒绝订单
    public List<OrderResp> okOrder(Long userId) {
//        List<Order> orderList = orderRepository.findByUserId(userId);
//        List<OrderResp> orderRespList = CopyUtil.copyList(orderList, OrderResp.class);
//        for (OrderResp orderResp : orderRespList) {
//
//            Barber barber = barberRepository.findByBarberId(orderResp.getBarberId());
//            Hair hair = hairRepository.findByHairId(orderResp.getHairId());
//
//            orderResp.setBarberName(barber.getBarberName());
//            orderResp.setHairName(hair.getHairName());
//            orderResp.setHairImg(hair.getHairImg());
//        }
//        return orderRespList;
        List<OrderResp> orderResp = orderToOrderResp(userId);
        //过滤订单状态
        List<OrderResp> respList = orderResp.stream().filter(u -> u.getOrderStatus() != 0).collect(Collectors.toList());
        return respList;

    }

    //查询待确认订单
    public List<OrderResp> orderStatus(Long userId) {
        List<OrderResp> orderResp = orderToOrderResp(userId);
        //过滤订单状态
        List<OrderResp> respList = orderResp.stream().filter(u -> u.getOrderStatus() == 0).collect(Collectors.toList());
        return respList;
    }

    //订单转换
    public List<OrderResp> orderToOrderResp(Long userId) {
        List<Order> orderList = orderRepository.findByUserId(userId);
        List<OrderResp> orderResp = CopyUtil.copyList(orderList, OrderResp.class);
        List<OrderResp> orderRespList = forList(orderResp);
//        for (OrderResp orderResp : orderRespList) {
//            Barber barber = barberRepository.findByBarberId(orderResp.getBarberId());
//            Hair hair = hairRepository.findByHairId(orderResp.getHairId());
//            orderResp.setBarberName(barber.getBarberName());
//            orderResp.setHairName(hair.getHairName());
//            orderResp.setHairImg(hair.getHairImg());
//        }
        return orderRespList;
    }

    //取消订单
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        order.setOrderStatus(2);
        orderRepository.save(order);
    }

    //分页查询订单
    public PageResp<OrderResp> list(OrderReq req) {
        PageRequest pageRequest = PageRequest.of(req.getPage(), req.getSize());
        Page<Order> orderPage = orderRepository.findAll(pageRequest);
//        PageInfo<User>pageInfo=new PageInfo<>(userList);
        log.info("总数:{}", orderPage.getTotalElements());
        log.info("总页数:{}", orderPage.getTotalPages());
        //拷贝工具类

        List<OrderResp> respList = CopyUtil.copyList(orderPage.getContent(), OrderResp.class);
        List<OrderResp> orderRespList = forList(respList);
//        for (OrderResp orderResp : respList) {
//            Barber barber = barberRepository.findByBarberId(orderResp.getBarberId());
//            Hair hair = hairRepository.findByHairId(orderResp.getHairId());
//            orderResp.setBarberName(barber.getBarberName());
//            orderResp.setHairName(hair.getHairName());
//            orderResp.setHairImg(hair.getHairImg());
//        }
        PageResp<OrderResp> pageResp = new PageResp<>();
        pageResp.setTotal(orderPage.getTotalElements());
        pageResp.setList(orderRespList);
        return pageResp;
    }

    public List<OrderResp> forList(List<OrderResp> respList) {
        for (OrderResp orderResp : respList) {
            Barber barber = barberRepository.findByBarberId(orderResp.getBarberId());
            Hair hair = hairRepository.findByHairId(orderResp.getHairId());

            User user = userRepository.findByPhone(orderResp.getPhone());
            if (user==null){
                orderResp.setUserName("新客户");
            }else {
            orderResp.setUserName(user.getName());
            }
            orderResp.setBarberName(barber.getBarberName());
            orderResp.setHairName(hair.getHairName());
            orderResp.setHairImg(hair.getHairImg());
        }
        Collections.reverse(respList);
        return respList;
    }

    //接受订单
    public void acceptOrder(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        order.setOrderStatus(1);
        orderRepository.save(order);
    }

    public List<OrderResp> findByPhone(String phone) {
        List<Order> orderList = orderRepository.findByPhone(phone);
        if (orderList != null && orderList.size() > 0) {
            List<OrderResp> copyList = CopyUtil.copyList(orderList, OrderResp.class);
            List<OrderResp> respList = forList(copyList);
            return respList;
        } else {
            return null;
        }
    }

    public void save(OrderForm orderForm) {
        Order order=CopyUtil.copy(orderForm,Order.class);
        order.setOrderId(snowFlake.nextId());
        order.setOrderStatus(1);
        orderRepository.save(order);
    }
}
