package com.intsmaze.jdbc.thread;

import com.intsmaze.jdbc.SyncMonitorStartUp;
import com.intsmaze.jdbc.bean.SyncTable;
import com.intsmaze.jdbc.service.CommonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：liuuyang
 * @date ：Created in 2020/4/29 15:09
 * @description： 查询mysql和oracle数据库中各表指定时间的快照数据量
 * @modified By：
 */
public class SelectThread implements Runnable {

    private String flag;

    private Map<String, List<String>> showTables;

    private CommonService commonService;

    public SelectThread() {
    }

    public SelectThread(String flag, Map<String, List<String>> showTables, CommonService commonService) {
        this.flag = flag;
        this.showTables = showTables;
        this.commonService = commonService;
    }


    @Override
    public void run() {
        List<SyncTable> syncTableList = new ArrayList<>();


        try {
            if (flag.equals(SyncMonitorStartUp.ORACLE)) {
                List<String> tableList = showTables.get(SyncMonitorStartUp.ORACLE);
                for (int i = 0; i < tableList.size(); i++) {
                    String table = tableList.get(i);
                    SyncTable syncTable = commonService.countTable(flag, table);
                    syncTableList.add(syncTable);
                }
            } else {
                for (int j = 0; j < SyncMonitorStartUp.PRE_TABLES.length; j++) {
                    String preTable = SyncMonitorStartUp.PRE_TABLES[j];
                    List<String> tableList = showTables.get(preTable);
                    for (int i = 0; i < tableList.size(); i++) {
                        String table = tableList.get(i);
                        SyncTable syncTable = commonService.countTable(preTable, table);
                        syncTableList.add(syncTable);
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(syncTableList);
        SyncMonitorStartUp.COLLECTOR_RESULT.put(flag,syncTableList);
        SyncMonitorStartUp.THREAD_END_ATOMIC.incrementAndGet();

    }
}
