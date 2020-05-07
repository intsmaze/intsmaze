package com.intsmaze.hive.udf;

import java.util.HashMap;

import org.apache.hadoop.hive.ql.exec.UDF;

public class ToLowerCase extends UDF {
	public static HashMap<String, String> provinceMap = new HashMap<String, String>();
	static {
		provinceMap.put("136", "beijing");
		provinceMap.put("137", "shanghai");
		provinceMap.put("138", "shenzhen");
	}

	// 必须是public
	public String evaluate(String field) {
		String result = field.toLowerCase();
		return result;
	}

	public String evaluate(int phonenbr) {
		
		String pnb = String.valueOf(phonenbr);
		return provinceMap.get(pnb.substring(0, 3)) == null ? "huoxing":provinceMap.get(pnb.substring(0,3));

	}

}
