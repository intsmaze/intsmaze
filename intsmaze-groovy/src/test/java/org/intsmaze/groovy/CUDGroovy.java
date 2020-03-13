package org.intsmaze.groovy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.intsmaze.common.redis.RedisCacheKey;
import org.intsmaze.common.redis.TwemJedisPool;
import org.intsmaze.groovy.RuleConfigModel;
import org.intsmaze.groovy.StormBeanFactory;
import org.nustaq.serialization.FSTConfiguration;

import redis.clients.jedis.Jedis;

/**
 * @author:YangLiu
 * @date:2018年5月17日 下午9:06:27
 * @describe:
 */
public class CUDGroovy {

	private FSTConfiguration fstConf = FSTConfiguration
			.createJsonConfiguration();

	private TwemJedisPool twemproxyPool;

	public TwemJedisPool getTwemproxyPool() {
		return twemproxyPool;
	}

	public void setTwemproxyPool(TwemJedisPool twemproxyPool) {
		this.twemproxyPool = twemproxyPool;
	}

	public static void main(String[] args) throws Exception {

		StormBeanFactory beanFactory = new StormBeanFactory("redis-cud.xml");
		CUDGroovy groovy = beanFactory.getBean(CUDGroovy.class);
		groovy.flushSingleRule();

	}

	public boolean flushSingleRule() throws Exception {
		Jedis jedis = null;
		jedis = twemproxyPool.getResource();
		List<RuleConfigModel> list = null;
		RuleConfigModel newModel = new RuleConfigModel(
				2l,
				"intsmaze测试groovy脚本",
				"脚本红引入的类必须在这个工程的jvm中存在",
				"import org.intsmaze.groovy.GroovyDemo\nimport org.intsmaze.groovy.GLogger\n\nGroovyDemo groovyDemo = GROOVY\nString age = AGE\n\nif (age.contains(\"-\")||age.contains(\"_\")) {\n    return false\n}\nString regAge = groovyDemo.getAge(age);\n\nLOGTAG = GLogger.text(\"传入参数\", age, \"处理后参数\", regAge)\nreturn true",
				"75E9BD03DF4CD5ECF29B557C9EC1A4D8", 1);

		byte[] data = jedis.get(RedisCacheKey.getRuleConfig());
		if (data != null) {
			list = (List<RuleConfigModel>) fstConf.asObject(data);
			if (list != null) {
				list.add(newModel);
			}
		} else {
			list = new ArrayList<RuleConfigModel>();
			list.add(newModel);
		}
		boolean result = false;
		result = jedis.set(RedisCacheKey.getRuleConfigVersion() + "_1",
				String.valueOf(new Date().getTime())).equals("OK") ? true
				: false;
		result = jedis.set(RedisCacheKey.getRuleConfig(),
				fstConf.asByteArray(list)).equals("OK") ? true : false;
		return result;
	}

	public boolean removeSingleRule(long id) throws Exception {
		Jedis jedis = null;
		jedis = twemproxyPool.getResource();
		boolean flag = false;
		byte[] data = jedis.get(RedisCacheKey.getRuleConfig());
		if (data != null) {

			List<RuleConfigModel> list = (List<RuleConfigModel>) fstConf
					.asObject(data);
			for (RuleConfigModel rule : list) {
				if (rule.getId() == id) {
					list.remove(rule);
					break;
				}
			}
			flag = jedis.set(RedisCacheKey.getRuleConfig(),
					fstConf.asByteArray(list)).equals("OK") ? true : false;
			;
		}
		jedis.set(RedisCacheKey.getRuleConfigVersion(),
				String.valueOf(new Date().getTime()));
		return flag;
	}

}
