package com.gxy.hairorder.entity;

import com.gxy.hairorder.enums.BarberStatusEnum;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barberId;
    private String barberName;
    private String des;
    private Long barberTypeId;
    //理发师工作状态:默认1工作,2休息
    private Integer barberStatus= BarberStatusEnum.WORK.getCode();
    private String barberImg;
    private Date createTime;
}
