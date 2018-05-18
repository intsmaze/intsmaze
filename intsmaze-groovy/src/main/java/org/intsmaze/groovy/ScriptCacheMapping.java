package org.intsmaze.groovy;

import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Maps;
import groovy.lang.Script;

public class ScriptCacheMapping implements Serializable {

	private static final long serialVersionUID = -6401408716509581348L;

	private static ConcurrentMap<Long, Pair<String, Script>> scriptCache = Maps.newConcurrentMap();

	public ConcurrentMap<Long, Pair<String, Script>> getScriptCache() {
		return scriptCache;
	}

	public void addScript(Long id, String hash, Script obj) {
		
		scriptCache.put(id, Pair.of(hash, obj));
	}

	public boolean contains(Long id) {
		return scriptCache.containsKey(id);
	}

	public boolean isDifference(Long id, String hash) {
		if (!contains(id)) {
			return false;
		}
		return scriptCache.get(id).getLeft().equals(hash);
	}

	public Script getScript(Long id) {
		if (!contains(id)) {
			return null;
		}
		return scriptCache.get(id).getRight();
	}

	public String getScriptHash(String scriptContent) {
		if (StringUtils.isNotEmpty(scriptContent)) {
			return MD5Util.getMd5(scriptContent);
		}
		return null;
	}

	public void remove(Long id) {
		if (contains(id)) {
			scriptCache.remove(id);
		}
	}

}
