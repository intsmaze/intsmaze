package cn.intsmaze.hbase.lock;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import cn.intsmaze.hbase.HbaseDemo;


public class UnlockPut implements Runnable{

	private Configuration conf = null;
	private Connection conn = null;
	
	String rowKey;
	String key;
	String value;
	
	public UnlockPut(String rowKey,String key,String value) throws IOException
	{
		this.rowKey=rowKey;
		this.key=key;
		this.value=value;
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.19.131:2181,192.168.19.133:2181,192.168.19.134:2181");
		conn = ConnectionFactory.createConnection(conf);
	}
	@Override
	public void run() {
		try {
			testPutOne(rowKey,key,value);
			Thread.sleep(50000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException
	{
		for(int i=10;i<20;i++)
		{
			Thread thread=new Thread(new UnlockPut("user001","password"+i,"aaa"+i));
			thread.start();
		}
		
	}
	
	public void testPutOne(String rowKey,String key,String value) throws Exception {

		Table table = conn.getTable(TableName.valueOf("t_user_info"));
		
		ArrayList<Put> puts = new ArrayList<Put>();
		
		Put put01 = new Put(Bytes.toBytes(rowKey));
		put01.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes(key), Bytes.toBytes(value));

		puts.add(put01);

		table.put(puts);
		table.close();
		conn.close();

	}
	
}
