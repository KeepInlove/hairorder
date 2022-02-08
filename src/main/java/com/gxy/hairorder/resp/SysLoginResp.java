package com.gxy.hairorder.resp;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author GUO
 * @Classname SysLoginResp
 * @Description TODO
 * @Date 2022/2/8 20:57
 */
@Data
public class SysLoginResp {
    private Long sysId;
    @NotNull(message = "【用户名】不能为空")
    private String username;

    @NotNull(message = "【昵称】不能为空")
    private String name;
    private String token;
}
