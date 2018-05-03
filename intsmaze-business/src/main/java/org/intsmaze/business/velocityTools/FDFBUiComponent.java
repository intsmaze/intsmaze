package org.intsmaze.business.velocityTools;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.intsmaze.business.Interfaces.IUserApi;
import org.intsmaze.business.service.ICodeValueService;
import org.intsmaze.business.vo.FDFBBimsCarShare;
import org.intsmaze.business.vo.FDFBColumnPermitVo;
import org.intsmaze.core.util.Constant;
import org.intsmaze.core.util.DateUtil;
import org.intsmaze.core.util.StringUtil;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class FDFBUiComponent {

	/**
	 * 根据bean的类型获取相应类型的对象，没有使用泛型，获得结果后，需要强制转换为相应的类型
	 * 
	 * @param clazz
	 *            bean的类型，没有使用泛型
	 * @return Object类型的对象
	 */
	private static Object getBean(Class clazz) {
		WebApplicationContext wac = ContextLoader
				.getCurrentWebApplicationContext();
		Object bean = wac.getBean(clazz);
		return bean;
	}// velocity toolbox.xml中配置的工具类貌似无法注入bean，只能通过spring上下文获取

	/**
	 * 根据参数构造<select>
	 * 
	 * @param type
	 *            map对应的键值，例如key_depid，详见Constant
	 * @param id
	 *            select的id和name
	 * @param currentValue
	 *            当前值
	 * @param checktype
	 *            Bootstrap check type
	 * @param needBlank
	 * 			  是否需要空白选项
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String generateSelect(String type, String id, String currentValue,
			String checktype, boolean needBlank) {
		StringBuffer sb = new StringBuffer("");
		Map resultMap = sortMapByKey(getMapByKey(type));

		StringBuffer selectSb = new StringBuffer("");
		if (resultMap == null || resultMap.size() < 1) {
			return "<select class=\"form-control\" id=\"" + id + "\" name=\"" + id + "\"><option value=\"\"></option></select>";
		} else {
			selectSb.append("<select ").append(checktype)
					.append(" class=\"form-control\" id=\"").append(id)
					.append("\" name=\"").append(id).append("\">");
			if(needBlank)
			{
				selectSb.append("<option value=\"\"></option>");
			}
			
			Iterator<Map.Entry> entries = resultMap
					.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = entries.next();
				String keyValue = String.valueOf(entry.getKey());
				String valueValue = String.valueOf(entry.getValue());
				
				String isSelected = currentValue.equals(String
						.valueOf(keyValue)) ? "selected=\"true\"" : "";

				selectSb.append("<option value=\"").append(keyValue)
						.append("\" ").append(isSelected).append(">")
						.append(valueValue).append("</option>");
			}
			
			selectSb.append("</select>");
			return selectSb.toString();
		}

	}

	/**
	 * 获取datatables editor特殊类型字段的fields内容，如select、checkbox、date等
	 * 
	 * @param cpv
	 * @return
	 */
	public String generateEditorFields(FDFBColumnPermitVo cpv) {
		StringBuffer sb = new StringBuffer("");
		String columnKey = cpv.getColumnCodeKey();
		String columnType = cpv.getColumnType();

		if (Constant.EDITOR_FIELDS_TYPE_SELECT.equals(columnType)) {
			sb.append(getEditorFieldsTypeHtml(columnKey, columnType));
		} else if (Constant.EDITOR_FIELDS_TYPE_CHECKBOX.equals(columnType)) {
			// do checkbox
		}
		else if(Constant.EDITOR_FIELDS_TYPE_TEXTAREA.equals(columnType))
		{
			sb.append(",type: \"textarea\"");
		}
		return sb.toString();

	}

	/**
	 * 获取datatables editor特殊类型字段的fields内容，如select、checkbox、date等
	 * @param columnName map对应的键值，例如key_depid，详见Constant
	 * @param columnType select、checkbox等
	 * @return
	 */
	public String getEditorFieldsTypeHtml(String columnName, String columnType) {

		StringBuffer sb = new StringBuffer("");
		Map resultMap = sortMapByKey(getMapByKey(columnName));

		if (resultMap == null || resultMap.size() < 1) {
			return "";
		} else if (Constant.EDITOR_FIELDS_TYPE_SELECT.equals(columnType)) {
			Iterator<Map.Entry> entries = resultMap
					.entrySet().iterator();
			sb.append(",type: \"").append(columnType).append("\",options: [");
			StringBuffer selectSb = new StringBuffer("");
			while (entries.hasNext()) {
				Map.Entry entry = entries.next();
				selectSb.append("{name:\"").append(entry.getValue())
						.append("\", ").append("id:\"").append(entry.getKey())
						.append("\"},");
			}
			String str = selectSb.toString();
			if (str.indexOf(",") > -1) {
				str = str.substring(0, str.length() - 1);
			}
			sb.append(str);
			sb.append("],optionsPair: {label: 'name',value: 'id'}");

		}
		return sb.toString();
	}

	/**
	 * 根据type获取key value对应的Map，用于构建select
	 * 
	 * @param type
	 * @return
	 */
	private Map getMapByKey(String type) {
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);
		ICodeValueService codeValueService = (ICodeValueService) getBean(ICodeValueService.class);
//		ITbmsApi tbmsAreaApi = (ITbmsApi) getBean(ITbmsApi.class);

		Map resultMap = null;

		if (Constant.EDITOR_FIELDS_TYPE_KEY_DEPID.equals(type)) {
			resultMap = userApi.getDepMap();
		} else if (Constant.EDITOR_FIELDS_TYPE_KEY_ROLEID.equals(type)) {
			resultMap = userApi.getRoleMap();
		} else if (Constant.EDITOR_FIELDS_TYPE_KEY_COMID.equals(type)) {
//			resultMap = bimsBusinessApi.getCompanyMap();
		} else if (Constant.EDITOR_FIELDS_TYPE_KEY_AREAID.equals(type)) {
//			resultMap = tbmsAreaApi.getAreaMap();
		} else if (Constant.EDITOR_FIELDS_TYPE_KEY_CARID.equals(type)) {
			
		}
		else {
			resultMap = codeValueService.getAllCodeValueMapByKey(type);
		}

		if (resultMap == null || resultMap.size() < 1) {
			return new HashMap(0);
		} else {
			return resultMap;
		}
	}
	
	/**
	 * 格式化时间 yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public String formatTime(Timestamp time)
	{
		return DateUtil.formatDateTime(time);
	}

	/**
	 * 判断是否为空字符串
	 * @param str
	 * @return
	 */
	public boolean isNull(String str)
	{
		if("".equals(StringUtil.isNullString(str)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 根据角色获取对应公司的所有该角色用户
	 * @param id 
	 * 			select的id和name
	 * @param currentValue
	 * 			当前值
	 * @param checktype
	 * 			bootstrap页面验证类型
	 * @param needBlank
	 * 			是否需要第一行的空白
	 * @param depid
	 * 			公司ID
	 * @param roleid
	 * 			角色ID
	 * @return
	 */
	public String generateMaintainUserSelect(String id, String currentValue,
			String checktype, boolean needBlank,String depid, String roleid) {
		StringBuffer sb = new StringBuffer("");
		Map resultMap = null;
		
		if("0a04798b-d4da-4127-b399-f098af755b62".equals(StringUtil.isNullString(depid)))//办公室人员
		{
			resultMap = getAllMaintainer();
		}
		else
		{
			resultMap = getUserMap(roleid, depid);
		}

		StringBuffer selectSb = new StringBuffer("");
		if (resultMap == null || resultMap.size() < 1) {
			return "<select class=\"form-control\" id=\"" + id + "\" name=\"" + id + "\"><option value=\"\"></option></select>";
		} else {
			selectSb.append("<select ").append(checktype)
					.append(" class=\"form-control\" id=\"").append(id)
					.append("\" name=\"").append(id).append("\">");
			if(needBlank)
			{
				selectSb.append("<option value=\"\"></option>");
			}
			
			Iterator<Map.Entry> entries = resultMap
					.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = entries.next();
				String keyValue = String.valueOf(entry.getKey());
				String valueValue = String.valueOf(entry.getValue());
				
				String isSelected = currentValue.equals(String
						.valueOf(keyValue)) ? "selected=\"true\"" : "";

				selectSb.append("<option value=\"").append(keyValue)
						.append("\" ").append(isSelected).append(">")
						.append(valueValue).append("</option>");
			}
			
			selectSb.append("</select>");
			return selectSb.toString();
		}
	}
	
	private Map getUserMap(String roleid, String depid) {
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);

		Map resultMap = userApi.getUserMapByRoleidAndDepid(roleid,depid);//roleID hardcoding，维修部维修员
		if (resultMap == null || resultMap.size() < 1) {
			return new HashMap(0);
		} else {
			return resultMap;
		}
	}
	
	private Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return new HashMap(0);
		}

		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}
	
	class MapKeyComparator implements Comparator<String>{

		public int compare(String str1, String str2) {
			
			return str1.compareTo(str2);
		}
	}
	
	public int getMapValueByKey(String key, Map map)
	{
		if(map==null || "".equals(StringUtil.isNullString(key)))
		{
			return 0;
		}
		else
		{
			Object obj = map.get(key);
			if(obj==null)
			{
				return 0;
			}
			else
			{
				return Integer.valueOf(String.valueOf(obj));
			}
		}
	}
	
	public double getMapValueByKeyByDouble(String key, Map map)
	{
		if(map==null || "".equals(StringUtil.isNullString(key)))
		{
			return 0;
		}
		else
		{
			Object obj = map.get(key);
			if(obj==null)
			{
				return 0;
			}
			else
			{
				double tmpDouble = Double.valueOf(String.valueOf(obj));
				tmpDouble = Math.floor(tmpDouble*10)/10;
				return tmpDouble;
			}
		}
	}
	
	public String calculatePercent(int begin, int all)
	{
		if(all == 0)
		{
			return "0%";
		}
		double percent = begin*100/all;
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(percent)+"%";
	}
	
	public String calculatePercentByDouble(double begin, double all)
	{
		if(all == 0)
		{
			return "0%";
		}
		double percent = begin*100/all;
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(percent)+"%";
	}
	
	public String calculateBglyhByDouble(double begin, double all)
	{
		if(all == 0)
		{
			return "0";
		}
		double percent = begin*100/all;
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(percent);
	}
	
	/**
	 * 
	 * @param type
	 * @param id
	 * @param currentValue
	 * @param checktype
	 * @param needBlank
	 * @param depid
	 * @return
	 */
	public String generateCarSelect(String type, String id, String currentValue,
			String checktype, boolean needBlank, String depid) {
		StringBuffer sb = new StringBuffer("");
		Map resultMap = sortMapByKey(getCarMap(depid));

		StringBuffer selectSb = new StringBuffer("");
		if (resultMap == null || resultMap.size() < 1) {
			return "<select class=\"form-control\" id=\"" + id + "\" name=\"" + id + "\"><option value=\"\"></option></select>";
		} else {
			selectSb.append("<select ").append(checktype)
					.append(" class=\"form-control\" id=\"").append(id)
					.append("\" name=\"").append(id).append("\">");
			if(needBlank)
			{
				selectSb.append("<option value=\"\"></option>");
			}
			
			Iterator<Map.Entry> entries = resultMap
					.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = entries.next();
				String keyValue = String.valueOf(entry.getKey());
				String valueValue = String.valueOf(entry.getValue());
				
				String isSelected = currentValue.equals(String
						.valueOf(keyValue)) ? "selected=\"true\"" : "";

				selectSb.append("<option value=\"").append(keyValue)
						.append("\" ").append(isSelected).append(">")
						.append(valueValue).append("</option>");
			}
			
			selectSb.append("</select>");
			return selectSb.toString();
		}
	}
	
	private Map getCarMap(String depid)
	{
		Map resultMap = null;
//		IBimsWzglApi wzglApi = (IBimsWzglApi) getBean(IBimsWzglApi.class);
//		List carList = wzglApi.getAvailbleCarList(depid);
		List carList = null;
		if(carList!=null)
		{
			resultMap = new HashMap(carList.size());
			for(int i=0,n=carList.size(); i<n; i++)
			{
				FDFBBimsCarShare carVo = (FDFBBimsCarShare)carList.get(i);
				resultMap.put(carVo.getSeqno(), carVo.getPlatenumber());
			}
		}
		return resultMap;
	}
	
	/**
	 * 保留小数点后一位
	 * @param tmp
	 * @return
	 */
	public static String keepOnePoint(double tmp)
	{
		DecimalFormat df = new DecimalFormat("#");
		double a = Math.floor(tmp*10)/10;
		if(String.valueOf(a).endsWith("0"))
		{
			return df.format(Math.floor(tmp));
		}
		else
		{
			return String.valueOf(a);
		}
	}
	
	/**
	 * 保留小数点后2位
	 * @param tmp
	 * @return
	 */
	public static String keepTwoPoint(double tmp)
	{
		DecimalFormat df = new DecimalFormat("#");
		double a = Math.floor(tmp*100)/100;
		if(String.valueOf(a).endsWith("0"))
		{
			return df.format(Math.floor(tmp));
		}
		else
		{
			return String.valueOf(a);
		}
	}
	
	public String formatAuditlog(String auditlog)
	{
		auditlog = auditlog.replaceAll("class org.intsmaze.persistence.pojos.FDFBBimsBooth［", "话亭信息［");
		auditlog = auditlog.replaceAll("class org.intsmaze.persistence.pojos.FDFBBimsBoothChange［", "话亭变更申请［");
		auditlog = auditlog.replaceAll("Filenumber", "留档编号");
		auditlog = auditlog.replaceAll("Boothid", "话亭ID");
		auditlog = auditlog.replaceAll("Areaid", "区局");
		auditlog = auditlog.replaceAll("Regions", "行政区");
		auditlog = auditlog.replaceAll("Periodtime", "期数");
		auditlog = auditlog.replaceAll("Manufacturer", "生产厂家");
		auditlog = auditlog.replaceAll("Boothaddress", "话亭地址");
		auditlog = auditlog.replaceAll("Boothstyle", "话亭样式");
		auditlog = auditlog.replaceAll("Phonenumber1", "电话号码1");
		auditlog = auditlog.replaceAll("Phonenumber2", "电话号码2");
		auditlog = auditlog.replaceAll("Phonetype1", "话机型号1");
		auditlog = auditlog.replaceAll("Phonetype2", "话机型号2");
		auditlog = auditlog.replaceAll("Electricboxadd", "电箱地址");
		auditlog = auditlog.replaceAll("Electricboxid", "电箱id");
		auditlog = auditlog.replaceAll("Belongstation", "所属供电所");
		auditlog = auditlog.replaceAll("Billaccount", "账单户号");
		auditlog = auditlog.replaceAll("Cleaningcompany", "保洁单位");
		auditlog = auditlog.replaceAll("Addequipment", "附加设备");
		auditlog = auditlog.replaceAll("Roaddistribut", "环线");
		auditlog = auditlog.replaceAll("Boothstate", "话亭状态");
		
		auditlog = auditlog.replaceAll("Newfilenumber", "新留档编号");
		auditlog = auditlog.replaceAll("Newboothid", "新话亭ID");
		auditlog = auditlog.replaceAll("Newareaid", "新区局");
		auditlog = auditlog.replaceAll("Newregions", "新行政区");
		auditlog = auditlog.replaceAll("Newperiodtime", "新期数");
		auditlog = auditlog.replaceAll("Newmanufacturer", "新生产厂家");
		auditlog = auditlog.replaceAll("Newboothaddress", "新话亭地址");
		auditlog = auditlog.replaceAll("Newboothstyle", "新话亭样式");
		auditlog = auditlog.replaceAll("Newphonenumber1", "新电话号码1");
		auditlog = auditlog.replaceAll("Newphonenumber2", "新电话号码2");
		auditlog = auditlog.replaceAll("Newphonetype1", "新话机型号1");
		auditlog = auditlog.replaceAll("Newphonetype2", "新话机型号2");
		auditlog = auditlog.replaceAll("Newelectricboxadd", "新电箱地址");
		auditlog = auditlog.replaceAll("Newelectricboxid", "新电箱id");
		auditlog = auditlog.replaceAll("Newbelongstation", "新所属供电所");
		auditlog = auditlog.replaceAll("Newbillaccount", "新账单户号");
		auditlog = auditlog.replaceAll("Newcleaningcompany", "新保洁单位");
		auditlog = auditlog.replaceAll("Newaddequipment", "新附加设备");
		auditlog = auditlog.replaceAll("Newroaddistribut", "新环线");
		auditlog = auditlog.replaceAll("Newboothstate", "新话亭状态");
		
		auditlog = auditlog.replaceAll("Status", "状态");
		
		return auditlog;
	}
	
	public String getMinus(double a, double b)
	{
		return keepTwoPoint(a-b);
	}
	
	public String getMultiply(String a, String b)
	{
		try
		{
			double bb = Double.parseDouble(b);
			double aa = Double.parseDouble(a);
			return keepTwoPoint(aa*bb);
		}
		catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
	}
	
	public String getAdd(String a, String b)
	{
		try{
			double aa = Double.parseDouble(a);
			double bb = Double.parseDouble(b);
			return keepTwoPoint(aa+bb);
		}catch (Exception e) {
			// TODO: handle exception
			return keepTwoPoint(0);
		}
	}
	
	private Map getAllMaintainer() {
		IUserApi userApi = (IUserApi) getBean(IUserApi.class);

		Map resultMap = userApi.getAllMaintainerAndSgry();//获取所有维修人员和施工人员
		if (resultMap == null || resultMap.size() < 1) {
			return new HashMap(0);
		} else {
			return resultMap;
		}
	}
}
