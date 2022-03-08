package com.gxy.hairorder.entity;

import com.gxy.hairorder.enums.BarberStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author GUO
 * @Classname Barber
 * @Description 理发师
 * @Date 2021/12/11 14:31
 */
@Entity
@Table(name = "t_barber")
@Data
public class Barber {
    @Id
    @CreatedBy
    @Column(name = "barber_id")
    private Long barberId;
    private String barberName;
    private String barberDes;
    private Long barberTypeId;
    private BigDecimal barberPrice;
    //理发师工作状态:默认1工作,0休息
    private Boolean barberStatus;
    private String barberImg;
    private Integer orderCount;
    private Date createTime;
}
