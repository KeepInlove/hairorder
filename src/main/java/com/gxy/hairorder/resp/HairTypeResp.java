package com.gxy.hairorder.resp;

import lombok.Data;

import java.util.Date;

/**
 * @author GUO
 * @Classname HairTypeResp
 * @Description TODO
 * @Date 2022/3/5 17:19
 */
@Data
public class HairTypeResp {
    private Long hairTypeId;
    private String hairTypeName;
    private  Integer count;
    private Date createTime;
}
