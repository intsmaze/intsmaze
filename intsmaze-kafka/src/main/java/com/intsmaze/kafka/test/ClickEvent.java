package com.intsmaze.kafka.test;

import java.text.SimpleDateFormat;

public class ClickEvent {

    public int userid;

    public String url;

    public String date;

    public long timeStamp;

    public String deviceType;

    public long count = 1;

    public ClickEvent() {
    }

    public static void main(String[] args) {

        long now = System.currentTimeMillis();
        System.out.println();
    }

    public ClickEvent(int userid, String url, long timeStamp, String deviceType) {
        this.userid = userid;
        this.url = url;
        this.timeStamp = timeStamp;
        this.deviceType = deviceType;
        SimpleDateFormat aDate = new SimpleDateFormat("HH:mm:ss");
        this.date = aDate.format(timeStamp);
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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "userid=" + userid +
                ", date=" + date +
                ", url='" + url + '\'' +
                ", timeStamp=" + timeStamp +
                ", deviceType='" + deviceType + '\'' +
                ", count=" + count +
                '}';
    }
}