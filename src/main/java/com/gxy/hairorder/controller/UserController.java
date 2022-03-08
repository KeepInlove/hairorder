package com.gxy.hairorder.controller;

import com.gxy.hairorder.req.UserReq;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.resp.PageResp;
import com.gxy.hairorder.resp.UserResp;
import com.gxy.hairorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author GUO
 * @Classname UserController
 * @Description TODO
 * @Date 2021/12/8 13:59
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public CommonResp userList(@Valid UserReq userReq){
        CommonResp<PageResp<UserResp>> resp=new CommonResp<>();
        PageResp<UserResp> pageResp = userService.list(userReq);
        resp.setContent(pageResp);
        return resp;
    }

}
