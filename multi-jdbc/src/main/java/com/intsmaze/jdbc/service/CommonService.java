package com.intsmaze.jdbc.service;

import com.intsmaze.jdbc.SyncMonitorStartUp;
import com.intsmaze.jdbc.bean.SyncTable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：intsmaze
 * @date ：Created in 2020/4/29 14:38
 * @description： https://www.cnblogs.com/intsmaze/
 * @modified By：
 */
public class CommonService {

    private JdbcTemplate mysqlgsystemJdbcTemplate;

    private JdbcTemplate mysqlhaloJdbcTemplate;

    private JdbcTemplate oracleJdbcTemplate;

    //单位为秒
    private long updateTime;


    public Map<String, List<String>> showTable(String dbFlag) {
        Map<String, List<String>> tableListMap = new HashMap<String, List<String>>();
        if (SyncMonitorStartUp.MYSQL.equals(dbFlag)) {
            List<String> gsystemTableList = showTable(mysqlgsystemJdbcTemplate);
            tableListMap.put(SyncMonitorStartUp.PRE_TABLES[0], gsystemTableList);

            List<String> haloTableList = showTable(mysqlhaloJdbcTemplate);
            tableListMap.put(SyncMonitorStartUp.PRE_TABLES[1], haloTableList);

        } else if (SyncMonitorStartUp.ORACLE.equals(dbFlag)) {
            List<String> oracleTableList = showTable(oracleJdbcTemplate);
            tableListMap.put(SyncMonitorStartUp.ORACLE, oracleTableList);
        }
        return tableListMap;
    }

    public List<String> showTable(JdbcTemplate jdbcTemplate) {
        List<String> tableList = new ArrayList<>();
        List<Map<String, Object>> showTables = jdbcTemplate.queryForList("Show tables");
        for (int i = 0; i < showTables.size(); i++) {
            Map<String, Object> schemaMapTable = showTables.get(i);
            Set<String> schemaSet = schemaMapTable.keySet();
            for (String schema : schemaSet) {
                String table = (String) schemaMapTable.get(schema);
                tableList.add(table);
            }
        }
        Collections.sort(tableList);
        return tableList;
    }

    public SyncTable countTable(String dbFlag, String table) {
        SyncTable syncTable = null;
        if (SyncMonitorStartUp.PRE_TABLES[0].equals(dbFlag)) {
            syncTable = countTable(mysqlgsystemJdbcTemplate, table);
        } else if (SyncMonitorStartUp.PRE_TABLES[1].equals(dbFlag)) {
            syncTable = countTable(mysqlhaloJdbcTemplate, table);
        } else if (SyncMonitorStartUp.ORACLE.equals(dbFlag)) {
            syncTable = countTable(oracleJdbcTemplate, table);
        }

        System.out.println(dbFlag + "--->>" + syncTable);
        return syncTable;
    }

    public SyncTable countTable(JdbcTemplate jdbcTemplate, String table) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String startTime = simpleDateFormat.format(System.currentTimeMillis());

        Integer countNum = jdbcTemplate.queryForObject("select count(1) from " + table + " where updateTime<'" + simpleDateFormat.format(updateTime) + "'", Integer.class);

        String endTime = simpleDateFormat.format(System.currentTimeMillis());
        SyncTable syncTable = new SyncTable(table, countNum, startTime, endTime);
        return syncTable;
    }


    public JdbcTemplate getMysqlgsystemJdbcTemplate() {
        return mysqlgsystemJdbcTemplate;
    }

    public void setMysqlgsystemJdbcTemplate(JdbcTemplate mysqlgsystemJdbcTemplate) {
        this.mysqlgsystemJdbcTemplate = mysqlgsystemJdbcTemplate;
    }

    public JdbcTemplate getMysqlhaloJdbcTemplate() {
        return mysqlhaloJdbcTemplate;
    }

    public void setMysqlhaloJdbcTemplate(JdbcTemplate mysqlhaloJdbcTemplate) {
        this.mysqlhaloJdbcTemplate = mysqlhaloJdbcTemplate;
    }

    public JdbcTemplate getOracleJdbcTemplate() {
        return oracleJdbcTemplate;
    }

    public void setOracleJdbcTemplate(JdbcTemplate oracleJdbcTemplate) {
        this.oracleJdbcTemplate = oracleJdbcTemplate;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
