package com.gxy.hairorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.gxy.hairorder.form.PhoneForm;
import com.gxy.hairorder.req.LoginReq;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.resp.UserLoginResp;
import com.gxy.hairorder.resp.UserResp;
import com.gxy.hairorder.service.SysService;
import com.gxy.hairorder.service.UserService;
import com.gxy.hairorder.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @author GUO
 * @Classname LoginController
 * @Description TODO
 * @Date 2022/2/8 21:10
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SnowFlake snowFlake;
    @Autowired
    private SysService sysService;
    @Autowired
    private UserService userService;
    @Autowired
    private   SMSUtil smsUtil;
    @PostMapping("/sys")
    public CommonResp sysLogin(@Valid @RequestBody LoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp=new CommonResp();
        UserLoginResp sysLoginResp = sysService.login(req);
        //生成单点登录token,存入redis
        Long token = snowFlake.nextId();
        sysLoginResp.setToken(token.toString());
        log.info("生成单点登录token:{},存入redis中",token);
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(sysLoginResp),3600*24, TimeUnit.SECONDS);
        resp.setContent(sysLoginResp);
        return resp;
    }
    //短信等会或注册
//    @PostMapping("/user")
//    public CommonResp userLogin(String phone){
//
//        CommonResp<UserLoginResp> resp=new CommonResp();
//        UserLoginResp sysLoginResp = sysService.login();
//        //生成单点登录token,存入redis
//        Long token = snowFlake.nextId();
//        sysLoginResp.setToken(token.toString());
//        log.info("生成单点登录token:{},存入redis中",token);
//        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(sysLoginResp),3600*24, TimeUnit.SECONDS);
//        resp.setContent(sysLoginResp);
//        return resp;
//    }
    //发送短信api
    @GetMapping("/sendSMS")
    public CommonResp sendSMS(HttpServletRequest request, String phone) {
        boolean b = PhoneUntil.isPhone(phone);
        CommonResp commonResp=new CommonResp();
        if(b){
            commonResp.setMessage("请输入正确的手机号");
            commonResp.setSuccess(false);
            return commonResp;
        }if(phone==null||phone.equals("")){
            commonResp.setMessage("输入的手机号为空");
            commonResp.setSuccess(false);
            return commonResp;
        } else {
            log.info("phone:{}", phone);
            String code =   KeyUtil.keyUtils();
            log.info(code);
            String s = smsUtil.sendSMS(request, phone, code);
            //redis 存储
            PhoneForm phoneForm=new PhoneForm();
            phoneForm.setPhone(phone);
            phoneForm.setCode(code);
            log.info(phoneForm.toString());
            redisTemplate.opsForValue().set(phone+code,JSONObject.toJSONString(phoneForm),60*20,TimeUnit.SECONDS);
            Object object = redisTemplate.opsForValue().get(phone+code);
            log.info("发送短信结果:{},redisCode;{}",s,object);
            commonResp.setMessage("短信发送成功,五分钟内有效!");
            return commonResp;
        }
    }
    //短信登录/注册
    @RequestMapping("/user")
    public CommonResp phoneLogin(@RequestBody LoginReq loginReq) {
        CommonResp commonResp=new CommonResp();
        //查询缓存
        Object o  = redisTemplate.opsForValue().get(loginReq.getPhone() + loginReq.getCode());
        if(ObjectUtils.isEmpty(o)){
            commonResp.setMessage("手机号验证码错误!!");
            commonResp.setSuccess(false);
            return commonResp;
        }else{
            UserResp userResp= userService.findByPhone(loginReq.getPhone());
//            UserLoginResp userLoginResp = new UserLoginResp();
            Long token = snowFlake.nextId();
            //查询手机号是否在数据库
            if (ObjectUtils.isEmpty(userResp)){
                //注册成功
                commonResp.setMessage("注册成功");
                userService.saveUser(loginReq.getPhone());

                UserResp user =userService.findByPhone(loginReq.getPhone());
                UserLoginResp userLoginResp = CopyUtil.copy(user, UserLoginResp.class);

                userLoginResp.setToken(token.toString());
                userLoginResp.setUsername(loginReq.getPhone());

                log.info("生成单点登录token:{},存入redis中",token);
                redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp),3600*24, TimeUnit.SECONDS);
                commonResp.setContent(userLoginResp);
                return commonResp;
            }else {
                UserLoginResp userLoginResp = CopyUtil.copy(userResp, UserLoginResp.class);
                userLoginResp.setToken(token.toString());
                userLoginResp.setUsername(loginReq.getPhone());
                log.info("生成单点登录token:{},存入redis中",token);
                redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp),3600*24, TimeUnit.SECONDS);
                commonResp.setContent(userLoginResp);
                commonResp.setMessage("登录成功!!!");
                return commonResp;
            }
        }
    }
}
