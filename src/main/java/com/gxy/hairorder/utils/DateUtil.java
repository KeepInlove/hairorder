package com.gxy.hairorder.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author GUO
 * @Classname DateUtil
 * @Description TODO
 * @Date 2022/2/20 21:08
 */
public class DateUtil {
    public static Date  DateToSimple() {
        Date date=new Date();//获取当前的时间
        SimpleDateFormat sd=new SimpleDateFormat("yyyy.MM.dd");//用这种格式去格式化
//        System.out.println(sd.format(date));
        return date;
    }

}
