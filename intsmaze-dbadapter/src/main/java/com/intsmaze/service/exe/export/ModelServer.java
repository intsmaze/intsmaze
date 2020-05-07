package com.intsmaze.service.exe.export;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intsmaze.adapter.bean.Result;
import com.intsmaze.service.exe.AbstractApplicationServer;
import com.intsmaze.service.exe.MysqlService;
import com.intsmaze.service.exe.bean.TestGroup;
import com.intsmaze.service.util.AmlException;
import com.intsmaze.service.util.FilesNameUtils;
import com.intsmaze.service.util.SqlUtils;
import com.intsmaze.adapter.dao.impl.MysqlDao;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:YangLiu
 * @date:2018年6月12日 上午10:58:54
 * @describe:
 */
public class ModelServer extends AbstractApplicationServer {

	private static final Logger logger = LoggerFactory
			.getLogger(ModelServer.class);

	private final static String SQL = "get_customer_infor";

	private MysqlService<TestGroup> mysqlService = new MysqlService<TestGroup>();

	private MysqlDao mysqlDao;

	@Override
	public String[] getPaths() {
		return new String[] {"com/intsmaze/test/service/exe/export/model.xml"};
	}

	private int date = 1;

	@Override
	public void addOptions(Options options) {
		options.addOption("date", true, "天数");
	}

	@Override
	public void setupOptionValue(CommandLine cmd) {
		mysqlDao=  (MysqlDao) this.getCt().getBean("blDao");;
		date = Integer.parseInt(cmd.getOptionValue("date", "1"));
		logger.debug("date is {}", date);
	}

	@Override
    public void service() throws Exception {
		mysqlService.setMysqlDao(this.getMysqlDao());
		AmlException exception = null;
		for (int i = 1; i <= date; i++) {

			String exeSql = (String) this.getSqlMap().get(SQL);
			Result result = this.getSqlAdapter().select(this.getDao(),
					"SELECT * from test_group");

			String[] names = FilesNameUtils.getFiledName(new TestGroup());
			String insertSql = SqlUtils.getInsertSql("test_group", names);
			
			List<TestGroup> list = new ArrayList<TestGroup>(100);
			int number = 0;
			while (result.hasNext()) {
				result.next();
				TestGroup br = (TestGroup) tableToBean(result, i,names);
				list.add(br);
				System.out.println(br);
				number++;
				if (number % 100 == 0) {
					try {
						mysqlService.insert(insertSql, list, names);
					} catch (Exception e) {
						exception = new AmlException(e);
					} finally {
						list.clear();
					}
				}
			}
			try {
				mysqlService.insert(insertSql, list, names);
				logger.info("insert data number is {}", number);
			} catch (Exception e) {
				logger.info("insert data number is {}", number);
				exception = new AmlException(e);
			} finally {
				result.close();
			}

		}
		if (exception != null) {
			throw exception;
		}
	}

	public static void main(String[] args) throws Exception {
		ModelServer applicationServer = new ModelServer();
		applicationServer.run(args);
		logger.info("execute sucess......");
		System.exit(0);
	}

	private Object tableToBean(Result result, int i, String[] names) throws Exception {
		Class clazz = TestGroup.class;
		TestGroup testGroup = (TestGroup) clazz.newInstance();
		for (int j = 0; j < names.length; j++) {
			Object object = result.getObjectByName(names[j]);
			if (object instanceof  String) {
			     Field f = clazz.getDeclaredField(names[j]);
			     f.setAccessible(true);
			     f.set(testGroup, object);
			} else if (object instanceof  Date) {
				 Field f = clazz.getDeclaredField(names[j]);
			     f.setAccessible(true);
			     f.set(testGroup, new java.sql.Date(((Date)object).getTime()));
			}
			else if (object instanceof Long) {
			     Field f = clazz.getDeclaredField(names[j]);
			     f.setAccessible(true);
			     f.set(testGroup, object);
			} 
		}
		return testGroup;
	}

	public MysqlDao getMysqlDao() {
		return mysqlDao;
	}

	public void setMysqlDao(MysqlDao mysqlDao) {
		this.mysqlDao = mysqlDao;
	}
}
