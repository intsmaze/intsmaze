package cn.intsmaze.rpc;

import java.util.Map;

import com.google.gson.Gson;

/** 
 * @author:YangLiu
 * @date:2018年4月15日 下午12:34:03 
 * @describe: 
 */
public interface RpcClient {
	
	public Gson CallMethod(Gson message);

}
