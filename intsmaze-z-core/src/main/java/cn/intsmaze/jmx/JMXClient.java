package cn.intsmaze.jmx;
import java.util.Iterator;
import java.util.Set;
import javax.management.Attribute;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
/** 
 * @author:YangLiu
 * @date:2017年12月10日 下午6:22:31 
 * @describe: 
 */
public class JMXClient {
	 public static void main(String[] args) throws Exception {  
	        //connect JMX  
	        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");  
	        JMXConnector jmxc = JMXConnectorFactory.connect(url,null);  
	        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
	        
	        //得到对应的注册对象
	        ObjectName mbeanName = new ObjectName("jmxBean:name=hello");     
	           
	        //print domains  
	        System.out.println("Domains:---------------");  
	        String domains[] = mbsc.getDomains();  
	        for (int i = 0; i < domains.length; i++) {         
	            System.out.println("Domain[" + i +"] = " + domains[i]);      
	        }     
	        System.out.println();
	         
	        //MBean count  
	        System.out.println("MBean count:---------------");  
	        System.out.println("MBean count = " + mbsc.getMBeanCount()); 
	        System.out.println();
	           
	        //process attribute  
	        System.out.println("process attribute:---------------"); 
	        //调用对象的名为setName的方法，参数为newName
	        mbsc.setAttribute(mbeanName, new Attribute("Name", "newName")); //set value  
	        //调用该对象名为getName的方法
	        System.out.println("Name = " + mbsc.getAttribute(mbeanName, "Name")); //get value  
	        System.out.println();
	           
	        //invoke via proxy  
	        System.out.println("invoke via proxy:---------------"); 
	        HelloMBean proxy = (HelloMBean) MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, HelloMBean.class, false);          
	        System.out.println(proxy.printHello());  
	        System.out.println(proxy.printHello("zhangsan")); 
	        System.out.println();
	           
	        //invoke via rmi  
	        System.out.println("invoke via rmi:---------------"); 
	        System.out.println(mbsc.invoke(mbeanName, "printHello", null, null));           
	        System.out.println(mbsc.invoke(mbeanName, "printHello", new Object[] { "lisi" }, new String[] { String.class.getName() }));     
	        System.out.println();
	           
	        //get mbean information  
	        System.out.println("get mbean information:---------------"); 
	        MBeanInfo info = mbsc.getMBeanInfo(mbeanName);           
	        System.out.println("Hello Class:" + info.getClassName());        
	        System.out.println("Hello Attribute:" + info.getAttributes()[0].getName());       
	        System.out.println("Hello Operation:" + info.getOperations()[0].getName());     
	        System.out.println();
	           
	        //ObjectName of MBean  
	        System.out.println("ObjectName of MBean:---------------");         
	        Set set = mbsc.queryMBeans(null, null);  
	        for (Iterator it = set.iterator(); it.hasNext();) {  
	            ObjectInstance oi = (ObjectInstance)it.next();          
	            System.out.println(oi.getObjectName());          
	        }  
	         
	        jmxc.close();       
	    }    
}
