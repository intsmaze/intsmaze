
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.intsmaze.adapter.base.SqlAdapter;
import com.intsmaze.adapter.bean.Result;
import com.intsmaze.adapter.dao.ComDao;

public class TestSqlAdapter {
/**
* @author:YangLiu
* @date:2018年7月31日 上午9:19:33
* @describe:
 */
	public static void main(String[] args) throws Exception {

		ApplicationContext ct=new ClassPathXmlApplicationContext("spring-adapter-db.xml");
		ComDao dao= (ComDao) ct.getBean("dbDao");
		SqlAdapter ts = (SqlAdapter) ct.getBean("sqlAdapter");

		Result result = ts.select(dao, "select * from  intsmaze");
//		Result result = ts.select("select * from ECS_SYSTEM_PARAMETER");
		if(result!=null)
		{
			while (result.hasNext()) {
				result.next();//方法一定要调用，虽然不用返回值，因为返回值赋给内部属性了
				System.out.println(result.getObject(0)+"::"+result.getObject(1));
			}
		}
	}

}
