package cn.intsmaze.collection;

import java.util.HashMap;
import java.util.HashSet;

public class Student {
	String name;

	public Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
		return "[" + this.name + "]";
	}

	@Override
    public boolean equals(Object o) {
		// 打印显示调用信息
		System.out.println("调用了" + this.name + "的equals方法，与"
				+ ((Student) o).name + "比！！！");
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		// 将引用类型强制类型转换
		Student s = (Student) o;
		// 测试内容是否相同
		if (this.name.equals(s.name)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
    public int hashCode() {
		int hc = 7 * this.name.charAt(0);
		System.out
				.println("调用了" + this.name + "的hashCode方法，哈希码为：" + hc + "！！！");
		if (this.name.equals("intsmaze") || this.name.equals("liuyang")) {
			return 100;
		} else if (this.name.equals("good")) {
			return 80;
		}
		return 70;
	}

	public static void main(String[] args) {

		HashMap<Student, String> map = new HashMap<Student, String>();
		map.put(new Student("intsmaze"), "1");
		map.put(new Student("liuyang"), "2");
		map.put(new Student("intsmaze"), "3");
		map.put(new Student("good"), "3");
		map.put(new Student("888"), "3");
		System.out.println(map);

		for (Student str : map.keySet()) {
			System.out.println(map.get(str));
		}
		// HashSet hs = new HashSet();
		// hs.add(new Student("tom"));
		// System.out.println(hs);
		// hs.add(new Student("wjc"));
		// System.out.println(hs);
		// hs.add(new Student("wyf"));
		// System.out.println(hs);
		// hs.add(new Student("wjc"));
		// System.out.println(hs);
		// System.out.println(hs.contains(new Student("wjc")));
	}
}
