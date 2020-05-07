package cn.intsmaze.hbase;//package cn.intsmaze.hbase;
//
//import java.io.IOException;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.Cell;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.hadoop.hbase.client.Durability;
//import org.apache.hadoop.hbase.client.HTable;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.client.Table;
//import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
//import org.apache.hadoop.hbase.coprocessor.ObserverContext;
//import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
//import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
//
//public class InverIndexCoprocessor extends BaseRegionObserver {
//
//	@Override
//	public void prePut(ObserverContext<RegionCoprocessorEnvironment> e, Put put, WALEdit edit, Durability durability) throws IOException {
//		Configuration conf = HBaseConfiguration.create();
//		Connection conn = ConnectionFactory.createConnection(conf);
//		Table table = conn.getTable(TableName.valueOf("ffindex"));
//
//		Cell fromCell = put.get("f1".getBytes(), "From".getBytes()).get(0);
//		Cell toCell = put.get("f1".getBytes(), "To".getBytes()).get(0);
//		byte[] valueArray = fromCell.getValueArray();
//		String from = new String(valueArray,fromCell.getValueOffset(),fromCell.getValueLength());
//		byte[] valueArray2 = toCell.getValueArray();
//		String to = new String(valueArray2,toCell.getValueOffset(),toCell.getValueLength());
//
//		Put putIndex = new Put((to+"-"+from).getBytes());
//		putIndex.addColumn("f1".getBytes(), "From".getBytes(),from.getBytes());
//		putIndex.addColumn("f1".getBytes(), "To".getBytes(),to.getBytes());
//
//		table.put(putIndex);
//		table.close();
//
//	}
//}
