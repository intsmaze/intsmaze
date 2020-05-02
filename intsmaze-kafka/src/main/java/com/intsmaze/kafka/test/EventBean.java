package com.intsmaze.kafka.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventBean {

    public int userid;

    public String date;

    public long timeStamp;

    public List<String> list = new ArrayList<String>();

    public EventBean() {
    }

    public static void main(String[] args) {
        SimpleDateFormat aDate = new SimpleDateFormat("HH:mm:ss:SSS");
        long now = System.currentTimeMillis();
        System.out.println(aDate.format(now));
    }

    public EventBean(int userid, long timeStamp) {
        this.userid = userid;
        this.timeStamp = timeStamp;
        SimpleDateFormat aDate = new SimpleDateFormat("HH:mm:ss:SSS");
        this.date = aDate.format(timeStamp);
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "userid=" + userid +
                ", date='" + date + '\'' +
                ", timeStamp=" + timeStamp +
                ", list=" + list +
                '}';
    }
}