package cn.intsmaze.jmx;
import java.util.HashMap;  
import java.util.Map;  
  
import javax.management.MBeanServerConnection;  
import javax.management.ObjectName;  
import javax.management.remote.JMXConnector;  
import javax.management.remote.JMXConnectorFactory;  
import javax.management.remote.JMXServiceURL;  
  
public class JMXManager {  
    /** 
     * 建立连接 
     *  
     * @param ip 
     * @param jmxport 
     * @return 
     */  
    public static MBeanServerConnection createMBeanServer(String ip,  
            String jmxport, String userName, String password) {  
        try {  
            String jmxURL = "service:jmx:rmi:///jndi/rmi://" + ip + ":"  
                    + jmxport + "/jmxrmi";  
            // jmx  
            // url  
            JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);  
  
            Map map = new HashMap();  
            String[] credentials = new String[] { userName, password };  
            map.put("jmx.remote.credentials", credentials);  
            JMXConnector connector = JMXConnectorFactory.connect(serviceURL,  
                    map);  
            MBeanServerConnection mbsc = connector.getMBeanServerConnection();  
            return mbsc;  
  
        } catch (Exception e) {  
            // e.printStackTrace();  
            System.err.println(ip + "的中间件不可以达");  
        }  
        return null;  
    }  
  
    /** 
     * 获取所有属性 
     *  
     * @param mbeanServer 
     * @param objName 
     * @return 
     */  
    public static Map getAllAttribute(MBeanServerConnection mbeanServer,  
            ObjectName objName) {  
        if (mbeanServer == null || objName == null) {
            throw new IllegalArgumentException();
        }
        try {  
            return null;  
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
    /** 
     * 使用MBeanServer获取对象名为[objName]的MBean的[objAttr]属性值 
     * <p> 
     * 静态代码: return MBeanServer.getAttribute(ObjectName name, String attribute) 
     *  
     * @param mbeanServer 
     *            - MBeanServer实例 
     * @param objName 
     *            - MBean的对象名 
     * @param objAttr 
     *            - MBean的某个属性名 
     * @return 属性值 
     */  
    private static Object getAttribute(MBeanServerConnection mbeanServer,  
            ObjectName objName, String objAttr) {  
        if (mbeanServer == null || objName == null || objAttr == null) {
            throw new IllegalArgumentException();
        }
        try {  
            return String.valueOf(mbeanServer.getAttribute(objName,  
                    "currentThreadsBusy"));  
        } catch (Exception e) {  
            return null;  
        }  
    }  
  
}  