package com.gxy.hairorder.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author GUO
 * @Classname User
 * @Description 用户
 * @Date 2021/12/8 12:38
 */
@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String password;
    private String phone;
    private String sex;
    private int integral;
    private Date createTime;
}
