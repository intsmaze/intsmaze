package com.intsmaze.redis.common;

import redis.clients.util.SafeEncoder;

import com.google.common.base.Joiner;

public class RedisKeys {

    private static Joiner joiner = Joiner.on(":").skipNulls();


    /**
     * Redis KEY前缀
     */
    private static final String KEY_PREFIX = "INTSMAZE";

    private static final String CONFIG_PREFIX = "CONFIG";// 系统配置


    private static final String VERSION_PREFIX = "VERSION";// 配置数据版本


    /**
     * 唯一数据过滤KEY
     *
     * @param keys
     * @return String
     */
    public static String onceFilter(Object... keys) {
        return joiner.join("service", "oncefilter", keys);
    }

    /**
     * grooby脚本key
     * @return byte[]
     */
    public static byte[] getRuleConfig() {
        return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "RULE"));
    }

    /**
     * grooby脚本版本
     * @return String
     */
    public static String getRuleConfigVersion() {
        return joiner.join(KEY_PREFIX, VERSION_PREFIX, "RULE");
    }


}