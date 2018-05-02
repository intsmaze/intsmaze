package cn.intsmaze.drpc;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift7.TException;

import com.google.gson.Gson;

import cn.intsmaze.rpc.RpcClient;
import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

public class DrpcClient implements RpcClient {

	DRPCClient client = new DRPCClient("192.168.19.131", 3772);

	public static void main(String[] args) throws TException,
			DRPCExecutionException {
		Gson g=new Gson();
		 Map<String, String> map = new HashMap<String, String>();
	        map.put("m1", "1");
	        map.put("m2", "2");
	        System.out.println(g.toJson(map));
	        System.out.println(g.toString());
	}

	public Gson CallMethod(Gson message,String methodName) {
		String result="";
		try {
			result = client.execute(methodName, message.toString());
		} catch (TException e) {
			e.printStackTrace();
		} catch (DRPCExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Gson CallMethod(Gson message) {
		// TODO Auto-generated method stub
		return null;
		 
	}
}
