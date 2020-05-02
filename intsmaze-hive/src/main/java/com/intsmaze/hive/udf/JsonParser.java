//package cn.hive.bigdata.hive.udf;
//
//import java.io.IOException;
//
//import org.apache.hadoop.hive.ql.exec.UDF;
//
//import parquet.org.codehaus.jackson.JsonParseException;
//import parquet.org.codehaus.jackson.map.JsonMappingException;
//import parquet.org.codehaus.jackson.map.ObjectMapper;
//
//public class JsonParser extends UDF {
//
//	public String evaluate(String jsonLine) {
//
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		try {
//			MovieRateBean bean = objectMapper.readValue(jsonLine, MovieRateBean.class);
//			return bean.toString();
//		} catch (Exception e) {
//
//		}
//		return "";
//	}
//
//	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		String str = "{\"movie\":\"1193\",\"rate\":\"5\",\"timeStamp\":\"978300760\",\"uid\":\"1\"}";
//		ObjectMapper objectMapper = new ObjectMapper();
//		MovieRateBean readValue = objectMapper.readValue(str, MovieRateBean.class);
//		System.out.println(readValue);
//	}
//}
