//package cn.hive.bigdata.hive.udf;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.hadoop.hive.ql.exec.UDF;
//import org.apache.hadoop.hive.ql.parse.HiveParser_IdentifiersParser.intervalLiteral_return;
//
//public class PhoneArea extends UDF{
//
//	static Map<String, String> area =  new HashMap<String, String>();
//	static{
//		area.put("135", "beijing");
//		area.put("136", "tianjing");
//		area.put("137", "nanjing");
//		area.put("138", "dongjing");
//		area.put("139", "dongjing");
//
//	}
//
//	public String evaluate(String phone) {
//
//		String prefix = phone.substring(0,3);
//
//		return area.get(prefix)==null?"huoxing":area.get(prefix);
//
//	}
//
//
//
//	public int evaluate(int x,int y) {
//
//		return x+y;
//
//	}
//
//}
