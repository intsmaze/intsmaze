package cn.intsmaze.jbdc.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cn.intsmaze.jbdc.mysql.utils.JdbcUtils;
import com.mysql.jdbc.Statement;

/**
 * java 中MySQL JDBC 封装了流式查询操作，通过设置几个参数，就可以避免一次返回数据过大导致 OOM。
 * 非流式方式由于是把符合条件的数据一下子全部加在到内存，并且由于数据量比较大，mysql准备数据的时间比较长，
 * 我测试情况下需要一分钟才会返回结果到内存（数据量比较大），然后才能通过数据集访问数据。
 * 而流式方式是每次返回一个记录到内存，所以占用内存开销比较小，并且调用后会马上可以访问数据集的数据。
 * 可知非流式时候内存会随着搜出来的记录增长而近乎直线增长，流式时候则比较平稳，另外非流式由于需要mysql服务器准备全部数据，所以调用后不会马上返回，需要根据数据量大小不同会等待一段时候才会返回，这时候调用方线程会阻塞，流式则因为每次返回一条记录，所以返回速度会很快。
 * 这里在总结下：client发送select请求给Server后，Server根据条件筛选符合条件的记录，然后就会把记录发送到自己的发送buffer,等buffer满了就flush缓存（这里要注意的是如果client的接受缓存满了，那么Server的发送就会阻塞主，直到client的接受缓存空闲。），通过网络发送到client的接受缓存，当不用游标时候MySqIo就会从接受缓存里面逐个读取记录到resultset。就这样client 从自己的接受缓存读取数据到resultset,同时Server端不断通过网络向client接受缓存发送数据，直到所有记录都放到了resultset。

如果使用了游标，则用户调用resultset的next的频率决定了Server发送时候的阻塞情况，如果用户调用next快，那么client的接受缓存就会有空闲，那么Server就会把数据发送过来，如果用户调用的慢，那么由于接受缓存腾不出来，Server的发送就会阻塞
 */
public class SelectOOMTest {


	/**
	 * @param args
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws SQLException, InterruptedException {

		selectData();

	}

	static void select() throws SQLException, InterruptedException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Thread.sleep(20000);
		try {
			conn = JdbcUtils.getInstance().getConnection();
			String sql = "select account,trade_type from testoom";
//			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getObject("account") + "\t"
						+ rs.getObject("trade_type") );
			}
		} finally {

		}
	}


	static void selectData() throws SQLException, InterruptedException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Thread.sleep(20000);
		try {
			conn = JdbcUtils.getInstance().getConnection();
			String sql = "select account,trade_type from testoom";
			//可知只是prepareStatement时候改变了参数，并且设置了PreparedStatement的fetchsize为Integer.MIN_VALUE。
			ps = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ps.setFetchSize(Integer.MIN_VALUE);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getObject("account") + "\t"
						+ rs.getObject("trade_type") );
			}
		} finally {

		}
		Thread.sleep(200000);
	}


	static void selectData1() throws SQLException, InterruptedException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Thread.sleep(20000);
		try {
			conn = JdbcUtils.getInstance().getConnection();
			String sql = "select account,trade_type from testoom";
			// MySQL在自己的JDBC驱动里提供了特有的功能，来实现查询的快速响应， 特别是结果集非常大或者时间较长，而用户非常想尽快看到第一条结果时特别有效。
			Statement stmt =  (Statement) conn.createStatement();
		     stmt.setFetchSize(1);
		        //按行读取
		        // 打开流方式返回机制
		        stmt.enableStreamingResults();
		        rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getObject("account") + "\t"
						+ rs.getObject("trade_type") );
			}
		} finally {

		}
		Thread.sleep(200000);
	}

}



