package cn.intsmaze.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

import junit.framework.TestCase;

/**
 * @author:YangLiu
 * @date:2018年6月5日 上午10:31:34
 * @describe:
 */
public class MapCase extends TestCase {

	private Map<String, String> map = new HashMap<String, String>();

	private int len;

	public MapCase(int len) {
		this.len = len;
	}

	boolean put(String key, String value) {
		if (map.size() == len) {
			return false;
		} else {
			map.put(key, value);
			return true;
		}
	}

	public static void testHashMapOrHashtable() {
		// Hashtable<String,String> map = new Hashtable<>();
		Map<String, String> map = new HashMap<>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");

		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			entry.getKey();
			entry.getValue();
		}

		// 拿到key的集合，二次查询对应value
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String val = map.get(key);
			System.out.println(key + "=" + val);
		}
		System.out.println("通过Map.keySet遍历key和value：");
		for (String key : map.keySet()) {
			System.out.println("key= " + key + " and value= " + map.get(key));
		}

		// 直接拿到value的集合，避免先拿key再拿value的效率问题
		System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
		for (String v : map.values()) {
			System.out.println("value= " + v);
		}
	}

	public static void testCurrentHashMap() {
		Map<String, String> map = new ConcurrentHashMap<>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");

		map.size();

		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			entry.getKey();
			entry.getValue();
		}
	}

	public static void testLinkedHashMap() {
		// LinkedHashMap lhm=new LinkedHashMap();
		// lhm.put("one", new Integer(1));
		// lhm.put("two", new Integer(2));
		// lhm.put("three", new Integer(3));
		// iter = lhm.keySet().iterator();
		// while (iter.hasNext()) {
		// Object key = iter.next();
		// Object val = lhm.get(key);
		// System.out.println(key+"："+val);
		// }

		LinkedHashMap<String, Integer> lhm = new LinkedHashMap<String, Integer>(
				16, 0.75f, true);
		lhm.put("one", new Integer(1));
		lhm.put("two", new Integer(2));
		lhm.put("three", new Integer(3));
		lhm.get("two");
	}

	public static void testWeakHash11() {
//		 WeakHashMap<String, String> weakHashMap=new WeakHashMap<String, String>();
//		 Map<String, String> map=Collections.synchronizedMap(weakHashMap);
//		 map.put("name", "name");

//		Map<Integer, Byte[]> map = null;
//		map = new WeakHashMap<Integer, Byte[]>();
//		for (int i = 0; i < 10000; i++) {
//			Integer integer = new Integer(i);
//			map.put(integer, new Byte[i]);
//		}
//
//		// java.lang.OutOfMemoryError: Java heap space
//		map = new HashMap<Integer, Byte[]>(10);
//		for (int i = 0; i < 100; i++) {
//			Integer integer = new Integer(i);
//			map.put(integer, new Byte[i]);
//		}

		// java.lang.OutOfMemoryError: Java heap space
		// at cn.intsmaze.collection.MapCase.testWeakHash(MapCase.java:119)
//		map = new WeakHashMap<Integer, Byte[]>();
//		List list = new ArrayList();
//		for (int i = 0; i < 10000; i++) {
//			Integer integer = new Integer(i);
//			map.put(integer, new Byte[i]);// 如果你看不起我，你可以把这行注释，你将会发现姜还是老的辣，内存溢出是WeakHashMap而不是List导致.
//			list.add(integer);
//		}

		

	}
}
