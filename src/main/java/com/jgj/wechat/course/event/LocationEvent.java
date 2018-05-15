package com.jgj.wechat.course.event;

/**
 * 上报地理位置时间
 * Created by guojun.jiao on 2018/5/9.
 */
public class LocationEvent extends BaseEvent {
//    地理位置维度
    private String Latitude;
//    地理位置经度
    private String Longitude;
//    地理位置精度
    private String Precision;

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}
