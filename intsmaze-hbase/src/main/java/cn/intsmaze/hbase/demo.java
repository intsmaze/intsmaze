package cn.intsmaze.hbase;

/**
 * @Description TODO
 * @Author intsmaze
 * @Date 2019/1/2 13:12
 * @Version 1.0
 **/
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class demo {

    private static final String TABLE_NAME = "speech";
    // private static final String CF_DEFAULT = "cf1";
//  public static final byte[] QUALIFIER = "col1".getBytes();
//  private static final byte[] ROWKEY = "999".getBytes();

    public static void main(String[] args) {
        Configuration config = HBaseConfiguration.create();
        String zkAddress = "192.168.19.131:2181";
        config.set(HConstants.ZOOKEEPER_QUORUM, zkAddress);
        Connection connection = null;

        try {
            connection = ConnectionFactory.createConnection(config);
            //
            // System.out.println("==========Get data==========");
            Table table = connection.getTable(TableName.valueOf(TABLE_NAME));
            try {

                DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

                // Scan data
                System.out.println("==========Scan data Start==========");
                System.out.println(df3.format(System.currentTimeMillis()));
                String start = "rokid-12016-12-18 11:37:23010116004126";
                String end = "rokid-12016-12-18 16:30:48010116000832";
                Scan scan = new Scan(start.getBytes(),end.getBytes());
                ResultScanner rs = table.getScanner(scan);
                for (Result result : rs) {
                    List<Cell> cs1 = result.listCells();
                    for (Cell cell : cs1) {
                        String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                        long timestamp = cell.getTimestamp();
                        String family = Bytes.toString(CellUtil.cloneFamily(cell));
                        String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                        String value = Bytes.toString(CellUtil.cloneValue(cell));
                        //System.out.println(" ===> rowKey : " + rowKey + ",  timestamp : " + timestamp + ", family : "
                        //         + family + ", qualifier : " + qualifier + ", value : " + value);
                    }
                }
                System.out.println(df3.format(System.currentTimeMillis()));
                System.out.println("==========Scan data Over==========");
                config.setLong(HConstants.HBASE_REGIONSERVER_LEASE_PERIOD_KEY,3000000);
            } finally {
                if (table != null)
                    table.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}