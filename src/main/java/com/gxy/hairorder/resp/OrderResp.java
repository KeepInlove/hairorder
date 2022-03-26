package com.gxy.hairorder.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/27 2:09
 * @Classname OrderResp
 * @Description TODO
 */
@Data
public class OrderResp {
    private Long orderId;
    private Long hairId;
    private Long barberId;
    private BigDecimal price;
    private String hairName;
    private String hairImg;
    private String barberName;
    private Date orderDate;
    private Integer orderStatus;
}
