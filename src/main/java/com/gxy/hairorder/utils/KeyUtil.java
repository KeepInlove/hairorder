package com.gxy.hairorder.utils;

import java.util.Random;

/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/12 16:14
 * @Classname KeyUtil
 * @Description 随机生成短信验证码
 */
public class KeyUtil {
    public static String keyUtils() {
        String str="0123456789";
        StringBuilder st=new StringBuilder(6);
        for(int i=0;i<6;i++){
            char ch=str.charAt(new Random().nextInt(str.length()));
            st.append(ch);
        }
        String findKey=st.toString().toLowerCase();
        return findKey;
    }

}