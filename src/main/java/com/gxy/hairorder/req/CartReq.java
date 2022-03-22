package com.gxy.hairorder.req;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/22 20:10
 * @Classname CartReq
 * @Description TODO
 */
@Data
public class CartReq {
    private Long userId;
    private Long hairId;
    private Long barberId;
    private BigDecimal price;
    private Date orderDate;
}
