package com.crland.jdbc.bean;

import java.util.Objects;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/4/29 15:00
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class SyncTable  {//implements Comparable<SyncTable>

    private String table;

    private int num;

    private String startTime;

    private String endTime;

    public SyncTable(String table, int num, String startTime, String endTime) {
        this.table = table;
        this.num = num;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "{" +
                "table='" + table + '\'' +
                ", num=" + num +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SyncTable syncTable = (SyncTable) o;
        return num == syncTable.num &&
                Objects.equals(table, syncTable.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, num);
    }


//    @Override
//    public int compareTo(SyncTable o) {
//        if (o.getTable().equals(this.getTable()) && o.getNum() == this.getNum()) {
//            return 0;
//        }
//        return 1;
//    }
}
