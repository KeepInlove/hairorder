package com.gxy.hairorder.req;

import lombok.Data;

/**
 * @author GUO
 * @Classname SysLoginReq
 * @Description TODO
 * @Date 2022/2/8 20:57
 */
@Data
public class LoginReq {
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String code;

}
