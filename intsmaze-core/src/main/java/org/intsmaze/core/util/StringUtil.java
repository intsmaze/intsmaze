package org.intsmaze.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

public class StringUtil {
	private static final String DOUBLESPLITFORMAT = "###,###";
	public static final String SPLIT = ",";

	/**
	 * 将字符串进行转码
	 * 
	 * @param name
	 * @param srccode
	 * @param destcode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String transcoding(String name, String srccode,
			String destcode) throws UnsupportedEncodingException {
		if (!StringUtil.isValidateString(name)
				|| !StringUtil.isValidateString(srccode)
				|| !StringUtil.isValidateString(destcode)) {
			return "";
		}
		return new String(name.getBytes(srccode), destcode);
	}

	/**
	 * 字符串数组合并
	 * 
	 * @param stringAry
	 *            字符串数组
	 * @param joinToken
	 *            分隔符
	 * @return 合并后的字符串
	 */
	public static String join(String[] stringAry, String joinToken) {
		if (stringAry == null || stringAry.length <= 0) {
			return null;
		}
		if (!isValidateString(joinToken)) {
			joinToken = "";
		}
		joinToken = joinToken.trim();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < stringAry.length; i++) {
			if (i > 0) {
				sb.append(" " + joinToken + " ");
			}
			sb.append(" ? ");
		}
		return sb.toString();
	}

	/**
	 * 字符串数组合并
	 * 
	 * @param stringAry
	 *            字符串数组
	 * @param joinToken
	 *            分隔符
	 * @return 合并后的字符串
	 */
	public static String join(String[] stringAry, String joinToken,
			String prefix, String suffix) {
		if (stringAry == null || stringAry.length <= 0) {
			return checkString(prefix) + checkString(suffix);
		}
		if (!isValidateString(joinToken)) {
			joinToken = "";
		}
		joinToken = checkString(joinToken);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < stringAry.length; i++) {
			if (i > 0) {
				sb.append(" " + joinToken + " ");
			}
			sb.append(checkString(prefix) + checkString(stringAry[i])
					+ checkString(suffix));
		}
		return sb.toString();
	}

	/**
	 * 字符串数组合并
	 * 
	 * @param stringAry
	 *            字符串数组
	 * @param joinToken
	 *            分隔符
	 * @return 合并后的字符串
	 */
	public static String join(Integer[] intAry, String joinToken) {
		if (intAry == null || intAry.length <= 0) {
			return null;
		}
		if (!isValidateString(joinToken)) {
			joinToken = "";
		}
		joinToken = joinToken.trim();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < intAry.length; i++) {
			if (i > 0) {
				sb.append(" " + joinToken + " ");
			}
			sb.append(" " + intAry[i] + " ");
		}
		return sb.toString();
	}

	/**
	 * 字符串数组合并
	 * 
	 * @param stringAry
	 *            字符串数组
	 * @param joinToken
	 *            分隔符
	 * @return 合并后的字符串
	 */
	public static String join(Double[] intAry, String joinToken) {
		if (intAry == null || intAry.length <= 0) {
			return null;
		}
		if (!isValidateString(joinToken)) {
			joinToken = "";
		}
		joinToken = joinToken.trim();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < intAry.length; i++) {
			if (i > 0) {
				sb.append(" " + joinToken + " ");
			}
			sb.append(" " + intAry[i] + " ");
		}
		return sb.toString();
	}

	/**
	 * 字符串数组合并
	 * 
	 * @param stringAry
	 *            字符串数组
	 * @param joinToken
	 *            分隔符
	 * @return 合并后的字符串
	 */
	public static String join(Float[] intAry, String joinToken) {
		if (intAry == null || intAry.length <= 0) {
			return null;
		}
		if (!isValidateString(joinToken)) {
			joinToken = "";
		}
		joinToken = joinToken.trim();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < intAry.length; i++) {
			if (i > 0) {
				sb.append(" " + joinToken + " ");
			}
			sb.append(" " + intAry[i] + " ");
		}
		return sb.toString();
	}

	/**
	 * 将整数字符串转化为整数
	 * 
	 * @param str
	 *            数字字符串
	 * @return 转化后的Int类型
	 * @throws NumberFormatException
	 */
	public static int convertInteger(String str) throws NumberFormatException {
		int number = 0;
		if (!isValidateString(str)) {
			return number;
		}
		str = str.trim();
		try {
			number = Integer.parseInt(str);
		} catch (NumberFormatException e) {

			number = 0;
			// throw e;
		}
		return number;
	}

	public static int convertInteger(String str, int defaultVal)
			throws NumberFormatException {
		if (!isValidateString(str)) {
			return defaultVal;
		}
		str = str.trim();
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {

		}
		return defaultVal;
	}

	/**
	 * 将浮点数的字符串转化为浮点数
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return 转化后的浮点数
	 * @throws NumberFormatException
	 */
	public static float convertFloat(String str) throws NumberFormatException {
		float number = 0.0f;
		if (!isValidateString(str)) {
			return number;
		}
		str = str.trim();
		try {
			number = Float.parseFloat(str);
		} catch (NumberFormatException e) {
			number = 0.0f;

		}
		return number;
	}

	/**
	 * 将双精度浮点数的字符串转化为双精度浮点数
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return 转化后的值
	 * @throws NumberFormatException
	 */
	public static double convertDouble(String str) throws NumberFormatException {
		double number = 0.0;
		if (!isValidateString(str)) {
			return number;
		}
		str = str.trim();
		str = str.replaceAll(SPLIT, "");
		try {
			number = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			number = 0.0;

		}
		return number;
	}

	public static long convertLong(String str) throws NumberFormatException {
		long number = 0l;
		if (!isValidateString(str)) {
			return number;
		}
		str = str.trim();
		try {
			number = Long.parseLong(str);
		} catch (NumberFormatException e) {
			number = 0l;
		}
		return number;
	}

	public static boolean convertBoolean(String str)
			throws NumberFormatException {
		boolean number = false;
		if (!isValidateString(str)) {
			return number;
		}
		str = str.trim();
		try {
			number = Boolean.parseBoolean(str);
		} catch (NumberFormatException e) {
			number = false;
		}
		return number;
	}

	public static short convertShort(String str) throws NumberFormatException {
		short number = 0;
		if (!isValidateString(str)) {
			return number;
		}
		str = str.trim();
		try {
			number = Short.parseShort(str);
		} catch (NumberFormatException e) {
			number = 0;
		}
		return number;
	}

	public static byte convertByte(String str) throws NumberFormatException {
		byte number = 0;
		if (!isValidateString(str)) {
			return number;
		}
		str = str.trim();
		try {
			number = Byte.parseByte(str);
		} catch (NumberFormatException e) {
			number = 0;
		}
		return number;
	}

	/**
	 * 验证字符串合法性（即该字符串不可以为null） 该方法不除去字符串中空格
	 * 
	 * @param str
	 *            String
	 * @return 如果为空就返回""
	 */
	public static String checkString(String str) {
		if (str == null || str.equalsIgnoreCase("null")) {
			return "";
		}
		/**
		 * 除去前后的空格
		 */
		return str.trim();
	}

	public static String checkStringForExcel(String str) {
		if (str == null || str.equalsIgnoreCase("null")) {
			return "";
		}
		if ("0E-8".equals(str)) {
			return "0";
		}
		/**
		 * 除去前后的空格
		 */
		return str.trim();
	}

	public static String checkBlankString(String str) {
		if (str == null || str.equalsIgnoreCase("null")
				|| str.equalsIgnoreCase("-2")) {
			return "";
		}
		/**
		 * 除去前后的空格
		 */
		return str.trim();
	}

	/**
	 * 验证字符串合法性（该字符串可以为null） 该方法不除去字符串中空格
	 * 
	 * @param str
	 *            String
	 * @return 如果为空就返回""
	 */
	public static String validateString(String str) {
		return checkString(str);
	}

	/**
	 * 验证是否是个有效字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidateString(String str) {
		if (str == null) {
			return false;
		}
		if (str.trim().equals("")) {
			return false;
		}
		if (str.equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}

	public static boolean isValidateBlankString(String str) {
		if (str == null) {
			return false;
		}
		if (str.trim().equals("")) {
			return false;
		}
		if (str.trim().equals("-2")) {
			return false;
		}
		if (str.trim().equals("''")) {
			return false;
		}
		if (str.equalsIgnoreCase("null")) {
			return false;
		}
		return true;
	}

	/**
	 * 从当前字符串中某个字符开始截取字符串
	 * 
	 * @param str
	 *            String
	 * @param singleChar
	 *            String字符标记
	 * @param front
	 *            boolean 如果值为true表式截取标记字符前面的字符，反之相反
	 * @return 截取后字符串
	 */
	public static String cutString(String str, String singleChar, boolean front) {
		if (!isValidateString(str)) {
			return "";
		}
		if (!isValidateString(singleChar)) {
			return "";
		}
		str = str.trim();
		singleChar = singleChar.trim();
		if (str.contains(singleChar)) {
			int index = str.indexOf(singleChar);
			if (front) {
				return str.substring(0, index);
			} else {
				return str.substring(index + 1, str.length());
			}
		} else {
			return str;
		}
	}

	/**
	 * 将数据库中取出以字符串形式表示的日期 转化为：1999-09-09日期格式形式
	 * 
	 * @param dbStr
	 *            String
	 * @return String 转化后的字符串
	 */
	public static String toDateString(String dbStr) {

		if (!isValidateString(dbStr)) {
			return "";
		}
		dbStr = dbStr.trim();
		return dbStr.substring(0, 10);
	}

	/**
	 * 将日期转化为字符串输出
	 * 
	 * @param dbStr
	 * @return
	 */
	public static String toDateString(java.util.Date date) {
		if (date == null) {
			return "";
		}
		String dStr = "";
		String format = "yyyy-MM-dd hh:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		dStr = sdf.format(date);
		dStr = dStr.trim();
		return dStr.substring(0, 10);
	}

	/**
	 * 将字符串一个字母转换为大写
	 * 
	 * @param name
	 * @return
	 */
	public static String toUpperCaseFirstLetter(String name) {
		if (!isValidateString(name)) {
			return "";
		}
		name = name.trim();
		if (name.length() > 0) {
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		return "";
	}

	/**
	 * 将JavaBean属性名转化为其set和get方法名称 其转化规则如下：（1）属性名的第二个字母若为大写则不用将首字母转换为大写
	 * 
	 * @param prppertyName
	 * @return
	 */
	public static String toJavaBeanPropertyForSetOrGetMethodName(
			String propertyName) {
		if (!isValidateString(propertyName)) {
			return "";
		}
		propertyName = propertyName.trim();
		if (propertyName.length() <= 1) {
			return propertyName.toUpperCase();
		} else {
			char secondLetter = propertyName.charAt(1);
			if (secondLetter >= 'A' && secondLetter <= 'Z') {
				return propertyName;
			} else {
				return toUpperCaseFirstLetter(propertyName);
			}
		}
	}

	/**
	 * 将日期字符串转换为日期对象
	 * 
	 * @param dateString
	 * @return
	 * @throws Exception
	 */
	public static Date toStringForDate(String dateString) throws Exception {
		Date date = null;
		if (!isValidateString(dateString)) {
			throw new Exception("非日期格式字符串");
		}
		dateString = dateString.trim();
		DateFormat format = DateFormat.getDateInstance();

		try {
			date = format.parse(dateString);
		} catch (Exception e) {
			throw e;
		}
		return date;
	}

	/**
	 * 验证字符串是不是完全有数字和小数点组成 此方法还不可以验证16进制和八进制的数据
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isNumberString(String number) {

		if (!isValidateString(number)) {
			return false;
		}
		number = number.trim();
		char[] nums = number.toCharArray();
		for (int i = 0; i < nums.length; i++) {
			if ((nums[i] >= '0' && nums[i] <= '9') || (nums[i] == '.')) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证字符串是不是完全有数字组成
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isCompleteNumberString(String number) {

		if (!isValidateString(number)) {
			return false;
		}
		number = number.trim();
		char[] nums = number.toCharArray();
		for (int i = 0; i < nums.length; i++) {
			if ((nums[i] < '0' || nums[i] > '9')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证日期格式字符串是否和指定样式匹配
	 * 
	 * @param date
	 * @param style
	 *            指定样式
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidateDateString(String dateStr, String style)
			throws Exception {
		if (!isValidateString(style)) {
			return false;
		}
		if (!isValidateString(dateStr)) {
			return false;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(style);
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			throw e;
		}
		if (date == null) {
			return false;
		}
		return true;
	}

	/**
	 * 转化
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getDefaultDateString(String dateStr) throws Exception {

		if (!isValidateString(dateStr)) {
			return "";
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			throw e;
		}
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 获得string 数组长度
	 * 
	 * @param str
	 * @return
	 */
	public static int getStringArrayLength(String[] str) {
		if (!isValidateStringArray(str)) {
			return 0;
		}
		return str.length;
	}

	/**
	 * 从字符串数组中安全获得数组中某个元素的值(String 类型)
	 * 
	 * @param str
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public static String getStringArrayValue(String[] str, int index)
			throws Exception {
		if (!isValidateStringArray(str)) {
			return "";
		}
		if (index < 0 || index >= getStringArrayLength(str)) {
			throw new Exception("数组下标越界");
		}

		return str[index].trim();
	}

	/**
	 * 从字符串数组中安全获得数组中某个元素的值(Integer 类型)
	 * 
	 * @param str
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public static int getStringArrayIntValue(String[] str, int index)
			throws Exception {
		if (!isValidateStringArray(str)) {
			return 0;
		}
		if (index < 0 || index >= getStringArrayLength(str)) {
			throw new Exception("数组下标越界");
		}
		int value = 0;
		value = Integer.parseInt(str[index].trim());
		return value;
	}

	/**
	 * 从字符串数组中安全获得数组中某个元素的值(Double 类型)
	 * 
	 * @param str
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public static double getStringArrayDoubleValue(String[] str, int index)
			throws Exception {
		if (!isValidateStringArray(str)) {
			return 0;
		}
		if (index < 0 || index >= getStringArrayLength(str)) {
			throw new Exception("数组下标越界");
		}
		double value = 0;
		value = Double.parseDouble(str[index].trim());
		return value;
	}

	/**
	 * 验证是否是个有效的字符串数组
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidateStringArray(String[] str) {
		if (str == null || str.length <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 将数字转换为字符串
	 * 
	 * @param number
	 * @return
	 */
	public static String checkIntString(Integer number) {
		return String.valueOf(number);
	}

	/**
	 * 将数字转换为字符串
	 * 
	 * @param number
	 * @return
	 */
	public static String checkLongString(Long number) {
		return String.valueOf(number);
	}

	/**
	 * 将浮点数转化为浮点数
	 * 
	 * @param number
	 * @return
	 */
	public static String checkDoubleString(Double number) {
		if (number == 0) {
			return "";
		}
		return String.valueOf(number);
	}

	public static Double checkDouble(Object number) {
		if (number == null) {
			return null;
		}
		return Double.valueOf(number.toString());
	}

	/**
	 * <p>
	 * 将数字转换含有0的数字字符串
	 * </p>
	 * <p>
	 * 例如：将"1"转换4位数字字符串,结果为:"0001"
	 * </p>
	 * 
	 * @param number
	 * @param digit
	 * @return
	 */
	public static String getStringNum(int number, int digit) {
		int c = number + 1;

		int n = getNumberDigit(c);

		if (n >= digit) {
			return StringUtil.checkIntString(c);
		} else {
			String temp = "";
			for (int i = 0; i < (digit - n); i++) {
				temp += "0";
			}
			return temp + c;
		}
	}

	/**
	 * <p>
	 * 获得一个整数位数
	 * </p>
	 * 
	 * @param number
	 * @return
	 */
	public static int getNumberDigit(int number) {
		int i = 1;
		while (number >= 10) {
			i++;
			number = number / 10;
		}
		return i;
	}

	/**
	 * 获得当期系统日期字符串形式
	 * 
	 * @return
	 */
	public static String getDefaultDateString() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 从完整文件路径中获得文件名和该文件所在文件夹
	 * 
	 * @param cfPath
	 * @return
	 */
	public static String getFnameAndPathFromCompleteFilePath(String cfPath,
			int type) {
		if (!isValidateString(cfPath)) {
			return "";
		}
		int sept = cfPath.lastIndexOf("\\\\");
		if (sept == -1) {
			sept = cfPath.lastIndexOf("/");
		}
		String str = "";
		if (type <= 0) {
			str = cfPath.substring(0, sept + 1);
		} else {
			str = cfPath.substring(sept + 1, cfPath.length());
		}
		return str;
	}

	/**
	 * 将时间字符串时间除去
	 * 
	 * @return
	 */
	public static String cutDateString(String dateStr) {
		if (!isValidateString(dateStr)) {
			return "";
		}
		return dateStr.substring(0, 10);
	}

	/**
	 * 给字符串加个后缀
	 * 
	 * @param suffix
	 * @return
	 */
	public static String addSuffixString(String source, String suffix,
			boolean before) {
		if (!isValidateString(source)) {
			return "";
		}
		if (!isValidateString(source)) {
			return source.trim();
		}
		if (before) {
			return suffix.trim() + " " + source.trim();
		} else {
			return source.trim() + " " + suffix.trim();
		}
	}

	/**
	 * java 正则表达式验证浮点数
	 * 
	 * @param str
	 */
	public static Object validateDoubleNumString(String str) {
		if (!isValidateString(str)) {
			return "0";
		}
		String patternStr = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0|\\d*)$";
		boolean b = Pattern.matches(patternStr, str);
		if (b) {
			return str;
		}
		return "0";
	}

	/**
	 * 插入英文空白字符
	 * 
	 * @param number
	 * @return
	 */
	public static String appendSpace(int number) {
		if (number <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < number; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * 验证数字,如果无效则提供默认值
	 * 
	 * @param itCount
	 * @param value
	 */
	public static int vidateInteger(String itCount, int value) {
		int temp = 0;
		try {
			temp = Integer.parseInt(itCount);
		} catch (NumberFormatException e) {
			temp = value;
		}
		return temp;
	}

	/**
	 * Decode hex string
	 * 
	 * @param hexString
	 * @return
	 */
	public static String HexDecode(String hexString) {
		String str = null;
		if (hexString != null && hexString.length() > 0) {
			String digital = "0123456789ABCDEF";
			char[] hex2char = hexString.toCharArray();
			byte[] bytes = new byte[hexString.length() / 2];
			int temp;
			for (int i = 0; i < bytes.length; i++) {
				temp = digital.indexOf(hex2char[2 * i]) * 16;
				temp += digital.indexOf(hex2char[2 * i + 1]);
				bytes[i] = (byte) (temp & 0xff);
			}
			try {
				str = new String(bytes, Constant.ENCODING_UTF_8);
			} catch (Exception e) {
			}
		}
		return str;
	}

	/**
	 * 验证长整数,如果无效则提供默认值
	 * 
	 * @param itCount
	 * @param value
	 * @return
	 */
	public static long vidateLong(String itCount, long value) {
		long temp = 0;
		try {
			temp = Integer.parseInt(itCount);
		} catch (NumberFormatException e) {
			temp = value;
		}
		return temp;
	}

	public static int str2int(String str) {
		if (str == null)
			return 0;
		try {
			return Integer.parseInt(str);
		} catch (Exception _ex) {
			return 0;
		}
	}

	public static String str2double(Double dbValue) {
		DecimalFormat format = new DecimalFormat("0.00000");
		return format.format(dbValue);
	}

	/**
	 * 这个方法仅仅验证是否非空
	 * 
	 * @param str
	 * @return
	 */
	public static String isNullString(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * 获得字符串第一个字母
	 * 
	 * @return
	 */
	public static String getFirstLetter(String str, String strDefaultValue) {
		if (!isValidateString(str)) {
			return isValidateString(strDefaultValue) ? strDefaultValue
					.substring(0, 1) : "";
		}
		return str.substring(0, 1);
	}

	/**
	 * sql查询条件前缀
	 * 
	 * @return
	 */
	public static String getConditionPrefix() {
		return " 1=1 ";
	}

	/**
	 * 判断两个字符串是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEqualString(String str1, String str2) {
		if (validateString(str1).equalsIgnoreCase(validateString(str2))) {
			return true;
		}

		return false;
	}

	/**
	 * 将strTemp以strCut结束的字符串删除
	 * 
	 * @param strTemp
	 * @param strCut
	 * @return
	 */
	public static String cutEnd(String strTemp, String strCut) {
		String b = "";
		if ((strTemp != null) && (!(strTemp.trim().equals("")))
				&& (!(strTemp.trim().equalsIgnoreCase("null")))) {
			if ((strTemp.substring(strTemp.length() - 1)).equals(strCut)) {
				b = strTemp.substring(0, strTemp.length() - 1);
			} else {
				b = strTemp;
			}
		}
		return b;
	}

	/**
	 * 将strTemp以strCut开头的字符串删除
	 * 
	 * @param strTemp
	 * @param strCut
	 * @return
	 */
	public static String cutBegin(String strTemp, String strCut) {
		String b = "";
		if ((strTemp != null) && (!(strTemp.trim().equals("")))
				&& (!(strTemp.trim().equalsIgnoreCase("null")))) {
			if ((strTemp.substring(0, strCut.length())).equals(strCut)) {
				b = strTemp.substring(strCut.length(), strTemp.length());
			} else {
				b = strTemp;
			}
		}
		return b;
	}

	/**
	 * 将String以","格开的转换成以单引号和逗号隔开的string a,ac,ac,a,a,aaaa 转成
	 * 'a','ac','ac','a','a','aaaa'
	 * 
	 * @param convertStr
	 * @return
	 */
	public static String convertStringToSQLString(String convertStr) {
		String tmpString = null;
		if ((convertStr != null) && (!(convertStr.trim().equals("")))) {
			String[] convertArray = convertStr.split(",");
			for (int i = 0; i < convertArray.length; i++)

			{
				if (convertArray[i] != null)
					if (tmpString == null)
						tmpString = "'" + convertArray[i] + "'";
					else
						tmpString = tmpString + "'" + convertArray[i] + "'";
				if (i < convertArray.length - 1)
					tmpString = tmpString + ",";
			}

		} else {
			tmpString = "''";
		}
		return tmpString;

	}

	/**
	 * 将string转化成like形式
	 * 
	 * @param convertStr
	 * @return
	 */
	public static String convertStringToSQLLike(String convertStr) {
		String tmpString = null;
		if (StringUtil.isValidateString(convertStr)) {
			tmpString = " '%" + convertStr + "%' ";
		} else {
			tmpString = "''";
		}
		return tmpString;
	}

	/**
	 * 截取浮点数
	 * 
	 * @param num
	 * @param format
	 *            按照指定样式 例如:".000"
	 * @return
	 */
	public static String truncateDouble(double num, String format) {
		if (!StringUtil.isValidateString(format)) {
			return "";
		}

		NumberFormat doubleFormat = new DecimalFormat(format);
		doubleFormat.setMaximumFractionDigits(8);
		doubleFormat.setMinimumFractionDigits(0);
		return doubleFormat.format(num);
	}

	/**
	 * 截取浮点数
	 * 
	 * @param num
	 * @param format
	 *            按照指定样式 例如:"0.000"
	 * @return
	 */
	public static String truncateDouble(double num, int median) {
		if (num == 0) {
			return "";
		}
		// double value=(new BigDecimal(num).setScale(
		// median, BigDecimal.ROUND_DOWN)).doubleValue();
		NumberFormat doubleFormat = new DecimalFormat(getNumberFormat(median));
		doubleFormat.setMaximumFractionDigits(median);
		doubleFormat.setMinimumFractionDigits(0);
		return doubleFormat.format(num);
	}

	/**
	 * 
	 * @保留小数点位数
	 * @param median
	 * @return
	 */
	public static String keepDouble(double num, int median) {
		if (num == 0) {
			return "";
		}

		DecimalFormat df = new DecimalFormat("#,##0.000");
		return df.format(num);
	}

	public static String truncateSplitDouble(double num, int median) {
		if (num == 0) {
			return "";
		}
		DecimalFormat doubleFormat = new DecimalFormat(DOUBLESPLITFORMAT
				+ getSharpNumberFormat(median));
		return doubleFormat.format(num);
	}

	/**
	 * 截取浮点数
	 * 
	 * @param num
	 * @param format
	 *            按照指定样式 例如:"0.000"
	 * @return
	 */
	public static String truncateDoubleScript(double num, double num2,
			int median) {
		if (num == 0 || num2 == 0) {
			return "''";
		}
		// double value=(new BigDecimal(num).setScale(
		// median, BigDecimal.ROUND_DOWN)).doubleValue();
		NumberFormat doubleFormat = new DecimalFormat(getNumberFormat(median));
		doubleFormat.setMaximumFractionDigits(median);
		doubleFormat.setMinimumFractionDigits(0);
		return doubleFormat.format(num / num2);
	}

	/**
	 * 截取浮点数
	 * 
	 * @param num
	 * @param format
	 *            按照指定样式 例如:"0.000"
	 * @return
	 */
	public static String truncateDouble(double num, int size, int median) {
		if (num == 0) {
			return "";
		}
		// double value=(new BigDecimal(num).setScale(
		// median, BigDecimal.ROUND_DOWN)).doubleValue();
		NumberFormat doubleFormat = new DecimalFormat(getNumberFormat(median));
		doubleFormat.setMaximumFractionDigits(median);
		doubleFormat.setMinimumFractionDigits(0);
		if (num > 1024 * 1024) {
			return doubleFormat.format(num / (size * size)) + "MB";
		} else {
			return doubleFormat.format(num / (size)) + "KB";
		}
	}

	private static String getNumberFormat(int median) {
		// if (median <= 0) {
		// return "0";
		// }
		StringBuffer sb = new StringBuffer();
		sb.append("0.");
		for (int i = 0; i < median; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	private static String getSharpNumberFormat(int median) {
		// if (median <= 0) {
		// return "0";
		// }
		StringBuffer sb = new StringBuffer();
		sb.append(".");
		for (int i = 0; i < median; i++) {
			sb.append("#");
		}
		return sb.toString();
	}

	/**
	 * 获得文件名（进使用于windows系统）
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		if (!isValidateString(path)) {
			return "";
		}
		path = StringUtil.checkString(path);
		return path.substring(path.lastIndexOf("\\") + 1);
	}

	/**
	 * 获得文件名后缀名
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileSuffix(String path) {
		if (!isValidateString(path)) {
			return "";
		}
		path = StringUtil.checkString(path);
		return path.substring(path.lastIndexOf(".") + 1);
	}

	/**
	 * 将字符串集合转化为字符串数组
	 * 
	 * @param list
	 * @return
	 */
	public static String[] listToStringArray(List<String> list) {
		if (list == null || list.size() <= 0) {
			return new String[0];
		}
		String[] arr = new String[list.size()];
		int i = 0;
		for (String str : list) {
			arr[i] = str;
			i++;
		}
		return arr;
	}

	/**
	 * hashmap转成string
	 * 
	 * @param inMap
	 * @param strPrefix
	 * @return
	 */
	public static String map2Str(HashMap<String, String> inMap, String strPrefix) {
		String strReturn = "";
		if ((inMap != null)) {
			Set entrys = inMap.entrySet();
			Iterator it = entrys.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (StringUtil.isValidateString((String) entry.getKey())) {
					strReturn = strReturn
							+ strPrefix
							+ (StringUtil.validateString((String) entry
									.getKey()));
				}
			}
		}
		// return cutBegin(cutEnd(strReturn, strPrefix), strPrefix);
		return cutEnd(strReturn, strPrefix);
	}

	/**
	 * 取得hashmap的长度
	 * 
	 * @param inMap
	 * @return
	 */
	public static int getHashMapLength(HashMap<String, String> inMap) {
		int i = 0;
		if ((inMap != null)) {
			Set entrys = inMap.entrySet();
			Iterator it = entrys.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (StringUtil.isValidateString((String) entry.getKey())) {
					i++;
				}
			}
			return i;
		} else {
			return 0;
		}
	}

	/**
	 * 判断两个map是否相同
	 * 
	 * @param fromMap
	 * @param toMap
	 * @return
	 */
	public static boolean compareHashMap(HashMap<String, String> fromMap,
			HashMap<String, String> toMap) {
		boolean bReturn = false;
		if (getHashMapLength(fromMap) != getHashMapLength(toMap)) {
			return false;
		} else {
			Set entrys = fromMap.entrySet();
			Iterator itFrom = entrys.iterator();
			while (itFrom.hasNext()) {
				Map.Entry entryFrom = (Map.Entry) itFrom.next();
				if ((fromMap.get((String) entryFrom.getKey())) != null) {
					toMap.remove(entryFrom.getKey());
				}
			}
		}
		if ((toMap.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将数据库日期转化为时间 格式为：“yyyy-mm-dd hh:mm:ss”
	 * 
	 * @param date
	 * @return
	 */
	// public static String DateToString(java.sql.Date date, boolean datetime) {
	// if (date == null) {
	// date = new java.sql.Date(System.currentTimeMillis());
	// }
	// if (datetime) {
	// return DateUtil.getDateTimeFormat().format(
	// new java.sql.Date(date.getTime()));
	// } else {
	// return DateUtil.getDateFormat().format(date);
	// }
	// }
	/**
	 * 将数据库日期转化为时间 格式为：“yyyy-mm-dd hh:mm:ss”
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToString(java.sql.Date date, String pattern) {
		if (date == null) {
			date = new java.sql.Date(System.currentTimeMillis());
		}
		if (!StringUtil.isValidateString(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		return DateUtil.dateToString(date, pattern);
	}

	/**
	 * 将数据库日期转化为时间 格式为：“yyyy-mm-dd hh:mm:ss”
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToString(java.sql.Timestamp date, String pattern) {
		if (date == null) {
			date = new java.sql.Timestamp(System.currentTimeMillis());
		}
		if (!StringUtil.isValidateString(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		return DateUtil.dateToString(date, pattern);
	}

	/**
	 * 将数据库日期转化为时间 格式为：“yyyy-mm-dd hh:mm:ss”
	 * 
	 * @param date
	 * @return
	 */
	// public static String DateToString(java.util.Date date, boolean datetime)
	// {
	// if (date == null) {
	// date = new java.util.Date();
	// }
	// if (datetime) {
	// return DateUtil.getDateTimeFormat().format(date);
	// } else {
	// return DateUtil.getDateFormat().format(date);
	// }
	// }
	/**
	 * 截取浮点数
	 * 
	 * @param num
	 * @param format
	 *            按照指定样式
	 * @return
	 */
	public static String truncateDouble(double num, int size, String format) {
		if (!StringUtil.isValidateString(format)) {
			return "";
		}
		NumberFormat doubleFormat = new DecimalFormat(format);

		return doubleFormat.format(num / size);
	}

	/**
	 * 将数据库日期转化为时间 格式为：“yyyy-mm-dd hh:mm:ss”
	 * 
	 * @param date
	 * @return
	 */
	public static String DateToString(String datestr) {
		if (!StringUtil.isValidateString(datestr)) {
			return "";
		}
		if (datestr.length() > 19) {
			return datestr.substring(0, 19);
		}
		return StringUtil.checkString(datestr);
	}

	/**
	 * 
	 * @param outPutStream
	 */
	public static InputStream getInputStream(ByteArrayOutputStream os) {
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}

	/**
	 * 将字符串以特定字符变成一个字符串数组
	 * 
	 * @param toStr
	 * @param split
	 * @return
	 */
	public static String[] toStringArray(String toStr, String split) {
		if (isValidateString(toStr)) {
			return toStr.split(split);
		} else {
			return null;
		}
	}

	/**
	 * 将字符串以特定字符变成一个字符串数组以后剔除重复的数据，然后返回字符串 例如 AAA, BBB, CCC, AAA, DDD
	 * 是一个字符串，返回AAA, BBB, CCC, DDD这个字符串
	 * 
	 * @param toStr
	 * @param split
	 * @return
	 */
	public static String removeDuplicateString(String toStr, String split) {
		String returnStr = "";
		if (isValidateString(toStr)) {
			String[] arrayStr = toStr.split(split);
			HashMap strMap = new HashMap<String, String>();
			if (arrayStr != null) {
				for (int i = 0; i < arrayStr.length; ++i) {
					strMap.put(arrayStr[i], arrayStr[i]);
				}
				if (strMap != null) {
					Set entrys = strMap.entrySet();
					Iterator it = entrys.iterator();
					while (it.hasNext()) {
						Map.Entry entry = (Map.Entry) it.next();
						if (StringUtil
								.isValidateString((String) entry.getKey())) {
							returnStr += (String) entry.getKey() + split;
						}
					}
				}
				return returnStr;
			} else {
				return returnStr;
			}

		} else {
			return returnStr;
		}
	}

	/**
	 * 获得产品单位
	 * 
	 * @param productType
	 * @return
	 */
	public static boolean getUnit(String productType) {
		return StringUtil.isEqualString(productType, "TTH2");

	}

	/**
	 * 拼装查询条件语句
	 * 
	 * @param cntNamesVals
	 * @return
	 */
	public static String getJQAutoCompleteCntSql(
			Map<String, String> cntNamesVals) {
		if (cntNamesVals == null && cntNamesVals.size() <= 0) {
			return "";
		}
		Set<String> keys = cntNamesVals.keySet();
		Iterator<String> it = keys.iterator();
		StringBuffer buf = new StringBuffer();
		while (it.hasNext()) {
			String key = it.next();
			if (StringUtil.isValidateString(key)) {
				String value = cntNamesVals.get(key);
				if (StringUtil.isValidateString(value)) {
					buf.append(" AND " + StringUtil.checkString(key) + " = '"
							+ StringUtil.checkString(value) + "' ");
				}
			}
		}
		return buf.toString();

	}

	/**
	 * 替换
	 * 
	 * @param strOrg
	 * @param strOld
	 * @param strNew
	 * @return
	 */
	public static String replaceStr(String strOrg, String strOld, String strNew) {
		return strOrg.replace(StringUtil.validateString(strOld),
				StringUtil.validateString(strNew));

	}

	/**
	 * 
	 * @param it
	 * @return
	 */
	public static ArrayList getArrayListFromIterator(Iterator it) {
		ArrayList mList = new ArrayList();
		while (it.hasNext()) {
			Object value = it.next();
			mList.add(value);
		}
		return mList;
	}

	public static String getSearchSubSql(String strAliasName,
			String strColumnName, String strInputValue) {
		String strReturn = "";
		if (!StringUtil.isValidateString(strAliasName)) {
			strAliasName = "a.";
		} else {
			strAliasName = strAliasName + ".";
		}
		if (StringUtil.isValidateString(strInputValue)) {
			String[] arrInputValue = strInputValue.split(" ");
			int i = 0;
			for (String sInputValue : arrInputValue) {
				i++;
				if (StringUtil.isValidateString(sInputValue)) {
					if (i > 1) {
						strReturn += " OR ";
					}
					strReturn += strAliasName
							+ StringUtil.validateString(strColumnName)
							+ " LIKE N'%"
							+ StringUtil.validateString(sInputValue) + "%' ";
				}
			}
		} else {
			strReturn = strAliasName + StringUtil.validateString(strColumnName)
					+ " LIKE ''";
		}
		return "(" + strReturn + ")";
	}

	public static String getTypeCodeByCode(String strCode) {
		if (!StringUtil.isValidateString(strCode)) {
			return "";
		}
		String[] codes = StringUtil.checkString(strCode).split("_");
		if (codes == null || codes.length <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		int k = 0;
		for (int i = 0; i < codes.length - 1; i++) {

			if (k > 0) {
				sb.append("_");
			}

			sb.append(codes[i]);
			k++;
		}
		return sb.toString();
	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String str = DecimalFormat.getNumberInstance().format(1245600000.0002);
		System.out.println("str：" + str);

		String currecy = NumberFormat.getCurrencyInstance().format(1245600000);

		System.out.println("转换成Currency格式：" + currecy);

		double d = 12000003456.78;
		// DecimalFormat df = new DecimalFormat("###,###.## ");
		System.out.println(String.format("%20.4f", d));

		System.out.println("***********************************8");
		String str1 = "附言测试附言测试附言测试附言测试附test言测试附言测试附言测试附言测试附言测试附言test测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言测试附言试附言测试附试附言测试附试附言测试附试附言附言试附试附附试附结束";
		System.out.println(getLengthWithDbccase(str1));
		str1 = leftWithDbccase(str1, 600);
		System.out.println(str1);
		System.out.println(getLengthWithDbccase(str1));
		// System.out.println(StringUtil.toJavaBeanPropertyForSetOrGetMethodName("aBc"));
		// if(StringUtil.isValidateDateString("19860912","yyyyMMdd")){
		// System.out.println("yes");
		// }else{
		// System.out.println("no");
		// }
		// String str=getFnameAndPathFromCompleteFilePath("ewfsfsd.jpg",1);
		// File f=new File(str);
		// if(!f.exists()){
		// f.mkdirs();
		// }
		// System.out.println(StringUtil.truncateDouble(99.12667, 2));
		// System.out
		// .println(getFileSuffix("C:\\Documents and
		// Settings\\bpsoft\\桌面\\ac_mmfileobj.xls"));
		// System.out.println(getStringNum(100002,5));

		// HashMap<String, String> fromMap = new HashMap<String, String>();
		// fromMap.put("1", "1");
		// fromMap.put("2", "2");
		// fromMap.put("3", "3");
		//
		// HashMap<String, String> toMap = new HashMap<String, String>();
		// toMap.put("1", "1");
		// toMap.put("2", "2");
		// toMap.put("3", "3");

		System.out.println(getRandColorCode());
	}

	/**
	 * 获得十六进制随机颜色码
	 * 
	 * @return
	 */
	public static String getRandColorCode() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;

		return r + g + b;
	}

	/**
	 * 
	 * @return
	 */
	public static String getFoeCastScriptCode(String elementID, String value) {
		StringBuffer buf = new StringBuffer();
		buf.append("<script type=\"text/javascript\">");
		buf.append("setTotalValue(" + checkString(elementID) + "," + value
				+ ")");
		buf.append("</script>");
		return buf.toString();
	}

	/**
	 * 
	 * @param strOldValue
	 * @param strNewValue
	 * @return
	 */
	public static String getStatisticCaption(String strOldValue,
			String strNewValue) {

		if (isEqualString(strOldValue, strNewValue)) {
			return "";
		} else {
			return validateString(strNewValue);
		}

	}

	public static boolean isIncludeStr(String mainStr, String subStr) {
		return mainStr.toUpperCase().contains(subStr.toUpperCase());
	}

	public static String removeSplitDouble(String str) {
		if (!isValidateString(str)) {
			return "";
		}
		return StringUtil.validateString(str).replaceAll(SPLIT, "");

	}

	/**
	 * 对double数据进行取精度.
	 * 
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数).
	 * @param roundingMode
	 *            精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static double roundDouble(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}

	public static String getDecimalFormat(int iZeroNum, Object dbAmount) {
		String sFormat = "#0";
		if (iZeroNum > 0) {
			sFormat = "";
			for (int i = 0; i < iZeroNum; i++) {
				sFormat = sFormat + "0";
			}
			sFormat = "#0." + sFormat;
		}
		java.text.DecimalFormat myformat = new java.text.DecimalFormat(sFormat);

		return myformat.format(dbAmount);
	}

	public static String handleChineseEncoding(String str) {
		String handleStr = "";
		if (isValidateString(str)) {

			try {
				// byte[] bytes = str.getBytes("UTF-8");
				// handleStr = String.valueOf(bytes);
				handleStr = new String(str.getBytes("ISO-8859-1"), Constant.ENCODING_UTF_8);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return handleStr;
	}

	/**
	 * 将string转化成in形式
	 * 
	 * @param convertStr
	 * @return
	 */
	public static String convertStringToSQLIN(String convertStr) {
		String tmpString = null;
		if (StringUtil.isValidateString(convertStr)) {
			tmpString = " (" + convertStr + ") ";
		} else {
			tmpString = "('')";
		}
		return tmpString;
	}

	/**
	 * 半角、全角字符判断
	 * 
	 * @param c
	 *            字符
	 * @return true：半角； false：全角
	 */
	public static boolean isDbcCase(char c) {
		// 基本拉丁字母（即键盘上可见的，空格、数字、字母、符号）
		if (c >= 32 && c <= 127) {
			return true;
		}
		// 日文半角片假名和符号
		else if (c >= 65377 && c <= 65439) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串长度取得（区分半角、全角）
	 * 
	 * @param str
	 *            字符串
	 * @return 字符串长度
	 */
	public static int getLengthWithDbccase(String str) {
		int len = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (isDbcCase(c)) { // 半角
				len = len + 1;
			} else { // 全角
				len = len + 3;
			}
		}
		return len;
	}

	/**
	 * 字符串截取（区分半角、全角）
	 * 
	 * @param str
	 *            字符串
	 * @param limit
	 *            长度
	 * @return
	 */
	public static String leftWithDbccase(String str, int limit) {
		if (getLengthWithDbccase(str) <= limit) {
			return str;
		}
		char[] chars = str.toCharArray();
		int charLenSum = 0;
		String result = "";
		for (int i = 0; i < chars.length; i++) {
			int charLen = isDbcCase(chars[i]) ? 1 : 3;
			if (charLenSum + charLen > limit) {
				return result;
			}
			charLenSum += charLen;
			result += chars[i];
			if (charLenSum == limit) {
				return result;
			}
		}
		return "";
	}
	
	/**
	 * 获取2进制对应的String，编码格式为UTF-8
	 * @param origByte
	 * @return
	 */
	public static String getStringFromByteArray(byte[] origByte) {
		try {
			return new String(origByte, Constant.ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 获取String对应的2进制，编码格式为UTF-8
	 * @param origStr
	 * @return
	 */
	public static byte[] getByteArrayFromString(String origStr) {
		try {
			return origStr.getBytes(Constant.ENCODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
