package com.intsmaze.rpc.tcp.three;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
public class ServiceRoute {
	
	public static Map<String,String> NAME=new HashMap<String, String>();
	
	public ServiceRoute()
	{
		ClassWays classWays=new ClassWays();
		Class[] argumentsType={String.class};
		classWays.setArgumentsType(argumentsType);
		classWays.setClassname("cn.intsmaze.tcp.three.service.SayHelloServiceImpl");
		classWays.setMethod("sayHello");
		classWays.setIp("127.0.0.1");
		classWays.setPort(1234);
		JSONObject js=JSONObject.fromObject(classWays);
		NAME.put("SayHello", js.toString());
	}
	
}
