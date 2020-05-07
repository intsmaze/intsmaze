package cn.intsmaze.refect;
import java.util.Date;

/**
 * @author:YangLiu
 * @date:2017年12月21日 下午7:40:00
 * @describe:
 */
public class Person {
	
	public String name = "intsmaze";
	
	private int age = 18;
	
	public static Date time;

	public int getAge() {
		return age;
	}

	// 构造函数
	public Person() {
		System.out.println("默认构造方法执行了");
	}

	public Person(String name) {
		System.out.println("姓名：" + name);
	}

	public Person(String name, int age) {
		System.out.println(name + "=" + age);
	}

	// 私有
	private Person(int age) {
		System.out.println("年龄：" + age);
	}

	// 方法
	public void m1() {
		System.out.println("m1");
	}

	public void m2(String name) {
		System.out.println(name);
	}

	public String m3(String name, int age) {
		System.out.println(name + ":" + age);
		return "itcast";
	}

	private void m4(Date d) {
		System.out.println(d);
	}
	
	private void M4(Date d) {
		System.out.println(d);
	}

	public static void m5() {
		System.out.println("m5");
	}

	private static void m6(String[] strs) {
		System.out.println(strs.length);
	}
}
