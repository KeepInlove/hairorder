package com.gxy.hairorder.utils;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/12 17:52
 * @Classname PhoneUntil
 * @Description 验证手机号
 */
public class PhoneUntil {
    //验证手机号
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    /**
     * 验证手机号是否合法
     * @Author HB
     * @param phone	手机号
     * @return  boolean false合法 true不合法
     * @Date 2021/2/26 11:05
     *
     **/
    public static  boolean isPhone(String phone){
        if (phone.matches(REGEX_MOBILE)){
            return false;
        }else {
            return true;
        }
    }
}
