package com.gxy.hairorder.req;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GUO
 * @Classname BarberTypeReq
 * @Description TODO
 * @Date 2021/12/11 15:25
 */
@Data
public class BarberTypeReq {
    private Long barberTypeId;
    private String barberTypeName;
    private BigDecimal barberPrice;
}
