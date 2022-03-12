package com.gxy.hairorder.resp;

import lombok.Data;

import java.sql.Date;

/**
 * @author GUO
 * @Classname UserResp
 * @Description TODO
 * @Date 2021/12/8 13:14
 */
@Data
public class UserResp {
    private Long id;
    private String name;
    private String phone;
    private String sex;
    private int integral;
    private Date createTime;
}
