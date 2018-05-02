package cn.intsmaze.jetty.handle;


import java.util.Arrays;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class ApplicationServer implements ApplicationServerMBean{

	final static Logger logger=LoggerFactory.getLogger(ApplicationServer.class);
	
	private AbstractApplicationContext applicationContext;
	
	
	public void close() {
		applicationContext.close();
	}
	
	
	public static void main(String[] args) throws Exception
	{
		logger.info("输入命令{}",Arrays.toString(args));
		Options options=new Options();
		options.addOption("start",false,"启动服务");
		options.addOption("stop",false,"停止服务");
		
		CommandLineParser parser=new GnuParser();
		
		try {
			CommandLine line=parser.parse(options, args);
			if(line.hasOption("start"))
			{
				printLoggerContext();
				startCommand();
			}
			else if(line.hasOption("stop")) {
				stopCommand();
				System.exit(1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}


	private static void printLoggerContext() {
		LoggerContext lc=(LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.setPrintStream(System.err);
		StatusPrinter.print(lc);
	}

	
	private static ObjectName getApplicationObjectName() throws MalformedObjectNameException {
		ObjectName mbeanName=new ObjectName("fm5:name=ApplicationServer");
		return mbeanName;
	}
	
	private static void startCommand() throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException {
		String configXmlFile=System.getProperty("Config");
		
		AbstractApplicationContext applicationContext=new FileSystemXmlApplicationContext(configXmlFile);
		
		MBeanServer mbs=java.lang.management.ManagementFactory.getPlatformMBeanServer();
		
		ObjectName mbeanName=getApplicationObjectName();
		ApplicationServer applicationServer=new ApplicationServer();
		applicationServer.setApplicationContext(applicationContext);
		mbs.registerMBean(applicationServer, mbeanName);
	}
	
	private static void stopCommand() throws Exception {
		String port=System.getProperty("jmx.port");
		String serviceURL="service:jmx:rmi:///jndi/rmi://:"+port+"/jmxrmi";
		
		JMXServiceURL jmxServiceURL=new JMXServiceURL(serviceURL);
		JMXConnector jmxc=JMXConnectorFactory.connect(jmxServiceURL,null);
		
		MBeanServerConnection mbsc=jmxc.getMBeanServerConnection();
		ObjectName mbeanName=getApplicationObjectName();
		ApplicationServerMBean mbean=MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, ApplicationServerMBean.class, true);
		
		try{
			mbean.close();
		} catch(Exception e)
		{
			logger.error("异常");
		}
	}

	public AbstractApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(AbstractApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
}
