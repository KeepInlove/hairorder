package com.gxy.hairorder.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author GUO
 * @Classname BarberTypeReq
 * @Description 理发师类型
 * @Date 2021/12/11 15:25
 */
@Data
public class BarberTypeResp {
    private Long barberTypeId;
    private String barberTypeName;
    private BigDecimal barberPrice;
}
