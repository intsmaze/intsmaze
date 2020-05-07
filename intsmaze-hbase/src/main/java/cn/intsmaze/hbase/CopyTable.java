package cn.intsmaze.hbase;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.log4j.Logger;

public class CopyTable {
	private static Logger logger = Logger.getLogger(CopyTable.class);

	public static void CopyRow(String sourcetable, String aimtable, String srow) {
		Configuration conf = HBaseConfiguration.create();
		HTable stable;
		HTable atable;
		try {
			stable = new HTable(conf, sourcetable);
			logger.info(String.format("table init %s  success ... ", sourcetable));
			atable = new HTable(conf, aimtable);
			logger.info(String.format("table init %s  success ... ", aimtable));

			Scan scan = new Scan();
			if (!"".equals(srow) && srow != null) {
				scan.setStartRow(srow.getBytes());
			}

			ResultScanner res = stable.getScanner(scan);
			long i = 0;

			for (Result rs : res) {
				byte[] b = rs.getRow();
				String row = HTableManager.generatRowkey(new String(b));
				Put put = new Put(row.getBytes());
				List<Cell> celllist = rs.listCells();
				for (Cell cell : celllist) {
					put.add("f1".getBytes(), CellUtil.cloneQualifier(cell), CellUtil.cloneValue(cell));
				}
				atable.put(put);
				i++;
				if (i % 2000 == 0) {
					System.out.println("已插入" + i + "条数据,当前row:" + row);
					logger.info("已插入" + i + "条数据,当前row:" + row);
				}
			}
			stable.close();
			atable.close();
		} catch (IOException e) {
			logger.info(String.format("table init failed"));
		}
	}

	//
	public static void main(String[] args) throws IOException {
		String a1 = args[0];
		String a2 = args[1];
		String a3 = null;
		if (args.length == 3) {
			a3 = args[2];
		}
		System.out.println("copy from " + a1 + " to " + a2);
		CopyRow(a1, a2, a3);
		System.out.println("ALL DONE!");
	}
}
