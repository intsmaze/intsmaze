package org.intsmaze.groovy;

import groovy.lang.Binding;
import groovy.lang.Script;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleCalc {

	protected final Logger logger = LoggerFactory.getLogger(RuleCalc.class);

	/**
	 * @author:YangLiu
	 * @date:2018年5月7日 下午7:44:32
	 * @describe:测试动态脚本
	 */
	public boolean authRuleCalc(GScriptExecutor scriptExecutor,
			Binding shellContext) {
		boolean ruleIsMatched = false;
		String LOGTAG = "";
		try {
			// groovy脚本规则
			// *********************配置脚本核心计算处*******************
			ConcurrentMap<Long, Pair<String, Script>> map = CacheGroovyService
					.getScriptCache();

			Iterator<Entry<Long, Pair<String, Script>>> entries = map
					.entrySet().iterator();
			while (entries.hasNext()) {

				Entry<Long, Pair<String, Script>> entry = entries.next();
				Pair<String, Script> pair = entry.getValue();
				Script script = pair.getRight();
				synchronized (script) {
					script.setBinding(shellContext);
					logger.info(
							"script is : {}.",
							CacheGroovyService.ScriptTextmap.get(entry.getKey()
									+ "a"));
					ruleIsMatched = scriptExecutor.execute(script);
					LOGTAG = String.valueOf(shellContext.getVariable("LOGTAG"));
					logger.info(LOGTAG);
				}

			}
			// *********************配置脚本核心计算处*******************
		} catch (Exception e) {
			logger.error("", e);
		} finally {
		}
		return ruleIsMatched;
	}

}
