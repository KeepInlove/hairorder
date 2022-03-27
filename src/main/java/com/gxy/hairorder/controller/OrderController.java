package com.gxy.hairorder.controller;

import com.gxy.hairorder.form.OrderForm;
import com.gxy.hairorder.req.OrderReq;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.resp.OrderResp;
import com.gxy.hairorder.resp.PageResp;
import com.gxy.hairorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/save")
    public CommonResp saveOrder(@Valid @RequestBody OrderForm orderForm){
        CommonResp resp=new CommonResp();
        orderService.save(orderForm);
        return resp;
    }
    @GetMapping("/list")
    public CommonResp userList(@Valid OrderReq orderReq){
        CommonResp<PageResp<OrderResp>> resp=new CommonResp<>();
        PageResp<OrderResp> pageResp = orderService.list(orderReq);
        resp.setContent(pageResp);
        return resp;
    }
    //待确认订单
    @GetMapping("/findByPhone/{phone}")
    public CommonResp orderStatus(@PathVariable String phone){
        CommonResp resp=new CommonResp();
        List<OrderResp> list = orderService.findByPhone(phone);
        if (list==null){
            resp.setMessage("未查到内容");
            resp.setSuccess(false);
            return resp;
        }else {
        resp.setContent(list);
        return resp;
        }
    }
    //接受订单
    @GetMapping("/acceptOrder/{orderId}")
    public CommonResp acceptOrder(@PathVariable Long orderId){
        CommonResp resp=new CommonResp();
        orderService.acceptOrder(orderId);
        return resp;
    }
    @PostMapping("/addOrder")
    public CommonResp addOrder(@RequestBody List<Long> cartIdList){
        CommonResp resp=new CommonResp();
//        log.info(cartIdList.toString());
        orderService.saveOrder(cartIdList);
        return resp;
    }
    //待确认订单
    @GetMapping("/orderStatus/{userId}")
    public CommonResp orderStatus(@PathVariable Long userId){
        CommonResp resp=new CommonResp();
        List<OrderResp> list = orderService.orderStatus(userId);
        resp.setContent(list);
        return resp;
    }
    //非待确认订单
    @GetMapping("/userOrder/{userId}")
    public CommonResp userOrder(@PathVariable Long userId){
        CommonResp resp=new CommonResp();
//        log.info(cartIdList.toString());
        List<OrderResp> list = orderService.okOrder(userId);
        resp.setContent(list);
        return resp;
    }
    //取消订单
    @GetMapping("/cancelOrder/{orderId}")
    public CommonResp cancelOrder(@PathVariable Long orderId){
        CommonResp resp=new CommonResp();
//        log.info(cartIdList.toString());
         orderService.cancelOrder(orderId);
        return resp;
    }

}
