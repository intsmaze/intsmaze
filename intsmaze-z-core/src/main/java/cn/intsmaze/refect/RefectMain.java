package cn.intsmaze.refect;
/** 
 * @author:YangLiu
 * @date:2017年12月21日 下午7:41:33 
 * @describe: 
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

//反射字段：Field
public class RefectMain {

	/**
	* Class clazz = Class.forName("cn.intsmaze.Person");
	* Class clazz = Person.class;
	*/
	public static void main(String[] strs) throws Exception {
		RefectMain d = new RefectMain();
		d.test4();
		d.privateFile();
		

	}
	
	//反射构造方法-----------------------------------------start-----------------------
	//public Person(String name,int age)
    public void test31() throws Exception{
        Class clazz = Class.forName("反射.Person");
        Constructor c = clazz.getConstructor(String.class,int.class);
        Person p = (Person)c.newInstance("朱巧玲",20);
        System.out.println(p.name);
        
    }
    
    //private Person(int age)
    public void test41() throws Exception{
        Class clazz = Class.forName("反射.Person");
        Constructor c = clazz.getDeclaredConstructor(int.class);//读取私有的构造方法的
        c.setAccessible(true);//暴力反射,不管访问权限是什么，都可以打开
        Person p = (Person)c.newInstance(20);
        System.out.println(p.name);
    }
    
    //类中的所有构造方法
    public void test51() throws Exception{
        Class clazz = Class.forName("反射.Person");
        Constructor[]  cs = clazz.getDeclaredConstructors();//注意这后面加的s
        System.out.println(cs.length);
        
    }
   //反射构造方法------------------------------------------start-----------------------
    
    
	//反射字段------------------------------------------start-----------------------
	// public String name="insmaze";
	public void publicFile() throws Exception {
//		Class clazz = Class.forName("cn.intsmaze.Person");
		Class clazz = Person.class;
		Person p = (Person) clazz.newInstance();//调用默认的构造方法
		// 获取字段的属性名
		Field f = clazz.getField("name");
		
		// 获取字段的值
		Object value = f.get(p);
		
		// 获取字段的类型
		Class type = f.getType();
		if (type.equals(String.class)) {
			System.out.print((String) value);
		}
		// 更改name的值
		f.set(p, "bolg is intsmaze");
	}

	// public static Date time;
	public void publicStatic() throws Exception {
		Class clazz = Person.class;
		Field f = clazz.getField("time");

		f.set(null, new Date());

		System.out.println(Person.time);
	}

	// private int age = 18;
	public void privateFile() throws Exception {
		Class clazz = Person.class;
		Person p = (Person) clazz.newInstance();
		
		Field f = clazz.getDeclaredField("age");
		f.setAccessible(true);

		Object age = f.get(p);
		System.out.println(age);

		f.set(p, 28);
	}
	//反射字段------------------------------------------end-----------------------
	
	
	
	//反射方法------------------------------------------start-----------------------
	//public void m1()
    public void test1() throws Exception{
        Class clazz = Person.class;//加载Person类   
        Person p = (Person) clazz.newInstance();       
        Method m = clazz.getMethod("m1", null);//得到m1方法，第二个告诉方法的参数是什么类型
        m.invoke(p, null);//让这个方法跑起来，第一个参数是指定对象，告诉跑的是那个对象的方法，，第二个参数是指定传入的参数值，返回类型是object
    }
    
    //public String m3(String name,int age)
    public void test3() throws Exception{
        Class clazz = Person.class;
        Person p = (Person) clazz.newInstance();
        Method m = clazz.getMethod("m3", String.class,int.class);
        
        String returnValue = (String)m.invoke(p, "intsmaze",23);
    }
    
    //private void m4(Date d)
    public void test4() throws Exception{
        Class clazz = Person.class;
        Person p = (Person) clazz.newInstance();
        Method m = clazz.getDeclaredMethod("m4", Date.class);
        m.setAccessible(true);
        
        m.invoke(p,new Date());
    }
    
    //public static void m5()
    public void test5() throws Exception{
        Class clazz = Person.class;
        Method m = clazz.getMethod("m5", null);
        //因为方法时静态的，所以不需要对象。
        m.invoke(null,null);
    }
    
    //当用反射调用参数是数组的方法时，jvm会把参数数组拆开，所以为了应对，有下面两种方式
    //private static void m6(String[] strs)
    public void test6() throws Exception{
        Class clazz = Person.class;
        Method m = clazz.getDeclaredMethod("m6",String[].class);
        m.setAccessible(true);
        //方法一
        m.invoke(null,(Object)new String[]{"a","b"});
    }
    
    public void test7() throws Exception{
        Class clazz = Person.class;
        Method m = clazz.getMethod("main",String[].class);
        //方法二
        m.invoke(null,new Object[]{new String[]{"a","b"}});
    }
    //反射方法------------------------------------------end-----------------------
    
    
}
