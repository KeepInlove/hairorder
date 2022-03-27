package com.gxy.hairorder.form;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/28 1:18
 * @Classname OrderForm
 * @Description TODO
 */
@Data
public class OrderForm {
    private Long barberId;
    private Long hairId;
    private Date orderDate;
    private String phone;
    private BigDecimal price;

}
