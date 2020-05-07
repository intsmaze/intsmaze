package cn.intsmaze.jmx.model;
/** 
 * @author:YangLiu
 * @date:2017年12月16日 下午8:46:09 
 * @describe: 
 */
public class HusbandLocal {
	
	//如果业务中有判断消费金额大于100的，一般是将100作为一个静态变量。if(trade>=Constant.number),但是如果想通过JMX动态的改变.
	//必须提供该字段的set,get方法，否则无法通过Attribute菜单修改（该菜单是通过该字段的set,get方法进行操作的，同时注意static的字段是无法在这里修改的）
	//static的修改的，operation菜单通过调用该对象的方法来进行的。
	public static int number=100;
	
	 public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		HusbandLocal.number = number;
	}

    private String name;  
    private int age;  
    private String message;  
  
    public String getName() {  
        System.out.println("name:"+name);  
        return name;  
    }  
  
    public void setName(String name) { 
    	System.out.println("intsmaze要给你起名字");  
        this.name = name;  
    }  
  
    public int getAge() {  
        System.out.println("age:"+age);  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
  
    public String getMessage() {  
        System.out.println("message:"+message);  
        return message;  
    }  
  
    public void setMessage(String message) {  
        this.message = message;  
    }  
    
}
