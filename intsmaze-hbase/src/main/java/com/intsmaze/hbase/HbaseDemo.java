package com.intsmaze.hbase;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.ByteArrayComparable;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

public class HbaseDemo {

	private Configuration conf = null;
	private Connection conn = null;

	public static void main(String[] args) throws Exception {
		HbaseDemo demo = new HbaseDemo();
		demo.init();
		demo.testScan();
	}
	
	@Before
	public void init() throws Exception {
		conf = HBaseConfiguration.create();
		// 对于hbase的客户端来说，只需要知道hbase所使用的zookeeper集群地址就可以了
		// 因为hbase的客户端找hbase读写数据完全不用经过hmaster
		conf.set("hbase.zookeeper.quorum", "192.168.19.131:2181");
		conn = ConnectionFactory.createConnection(conf);
	}

	/**
	 * 建表
	 */
	public void testCreate(int i) throws Exception {
		// 获取一个表管理器
		Admin admin = conn.getAdmin();
		// 构造一个表描述器，并指定表名
		HTableDescriptor htd = new HTableDescriptor(TableName.valueOf("tianmeng"+i));
		// 构造一个列族描述器，并指定列族名
		HColumnDescriptor hcd1 = new HColumnDescriptor("base_info");
		// 为该列族设定一个布隆过滤器类型参数/版本数量
		hcd1.setBloomFilterType(BloomType.ROW);
		//最少保存1个版本，最多保存3个版本
		// 构造第二个列族描述器，并指定列族名
		HColumnDescriptor hcd2 = new HColumnDescriptor("extra_info");
		hcd2.setBloomFilterType(BloomType.ROW);

		// 将列族描述器添加到表描述器中
		htd.addFamily(hcd1).addFamily(hcd2);

		admin.createTable(htd);
		admin.close();
		conn.close();
	}
	

	/**
	 * 删除表
	 */
	public void testDrop() throws Exception {
		Admin admin = conn.getAdmin();
		admin.disableTable(TableName.valueOf("user"));
		admin.deleteTable(TableName.valueOf("user"));
		admin.close();
		conn.close();
	}

	/**
	 * 修改表定义（schema）
	 */
	@Test
	public void testModify() throws Exception {

		Admin admin = conn.getAdmin();
		// 修改已有的ColumnFamily
		HTableDescriptor table = admin.getTableDescriptor(TableName.valueOf("t_user_info"));
		HColumnDescriptor f2 = table.getFamily("extra_info".getBytes());
		f2.setBloomFilterType(BloomType.ROWCOL);
		// 添加新的ColumnFamily
		table.addFamily(new HColumnDescriptor("other_info"));

		admin.modifyTable(TableName.valueOf("t_user_info"), table);

		admin.close();
		conn.close();

	}

	/**
	 * 插入/修改 数据  DML
	 */
	@Test
	public void testPut() throws Exception {

		Table table = conn.getTable(TableName.valueOf("intsmaze1"));
		
		ArrayList<Put> puts = new ArrayList<Put>();
		
		// 构建一个put对象（kv），指定其行键
		Put put01 = new Put(Bytes.toBytes("user001"));
		put01.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhangsan"));

		Put put02 = new Put("user001".getBytes());
		put02.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("password"), Bytes.toBytes("123456"));

		Put put03 = new Put("user002".getBytes());
		put03.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("lisi"));
		put03.addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));
		
		Put put04 = new Put("zhang_sh_01".getBytes());
		put04.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhang01"));
		put04.addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));

		Put put05 = new Put("zhang_sh_02".getBytes());
		put05.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhang02"));
		put05.addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));

		Put put06 = new Put("liu_sh_01".getBytes());
		put06.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("liu01"));
		put06.addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));

		Put put07 = new Put("zhang_bj_01".getBytes());
		put07.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhang03"));
		put07.addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));
		
		Put put08 = new Put("zhang_bj_01".getBytes());
		put08.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("username"), Bytes.toBytes("zhang04"));
		put08.addColumn(Bytes.toBytes("extra_info"), Bytes.toBytes("married"), Bytes.toBytes("false"));

		
		puts.add(put01);
		puts.add(put02);
		puts.add(put03);
		puts.add(put04);
		puts.add(put05);
		puts.add(put06);
		puts.add(put07);
		puts.add(put08);

		table.put(puts);
		table.close();
		conn.close();

	}

	
	// 读取数据  ---get：一次读一行
	@Test
	public void testGet() throws Exception {
		Table table = conn.getTable(TableName.valueOf("t_user_info"));
		// 构造一个get查询参数对象，指定要get的是哪一行
		Get get = new Get("user001".getBytes());
		Result result = table.get(get);
		CellScanner cellScanner = result.cellScanner();
		while (cellScanner.advance()) {
			Cell current = cellScanner.current();
			byte[] familyArray = current.getFamilyArray();
			byte[] qualifierArray = current.getQualifierArray();
			byte[] valueArray = current.getValueArray();
			
			System.out.print(new String(familyArray, current.getFamilyOffset(), current.getFamilyLength()));
			System.out.print(":" + new String(qualifierArray, current.getQualifierOffset(), current.getQualifierLength()));
			System.out.println(" " + new String(valueArray, current.getValueOffset(), current.getValueLength()));
		}
		table.close();
		conn.close();

	}

	
	/**
	 * 删除表中的列数据
	 * @throws Exception
	 */
	@Test
	public void testDel() throws Exception {

		Table t_user_info = conn.getTable(TableName.valueOf("t_user_info"));
		Delete delete = new Delete("user001".getBytes());
		delete.addColumn("base_info".getBytes(), "password".getBytes());
		t_user_info.delete(delete);

		t_user_info.close();
		conn.close();
	}

	
	/**
	 * scan 批量查询数据
	 * 
	 * @throws Exception
	 */
	@Test
	public void testScan() throws Exception {

		Table t_user_info = conn.getTable(TableName.valueOf("intsmaze1"));

		//这里有一个技巧，这样可以包含尾部
		Scan scan = new Scan(Bytes.toBytes("liu_sh_01"),Bytes.toBytes("zhang_bj_01"+"\000"));

		ResultScanner scanner = t_user_info.getScanner(scan);
		Iterator<Result> iter = scanner.iterator();
		while (iter.hasNext()) {
			Result result = iter.next();
			CellScanner cellScanner = result.cellScanner();
			while (cellScanner.advance()) {
				Cell current = cellScanner.current();
				byte[] familyArray = current.getFamilyArray();
				byte[] valueArray = current.getValueArray();
				byte[] qualifierArray = current.getQualifierArray();
				byte[] rowArray = current.getRowArray();

				System.out.println(new String(rowArray, current.getRowOffset(), current.getRowLength()));
				System.out.print(new String(familyArray, current.getFamilyOffset(), current.getFamilyLength()));
				System.out.print(":" + new String(qualifierArray, current.getQualifierOffset(), current.getQualifierLength()));
				System.out.println(" " + new String(valueArray, current.getValueOffset(), current.getValueLength()));
			}
			System.out.println("-----------------------");
		}

	}

	@Test
	public void testFilter() throws Exception {
		
		 // 针对行键的前缀过滤器
		 /*Filter pf = new PrefixFilter(Bytes.toBytes("liu")); 
		 testScan(pf);*/
		 

		 // 行过滤器
		 /*RowFilter rf1 = new RowFilter(CompareOp.LESS, new BinaryComparator(Bytes.toBytes("user002"))); 
		 RowFilter rf2 = new RowFilter(CompareOp.EQUAL, new SubstringComparator("00"));
		 testScan(rf1); 
		 System.out.println("**********"); 
		 testScan(rf2);*/
		 

		// 针对指定一个列的value来过滤
		/*SingleColumnValueFilter scvf = new SingleColumnValueFilter("base_info".getBytes(), "password".getBytes(), CompareOp.GREATER, "123456".getBytes()); 
		scvf.setFilterIfMissing(true);   // 如果指定的列缺失，则也过滤掉
		testScan(scvf);*/
		/*ByteArrayComparable comparator1 = new RegexStringComparator("^zhang");
		ByteArrayComparable comparator2 = new SubstringComparator("ang");
		SingleColumnValueFilter scvf = new SingleColumnValueFilter("base_info".getBytes(), "username".getBytes(), CompareOp.EQUAL, comparator1);
		testScan(scvf);*/
		 
		// 针对列族名的过滤器   返回结果中只会包含满足条件的列族中的数据
		/*FamilyFilter ff1 = new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("inf")));  
		FamilyFilter ff2 = new FamilyFilter(CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes("base")));  
		testScan(ff2);*/

		// 针对列名的过滤器 返回结果中只会包含满足条件的列的数据
		/*QualifierFilter qf = new QualifierFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("password")));
		QualifierFilter qf2 = new QualifierFilter(CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes("us")));
		testScan(qf);*/
		
		// 跟SingleColumnValueFilter结果不同，只返回符合条件的该column
		/*ColumnPrefixFilter cf = new ColumnPrefixFilter("passw".getBytes());
		testScan(cf);*/
		
		/*byte[][] prefixes = new byte[][] { Bytes.toBytes("username"),Bytes.toBytes("password") };
		MultipleColumnPrefixFilter mcf = new MultipleColumnPrefixFilter(prefixes);
		testScan(mcf);*/
		
		
		/*FamilyFilter ff2 = new FamilyFilter(CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes("base")));
		ColumnPrefixFilter cf = new ColumnPrefixFilter("passw".getBytes());
		FilterList filterList = new FilterList(Operator.MUST_PASS_ALL);
		filterList.addFilter(ff2);
		filterList.addFilter(cf);
		testScan(filterList);*/
	}

	public void testScan(Filter filter) throws Exception {

		Table t_user_info = conn.getTable(TableName.valueOf("t_user_info"));

		Scan scan = new Scan();
		scan.setFilter(filter);
		ResultScanner scanner = t_user_info.getScanner(scan);

		Iterator<Result> iter = scanner.iterator();
		while (iter.hasNext()) {
			Result result = iter.next();
			CellScanner cellScanner = result.cellScanner();
			while (cellScanner.advance()) {
				Cell current = cellScanner.current();
				byte[] familyArray = current.getFamilyArray();
				byte[] valueArray = current.getValueArray();
				byte[] qualifierArray = current.getQualifierArray();
				byte[] rowArray = current.getRowArray();

				System.out.println(new String(rowArray, current.getRowOffset(), current.getRowLength()));
				System.out.print(new String(familyArray, current.getFamilyOffset(), current.getFamilyLength()));
				System.out.print(":" + new String(qualifierArray, current.getQualifierOffset(), current.getQualifierLength()));
				System.out.println(" " + new String(valueArray, current.getValueOffset(), current.getValueLength()));
			}
			System.out.println("-----------------------");
		}
	}

	/**
	 * 分页查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void pageScan() throws Exception{
		
		final byte[] POSTFIX = new byte[] { 0x00 }; 
		Table table = conn.getTable(TableName.valueOf("t_user_info"));
		Filter filter = new PageFilter(3);   // 一次需要获取一页的条数
		byte[] lastRow = null;  
		int totalRows = 0;  
		while (true) {  
		    Scan scan = new Scan();  
		    scan.setFilter(filter);  
		    if(lastRow != null){  
		        byte[] startRow = Bytes.add(lastRow,POSTFIX);   //设置本次查询的起始行键  
		        scan.setStartRow(startRow);  
		    }  
		    ResultScanner scanner = table.getScanner(scan); 
		    int localRows = 0;  
		    Result result;  
		    while((result = scanner.next()) != null){  
		        System.out.println(++localRows + ":" + result); 
		        totalRows ++;  
		        lastRow = result.getRow();  
		    }  
		    scanner.close();  
		    if(localRows == 0) {
                break;
            }
		    Thread.sleep(2000);
		}  
		System.out.println("total rows:" + totalRows);  
	
	}
	
	
	

}
