package com.crland.jdbc.thread;

import com.crland.jdbc.bean.SyncTable;
import com.crland.jdbc.service.CommonService;
import com.crland.jdbc.SyncMonitorStartUp;

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

        if (flag.equals(SyncMonitorStartUp.ORACLE)) {
            List<String> tableList = showTables.get(SyncMonitorStartUp.ORACLE);
            selectTableInfo(syncTableList, flag, tableList);
        } else {
            for (int j = 0; j < SyncMonitorStartUp.PRE_TABLES.length; j++) {
                String preTable = SyncMonitorStartUp.PRE_TABLES[j];
                List<String> tableList = showTables.get(preTable);
                selectTableInfo(syncTableList, preTable, tableList);
            }
        }
//        System.out.println(syncTableList);
        SyncMonitorStartUp.COLLECTOR_RESULT.put(flag, syncTableList);
        SyncMonitorStartUp.THREAD_END_ATOMIC.incrementAndGet();

    }

    private void selectTableInfo(List<SyncTable> syncTableList, String preTable, List<String> tableList) {
        for (int i = 0; i < tableList.size(); i++) {
            String table = tableList.get(i);
            try {
                SyncTable syncTable = commonService.countTable(preTable, table);
                syncTableList.add(syncTable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
