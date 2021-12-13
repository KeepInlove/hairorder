package com.gxy.hairorder.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GUO
 * @Classname 理发师类型
 * @Description TODO
 * @Date 2021/12/11 15:16
 */
@Entity
@Table(name = "t_barber_type")
@Data
public class BarberType {

    @Id
    @CreatedBy
    @Column(name = "barber_type_id")
    private Long barberTypeId;
    private String barberTypeName;
    private BigDecimal barberPrice;
    private Date createTime;

}
