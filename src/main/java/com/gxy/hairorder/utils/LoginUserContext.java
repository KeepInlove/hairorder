package com.gxy.hairorder.utils;

import com.gxy.hairorder.resp.UserLoginResp;

import java.io.Serializable;

/**
 * @author GUO
 * @Classname LoginUserContext
 * @Description TODO
 * @Date 2022/2/24 0:31
 */
public class LoginUserContext  implements Serializable {

    private static ThreadLocal<UserLoginResp> user = new ThreadLocal<>();

    public static UserLoginResp getUser() {
        return user.get();
    }

    public static void setUser(UserLoginResp user) {
        LoginUserContext.user.set(user);
    }

}
