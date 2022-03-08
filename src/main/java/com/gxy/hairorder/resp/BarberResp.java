package com.gxy.hairorder.resp;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GUO
 * @Classname BarberResp
 * @Description TODO
 * @Date 2022/2/26 17:42
 */
@Data
public class BarberResp {
    private Long barberId;
    private String barberName;
    private String barberDes;
    private Long barberTypeId;
    private String barberTypeName;
    private BigDecimal barberPrice;
    //理发师工作状态:默认1工作,0休息
    private Boolean barberStatus;
    private String barberImg;
    private Integer orderCount;
    private Date createTime;
}
