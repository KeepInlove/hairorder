package com.gxy.hairorder.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GUO
 * @Classname Order
 * @Description 预约单
 * @Date 2021/12/8 19:29
 */
@Data
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @CreatedBy
    @Column(name = "order_id")
    private Long orderId;
    private Long userId;
    private Long hairId;
    private Long barberId;
    private BigDecimal price;
    private Date orderDate;
    /*订单状态,默认新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
   */
    private Integer orderStatus;
    private String orderInfo;
    private String phone;
    private Date createTime;
}
