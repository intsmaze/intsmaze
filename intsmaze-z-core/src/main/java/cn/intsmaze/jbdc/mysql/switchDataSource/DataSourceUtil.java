package cn.intsmaze.jbdc.mysql.switchDataSource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * The Class DataSourceUtil.
 */
public class DataSourceUtil {

	/** 使用配置文件构建Druid数据源. */
	public static final int DRUID_MYSQL_SOURCE = 0;

	/** 使用配置文件构建Dbcp数据源. */
	public static final int DBCP_SOURCE = 1;

	public static String confile = "druid.properties";
	public static Properties druidP = null;

	public static String dbcpconfig = "dbcpconfig.properties";
	public static Properties dbcpP = null;

	static {
		druidP = new Properties();
		InputStream inputStream = null;
		try {
			confile = DataSourceUtil.class.getClassLoader().getResource("")
					.getPath()
					+ confile;
			System.out.println(confile);
			File file = new File(confile);
			inputStream = new BufferedInputStream(new FileInputStream(file));
			druidP.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		dbcpP = new Properties();
		InputStream inputStreamDbcp = null;
		try {
			dbcpconfig = DataSourceUtil.class.getClassLoader().getResource("")
					.getPath()
					+ dbcpconfig;
			System.out.println(dbcpconfig);
			File file = new File(dbcpconfig);
			inputStreamDbcp = new BufferedInputStream(new FileInputStream(file));
			dbcpP.load(inputStreamDbcp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据类型获取数据源
	 * @param sourceType
	 *            数据源类型
	 * @return druid或者dbcp数据源
	 * @throws Exception
	 *             the exception
	 */
	public static final DataSource getDataSource(int sourceType)
			throws Exception {
		DataSource dataSource = null;
		switch (sourceType) {
		case DRUID_MYSQL_SOURCE:
			dataSource = DruidDataSourceFactory.createDataSource(druidP);
			break;
		case DBCP_SOURCE:
			dataSource = BasicDataSourceFactory.createDataSource(dbcpP);
			break;
		}
		return dataSource;
	}
}