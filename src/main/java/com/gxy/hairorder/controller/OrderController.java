package com.gxy.hairorder.controller;

import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/27 1:20
 * @Classname OrderController
 * @Description TODO
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public CommonResp addOrder(@RequestBody List<Long> cartIdList){
        CommonResp resp=new CommonResp();
//        log.info(cartIdList.toString());
        orderService.saveOrder(cartIdList);
        return resp;
    }

    @GetMapping("/userOrder/{userId}")
    public CommonResp userOrder(@RequestBody Long userId){
        CommonResp resp=new CommonResp();
//        log.info(cartIdList.toString());
        orderService.userOrder(userId);
        return resp;
    }
}
