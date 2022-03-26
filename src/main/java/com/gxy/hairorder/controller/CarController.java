package com.gxy.hairorder.controller;

import com.gxy.hairorder.req.CartReq;
import com.gxy.hairorder.resp.CartResp;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/22 20:03
 * @Classname CarController
 * @Description TODO
 */
@RestController
@RequestMapping("/cart")
public class CarController {
    @Autowired
    private CartService cartService;

    @PostMapping("/addCart")
    public CommonResp add(@Valid @RequestBody CartReq cartReq){
        CommonResp commonResp=new CommonResp();
        cartService.save(cartReq);
        return commonResp;
    }

    @GetMapping("/findCart/{userId}")
    public CommonResp findCart(@PathVariable Long userId){
        CommonResp commonResp=new CommonResp();
        List<CartResp> cartRespList = cartService.findCart(userId);
        commonResp.setContent(cartRespList);
        return commonResp;
    }

    @DeleteMapping("/delCart/{cartId}")
    public CommonResp delCart(@PathVariable Long cartId){
        CommonResp commonResp=new CommonResp();
         cartService.delCart(cartId);
        return commonResp;
    }

    @GetMapping("/count/{userId}")
    public CommonResp countByUserId(@PathVariable Long userId){
        CommonResp commonResp=new CommonResp();
        Integer count = cartService.countByUserId(userId);
        commonResp.setContent(count);
        return commonResp;
    }
    @GetMapping("/isCheck/{cartId}")
    public CommonResp isCheck(@PathVariable Long cartId){
        CommonResp commonResp=new CommonResp();
         cartService.isCheck(cartId);
        return commonResp;
    }

    @GetMapping("/updateDate")
    public CommonResp updateDate(Long cartId, Date orderDate){
        CommonResp commonResp=new CommonResp();
        cartService.updateDate(cartId,orderDate);
        return commonResp;
    }
}
