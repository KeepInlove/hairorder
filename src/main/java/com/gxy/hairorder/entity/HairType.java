package com.gxy.hairorder.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author GUO
 * @Classname HairsType
 * @Description TODO
 * @Date 2022/3/5 16:24
 */
@Entity
@Data
@Table(name = "t_hair_type")
public class HairType {
    @Id
    @CreatedBy
    @Column(name = "hair_type_id")
    private Long hairTypeId;
    private String hairTypeName;
    private Date createTime;
}
