package com.gxy.hairorder.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author GUO
 * @Classname BarberForm
 * @Description TODO
 * @Date 2022/3/1 15:20
 */
@Data
public class BarberForm {
    private Long barberId;
    private String barberName;
    private String barberDes;
    private Long barberTypeId;
    private BigDecimal barberPrice;
    //理发师工作状态:默认1工作,0休息
    private Boolean barberStatus;
    private String barberImg;
}
