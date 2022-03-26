package com.gxy.hairorder.service;

import com.gxy.hairorder.req.CartReq;
import com.gxy.hairorder.resp.CartResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/24 21:33
 * @Classname CartServiceTest
 * @Description TODO
 */
@SpringBootTest
@Slf4j
public class CartServiceTest {
    @Autowired
    public CartService cartService;

    @Test
    public void save() {
        CartReq cartReq = new CartReq();
        cartReq.setUserId(9863294692115456l);
        cartReq.setBarberId(9797145943360512l);
        cartReq.setHairId(9837053321167872l);
        cartReq.setOrderDate(Date.valueOf("2022-03-24"));
        cartService.save(cartReq);
    }

    @Test
    public void list() {
        List<CartResp> cartRespList = cartService.findCart(9863294692115456l);
        log.info(cartRespList.toString());
    }
    @Test
    public void count() {
        Integer count = cartService.countByUserId(9863294692115456l);
        log.info(String.valueOf(count));
    }
}