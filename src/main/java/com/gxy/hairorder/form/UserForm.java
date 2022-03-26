package com.gxy.hairorder.form;

import lombok.Data;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/16 19:19
 * @Classname UserForm
 * @Description TODO
 */
@Data
public class UserForm {
    private Long id;
    private String name;
    private String password;
    private String phone;
    private int integral;
    private String sex;
}
