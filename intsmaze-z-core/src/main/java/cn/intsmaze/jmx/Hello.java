package cn.intsmaze.jmx;
/** 
 * @author:YangLiu
 * @date:2017年12月10日 下午6:18:55 
 * @describe: 
 */
public class Hello implements HelloMBean{
	private String name;
    
    @Override
    public String getName() {
        return "..............."+name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
  
    @Override
    public String printHello() {
        return "Hello------------------ "+ name;
    }
  
    @Override
    public String printHello(String whoName) {
        return "Hello++++++++++++++++++++++++++  " + whoName;
    }
}
