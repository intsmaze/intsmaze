package com.crland.jdbc;

import com.crland.jdbc.utils.MailMonitorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/4/30 14:03
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class TestMail {
    public static void main(String[] args) {
        List<String> userMailList=new ArrayList<>();
        userMailList.add("979222969@qq.com");
        userMailList.add("yang.liu17@hand-china.com");
        MailMonitorUtils.sendMail(userMailList, "新营销数据库同步不一致");
    }
}
