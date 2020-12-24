package cn.intsmaze.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author:YangLiu
 * @date:2018年6月4日 下午4:11:41
 * @describe:
 */
public class ListCase extends TestCase {

	public static void testArrayList() {
		List<Integer> list = new ArrayList<>(3);
		list.add(100);
		list.add(200);
		list.add(300);
		list.add(400);

		list.forEach(item -> {
			if (200 == item) {
				System.out.println(item);
			} else
				System.out.println("foreach + 拉姆达表达式:" + item);
		});

		// 使用迭代器删除集合指定位置元素
		Iterator<Integer> it = list.iterator();
		int j = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			if (j == 0 || j == 2) {
				System.out.println("---");
				it.remove();
			}
			j++;
		}
		System.out.println(list);

		// 不使用迭代器移除元素，必须从坐标最大的开始依次依次，否则会和自己指定移除的元素不一致
		list.clear();
		list.add(100);
		list.add(200);
		list.add(300);
		list.add(400);

		System.out.println(list.remove(2));
		System.out.println(list.remove(0));
		System.out.println(list);

		list.clear();
		list.add(100);
		list.add(200);
		list.add(300);
		list.add(400);
		System.out.println(list.remove(0));
		System.out.println(list.remove(2));
		System.out.println(list);
		// 不使用迭代器移除元素，必须从坐标最大的开始依次依次，否则会和自己指定移除的元素不一致

		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}

		for (Integer integer : list) {
			System.out.println(integer);
		}
	}


	public static void testModifyArrayList() {
		List<Integer> list = new ArrayList<>(3);
		list.add(100);
		list.add(200);
		list.add(300);
		list.add(400);
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
			list.remove(i);
			System.out.println(list.get(i));
		}
	}


	public static void testLinkedList() {
//		List<Integer> list=Collections.synchronizedList(new ArrayList<>());//就是对list对象进行了一层封装，封装的方法加上了synchronized关键字
		List<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(200);
		list.add(3);
		list.add(4);

		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			it.next();
		}

		list.forEach(item -> {
			if (item == 200) {
				System.out.println("ok");
			} else
				System.out.println("fuck");
		});
//		Collections.synchronizedList(list);
	}



}
