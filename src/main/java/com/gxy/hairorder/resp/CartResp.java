package com.gxy.hairorder.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/22 19:31
 * @Classname CartResp
 * @Description TODO
 */
@Data
public class CartResp {
    private Long cartId;
    private Long userId;
    private Long hairId;
    private Long barberId;
    private BigDecimal price;
    private String hariName;
    private String barberName;
    private Date orderDate;
}
