package org.intsmaze.common.redis;

import redis.clients.util.SafeEncoder;

import com.google.common.base.Joiner;

public class RedisKeys {

private static Joiner joiner = Joiner.on(":").skipNulls();

	
	private static byte[] encode(String s) {
		return SafeEncoder.encode(s);
	}

	/**
	 * 活动自定义变量KEY,组限量、活动累计值,存入redis后同步到hbase
	 * 
	 * @param eventType
	 * @return String
	 */
	public static byte[] groupVariableKey(long activityId) {
		return encode(joiner.join("gp", activityId));
	}

	/**
	 * 活动自定义变量KEY,组限量、活动累计值,存入redis后同步到hbase
	 * 
	 * @param eventType
	 * @return String
	 */
	public static byte[] groupVariableUpdated() {
		return encode("gp:updated");
	}

	/**
	 * 活动自定义变量KEY,活动限量、活动累计值,存入redis后同步到hbase
	 * 
	 * @param eventType
	 * @return String
	 */
	public static byte[] activityVariableKey(long activityId) {
		return encode(joiner.join("ac", activityId));
	}

	/**
	 * 活动自定义变量KEY,活动限量、活动累计值,存入redis后同步到hbase
	 * 
	 * @param eventType
	 * @return String
	 */
	public static byte[] activityVariableUpdated() {
		return encode("ac:updated");
	}


	/**
	 * 活动锁KEY
	 * 
	 * @param activityId
	 * @param lockId
	 * @return String
	 */
	public static String activityLock(long activityId, int lockId) {
		return joiner.join("lock:ac", activityId, lockId);
	}

	/**
	 * 用户锁KEY
	 * 
	 * @param idNo
	 * @param activityId
	 * @return String
	 */
	public static String userLock(String idNo, long activityId) {
		return joiner.join("lock:us", idNo, activityId);
	}

	public static String userLock(String idNo, String activityName) {
		return joiner.join("lock:us", idNo, activityName);
	}
	
	/**
	 * 用户最近位置
	 * 
	 * @param idNo
	 * @return String
	 */
	public static String userLocation(String idNo) {
		return joiner.join("us:location", null, idNo);
	}

	/**
	 * 变量字典数据版本KEY
	 * 
	 * @return String
	 */
	public static String variableDictVersion() {
		return "db:variable:dict:version";
	}

	/**
	 * 变量字典KEY
	 * 
	 * @return String
	 */
	public static byte[] variableDict() {
		return encode("db:variable:dict");
	}

	/**
	 * 活动组数据版本KEY
	 * 
	 * @return String
	 */
	public static String activityGroupsVersion() {
		return "db:activity:groups:version";
	}

	/**
	 * 活动组KEY
	 * 
	 * @param type
	 * @return String
	 */
	public static byte[] activityGroups(String type) {
		return encode(joiner.join("db:activity:groups", type));
	}

	/**
	 * 算法类映射 KEY
	 * 
	 * @return String
	 */
	public static byte[] algorithmClassMap() {
		return encode("db:algorithm:map");
	}
	
	public static String readPacketListVersion() {
		return "db:redpacket:list:version";
	}
	
	public static byte[] readPacketList() {
		return encode("db:redpacket:list");
	}

	public static byte[] readSpendingRankList() {
		return encode("db:spendingrank:list");
	}
	public static String readSpendingRankListStr() {
		return "db:spendingrank:list";
	}
	
	public static String readActivityfilterStr() {
		return "db:activityfilter:list";
	}
	public static String readActivityconfigfilterStr() {
		return "db:activityconfigfilter:list";
	}
	
	public static String readActivityconfigfilterVersion() {
		return "db:activityconfigfilter:list:version";
	}
	
	public static String readActiveKeysStr() {
		return "rank:active:keys";
	}
	public static byte[] currencyRatelist() {
		return encode("db:currencyrate:list");
	}
	/**
	 * 算法类JAR文件流KEY
	 * 
	 * @return String
	 */
	public static byte[] algorithmClassStream() {
		return encode("stream");
	}

	/**
	 * 微信交易提醒模板Key
	 * 
	 * @param type
	 * @return
	 */
	public static String TranSactionAlertTemplate(){
		return "db:transAlertTemplate:keys";
	}
	
	/**
	 * 算法类名称KEY
	 * 
	 * @return String
	 */
	public static byte[] algorithmClassNames() {
		return encode("classnames");
	}

	/**
	 * 算法类版本KEY
	 * 
	 * @return String
	 */
	public static byte[] algorithmClassVersion() {
		return encode("version");
	}

	/**
	 * 系统常量map的key
	 * 
	 * @return byte[]
	 */
	public static byte[] systemVariableMap() {
		return encode("db:system:variables");
	}

	public static byte[] virtualMerchantMap() {
		return encode("db:virtual:merchant");
	}

	public static String eventMessageKey(String eventType) {
		return joiner.join("marquee", eventType);
	}

	/**
	 * 可用的用户信息表KEY
	 * 
	 * @return String
	 */
	public static String activeWideTable() {
		return "WIDE_ACTIVE_TABLE";
	}

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
	 * 事件计数器指标KEY
	 * 
	 * @param keys
	 * @return String
	 */
	public static String metricsEvent(Object... keys) {
		return joiner.join("e", null, keys);
	}

	/**
	 * 
	 * 活动计数器指标KEY
	 * 
	 * @param keys
	 * @return String
	 */
	public static String metricsActivity(Object... keys) {
		return joiner.join("a", null, keys);
	}

	/**
	 * 活动渠道计数器指标KEY
	 * 
	 * @param keys
	 * @return String
	 */
	public static String metricsActivityChannel(Object... keys) {
		return joiner.join("z", null, keys);
	}

	/**
	 * 渠道计数器指标KEY
	 * 
	 * @param keys
	 * @return String
	 */
	public static String metricsChannel(Object... keys) {
		return joiner.join("c", null, keys);
	}
	/**
	 * 渠道计数器指标KEY
	 * 
	 * @param keys
	 * @return String
	 */
	public static String errorChannel(Object... keys) {
		return joiner.join("r", null, keys);
	}
	
	
	public static String readActivityVirList(){
		return "db:activityVir:list";
	}
	
	
	/** Redis KEY前缀 */
	private static final String KEY_PREFIX = "INTSMAZE";
	
	private static final String CONFIG_PREFIX = "CONFIG";// 系统配置
	
	
	
	private static final String DATA_PREFIX = "DATA";// 系统数据
	private static final String VERSION_PREFIX = "VERSION";// 配置数据版本
	private static final String METRICS_PREFIX = "METRICS";// 计数器
	private static final String WS_PREFIX = "WS";// 核身查询接口请求队列
	
	
	
	
	
	
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

	
	

	/**
	 * 电话号码白名单列表
	 * 
	 * @return byte[]
	 */
	public static byte[] getPhoneWhiteList() {

		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "PHONE_WHITE"));
	}

	/**
	 * 电话号码黑名单列表
	 * 
	 * @return byte[]
	 */
	public static byte[] getPhoneBlackList() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "PHONE_BLACK"));
	}
	
	/**
	 * 功能名单列表
	 * 
	 * @return byte[]
	 */
	public static byte[] getMultipleList() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "MULTIPLE_LIST"));
	}

	
	/**
	 * 自定义渠道通知配置表达式
	 * 
	 * @return byte[]
	 */
	public static byte[] getChannelAdviceExpression() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "CHANNEL_ADVICE_EXPRESSION"));
	}
	
	/**
	 * 自定义渠道通知逻辑节点可配置参数字典
	 * 
	 * @return byte[]
	 */
	public static byte[] getChannelAdviceParam() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "CHANNEL_ADVICE_PARAM"));
	}
	
	/**
	 * 自定义规则逻辑节点可配置参数字典
	 * 
	 * @return byte[]
	 */
	public static byte[] getDiyRuleParam() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "DIY_RULE_PARAM"));
	}
	
	/**
	 * 渠道通知
	 * 
	 * @return byte[]
	 */
	public static byte[] getChannelDatas() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "CHANNEL_ADVICE_DATAS"));
	}
	
	/**
	 * 渠道通知的系统参数
	 * 
	 * @return byte[]
	 */
	public static byte[] getChannelSystemAttrs() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "CHANNEL_ADVICE_System_ATTRS"));
	}

	/**
	 * 核身规则组配置
	 * 
	 * @return byte[]
	 */
	public static byte[] getRuleGroup() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "RULE_GROUP"));
	}

	/**
	 * 数据规则配置
	 * 
	 * @return byte[]
	 */
	public static byte[] getDataRuleConfig() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "DATA_RULE"));
	}

	/**
	 * 字典配置
	 * 
	 * @return byte[]
	 */
	public static byte[] getDictConfig() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "DICT"));
	}

	/**
	 * 字典配置版本
	 * 
	 * @return String
	 */
	public static String getDictConfigVersion() {
		return joiner.join(KEY_PREFIX, VERSION_PREFIX, "DICT");
	}



	/**
	 * 自定义渠道通知配置版本
	 * 
	 * @return String
	 */
	public static String getChannelAdviceVersion() {
		return joiner.join(KEY_PREFIX, VERSION_PREFIX, "CHANNEL_ADVICE");
	}
	
	/**
	 * 自定义规则、渠道通知,逻辑节点可配置参数字典版本
	 * 
	 * @return String
	 */
	public static String getDiyParamDictVersion() {
		return joiner.join(KEY_PREFIX, VERSION_PREFIX, "DIY_PARAM_DICT");
	}
	
	/**
	 * 渠道及渠道参数配置版本
	 * 
	 * @return String
	 */
	public static String getChannelInfoVersion() {
		return joiner.join(KEY_PREFIX, VERSION_PREFIX, "CHANNEL_INFO");
	}
	
	/**
	 * 数据规则配置版本
	 * 
	 * @return String
	 */
	public static String getDataRuleConfigVersion() {
		return joiner.join(KEY_PREFIX, VERSION_PREFIX, "DATA_RULE");
	}

	/**
	 * 计数器
	 * 
	 * @return byte[]
	 */
	public static byte[] getMetrics(String type, String datetime) {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, METRICS_PREFIX, type, datetime));
	}

	/**
	 * 用户GPS位置
	 * 
	 * @return String
	 */
	public static String getUserLocation(String custId) {
		return joiner.join(KEY_PREFIX, DATA_PREFIX, "LOCATION", custId);
	}

	/**
	 * 核身请求队列
	 * 
	 * @return byte[]
	 */
	public static String getAuthRequestQueue(Object... keys) {
		return joiner.join(KEY_PREFIX, WS_PREFIX, keys);
	}

	/**
	 * 城市区域代码
	 * 
	 * @return byte[]
	 */
	public static byte[] getCityAreaCode() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "CITY_AREA"));
	}

	/**
	 * 城市名称代码
	 * 
	 * @return byte[]
	 */
	public static byte[] getCityNameCode() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "CITY_NAME"));
	}

	/**
	 * 城市所属省
	 */
	public static byte[] getProvinceNameCode() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "PROVINCE_NAME"));
	}

	/**
	 * 号码归属地
	 */
	public static byte[] getNumAttrCode(String keys) {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "NUM_ATTR", keys));
	}

	/**
	 * 区号归属地
	 */
	public static byte[] getNumAttrAreaCode() {
		return SafeEncoder.encode(joiner.join(KEY_PREFIX, CONFIG_PREFIX, "NUM_ATTR_AREA"));
	}
	
	/**
	 * 用户某天的交易情况(地点、次数)
	 */
	public static byte[] getTradeTimesByDay(String custId,String date) {
		return SafeEncoder.encode(joiner.join("IDV","TRADE",custId,date));
	}



	/**
	 * 可用的宽表名称
	 * 
	 * @return String
	 */
	public static String getActiveWideTable() {
		return "WIDE_ACTIVE_TABLE";
	}


	/**
	 * 核身规则统计
	 * 
	 * @param datetime
	 * @return
	 */
	public static byte[] getRuleCount(String datetime) {
		return SafeEncoder.encode(joiner.join("IDV:RULE:MATCH:COUNT", datetime));
	}
	
	/**
	 * 核身规则耗时
	 * @param datetime
	 * @return
	 */
	public static byte[] getCostTime(String datetime) {
		return SafeEncoder.encode(joiner.join("IDV:RULE:MATCH:COSTTIME", datetime));
	}
	/**
	 * 核身接口统计
	 * @param datetime
	 * @return
	 */
	public static byte[] getInterfaceCount(String datetime) {
		return SafeEncoder.encode(datetime);
	}
	/**
	 * 核身接口耗时统计
	 * @param datetime
	 * @return
	 */
	public static byte[] getInterfaceCost(String costtime) {
		return SafeEncoder.encode(costtime);
	}
	/**
	 * 核身接口失败统计
	 * @param failtime
	 * @return
	 */
	public static byte[] getInterfaceFail(String failtime) {
		return SafeEncoder.encode(failtime);
	}
	/**渠道通知统计
	 * 
	 */
	public static byte[] getChannelCount(String key) {
		return SafeEncoder.encode(key);
	}
	/**
	 * 计数
	 * 
	 * @param key
	 * @return
	 */
	public static String getMetricKey(String key) {
		return joiner.join("IDV:CUSTOM:METRIC", key);
	}
}