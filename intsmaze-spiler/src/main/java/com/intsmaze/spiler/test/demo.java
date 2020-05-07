package com.intsmaze.spiler.test;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.intsmaze.spiler.util.HttpUtils;

import java.util.HashMap;

/**
 * @author:YangLiu
 * @date:2018年5月17日 下午4:25:30
 * @describe:
 */
public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String smess = HttpUtils.toHttp("https://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=spot&from=webmap&c=179&wd=加油站&wd2=&pn=0&nn=0&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=12&rn=50&tn=B_NORMAL_MAP&auth=2NVv9C3gzxgfaL960fWYEYyAFSP4X=PvuxHHTLVTHBLtAmk5zC88y1GgvPUDZYOYIZuVt1cv3uVtGccZcuVtPWv3Guzt7xjhN@ThwzBDGJ4P6VWvcEWe1GD8zv7ucvY1SGpuTt0f7D=Ceuxtf0wd0vyIIUM7UCMAugjLLwzvs99Xv7&u_loc=13392167,3493157&ie=utf-8&b=(13327332.03,3523041.39;13425636.03,3541857.39)&t=155960953871\n", "utf-8");
//		String smess = HttpUtils.toHttp("https://map.baidu.com/?newmap=1&reqflag=pcmap&biz=1&from=webmap&da_par=direct&pcevaname=pc4.1&qt=spot&from=webmap&c=179&wd=加油站&wd2=&pn=0&nn=0&db=0&sug=0&addr=0&&da_src=pcmappg.poi.page&on_gel=1&src=7&gr=3&l=11&rn=50&tn=B_NORMAL_MAP&auth=2NVv9C3gzxgfaL960fWYEYyAFSP4X=PvuxHHTLxxVTNt1qo6DF==Cy1uVt1GgvPUDZYOYIZuxtEdwKv7uGccZcuVtPWv3GuzVtUvhgMZSguxzBEHLNRTVtcEWe1GD8zv7u@ZPuHxtoEIZ1lnDjnCENNZR@ZHRGBexZFTHrwzDvquTTGIFs99XvyM&u_loc=13392167,3493157&ie=utf-8&b=(13300227.86,3528095.83;13496835.86,3561375.83)&t=1559611099574\n", "utf-8");
		String smess = HttpUtils.toHttp("https://www.amap.com/service/poiInfo?query_type=TQUERY&pagesize=20&pagenum=10&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&div=PC1000&addr_poi_merge=true&is_classify=true&zoom=12&city=330100&geoobj=120.027233|30.205898|120.389782|30.374876&keywords=加油站", "utf-8");
		System.out.println(smess);
		Document doc = Jsoup.parse(smess);
//		System.out.println(doc.body().text());

		Gson gson=new Gson();
		HashMap map=gson.fromJson(doc.body().text(), HashMap.class);
		System.out.println(map);

//		System.out.println(doc.toString());
	}

}
