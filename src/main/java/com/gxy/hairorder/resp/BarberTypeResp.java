package com.gxy.hairorder.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
    private  Integer count;
    private Date createTime;
}
