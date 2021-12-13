package com.gxy.hairorder.enums;

import lombok.Getter;

/**
 * @author GUO
 * @Classname BarberStatusEnum
 * @Description TODO
 * @Date 2021/12/11 15:01
 */
@Getter
public enum BarberStatusEnum implements CodeEnum {
    WORK(1, "工作"),
    REST(2, "休息");
    private Integer code;
    private String message;
    ;
    BarberStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
