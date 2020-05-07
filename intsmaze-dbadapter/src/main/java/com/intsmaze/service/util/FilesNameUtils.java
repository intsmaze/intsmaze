package com.intsmaze.service.util;

import com.intsmaze.service.exe.bean.TestGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:YangLiu
 * @date:2017年11月7日 下午1:41:43
 * @describe:
 */
public class FilesNameUtils {

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException {

		TestGroup priCommon = new TestGroup();

		String[] names = FilesNameUtils.getFiledName(priCommon);
		for (int i = 0; i < names.length; i++) {
			System.out.print(names[i]);
			System.out.print(FilesNameUtils.getFieldValueByName(names[i],
					priCommon));
			System.out
					.println(FilesNameUtils.getFieldType(names[i], priCommon));
		}

	}

	/**
	 * @Description 根据属性名获取属性值
	 * @param
	 * @return
	 */
	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @author:YangLiu
	 * @date:2017年12月25日 下午4:39:55
	 * @describe:根据属性名称返回字段类型
	 */
	public static Type getFieldType(String fieldName, Object object)
			throws NoSuchFieldException, SecurityException {
		Class clazz = object.getClass();
		Field field = clazz.getDeclaredField(fieldName);
		return field.getGenericType();
	}

	/**
	 * 获取属性名数组
	 * */
	public static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 * */
	private static List getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		List list = new ArrayList();
		Map infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);
		}
		return list;
	}

}
