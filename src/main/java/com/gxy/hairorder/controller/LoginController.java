package com.gxy.hairorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.gxy.hairorder.req.SysLoginReq;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.resp.UserLoginResp;
import com.gxy.hairorder.service.SysService;
import com.gxy.hairorder.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/sys")
    public CommonResp sysLogin(@Valid @RequestBody SysLoginReq req){
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
}
