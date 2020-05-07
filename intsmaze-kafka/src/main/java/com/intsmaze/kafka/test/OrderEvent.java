package com.intsmaze.kafka.test;

public class OrderEvent {

    public int userid;

    public String url;

    public String deviceType;

    public long count=1;

    public OrderEvent() {}

    public OrderEvent(int  userid, String url, String deviceType) {
        this.userid = userid;
        this.url = url;
        this.deviceType = deviceType;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getUserid() {

        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "userid=" + userid +
                ", url='" + url + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", count=" + count +
                '}';
    }
}