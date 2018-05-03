package org.intsmaze.business.springmvc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.persistence.pojos.FDFBUser;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.xml.XmlMapper;

public class JsonUtil {

	private static Logger logger = Logger.getLogger(JsonUtil.class);

	/**
	 * 将Json对象字符串转化为Map对象
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @return 转换成功返回Map对象，失败则返回null
	 */
	public static Map<String, String> JsonToMap(String jsonStr) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, String> params = objectMapper.readValue(jsonStr,
					Map.class);
			return params;
		} catch (Exception e) {
			logger.error("JsonToMap Error " + jsonStr, e);
			return null;
		}
	}

	/**
	 * 将Json对象字符串转化为Map<String, Object>对象
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @return 转换成功返回Map对象，失败则返回null
	 */
	public static Map<String, Object> readJson2Map(String jsonStr) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> params = objectMapper.readValue(jsonStr,
					Map.class);
			return params;
		} catch (Exception e) {
			logger.error("JsonToMap Error " + jsonStr, e);
			return null;
		}
	}

	/**
	 * 将Json对象字符串转化为JavaBean类对象
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @param clazz
	 *            类类型
	 * @return 转换成功返回JavaBean类对象，失败则返回null
	 */
	public static <T> T readJson2Bean(String jsonStr, Class<T> clazz) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			T t = objectMapper.readValue(jsonStr, clazz);
			return t;
		} catch (Exception e) {
			logger.error("readJson2Bean Error " + jsonStr, e);
			return null;
		}
	}

	/**
	 * 将Json对象字符串转化为List<Map>对象
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @return 转换成功返回Map对象，失败则返回null
	 */
	public static List<HashMap<String, Object>> JsonToMapList(String jsonStr) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<HashMap<String, Object>> params = objectMapper.readValue(
					jsonStr, List.class);
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map = params.get(i);
				Set<String> set = map.keySet();
				for (Iterator<String> it = set.iterator(); it.hasNext();) {
					String key = it.next();
				}
			}
			return params;
		} catch (Exception e) {
			logger.error("JsonToMap Error " + jsonStr, e);
			return null;
		}
	}

	/**
	 * 将传入能够整除2的数量的参数序列化为Json对象
	 * 
	 * @param result
	 * @return
	 */
	public static String WriteToJson(Object[] paramObjs) {
		try {
			if (paramObjs == null || paramObjs.length == 0) {
				logger.error("参数实例为空");
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (int i = 0; i < paramObjs.length; i += 2) {
				paramMap.put((String) paramObjs[i], paramObjs[i + 1]);
			}
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(paramMap);
		} catch (Exception e) {
			return "";
		}
	}

	public static String toJson(Object target) {
		String result = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.writeValueAsString(target);
			result = result.replaceAll("dt_RowId", "DT_RowId");
		} catch (Exception e) {
			logger.error("convert to Json Str Fail !");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将List或Map转换成json
	 * 
	 * @param bean
	 * @return
	 */
	public String writeObject2Xml(Object bean) {
		// stax2-api-3.0.2.jar
		XmlMapper xml = new XmlMapper();
		String result = null;
		try {
			result = xml.writeValueAsString(bean);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将Jquery传来的字符串映射到对应实体类
	 * @param str 格式为key=value&key=value&。。。key=value
	 * @param obj 实体类对象
	 * @return 赋值后的实体类
	 */
	public static Object jqueryStrToBean(String str, Object obj) {
		if (str == null) {
			return null;
		} else {
			try {
				str = URLDecoder.decode(str,Constant.ENCODING_UTF_8);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String[] strList = str.split("&");
				Arrays.sort(strList);
				Map<String, Object> jqueryValue = new HashMap<String, Object>();
				
				List<String> mDataProp = new ArrayList();
				Map tempMDataProp = new TreeMap<Integer, String>();
				for (int i = 0, n = strList.length; i < n; i++) {
					String tmpStr = strList[i];
					String[] tmpStrlist = tmpStr.split("=");
					if(tmpStrlist[0].startsWith("mDataProp"))
					{
						int indexOfColumn = Integer.parseInt(tmpStrlist[0].substring(tmpStrlist[0].indexOf("_")+1,tmpStrlist[0].length()));//mDataProp_14.indexOf("_")+1,mDataProp_14.length()
//						mDataProp.add(indexOfColumn, tmpStrlist.length<2?"":tmpStrlist[1]);
						tempMDataProp.put(indexOfColumn, tmpStrlist.length<2?"":tmpStrlist[1]);
					}
					else
					{
						jqueryValue.put(tmpStrlist[0], tmpStrlist.length<2?"":tmpStrlist[1]);
					}
				}
				Iterator it=tempMDataProp.entrySet().iterator();          
				while(it.hasNext()){   
			        Map.Entry entry = (Map.Entry)it.next();          
			        int key = (Integer)entry.getKey();          
			        String value = String.valueOf(entry.getValue());          
			        mDataProp.add(key, value);                 
				}  
				
				jqueryValue.put("mDataProp", mDataProp);
				obj = copyBean(jqueryValue, obj);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 将map值赋值给model的对应属性
	 * @param map
	 * @param model
	 * @return
	 */
	public static Object copyBean(Map map, Object model) {
		Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字
				if(map.get(name) == null || "".equals(map.get(name)))
				{
					continue;
				}
				// name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (type.indexOf("ArrayList") > -1) { // 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = null;
					try{
						m = model.getClass().getMethod("set" + name, java.util.ArrayList.class);
					}catch(NoSuchMethodException e)
					{
						String upperName = name.substring(0,1).toUpperCase() + name.substring(1);
						m = model.getClass().getMethod("set" + upperName, java.util.ArrayList.class);
					}
					java.util.ArrayList value = (java.util.ArrayList)map.get(name);
					m.invoke(model, value);
				}
				else if (type.indexOf("String") > -1) { // 如果type是类类型，则前面包含"class "，后面跟类名
					Method m = null;
					try{
						m = model.getClass().getMethod("set" + name, String.class);
					}catch(NoSuchMethodException e)
					{
						String upperName = name.substring(0,1).toUpperCase() + name.substring(1);
						m = model.getClass().getMethod("set" + upperName, String.class);
					}
					String value = (String)map.get(name);
					m.invoke(model, value);
				}
				else if (type.indexOf("int") > -1) {
					Method m = null;
					try{
						m = model.getClass().getMethod("set" + name, int.class);
					}catch(NoSuchMethodException e)
					{
						String upperName = name.substring(0,1).toUpperCase() + name.substring(1);
						m = model.getClass().getMethod("set" + upperName, int.class);
					}
					m.invoke(model, Integer.parseInt((String)map.get(name)));
				}
				else if (type.indexOf("Integer") > -1) {
					Method m = null;
					try{
						m = model.getClass().getMethod("set" + name, Integer.class);
					}catch(NoSuchMethodException e)
					{
						String upperName = name.substring(0,1).toUpperCase() + name.substring(1);
						m = model.getClass().getMethod("set" + upperName, Integer.class);
					}
					m.invoke(model, Integer.parseInt((String)map.get(name)));
				}
				else if (type.indexOf("Date") > -1) {
					Method m = null;
					try{
						m = model.getClass().getMethod("set" + name, Date.class);
					}catch(NoSuchMethodException e)
					{
						String upperName = name.substring(0,1).toUpperCase() + name.substring(1);
						m = model.getClass().getMethod("set" + upperName, Date.class);
					}
					m.invoke(model, DateUtil.parse(((String)map.get(name)), DateUtil.default_dateformat));
				}
				else if (type.indexOf("boolean") > -1) {
					Method m = null;
					try{
						m = model.getClass().getMethod("set" + name, boolean.class);
					}catch(NoSuchMethodException e)
					{
						String upperName = name.substring(0,1).toUpperCase() + name.substring(1);
						m = model.getClass().getMethod("set" + upperName, boolean.class);
					}
					boolean value = "true".equals((String)map.get(name))?true:false;
					m.invoke(model, value);
				}
				else if (type.indexOf("BigDecimal") > -1) {
					Method m = null;
					try{
						m = model.getClass().getMethod("set" + name, java.math.BigDecimal.class);
					}catch(NoSuchMethodException e)
					{
						String upperName = name.substring(0,1).toUpperCase() + name.substring(1);
						m = model.getClass().getMethod("set" + upperName, java.math.BigDecimal.class);
					}
					java.math.BigDecimal value = new java.math.BigDecimal((String)map.get(name));
					m.invoke(model, value);
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	/**
	 * 页面datatables编辑后的json处理，返回对应的Obj
	 * @param str
	 * @param obj
	 * @return
	 */
	public static Object copyDetailEditStrToBean(String str, Object obj)
	{
		Map map1 = null;
		try{
			String methodname = null;
			String value = null;
			String seqno = null;
			String seqnoName = null;
			
			Map map = JsonUtil.JsonToMap(str);
	        Map data = (Map)map.get("data");
	        Iterator entries = data.entrySet().iterator();  
	        while (entries.hasNext()) {  
	            Map.Entry entry = (Entry) entries.next();  
	            seqno = (String)entry.getKey();
//	            String[] seqnoArray = seqno.split(":");
//	            seqnoName = seqnoArray[0];
//	            seqno = seqnoArray[1];
	            seqnoName = "seqno";
	            seqno = seqno.replaceAll(Constant.DT_ROWID_PREFIX, "");
	            Map methodValue = (Map)entry.getValue();
	            Iterator iter = methodValue.entrySet().iterator();
	            while(iter.hasNext())
	            {
	            	Map.Entry entry1 = (Entry) iter.next();  
	            	methodname = (String)entry1.getKey();
	            	value = String.valueOf(entry1.getValue());
	            }
	        }
	        
	        map1 = new HashMap(2);
			map1.put(seqnoName, seqno);
			map1.put(methodname, value);
			return copyBean(map1, obj);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FDFBUser user = new FDFBUser();
				
		// TODO Auto-generated method stub
        String str = "{\"action\":\"edit\",\"data\":{\"seqno:0680c895-e0f6-48b7-a9b7-6d764d34f172\":{\"username\":\"干扰法地方12\"}}}";
        user = (FDFBUser) copyDetailEditStrToBean(str, user);
        System.out.println(user.getSeqno());
        System.out.println(user.getUsername());
	}

}
