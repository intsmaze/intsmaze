package com.intsmaze.groovy;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import com.intsmaze.redis.common.JedisFactory;
import com.intsmaze.redis.common.RedisKeys;
import com.intsmaze.redis.common.TwemJedisPool;
import com.intsmaze.groovy.bean.RuleConfigModel;
import com.intsmaze.groovy.bean.ScriptCacheMapping;
import org.nustaq.serialization.FSTConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

import groovy.lang.Script;
import redis.clients.jedis.Jedis;


/**
 * @author intsmaze
 * @description: https://www.cnblogs.com/intsmaze/
 * @date : 2020/6/6 21:03
 */
public class CacheGroovyService {

    private final Logger logger = LoggerFactory.getLogger(CacheGroovyService.class);

    private static FSTConfiguration jsonConfiguration = FSTConfiguration.createJsonConfiguration();

    private TwemJedisPool twemproxyPool;

    //这个是最主要的，脚本调用
    private static ScriptCacheMapping scriptCacheMapping = new ScriptCacheMapping();

    public static Map ScriptTextmap = new HashMap();

    private static volatile boolean inited = false;

    private static volatile Thread updateThread;

    private String ruleConfigVersion;

    private static Map<String, String> diyParamDict = new HashMap<String, String>();

    public void init() {
        synchronized (CacheGroovyService.class) {
            if (inited) {
                return;
            }
            initRuleConfig();
            inited = true;
            threadMetho();
        }
    }

    //不用刻意去掉start方式，将这放在init方法，bean创建时自动被调用
    public void start() {
        synchronized (CacheGroovyService.class) {
            threadMetho();
        }
    }

    private void threadMetho() {
        if (updateThread != null) {
            return;
        }
        String name = "groovy-CacheService-Update" + new Random().nextInt(100);
        updateThread = new UpdateThread(name);
        logger.info("Begin updateThread...");
        updateThread.start();
    }

    private class UpdateThread extends Thread {
        public UpdateThread(String threadName) {
            super.setName(threadName);
        }

        @Override
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


    public void initRuleConfig() {
        Jedis jedis = null;
        try {
            jedis = twemproxyPool.getResource();
            String newVersion = jedis.get(RedisKeys.getRuleConfigVersion());
            // 判断Redis中的缓存版本，如果有新版本则更新数据
            if (!StringUtils.equals(newVersion, ruleConfigVersion)) {
                byte[] data = jedis.get(RedisKeys.getRuleConfig());
                Preconditions.checkNotNull(data);

                // 规则更新
                List<RuleConfigModel> rules = (List<RuleConfigModel>) asObject(data);
                for (RuleConfigModel rule : rules) {
                    Long id = rule.getId();
                    cacheScript(id, rule.getRuleScript(), rule.getHashcode());
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
            scriptCacheMapping.addScript(id, hashcode, GroovyScriptExecutor.getDefaultShell().parse(scriptContent));
            ScriptTextmap.put(id + "a", scriptContent);
            logger.info("Finding a new script, recompile and cache, rule id: {}.", id);
        }
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
            return jsonConfiguration.asObject(b);
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


}