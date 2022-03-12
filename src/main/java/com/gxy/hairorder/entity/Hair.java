package com.gxy.hairorder.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author GUO
 * @Classname Hairs
 * @Description TODO
 * @Date 2022/3/5 16:04
 */
@Entity
@Table(name = "t_hair")
@Data
public class Hair {
    @Id
    @CreatedBy
    @Column(name = "hair_id")
    private Long hairId;
    private String hairName;
    private String hairDes;
    private Long hairTypeId;
    private Long barberTypeId;
    private BigDecimal hairPrice;
    //理发师工作状态:默认1工作,0休息
    private Boolean hairStatus;
    private String hairImg;
    private Integer orderCount;
    private Date createTime;
}
