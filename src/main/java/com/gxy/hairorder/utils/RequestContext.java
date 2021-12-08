package com.gxy.hairorder.utils;


import java.io.Serializable;

/**
 * @author GUO
 * @Classname RequestContext
 * @Description 线程
 * @Date 2021/12/4 16:23
 */
public class RequestContext implements Serializable {

    private static ThreadLocal<String> remoteAddr = new ThreadLocal<>();

    public static String getRemoteAddr() {
        return remoteAddr.get();
    }

    public static void setRemoteAddr(String remoteAddr) {
        RequestContext.remoteAddr.set(remoteAddr);
    }

}
