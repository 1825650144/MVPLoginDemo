package com.sadink.model.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dongdd on 2016/5/20 0020 13:59
 */

/*

public class Weather2 implements Serializable {

    @SerializedName("weatherinfo")
    public WeatherInfo weatherinfo;

    public static class WeatherInfo implements Serializable {
        @SerializedName("city")
        public String city;
        @SerializedName("cityid")
        public String cityid;
        @SerializedName("temp")
        public String temp;
        @SerializedName("WD")
        public String WD;
        @SerializedName("WS")
        public String WS;
        @SerializedName("SD")
        public String SD;
        @SerializedName("WSE")
        public String WSE;
        @SerializedName("time")
        public String time;
        @SerializedName("isRadar")
        public String isRadar;
        @SerializedName("Radar")
        public String Radar;
        @SerializedName("njd")
        public String njd;
        @SerializedName("qy")
        public String qy;
        @SerializedName("rain")
        public String rain;


//        @Override
//        public String toString() {
//            return "WeatherInfo{" +
//                    "city='" + city + '\n' +
//                    ", cityid='" + cityid + '\n' +
//                    ", temp='" + temp + '\n' +
//                    ", WD='" + WD + '\n' +
//                    ", WS='" + WS + '\n' +
//                    ", SD='" + SD + '\n' +
//                    ", WSE='" + WSE + '\n' +
//                    ", time='" + time + '\n' +
//                    ", isRadar='" + isRadar + '\n' +
//                    ", Radar='" + Radar + '\n' +
//                    ", njd='" + njd + '\n' +
//                    ", qy='" + qy + '\n' +
//                    ", rain='" + rain + '\n' +
//                    '}';
//        }
    }
}

*/






public class Weather2 {
    //weatherinfo需要对应json数据的名称，我之前随便写了个，被坑很久
    public Weatherinfo weatherinfo;

    public Weatherinfo getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(Weatherinfo weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    //city、cityid必须对应json数据的名称，不然解析不了
    public class Weatherinfo {
        private String city;
        private String cityid;
        private String temp;
        private String WD;
        private String WS;
        private String SD;
        private String WSE;
        private String time;
        private String isRadar;
        private String Radar;
        private String njd;
        private String qy;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWD() {
            return WD;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public String getWS() {
            return WS;
        }

        public void setWS(String WS) {
            this.WS = WS;
        }

        public String getSD() {
            return SD;
        }

        public void setSD(String SD) {
            this.SD = SD;
        }

        public String getWSE() {
            return WSE;
        }

        public void setWSE(String WSE) {
            this.WSE = WSE;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIsRadar() {
            return isRadar;
        }

        public void setIsRadar(String isRadar) {
            this.isRadar = isRadar;
        }

        public String getRadar() {
            return Radar;
        }

        public void setRadar(String radar) {
            Radar = radar;
        }

        public String getNjd() {
            return njd;
        }

        public void setNjd(String njd) {
            this.njd = njd;
        }

        public String getQy() {
            return qy;
        }

        public void setQy(String qy) {
            this.qy = qy;
        }



/**
 * 使用Retrofit原生处理方式中---同步方式时不能重写JavaBean的toString()方法
 *
 * @return
 */


        @Override
        public String toString() {
            return "Weatherinfo{" +
                    "city='" + city + '\n' +
                    ", cityid='" + cityid + '\n' +
                    ", temp='" + temp + '\n' +
                    ", WD='" + WD + '\n' +
                    ", WS='" + WS + '\n' +
                    ", SD='" + SD + '\n' +
                    ", WSE='" + WSE + '\n' +
                    ", time='" + time + '\n' +
                    ", isRadar='" + isRadar + '\n' +
                    ", Radar='" + Radar + '\n' +
                    ", njd='" + njd + '\n' +
                    ", qy='" + qy + '\n' +
                    '}';
        }
    }
}
