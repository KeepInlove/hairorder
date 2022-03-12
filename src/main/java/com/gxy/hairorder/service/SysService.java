package com.gxy.hairorder.service;

import com.gxy.hairorder.entity.Sys;
import com.gxy.hairorder.exception.BusinessException;
import com.gxy.hairorder.exception.BusinessExceptionCode;
import com.gxy.hairorder.repository.SysRepository;
import com.gxy.hairorder.req.LoginReq;
import com.gxy.hairorder.resp.UserLoginResp;
import com.gxy.hairorder.utils.CopyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author GUO
 * @Classname SysService
 * @Description TODO
 * @Date 2022/2/8 20:50
 */
@Service
@Slf4j
public class SysService {
    @Autowired
    private SysRepository sysRepository;


    public UserLoginResp login(LoginReq req) {
        Sys sys = sysRepository.findByUsernameAndPassword(req.getUsername(), req.getPassword());
        if (ObjectUtils.isEmpty(sys)){
            //用户名不存在
            log.info("用户名不存在,{}",req.getUsername());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }else {
            if (sys.getPassword().equals(req.getPassword())){
                //登录成功
                UserLoginResp sysLoginResp= CopyUtil.copy(sys, UserLoginResp.class);
                return sysLoginResp;
            }else {
                //密码错误
                log.info("密码错误,输入密码:{},数据库密码:{}",req.getPassword(),sys.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }


}
