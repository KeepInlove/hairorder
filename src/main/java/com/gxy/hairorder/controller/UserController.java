package com.gxy.hairorder.controller;

import com.gxy.hairorder.form.UserForm;
import com.gxy.hairorder.req.UserReq;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.resp.PageResp;
import com.gxy.hairorder.resp.UserResp;
import com.gxy.hairorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/save")
    public CommonResp userSave(@Valid @RequestBody UserForm userForm){

        CommonResp resp=new CommonResp();
        resp.setMessage("更新个人信息");
        userService.save(userForm);
        return resp;
    }
    @DeleteMapping("/userDel/{userId}")
    public CommonResp userDel(@PathVariable Long userId){
        CommonResp resp=new CommonResp();
        resp.setMessage("删除用户成功");
        userService.userDel(userId);
        return resp;
    }
    @GetMapping ("/integral/{userId}")
    public CommonResp integral(@PathVariable Long userId){
        CommonResp resp=new CommonResp();
        Integer integral = userService.integral(userId);
        resp.setContent(integral);
        return resp;
    }
}
