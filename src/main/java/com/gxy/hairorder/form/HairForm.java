package com.gxy.hairorder.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author GUO
 * @Classname HairsForm
 * @Description TODO
 * @Date 2022/3/5 17:02
 */
@Data
public class HairForm {
    private Long hairId;
    private String hairName;
    private String hairDes;
    private Long hairTypeId;
    private Long barberTypeId;
    private BigDecimal hairPrice;
    //理发师工作状态:默认1工作,0休息
    private String hairImg;
}
