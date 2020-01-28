//package cn.intsmaze.spiler;
//
//import cn.intsmaze.hash.shard.model.IntsmazeShardInfo;
//import cn.intsmaze.hash.shard.model.IntsmazeShardedConnection;
//import cn.intsmaze.hash.shard.routTable.RouteTableStart;
//import cn.intsmaze.spiler.bean.Constant;
//import cn.intsmaze.spiler.bean.JobDescription;
//import cn.intsmaze.spiler.service.Spiler;
//import com.intsmaze.adapter.dao.impl.MysqlDao;
//import com.intsmaze.service.exe.MysqlService;
//import com.intsmaze.service.exe.bean.TestGroup;
//import com.intsmaze.service.util.FilesNameUtils;
//import com.intsmaze.service.util.SqlUtils;
//import jdk.nashorn.internal.scripts.JO;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author:YangLiu
// * @date:2018年4月9日 下午1:54:09
// * @describe:
// */
//public class DBService extends Spiler implements Runnable{
//
//	private static final Logger logger = LoggerFactory
//			.getLogger(DBService.class);
//
//	private MysqlService<JobDescription> mysqlService = new MysqlService<JobDescription>();
//
//	private RouteTableStart routeTable;
//
//	private  Map<String,List<JobDescription>> jobTableMap;
//
//	private ApplicationContext applicationContext;
//
//	public DBService(ApplicationContext applicationContext)
//	{
//		this.applicationContext=applicationContext;
//	}
//
//	public void init()
//	{
//		routeTable= (RouteTableStart) applicationContext.getBean("routeTableStart");
//		mysqlService.setMysqlDao( (MysqlDao)applicationContext.getBean("blDao"));
//
//		jobTableMap = new HashMap<String,List<JobDescription>>(100);
//		for (int i = 0; i < 100; i++) {
//			jobTableMap.put(StringUtils.join("JobDescription", "-", i),new ArrayList<JobDescription>(100));
//		}
//
//	}
//
//
//	public void run() {
//
//		String[] names = FilesNameUtils.getFiledName(new JobDescription());
//		String insertSql = SqlUtils.getInsertSql("JobDescription", names);
//
//		while (true) {
//			JobDescription jp = Spiler.qcwuJobDescriptionQueue.poll();
//			String tableName = routeTable.shardTable(jp.getCompanyCode());
//
//			List<JobDescription> tableJob=jobTableMap.get(tableName);
//			tableJob.add(jp);
//			if (tableJob.size() % 100 == 0) {
//				try {
//					insertSql.replace("JobDescription",tableName);
//					mysqlService.insertBatch(insertSql, tableJob, names);
//				} catch (Exception e) {
//				} finally {
//					tableJob.clear();
//				}
//			}
//		}
//
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
