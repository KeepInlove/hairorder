package com.gxy.hairorder.resp;

import lombok.Data;

/**
 * @author GUO
 * @Classname UserLoginResp
 * @Description 登录拦截对象
 * @Date 2022/2/8 20:57
 */
@Data
public class UserLoginResp {
    private Long id;
    private String username;
    private String name;
    private String phone;
    private String token;
}
