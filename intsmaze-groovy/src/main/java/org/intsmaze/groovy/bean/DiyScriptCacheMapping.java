package org.intsmaze.groovy.bean;
//package org.intsmaze.groovy;
//
//import java.io.Serializable;
//import java.util.concurrent.ConcurrentMap;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.tuple.Pair;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.collect.Maps;
//import com.hand.rule.node.LogicNode;
//
//
//public class DiyScriptCacheMapping implements Serializable {
//
//	private static final long serialVersionUID = -6401408716509581348L;
//
//	private ConcurrentMap<Long, Pair<String, LogicNode<JSONObject>>> scriptCache = Maps.newConcurrentMap();
//
//	public ConcurrentMap<Long, Pair<String, LogicNode<JSONObject>>> getScriptCache() {
//		return scriptCache;
//	}
//
//	public void addScript(Long id, String hash, LogicNode<JSONObject> obj) {
//		
//		scriptCache.put(id, Pair.of(hash, obj));
//	}
//
//	public boolean contains(Long id) {
//		return scriptCache.containsKey(id);
//	}
//
//	public boolean isDifference(Long id, String hash) {
//		if (!contains(id)) {
//			return false;
//		}
//		return scriptCache.get(id).getLeft().equals(hash);
//	}
//
//	public LogicNode<JSONObject> getScript(Long id) {
//		if (!contains(id)) {
//			return null;
//		}
//		return scriptCache.get(id).getRight();
//	}
//
//	public String getScriptHash(String scriptContent) {
//		if (StringUtils.isNotEmpty(scriptContent)) {
//			return Md5Util.getMd5(scriptContent);
//		}
//		return null;
//	}
//
//	public void remove(Long id) {
//		if (contains(id)) {
//			scriptCache.remove(id);
//		}
//	}
//
//}
