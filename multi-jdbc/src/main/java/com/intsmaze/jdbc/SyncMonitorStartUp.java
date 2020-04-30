package com.intsmaze.jdbc;

import com.intsmaze.jdbc.thread.SelectThread;
import com.intsmaze.jdbc.bean.SyncTable;
import com.intsmaze.jdbc.service.CommonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.concurrent.*;
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
    public static final String[] PRE_TABLES = new String[]{"hou_", "fin_"};


    public static AtomicInteger THREAD_END_ATOMIC = new AtomicInteger(0);

    public static ConcurrentHashMap<String, List<SyncTable>> COLLECTOR_RESULT = new ConcurrentHashMap(2);

    /**
     * @author liuyang
     * @date : 2020/4/30 11:15
     * 参数为当前时间前多少秒，例如 60
     */
    public static void main(String[] args) {

        Long intervalTime = Long.valueOf(args[0])*1000;

        ApplicationContext ct = new ClassPathXmlApplicationContext(new String[]{"jdbctemplate-applicationcontext.xml"});

        CommonService commonService = (CommonService) ct.getBean("commonService");
        commonService.setUpdateTime(System.currentTimeMillis()-intervalTime);


        Map<String, List<String>> oracleTableList = commonService.showTable(ORACLE);
        System.out.println(oracleTableList);

        Map<String, List<String>> mysqlTableList = commonService.showTable(MYSQL);
        System.out.println(mysqlTableList);


        mysqlTableList = removeNoSyncTable(ORACLE, oracleTableList, mysqlTableList);
        System.out.println(mysqlTableList);

//        oTableList = removeNoSyncTable(MYSQL, oTableList, showTables);
//        System.out.println(oTableList);

        ExecutorService threadPool = new ThreadPoolExecutor(0, 2,
                10L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                threadPool.execute(new SelectThread(MYSQL, mysqlTableList, commonService));
            } else if (i == 1) {
                threadPool.execute(new SelectThread(ORACLE, oracleTableList, commonService));
            }
        }

        while (THREAD_END_ATOMIC.get() != 2) {
            try {
                System.out.println("睡眠3秒");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        comparableDbInfo();
        System.out.println("运行结束");
    }

    /**
     * @author intsmaze
     * @date : 2020/4/29 16:09
     * 比较两个库中相同表的数据量是否一致，不一致则打印出来
     */
    public static void comparableDbInfo() {
        //获取oracle和mysql 两个数据库 各表的信息


        List<SyncTable> mysqlDbInfo = COLLECTOR_RESULT.get(MYSQL);
        List<SyncTable> oracleDbInfo = COLLECTOR_RESULT.get(ORACLE);
//        List<SyncTable> beforeDbInfo = null;
//        List<SyncTable> afterDbInfo = null;
//        Set<Map.Entry<String, List<SyncTable>>> entries = COLLECTOR_RESULT.entrySet();
//        Iterator<Map.Entry<String, List<SyncTable>>> iterator = entries.iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, List<SyncTable>> next = iterator.next();
//            beforeDbInfo = next.getValue();
//            while (iterator.hasNext()) {
//                next = iterator.next();
//                afterDbInfo = next.getValue();
//            }
//        }

        Map<String, SyncTable> mysqlMap = new HashMap<String, SyncTable>();
        Map<String, SyncTable> oracleMap = new HashMap<String, SyncTable>();

        Set<SyncTable> syncTableSet = new HashSet<>();
        if (oracleDbInfo != null && mysqlDbInfo != null) {

            for (int i = 0; i < mysqlDbInfo.size(); i++) {
                SyncTable syncTable = mysqlDbInfo.get(i);
                syncTableSet.add(syncTable);
                mysqlMap.put(syncTable.getTable(),syncTable);
            }

            for (int i = 0; i < oracleDbInfo.size(); i++) {
                SyncTable syncTable = oracleDbInfo.get(i);
                oracleMap.put(syncTable.getTable(),syncTable);

                int beforeSize = syncTableSet.size();
                syncTableSet.add(syncTable);
                int afterSize = syncTableSet.size();
                if (beforeSize != afterSize) {
                    System.out.println("数据不一致,oracle数据为:" + oracleMap.get(syncTable.getTable())+" mysql数据为:"+mysqlMap.get(syncTable.getTable()));
                }
            }
        }
    }

    /**
     * @author intsmaze
     * @date : 2020/4/29 16:09
     * 移除mysql中有，oracle没有的表名。可能存在oracle中有表，mysql没有对应表情况。
     */
    public static Map<String, List<String>> removeNoSyncTable(String flag, Map<String, List<String>> oracleTableMap, Map<String, List<String>> mysqlTableMap) {

        List<String> oracleTableList = oracleTableMap.get(SyncMonitorStartUp.ORACLE);

        for (int j = 0; j < PRE_TABLES.length; j++) {
            List<String> removeTable = new ArrayList<>();
            String preTable = PRE_TABLES[j];
            List<String> mysqlTableList = mysqlTableMap.get(preTable);
            for (int i = 0; i < mysqlTableList.size(); i++) {
                String mysqlTable = mysqlTableList.get(i);
                if (!oracleTableList.contains(mysqlTable)) {
                    System.out.println(flag + "不包含:" + mysqlTable + " 从mysql中删除对该表的监控");
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
