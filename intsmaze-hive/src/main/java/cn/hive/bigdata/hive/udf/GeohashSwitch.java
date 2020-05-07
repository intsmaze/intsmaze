package cn.hive.bigdata.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import java.util.HashMap;

public class GeohashSwitch extends UDF {

	// 必须是public
	public String evaluate(String lat, String lng,int length) {
		GeoHash g = new GeoHash(Double.valueOf(lat), Double.valueOf(lng));
		g.sethashLength(length);
		return g.getGeoHashBase32();
	}



}
