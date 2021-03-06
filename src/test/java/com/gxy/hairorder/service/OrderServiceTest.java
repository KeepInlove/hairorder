package com.gxy.hairorder.service;

import com.gxy.hairorder.req.OrderReq;
import com.gxy.hairorder.resp.OrderResp;
import com.gxy.hairorder.resp.PageResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/27 1:01
 * @Classname OrderServiceTest
 * @Description TODO
 */
@SpringBootTest
@Slf4j
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Test
    public void saveOrder(){
        List<Long> list=new ArrayList<>();
        list.add(10184747217339392l);
        orderService.saveOrder(list);
    }
    @Test
    public void userOrder(){
        List<OrderResp> orderRespList = orderService.orderStatus(9863294692115456l);
        log.info(orderRespList.toString());
    }
    @Test
    public void list(){
        OrderReq orderReq=new OrderReq();
        orderReq.setPage(0);
        orderReq.setSize(3);
        PageResp<OrderResp> pageResp = orderService.list(orderReq);
        log.info(pageResp.toString());
    }
}