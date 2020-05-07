//package cn.hive.bigdata.hive.udf;
//
//import java.util.HashMap;
//
//import org.apache.hadoop.hive.ql.exec.UDF;
//
//public class ToProvince extends UDF{
//
//	//注意，用代码提示出来的方法定义就是这个样子的
///*	private void evaluate() {
//
//
//	}*/
//
//	static HashMap<String, String> provinceMap = new HashMap<String, String>();
//
//	static{
//
//		provinceMap.put("138", "beijing");
//		provinceMap.put("139", "shanghai");
//		provinceMap.put("137", "dongjing");
//		provinceMap.put("156", "huoxing");
//
//
//	}
//
//
//
//
//	//我们需要重载这个方法，来适应我们的业务逻辑
//	public String evaluate(String phonenbr){
//		String res = provinceMap.get(phonenbr.substring(0, 3));
//
//		return res==null?"wukong":res;
//
//	}
//
//
//	public int evaluate(int x,int y){
//
//		return x+y;
//
//	}
//
//
//
//}
