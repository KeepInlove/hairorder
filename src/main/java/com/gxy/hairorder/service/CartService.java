package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.Barber;
import com.gxy.hairorder.entity.Cart;
import com.gxy.hairorder.entity.Hair;
import com.gxy.hairorder.repository.BarberRepository;
import com.gxy.hairorder.repository.CartRepository;
import com.gxy.hairorder.repository.HairRepository;
import com.gxy.hairorder.req.CartReq;
import com.gxy.hairorder.resp.CartResp;
import com.gxy.hairorder.utils.CopyUtil;
import com.gxy.hairorder.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/22 19:15
 * @Classname CartService
 * @Description TODO
 */
@Service
public class CartService {
    @Autowired
    public CartRepository cartRepository;
    @Autowired
    private HairRepository hairRepository;
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private SnowFlake snowFlake;
    //新增
    public void save(CartReq cartReq){
        Cart cart = CopyUtil.copy(cartReq, Cart.class);
        cart.setCartId(snowFlake.nextId());
        cart.setCreateDate(new Date(System.currentTimeMillis()));
        cartRepository.save(cart);
    }
    public List<CartResp> findByUserId(Long userId){
        List<Cart> cartList = cartRepository.findByUserId(userId);
        List<CartResp> cartRespList = CopyUtil.copyList(cartList, CartResp.class);
        for (CartResp cartResp:cartRespList){
            Hair hair = hairRepository.findByHairId(cartResp.getHairId());
            Barber barber = barberRepository.findByBarberId(cartResp.getBarberId());
            cartResp.setBarberName(barber.getBarberName());
            cartResp.setHariName(hair.getHairName());
        }
        return cartRespList;
    }
}
