package com.intsmaze.service.exe;

import java.util.Map;

import com.intsmaze.service.util.XmlUtils;
import com.intsmaze.adapter.base.SqlAdapter;
import com.intsmaze.adapter.dao.ComDao;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class AbstractApplicationServer {

	final static Logger logger=LoggerFactory.getLogger(AbstractApplicationServer.class);
	
	private String date;
	
	private SqlAdapter sqlAdapter;
	
	private ComDao dao;

	private Map<String,String> sqlMap;
	
	private ApplicationContext ct;
	
	protected Options options = new Options();
	
	/**
	 * default 1
	 * 用于sql的where条件时间比较，1代表交易日期=系统前一天
	 */
	public int tradeTime=1;
	
	protected void setupConfig(String[] args) throws DocumentException, ParseException  {
		//=========================================================
		ct=new ClassPathXmlApplicationContext(new String[] {"spring-adapter-db.xml","spring-rdbs-sql.xml"});
		sqlAdapter= (SqlAdapter) ct.getBean("sqlAdapter");
		dao= (ComDao) ct.getBean("dbDao");
		sqlMap = XmlUtils.readXMLForSql(this.getPaths());
		//=========================================================
		
		addOptions(options);
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);
		HelpFormatter formatter = new HelpFormatter();
		setupOptionValue(cmd);
		
	}
	
	protected void setupConfig() throws DocumentException  {
		//=========================================================
		ct=new ClassPathXmlApplicationContext(new String[] {"spring-adapter-db.xml","spring-rdbs-sql.xml"});
		sqlAdapter= (SqlAdapter) ct.getBean("sqlAdapter");
		dao= (ComDao) ct.getBean("dbDao");
		sqlMap = XmlUtils.readXMLForSql(this.getPaths());
		//=========================================================
	}
	
	
	public static AbstractApplicationServer run(AbstractApplicationServer applicationServer) throws Exception
	{
		applicationServer.setupConfig();
		return applicationServer;
	}
	
	public void run(String[] args) throws Exception
	{
		setupConfig(args);
		service();
	}
	
	public abstract String[] getPaths();
	
	public abstract void service() throws Exception;
	
	//是为了不影响去修改其他类，而做的妥协。
	public void addOptions(Options options) {
		
	}
	
	public void setupOptionValue(CommandLine cmd) {
		
	}
	
	
	public SqlAdapter getSqlAdapter() {
		return sqlAdapter;
	}

	public void setSqlAdapter(SqlAdapter sqlAdapter) {
		this.sqlAdapter = sqlAdapter;
	}

	public ComDao getDao() {
		return dao;
	}

	public void setDao(ComDao dao) {
		this.dao = dao;
	}


	public Map<String,String> getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(Map<String,String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	public ApplicationContext getCt() {
		return ct;
	}

	public void setCt(ApplicationContext ct) {
		this.ct = ct;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

//	public static void main(String[] args) throws Exception
//	{
//		AbstractApplicationServer applicationServer= AbstractApplicationServer.run();
////		Result result = applicationServer.run().getSqlAdapter().select(applicationServer.getDao(), "select * from person");
//		Result result = applicationServer.run().getSqlAdapter().select(applicationServer.getDao(), "select * from amltp_test;");
//		if(result!=null)
//		{
//			while (result.hasNext()) {
//				result.next();//方法一定要调，虽然不用返回值，因为返回值赋给内部属性了
//				System.out.println(result.getObject(0));
//			}
//		}
//		//本系统不会多线程，所以不释放连接，一个连接用到死。后期考虑网络波动导致连接重建问题。
//		
//	}
}
