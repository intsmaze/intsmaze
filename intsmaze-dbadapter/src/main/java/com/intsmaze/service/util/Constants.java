package com.intsmaze.service.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:YangLiu
 * @date:2017年12月12日 下午1:55:49
 * @describe:
 */
public class Constants {

	public static final String SPLIT_ARR = "#:#";

	public static final String CLAIM = "claim";

	public static final String TRADE_TYPE_FIELD_NAME = "mark=";

	public static final String FIELD = "_fields";

	public static final String DAY_CONDITIONS = "today";

	public static final String[] TRADE_TYPE = new String[] { "refund",
			"insure", "modify", "claim" };

	public static final String[] MATHAR = new String[] { ">=", "<=", "<", "=",
			">" };

	public static final String[] DATE_MODEL = new String[] { "#{today}",
			"#{before_today}" };

	// 用做表的别名
	public static final String[] ALIAS = new String[] { "a", "b", "c", "d",
			"e", "f", "g", "h", "i", "j", "k", "l" , "m", "n", "o", "p" };

	public static final String[] FIELD_TYPE = new String[] { "String", "date",
			"number", "null" };

	public static Map<String, String> TABLE_ALIAS = new HashMap<String, String>() {
		{
			put("insure", "dw_w_aml_policy_info:temp");
			put("modify", "dw_w_aml_policy_info:temp");
			put("claim", "dw_f_aml_claim_info:temp1");
		}
	};

	public static class XmlId {

		public static final String COMMON_FIELD = "common_field";

		public static final String COMMON_FATHER_FIELD = "common_father_field";

		public static final String COMMON_SON_FIELD = "common_son_field";
		
		public static final String TOTAL_COMMON_FIELD = "total_trade_common_field";
		
		public static final String REFUND_FIELDS = "refund_fields";

		public static final String INSURE_FIELDS = "insure_fields";

		public static final String MODIFY_FIELDS = "modify_fields";

		public static final String CLAIM_FIELDS = "claim_fields";

		public static final String CLEAN_SUS_TRADE_TEMP_SQL = "clean_sus_trade_temp";

		public static final String NO_CLAIM_MODEL_SQL = "insure_modify_refund_model_sql";

		public static final String CLAIM_MODEL_SQL = "claim_model_sql";

		public static final String SINFLE_TOTAL_RULE_SQL = "total_trade_single_rule_model_sql";

		public static final String FATHER_MULTI_TOTAL_RULE_SQL = "total_trade_multi_rule_father_model_sql";

		public static final String SON_MULTI_TOTAL_RULE_SQL = "total_trade_multi_rule_son_model_sql";

		public static final String INSERT_TEMP_TABLE = "insert_sus_trade_table_temp";

		public static final String CUSTOMER_LEGAL = "aml_customer_legal";

		public static final String CUSTOMER_NATURE = "aml_customer_nature";
		
		public static final String SON_SQL = "son_sql";
	}

	public static class SqlVar {
		
		public static final String POLICY_CONDITION_FIELDS = "#{policy_condition_fields}";
		
		public static final String CLAIM_CONDITION_FIELD = "#{claim_condition_fields}";

		public static final String COMMON_FIELD = "#{common_field}";

		public static final String COMMON_FATHER_FIELD = "#{common_father_field}";

		public static final String COMMON_SON_FIELD = "#{common_son_field}";
		
		public static final String TOTAL_COMMON_FIELD = "#{total_trade_common_field}";

		public static final String DT = "#{date}";
		
		public static final String PAR_DT = "#{date_val}";
		
		public static final String WHERE_TRADE_TIME = "#{days}";

		public static final String FEATURE_CODE = "#{trade_feature}";

		// 该交易类型需要组装的特点sql字段
		public static final String TRADE_FIELDS = "#{trade_type_fields}";

		// 交易类型名称
		public static final String TRADE_TYPE = "#{trade_type}";

		public static final String CONDITIONS = "#{conditions}";

		public static final String CUMULATIVE_DAYS = "#{cumulative_days}";
		
		public static final String CUMULATIVE_DAYS_VAL = "#{cumulative_days_val}";
		
		public static final String CUMULATIVE_DAYS_1 = "#{cumulative_days-1}";

		public static final String SON_SQL = "#{son_sql}";
		
		public static final String CHG_RATE = "#{chg_rate}";

	}
}
