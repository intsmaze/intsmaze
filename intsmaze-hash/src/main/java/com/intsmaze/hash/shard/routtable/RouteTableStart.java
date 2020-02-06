package com.intsmaze.hash.shard.routtable;

import com.intsmaze.hash.shard.model.IntsmazeShardInfo;
import com.intsmaze.hash.shard.model.IntsmazeShardedConnection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;

/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/2/6 10:01
 * 
 */
public class RouteTableStart {

    private  IntsmazeShardedConnection sharding;

    private String tableName;

    private String hostPort="intsmaze:3306";

    public RouteTableStart(String tableName,String hostPort)
    {
        this.tableName=tableName;
        this.hostPort=hostPort;
    }
    public RouteTableStart(String tableName)
    {
        this.tableName=tableName;
    }
    public RouteTableStart()
    {

    }

    //100个散列表，名为JobDescription+i
    public  void init() throws Exception {
        List<IntsmazeShardInfo> shards = new ArrayList<IntsmazeShardInfo>(100);
        for (int i = 0; i < 100; i++) {
            shards.add(new IntsmazeShardInfo(hostPort, StringUtils.join(tableName, "-", i)));
        }
        sharding = new IntsmazeShardedConnection(shards);
    }

    public String shardTable(String key) {
        return sharding.getTable(key);
    }

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


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }
}