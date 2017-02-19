package com.sadink.model.bean;

/**
 * Created by dongdd on 2016/5/20 0020 13:59
 */
public class Weather {
    private String city;
    private String wd;
    private String ws;
    private String time;

    public Weather() {
    }

    public Weather(String city) {
        this.city = city;
    }

    public Weather(String city, String wd, String ws, String time) {
        this.city = city;
        this.wd = wd;
        this.ws = ws;
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
