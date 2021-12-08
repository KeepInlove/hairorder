package com.gxy.hairorder.resp;

import java.util.List;

/**
 * @author GUO
 * @Classname PageResp
 * @Description TODO
 * @Date 2021/12/8 13:12
 */
public class PageResp<T> {
    private long total;
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
