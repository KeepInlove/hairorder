package com.gxy.hairorder.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/22 19:02
 * @Classname Cart
 * @Description 购物车
 */
@Entity
@Table(name = "t_cart")
@Data
public class Cart {
    @Id
    @CreatedBy
    @Column(name = "cart_id")
    private Long cartId;
    private Long userId;
    private Long hairId;
    private Long barberId;
    private BigDecimal price;
    private Date orderDate;
    private Date createTime;
}
