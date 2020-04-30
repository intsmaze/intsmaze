package com.intsmaze.test;

import com.crland.jdbc.bean.SyncTable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/4/29 19:23
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class JunitHashSet {

    public static void main(String[] args) {

        Set<SyncTable> syncTableSet=new HashSet<>();

        syncTableSet.add(new SyncTable("name", 100, "2020-12-12 12:12:12", "2020-12-12 12:12:12"));
        syncTableSet.add(new SyncTable("table", 100, "2020-12-12 12:12:12", "2020-12-12 12:12:12"));
        syncTableSet.add(new SyncTable("demo", 100, "2020-12-12 12:12:12", "2020-12-12 12:12:12"));
        syncTableSet.add(new SyncTable("test", 100, "2020-12-12 12:12:12", "2020-12-12 12:12:12"));
        syncTableSet.add(new SyncTable("test", 100, "2020-12-12 12:12:12", "2020-12-12 12:12:12"));
        syncTableSet.add(new SyncTable("junit", 100, "2020-12-12 12:12:12", "2020-12-12 12:12:12"));
        syncTableSet.add(new SyncTable("name", 200, "2020-12-12 12:12:12", "2020-12-12 12:12:12"));

        Iterator<SyncTable> iterator = syncTableSet.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

    }
}
