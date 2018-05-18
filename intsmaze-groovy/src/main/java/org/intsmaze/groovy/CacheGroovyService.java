package org.intsmaze.groovy;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.intsmaze.common.redis.JedisFactory;
import org.intsmaze.common.redis.RedisCacheKey;
import org.intsmaze.common.redis.TwemJedisPool;
import org.intsmaze.groovy.DataSerializer;
import org.intsmaze.groovy.DiyScriptCacheMapping;
import org.intsmaze.groovy.GScriptExecutor;
import org.intsmaze.groovy.RuleConfigModel;
import org.intsmaze.groovy.ScriptCacheMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.hand.rule.NodeBuilder;
import com.hand.rule.exception.RuleJsonParseException;
import com.hand.rule.node.LogicNode;

import groovy.lang.Script;
import redis.clients.jedis.Jedis;

/**
 * 核心规则计算数据缓存服务
 * 
 * @author willian
 */
public class CacheGroovyService {

	private final Logger logger = LoggerFactory.getLogger(CacheGroovyService.class);

	private TwemJedisPool twemproxyPool;

	//这个是最主要的，脚本调用
	private static ScriptCacheMapping scriptCacheMapping = new ScriptCacheMapping();
	
	public static Map ScriptTextmap = new HashMap();
	
	private static DiyScriptCacheMapping diyScriptCacheMapping = new DiyScriptCacheMapping();
	
	private static volatile boolean inited = false;

	private static volatile Thread updateThread;

	private String ruleConfigVersion;

	private static Map<String,String> diyParamDict = new HashMap<String,String>();   // 自定义规则、渠道通知,逻辑节点可配置参数字典
	
	

	public static DiyScriptCacheMapping getDiyScriptCacheMapping() {
		return diyScriptCacheMapping;
	}

	public static void setDiyScriptCacheMapping(DiyScriptCacheMapping diyScriptCacheMapping) {
		CacheGroovyService.diyScriptCacheMapping = diyScriptCacheMapping;
	}

	public static Map<String, String> getDiyParamDict() {
		return diyParamDict;
	}

	public static void setDiyParamDict(Map<String, String> diyParamDict) {
		CacheGroovyService.diyParamDict = diyParamDict;
	}


	public static ConcurrentMap<Long, Pair<String, Script>> getScriptCache() {
		return scriptCacheMapping.getScriptCache();
	}

	public synchronized Object asObject(byte[] b) {
		try {
			return DataSerializer.getJsonFSTConfiguration().asObject(b);
		} catch (Exception e) {
			try {
				throw new RuntimeException(new String(b, "UTF-8"), e);
			} catch (UnsupportedEncodingException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public void setTwemproxyPool(TwemJedisPool twemproxyPool) {
		this.twemproxyPool = twemproxyPool;
	}

	/**
	 * 初始化数据
	 */
	public void init() {
		synchronized (CacheGroovyService.class) {
			if (inited) {
				return;
			}
			initRuleConfig();
			inited = true;
		}
	}
	
	
	
	public void start() {
		synchronized (CacheGroovyService.class) {
			if (updateThread != null) {
				return;
			}
			String name = "groovy-CacheService-Update" + new Random().nextInt(100);
			updateThread = new UpdateThread(name);
			logger.info("Begin updateThread...");
			updateThread.start();
		}
	}

	/**
	 * 更新线程
	 * @author willian
	 */
	private class UpdateThread extends Thread {
		public UpdateThread(String threadName) {
			super.setName(threadName);
		}
		  
		public void run() {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(20);
				} catch (InterruptedException e1) {
					logger.error("", e1);
				}
				logger.info("Begin update groovy data cache...");
				try {
					initRuleConfig();
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}

	/**
	 * 初始化规则配置
	 * @throws RuleJsonParseException 
	 */
	@SuppressWarnings("unchecked")
	public void initRuleConfig() {
		Jedis jedis = null;
		try {
			jedis = twemproxyPool.getResource();
			String newVersion = jedis.get(RedisCacheKey.getRuleConfigVersion()+"_1");
			// 判断Redis中的缓存版本，如果有新版本则更新数据
			if (!StringUtils.equals(newVersion, ruleConfigVersion)) {
				byte[] data = jedis.get(RedisCacheKey.getRuleConfig());
				Preconditions.checkNotNull(data);

				// 规则更新
				List<RuleConfigModel> rules = (List<RuleConfigModel>) asObject(data);
				for (RuleConfigModel rule : rules) {
					Long id = rule.getId();
					if(rule.getRuleType() == 1)
					{
						cacheScript(id, rule.getRuleScript(), rule.getHashcode());
					}
					else if(rule.getRuleType() == 2)
					{
						cacheDiyRuleScript(id, rule.getRuleScript(), rule.getHashcode());
					}
				}
			}
			ruleConfigVersion = newVersion;
		} finally {
			JedisFactory.closeQuietly(jedis);
		}
	}

	/**
	 * 缓存脚本
	 * 
	 * @param id
	 * @param scriptContent
	 * @param hashcode
	 */
	private void cacheScript(Long id, String scriptContent, String hashcode) {
		// 判断新的脚本内容与缓存是否有差异，如果有则重新编译
		if (!scriptCacheMapping.isDifference(id, hashcode)) {
			scriptCacheMapping.addScript(id, hashcode, GScriptExecutor.getDefaultShell().parse(scriptContent));
			ScriptTextmap.put(id+"a",scriptContent);
			logger.info("Finding a new script, recompile and cache, rule id: {}.", id);
		}
	}
	
	/**
	 * 缓存自定义规则脚本 
	 * @throws RuleJsonParseException 
	 **/
	@SuppressWarnings("unchecked")
	private void cacheDiyRuleScript(Long id, String scriptContent, String hashcode)
	{
		try {
			// 判断新的脚本内容与缓存是否有差异，如果有则重新编译
			if(!diyScriptCacheMapping.isDifference(id, hashcode)) 
			{
				LogicNode<JSONObject> logicNode = NodeBuilder.build(scriptContent, diyParamDict);
				
				diyScriptCacheMapping.addScript(id, hashcode, logicNode);
				logger.info("Finding a new diyScript, recompile and cache, rule id: {}.", id);
			}
		} catch (RuleJsonParseException e) {
			logger.error("",e);
		}
	}
	
}