package com.gxy.hairorder.exception;

public enum BusinessExceptionCode {
    HAIR_ERROR("项目不存在"),
    BARBER_ERROR("理发师不存在"),
    BARBER_TYPE_ERROR("理发师类型不存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    VOTE_REPEAT("您已点赞过"),
    SERVICE_ERROR("图片上传异常")
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
