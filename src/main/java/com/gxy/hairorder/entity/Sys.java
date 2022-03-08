package com.gxy.hairorder.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author GUO
 * @Classname Sys
 * @Description TODO
 * @Date 2022/2/7 21:53
 */
@Data
@Entity
@Table(name = "t_sys")
public class Sys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sysId;
    private String password;
    private String username;
    private String name;
    private Date createTime;
}
