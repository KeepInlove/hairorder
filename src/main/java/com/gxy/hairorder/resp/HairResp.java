package com.gxy.hairorder.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GUO
 * @Classname HairsResp
 * @Description TODO
 * @Date 2022/3/5 16:29
 */
@Data
public class HairResp {
    private Long hairId;
    private String hairName;
    private String hairDes;
    private Long hairTypeId;
    private Long barberTypeId;
    private String hairTypeName;
    private String barberTypeName;
    private BigDecimal hairPrice;
    //理发师工作状态:默认1工作,0休息
    private Boolean hairStatus;
    private String hairImg;
    private Integer orderCount;
    private Date createTime;
}
