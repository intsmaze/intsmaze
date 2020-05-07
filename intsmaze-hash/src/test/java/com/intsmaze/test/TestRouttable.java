package com.intsmaze.test;

import com.intsmaze.hash.shard.routtable.RouteTableStart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/2/6 17:06
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class TestRouttable {


    /**
     * @author intsmaze
     * @description: https://www.cnblogs.com/intsmaze/
     * @date : 2020/2/6 17:07
     *
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext ct=new ClassPathXmlApplicationContext("spring-hash.xml");
        RouteTableStart testRouteTable= (RouteTableStart) ct.getBean("routeTableStart");


        Map<String, Long> map = new HashMap<String, Long>();
        for (int i = 0; i < 10000000; i++) {
            {
                String tableName = testRouteTable.shardTable("intsmaze" + i);
                Long num = map.get(tableName);
                if (num == null) {
                    map.put(tableName, 1L);
                } else {
                    num = num + 1;
                    map.put(tableName, num);
                }
            }
        }
        Set<Map.Entry<String, Long>> entries = map.entrySet();
        Iterator<Map.Entry<String, Long>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> next = iterator.next();
            System.out.println(next.getKey() + "--->>>" + next.getValue());
        }
    }
}
