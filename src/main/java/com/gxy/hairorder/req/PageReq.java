package com.gxy.hairorder.req;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
/**
 * @author GUO
 * @Classname PageReq
 * @Description TODO
 * @Date 2021/12/8 13:10
 */
public class PageReq {
    @NotNull(message = "【页码】不能为空")
    private int page;
    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 200,message ="【每页条数】不能超过200" )
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}

