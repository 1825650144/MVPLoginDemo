package com.sadink.mvp_rxjava_dagger2.ui.weather.model.bean;

/**
 * Created by dongdd on 2016/5/26 0026 15:45
 */
public class HttpResult<T> {
    private Integer count;
    private Integer start;
    private Integer total;
    private String title;

    // 用来模仿Data
    private T subjects;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }
}
