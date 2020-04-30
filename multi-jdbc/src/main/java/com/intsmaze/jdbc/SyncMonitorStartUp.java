package com.intsmaze.jdbc;

import com.intsmaze.jdbc.thread.SelectThread;
import com.intsmaze.jdbc.bean.SyncTable;
import com.intsmaze.jdbc.service.CommonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncMonitorStartUp {

    public static final String MYSQL = "mysql";

    public static final String ORACLE = "oracle";

    /**
     * 房源库 hou_
     * 财务库 fin_
     * 公共库 com_
     * 交易库 sal_
     * 客户库 cus_
     */
//    public static final String[] PRE_TABLES = new String[]{"hou_","fin_","com_","sal_","cus_"};
    public static final String[] PRE_TABLES = new String[]{"hou_","fin_"};


    public static AtomicInteger THREAD_END_ATOMIC = new AtomicInteger(0);

    public static ConcurrentHashMap<String, List<SyncTable>> COLLECTOR_RESULT = new ConcurrentHashMap(2);

    public static void main(String[] args)  {
        ApplicationContext ct = new ClassPathXmlApplicationContext(new String[]{"jdbctemplate-applicationcontext.xml"});

        CommonService commonService = (CommonService) ct.getBean("commonService");

        Map<String,List<String>> oracleTableList = commonService.showTable(ORACLE);
        System.out.println(oracleTableList);

        Map<String,List<String>> mysqlTableList = commonService.showTable(MYSQL);
        System.out.println(mysqlTableList);


        mysqlTableList = removeNoSyncTable(ORACLE, oracleTableList,mysqlTableList);
        System.out.println(mysqlTableList);

//        oTableList = removeNoSyncTable(MYSQL, oTableList, showTables);
//        System.out.println(oTableList);

        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                threadPool.execute(new SelectThread(MYSQL, mysqlTableList, commonService));
            } else if (i == 1) {
                threadPool.execute(new SelectThread(ORACLE, oracleTableList, commonService));
            }
        }

//
        while (THREAD_END_ATOMIC.get() != 2) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ComparableDbInfo();
    }

    /**
     * @author intsmaze
     * @date : 2020/4/29 16:09
     * 比较两个库中相同表的数据量是否一致，不一致则打印出来
     */
    public static void ComparableDbInfo()
    {
        List<SyncTable> beforeDbInfo = null;
        List<SyncTable> afterDbInfo = null;
        Set<Map.Entry<String, List<SyncTable>>> entries = COLLECTOR_RESULT.entrySet();
        Iterator<Map.Entry<String, List<SyncTable>>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<SyncTable>> next = iterator.next();
            beforeDbInfo = next.getValue();
            while (iterator.hasNext()) {
                next = iterator.next();
                afterDbInfo = next.getValue();
            }
        }

        Set<SyncTable> syncTableSet=new HashSet<>();
        if (beforeDbInfo != null && afterDbInfo != null) {

            for (int i = 0; i < beforeDbInfo.size(); i++) {
                SyncTable syncTable = beforeDbInfo.get(i);
                syncTableSet.add(syncTable);
            }

            for (int i = 0; i < afterDbInfo.size(); i++) {
                SyncTable syncTable = afterDbInfo.get(i);
                int beforeSize = syncTableSet.size();
                syncTableSet.add(syncTable);
                int afterSize = syncTableSet.size();
                if(beforeSize!=afterSize)
                {
                    System.out.println("数据不一致:"+syncTable);
                }
            }
        }
    }

    /**
     * @author intsmaze
     * @date : 2020/4/29 16:09
     * 移除两个库中没有交集的表名
     */
    public static Map<String,List<String>>  removeNoSyncTable(String flag, Map<String,List<String>> oracleTableMap, Map<String,List<String>> mysqlTableMap) {

        List<String> oracleTableList = oracleTableMap.get(SyncMonitorStartUp.ORACLE);

        for (int j = 0; j < PRE_TABLES.length; j++) {
            List<String> removeTable = new ArrayList<>();
            String preTable = PRE_TABLES[j];
            List<String> mysqlTableList = mysqlTableMap.get(preTable);
            for (int i = 0; i < mysqlTableList.size(); i++) {
                String mysqlTable = mysqlTableList.get(i);
                if (!oracleTableList.contains(mysqlTable)) {
                    System.out.println(flag + "不包含:" + mysqlTable+" 从mysql中删除对该表的监控");
                    removeTable.add(mysqlTable);
                }
            }
            for (int i = 0; i < removeTable.size(); i++) {
                String table = removeTable.get(i);
                mysqlTableList.remove(table);
            }
        }

        return mysqlTableMap;
    }

}
