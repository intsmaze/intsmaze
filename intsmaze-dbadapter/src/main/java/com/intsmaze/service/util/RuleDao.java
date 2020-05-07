package com.intsmaze.service.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.intsmaze.adapter.dao.impl.MysqlDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author:YangLiu
 * @date:2017年12月11日 下午4:08:31
 * @describe:
 */
public class RuleDao {

	private static final Logger logger = LoggerFactory.getLogger(RuleDao.class);

	/**
	 * @author:YangLiu
	 * @date:2017年12月13日 上午9:29:22
	 */
	public static String assembleExpress(Map mapOper, String express,
			Set<String> setDays, Set<String> setMark) {
		Iterator<Map.Entry> entries = mapOper.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = entries.next();
			String judgeid = (String) entry.getKey();
			String operate = (String) entry.getValue();
			logger.debug("operate :" + operate);
			String operateNoTable = operate.split("\\.")[1].trim();
			// String str = operateNoTable.split("=")[0].trim();
			for (String mark : Constants.TRADE_TYPE) {
				if (operateNoTable.indexOf(mark) != -1) {
					setMark.add(mark);
				}
			}
			if (operateNoTable.indexOf(Constants.SqlVar.CUMULATIVE_DAYS) != -1) {
				setDays.add(operateNoTable);
			}

			express = express.replace(StringUtils.join("#{", judgeid, "}"),
					operate);
		}
		return express;
	}

	/**
	 * @author:YangLiu
	 * @date:2017年12月12日 下午7:36:29
	 * @describe:只可意会，无法言传
	 */
	public static Map<String, Set<String>> understandByInsight(
			List<String> sonExpreList) {
		Map<String, Set<String>> asseSql = new HashMap<String, Set<String>>();
		for (String expre : sonExpreList) {
			// String arr[] = expre.split(Constants.SPLIT_ARR);
			// System.out.println(expre + "================");
			// String[] arrs = arr[0].trim().split("and");
			String[] arrs = expre.trim().split("and");
			String days = "";
			String conditions = "";
			for (int i = 0; i < arrs.length; i++) {
				String fieldName = arrs[i].split("\\.")[1];
				if (fieldName.indexOf(Constants.SqlVar.CUMULATIVE_DAYS) != -1) {
					String math = StringUtil.stringIndex(fieldName,
							Constants.MATHAR);
					days = fieldName.trim().split(math)[1].trim();
				} else {
					conditions = StringUtils.join(conditions, fieldName.trim());
				}
			}

			for (String ep : sonExpreList) {
				String[] ar = ep.trim().split("and");
				String days1 = "";
				String conditions1 = "";
				for (int i = 0; i < ar.length; i++) {
					String fieldName = ar[i].split("\\.")[1];
					if (fieldName.indexOf(Constants.SqlVar.CUMULATIVE_DAYS) != -1) {
						String math = StringUtil.stringIndex(fieldName,
								Constants.MATHAR);
						days1 = fieldName.trim().split(math)[1].trim();
					} else {
						conditions1 = StringUtils.join(conditions1,
								fieldName.trim());
					}
				}

				if (!asseSql.containsKey(StringUtils.join(days,
						Constants.SPLIT_ARR, conditions))) {
					Set<String> l = new HashSet<String>();
					l.add(StringUtils.join(days1, Constants.SPLIT_ARR,
							conditions1));
					asseSql.put(days + Constants.SPLIT_ARR + conditions, l);
				} else {
					Set<String> l = (Set<String>) asseSql.get(days
							+ Constants.SPLIT_ARR + conditions);
					l.add(days1 + Constants.SPLIT_ARR + conditions1);
					asseSql.put(days + Constants.SPLIT_ARR + conditions, l);
				}
			}
			logger.debug("cumulative_days is :{}", days);
			logger.debug("where the conditions behind is :{}", conditions);
		}
		logger.debug("------------- :" + asseSql.toString());
		return asseSql;
	}

	/**
	 * @author:YangLiu
	 * @date:2017年12月13日 上午10:12:41
	 */
	public static Map<String, String> getOperters(MysqlDao mysqlDao,
			int ruleId, boolean isTotal, Map<String, String> mapParam)
			throws Exception {
		Set<String> tables = new HashSet<String>();
		Map<String, String> mapOper = new HashMap<String, String>();
		getRuleParamMysql(mysqlDao, ruleId, isTotal, mapParam, tables);
		getRuleOperateMysql(mysqlDao, ruleId, mapOper, mapParam);
		return mapOper;
	}

	/**
	 * @author:YangLiu
	 * @date:2017年12月13日 下午8:20:32
	 */
	public static String useDateFunReplaceExpress(String[] operateArr) {
		String tardeTypeConditions = "";
		for (int i = 0; i < operateArr.length; i++) {
			List list = new ArrayList();
			if (operateArr[i].indexOf(":date:") != -1) {
				list = StringUtil.strIndexOfS(operateArr[i], ":date:");
				operateArr[i] = operateArr[i].replace(":date:", "");
			} else {
				for (String fieldType : Constants.FIELD_TYPE) {
					operateArr[i] = operateArr[i].replace(
							StringUtils.join(":", fieldType, ":"), "");
				}
			}

			// if(operateArr[i].indexOf(Constants.DAY_CONDITIONS)!=-1)
			if (list.size() == 1) {
				String math = StringUtil.stringIndex(operateArr[i],
						Constants.MATHAR);
				if ("=".equals(math)) {
					String model = "datetrunc(#{field_name},'DD')=datetrunc(dateadd(getdate(), #{days}, 'dd'), 'DD')";
					String[] arr = operateArr[i].split("=");
					String day = "-1";
					String field = "";
					for (String s : arr) {
						if (s.indexOf("today") != -1) {
							s = s.trim().replaceAll(" +", "");
							String[] str = s.split("-");
							if (str.length > 1) {
								day = StringUtils.join("-",
										Integer.valueOf(str[1]) + 1);
							}
						} else {
							field = s;
						}
					}
					model = model.replace("#{field_name}", field).replace(
							"#{days}", day);
					operateArr[i] = model;
				} else {
					String model = "dateadd(getdate(), #{days}, 'dd')";
					String[] arr = operateArr[i].split(math);
					String day = "-1";
					String field = "";
					for (String s : arr) {
						if (s.indexOf("today") != -1) {
							s = s.trim().replaceAll(" +", "");
							String[] str = s.split("-");
							if (str.length > 1) {
								day = StringUtils.join("-",
										Integer.valueOf(str[1]) + 1);
							}
						} else {
							field = s;
						}
					}
					model = model.replace("#{days}", day);
					operateArr[i] = StringUtils.join(field, math, model);
				}
			} else if (list.size() > 1) {
				String modelSql = "datediff(#{today}, #{before_today}, 'dd')";
				String split = "";
				for (String s : Constants.MATHAR) {
					if (operateArr[i].indexOf(s) != -1) {
						split = s;
						break;
					}
				}
				String intType = "";
				String dayOne = operateArr[i].split(split)[0].trim();
				String dayTwo = operateArr[i].split(split)[1].trim();
				if (StringUtil.isNumeric(dayOne)) {
					intType = dayOne;
					String[] dateArr = dayTwo.split("-");
					for (int j = 0; j < dateArr.length; j++) {
						modelSql = modelSql.replace(Constants.DATE_MODEL[j],
								dateArr[j]);
					}
					modelSql = StringUtils.join(intType, split, modelSql);
				} else {
					intType = dayTwo;
					String[] dateArr = dayOne.split("-");
					for (int j = 0; j < dateArr.length; j++) {
						modelSql = modelSql.replace(Constants.DATE_MODEL[j],
								dateArr[j]);
					}
					modelSql = StringUtils.join(modelSql, split, intType);
				}
				operateArr[i] = modelSql;
			}
		}
		return tardeTypeConditions;
	}

	/**
	 * @author:YangLiu
	 * @date:2017年12月14日 下午1:50:25
	 * @describe:
	 */
	public static void assembleSingleTradeRuleSql(List<String> finalExeSqlList,
			MysqlDao mysqlDao, Map<String, String> sqlMap, String isTotal) {

		try {
			int ruleId;
			ResultSet rsRuleids = mysqlDao
					.select("select ruleid from rule_express where isTotal="
							+ isTotal);

			while (rsRuleids.next()) {
				ruleId = rsRuleids.getInt(1);
				logger.debug("the ruleid is : {}", ruleId);

				String policyConditionFields = "";
				String claimConditionFields = "";

				Map<String, String> mapParam = new HashMap<String, String>();
				Map<String, String> mapOper = getOperters(mysqlDao, ruleId,
						false, mapParam);

				Iterator<Entry<String, String>> entries = mapParam.entrySet()
						.iterator();
				while (entries.hasNext()) {
					Map.Entry entry = entries.next();
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					for (String fieldType : Constants.FIELD_TYPE) {
						value = value.replace(
								StringUtils.join(":", fieldType, ":"), "");
					}
					String[] arr = value.split("\\.");
					if ("dw_w_aml_policy_info".equalsIgnoreCase(arr[0].trim())) {
						if (arr[1].indexOf("prem_refund") > -1
								|| arr[1].indexOf("fore_cur_prem_refund") > -1
								|| arr[1].indexOf("premium_new") > -1
								|| arr[1].indexOf("fore_cur_prem_new") > -1
								|| arr[1].indexOf("premium_original") > -1
								|| arr[1].indexOf("fore_cur_prem_original") > -1) {
							arr[1] = StringUtils.join("abs(", arr[1], ") as ",
									arr[1]);
						}
						if (policyConditionFields.indexOf(arr[1]) > 0) {

						} else {
							policyConditionFields = policyConditionFields
									+ "," + arr[1];
						}
					} else if ("dw_f_aml_claim_info".equalsIgnoreCase(
							arr[0].trim())) {
						if (arr[1].indexOf("register_amt") > -1) {
							arr[1] = StringUtils.join("abs(", arr[1], ") as ",
									arr[1]);
						}
						if (claimConditionFields.indexOf(arr[1]) > 0) {

						} else {
							claimConditionFields = claimConditionFields
									+ "," + arr[1];
						}
					}
				}

				String nativeExpress = "";
				String tradeFeature = "";
				String tardeConditions = "";
				String tardeType = "";
				ResultSet rsExpres = mysqlDao
						.select("select express,trade_feature,trade_type from rule_express where ruleid="
								+ ruleId);
				if (rsExpres.next()) {
					nativeExpress = rsExpres.getString(1);
					tradeFeature = rsExpres.getString(2);
					tardeType = rsExpres.getString(3);
					logger.debug("the nativeExpress is : {}", nativeExpress);

					Set<String> setDays = new HashSet<String>();
					Set<String> setTradeType = new HashSet<String>();
					String fullExpress = assembleExpress(mapOper,
							nativeExpress, setDays, setTradeType);
					logger.debug("the fullExpress is : {}", fullExpress);

					String[] operateArr = fullExpress.split("and");
					useDateFunReplaceExpress(operateArr);

					tardeConditions = getTardeConditions(operateArr);

					String tradeTypeField = sqlMap.get(tardeType
							+ Constants.FIELD);
					String sqlModel = "";
					if (tardeType.equals(Constants.CLAIM)) {
						sqlModel = sqlMap.get(Constants.XmlId.CLAIM_MODEL_SQL);
					} else {
						sqlModel = sqlMap
								.get(Constants.XmlId.NO_CLAIM_MODEL_SQL);
					}

					String commonSonSql = sqlMap
							.get(Constants.XmlId.COMMON_SON_FIELD);
					String commonFatherSql = sqlMap
							.get(Constants.XmlId.COMMON_FATHER_FIELD);

					String exeSql = sqlModel
							.replace(Constants.SqlVar.TRADE_FIELDS,
									tradeTypeField)
							.replace(Constants.SqlVar.COMMON_SON_FIELD,
									commonSonSql)
							.replace(Constants.SqlVar.COMMON_FATHER_FIELD,
									commonFatherSql)
							.replace(Constants.SqlVar.FEATURE_CODE,
									tradeFeature)
							.replace(Constants.SqlVar.POLICY_CONDITION_FIELDS,
									policyConditionFields)
							.replace(Constants.SqlVar.CLAIM_CONDITION_FIELD,
									claimConditionFields)
							.replace(Constants.SqlVar.DT,
									DateUtils.getDateToTheDay(2))
							.replace("#{insert_date}", DateUtils.getYesterday());
					tardeConditions = tardeConditions.replace(
							"dw_w_aml_policy_info", "temp").replace(
							"dw_f_aml_claim_info", "temp1");
					finalExeSqlList.add(StringUtils.join(exeSql,
							tardeConditions));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void assembleTotalTradeRuleSql(List<String> finalExeSqlList,
			MysqlDao mysqlDao, Map<String, String> sqlMap, String isTotal) {
		int ruleId = 0;
		ResultSet rsRuleids = null;
		try {
			rsRuleids = mysqlDao
					.select("select ruleid from rule_express where isTotal="
							+ isTotal);
			while (rsRuleids.next()) {

				ResultSet rsExpres = null;
				try {
					ruleId = rsRuleids.getInt(1);
					logger.debug("ruleid is : {}", ruleId);

					Map<String, String> mapParam = new HashMap<String, String>();
					Map<String, String> mapOper = getOperters(mysqlDao, ruleId,
							true, mapParam);

					String nativeExpress = "";
					String tradeFeature = "";
					rsExpres = mysqlDao
							.select("select express,trade_feature from rule_express where ruleid="
									+ ruleId);

					if (rsExpres.next()) {
						nativeExpress = rsExpres.getString(1);
						tradeFeature = rsExpres.getString(2);
						logger.debug("nativeExpress is : {}", nativeExpress);

						Set<String> setDays = new HashSet<String>();
						Set<String> setTradeType = new HashSet<String>();
						String fullExpress = assembleExpress(mapOper,
								nativeExpress, setDays, setTradeType);
						logger.debug("fullExpress is : {}", fullExpress);

						if (setDays.size() == 1 && setTradeType.size() == 1) {
							String days = "";
							String tardeType = "";
							String conditions = "";
							String[] operateArr = fullExpress.split("and");
							for (int i = 0; i < operateArr.length; i++) {
								String fieldName = operateArr[i].split("\\.")[1];
								if (fieldName
										.indexOf(Constants.SqlVar.CUMULATIVE_DAYS) != -1) {
									String math = StringUtil.stringIndex(
											fieldName, Constants.MATHAR);
									days = fieldName.trim().split(math)[1]
											.trim();
								} else {
									conditions = StringUtils.join(conditions,
											fieldName.trim());
									tardeType = StringUtil
											.getTardeTypeConditions(fieldName);
								}
							}
							logger.debug("cumulative_days is :{}", days);
							logger.debug("where the conditions behind is :{}",
									conditions);

							String insertSql = sqlMap
									.get(Constants.XmlId.INSERT_TEMP_TABLE)
									.replace(Constants.SqlVar.FEATURE_CODE,
											tradeFeature)
									.replace(Constants.SqlVar.DT,
											DateUtils.getYesterday());

							String exeSql = sqlMap
									.get("total_trade_single_rule_model_sql")
									.replace(
											Constants.SqlVar.TOTAL_COMMON_FIELD,
											sqlMap.get(Constants.XmlId.TOTAL_COMMON_FIELD))
									.replace(Constants.SqlVar.CUMULATIVE_DAYS,
											days)
									.replace(Constants.SqlVar.CONDITIONS,
											conditions)
									.replace(Constants.SqlVar.FEATURE_CODE,
											tradeFeature)
									.replace(Constants.SqlVar.TRADE_TYPE,
											tardeType)
									.replace(Constants.SqlVar.DT,
											DateUtils.getYesterday())
									.replace(
											Constants.SqlVar.CUMULATIVE_DAYS_VAL,
											DateUtils.getDateToTheDay(Integer
													.valueOf(days)))
									.replace("#{cumulative_days_val_temp}",
											DateUtils.getDateToTheDay(1));
							finalExeSqlList.add(insertSql + exeSql);
						} else {
							List<Integer> listStart = StringUtil.strIndexOfS(
									fullExpress, "(");
							List<Integer> listEnd = StringUtil.strIndexOfS(
									fullExpress, ")");
							List<String> sonExpreList = StringUtil
									.getIndexExpress(fullExpress, listStart,
											listEnd);

							Map<String, Set<String>> asseSql = understandByInsight(sonExpreList);
							Iterator<Entry<String, Set<String>>> entries = asseSql
									.entrySet().iterator();
							List<String> fatherSqlList = new LinkedList<String>();
							while (entries.hasNext()) {
								Entry<String, Set<String>> entry = entries
										.next();
								String firstCondition = (String) entry.getKey();
								Set<String> setCondition = (Set<String>) entry
										.getValue();
								String fatherSql = sqlMap
										.get("total_trade_multi_rule_father_model_sql");

								String tardeType = StringUtil
										.getTardeTypeConditions(firstCondition
												.split(Constants.SPLIT_ARR)[1]);

								String dyasVal = firstCondition
										.split(Constants.SPLIT_ARR)[0];
								fatherSql = fatherSql
										.replace(Constants.SqlVar.FEATURE_CODE,
												tradeFeature)
										.replace(
												Constants.SqlVar.CUMULATIVE_DAYS,
												dyasVal)
										.replace(Constants.SqlVar.TRADE_TYPE,
												tardeType)
										.replace(
												Constants.SqlVar.CUMULATIVE_DAYS_VAL,
												DateUtils
														.getDateToTheDay(Integer
																.valueOf(dyasVal)))
										.replace("#{cumulative_days_val_temp}",
												DateUtils.getDateToTheDay(1));
								logger.debug("fatherSql======" + fatherSql);

								String sonSqlModel = sqlMap
										.get("total_trade_multi_rule_son_model_sql");
								Iterator<String> itCondition = setCondition
										.iterator();
								List<String> sonSqlList = new ArrayList<String>();
								int i = 0;
								while (itCondition.hasNext()) {
									String str = itCondition.next();
									String sonDays = str
											.split(Constants.SPLIT_ARR)[0];
									String sonCondition = str
											.split(Constants.SPLIT_ARR)[1];

									sonSqlList
											.add("("
													+ sonSqlModel
															.replace(
																	Constants.SqlVar.CUMULATIVE_DAYS,
																	sonDays)
															.replace(
																	Constants.SqlVar.CONDITIONS,
																	sonCondition)
													+ ") " + Constants.ALIAS[i]);
									i++;
								}
								logger.debug("sonSqlList :{}", sonSqlList);

								String sonSql = sonSqlJoin(sonSqlList);
								fatherSql = fatherSql
										.replace(Constants.SqlVar.SON_SQL,
												sonSql)
										.replace(
												Constants.SqlVar.TOTAL_COMMON_FIELD,
												sqlMap.get(Constants.XmlId.TOTAL_COMMON_FIELD))
										.replace(Constants.SqlVar.DT,
												DateUtils.getYesterday());
								fatherSqlList.add(fatherSql);
								logger.debug(
										"fatherSql and sonSql join is :{}",
										fatherSql);
							}

							String exeSql = sqlMap
									.get(Constants.XmlId.INSERT_TEMP_TABLE)
									.replace(Constants.SqlVar.FEATURE_CODE,
											tradeFeature)
									.replace(Constants.SqlVar.DT,
											DateUtils.getYesterday());
							sqlUnion(exeSql, fatherSqlList, finalExeSqlList);
						}
					}
				} catch (Exception e) {
					logger.error("ruleid is : " + ruleId);
					e.printStackTrace();
				} finally {
					if (rsExpres != null) {
						rsExpres.close();
					}
				}
			}
		} catch (Exception e) {
			logger.error("happen exception ruleid is : {}", ruleId);
			logger.error("happen exception is : {}", e.toString());
		} finally {
			if (rsRuleids != null) {
				try {
					rsRuleids.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @author:YangLiu
	 * @date:2017年11月14日 上午10:40:57
	 */
	public static List<String> assembleExeSql(MysqlDao mysqlDao,
			Map<String, String> sqlMap) {
		List<String> finalExeSqlList = new LinkedList<String>();

		assembleSingleTradeRuleSql(finalExeSqlList, mysqlDao, sqlMap, "0");

		assembleTotalTradeRuleSql(finalExeSqlList, mysqlDao, sqlMap, "1");

		return finalExeSqlList;
	}

	public static String sonSqlJoin(List<String> sonList) {
		String exesql = "";
		for (int j = 0; j < sonList.size(); j++) {
			if (j == sonList.size() - 1) {
				exesql = StringUtils.join(exesql, sonList.get(j), " on ",
						Constants.ALIAS[j - 1], ".party_id=",
						Constants.ALIAS[j], ".party_id");
			} else if (j == 0) {
				exesql = StringUtils.join(exesql, sonList.get(j), " join ");
			} else {
				exesql = StringUtils.join(exesql, sonList.get(j), " on ",
						Constants.ALIAS[j - 1], ".party_id=",
						Constants.ALIAS[j], ".party_id join ");
			}
		}
		logger.debug("sonSql join is :{}", exesql);
		return "select a.party_id from " + exesql;
	}

	/**
	 * @author:YangLiu
	 * @date:2017年12月12日 下午8:08:28
	 */
	public static void sqlUnion(String exeSql, List exeFatherSql,
			List finalExeSqlList) {
		for (int i = 0; i < exeFatherSql.size(); i++) {
			if (i == 0) {
				exeSql = StringUtils.join(exeSql, exeFatherSql.get(i));
			} else {
				exeSql = StringUtils.join(exeSql, " union ",
						exeFatherSql.get(i));
			}
		}
		finalExeSqlList.add(exeSql);
	}

	/**
	 * @author:YangLiu
	 * @date:2017年11月15日 上午9:54:37
	 */
	public static void getRuleParamMysql(MysqlDao mysqlDao, int ruleId,
			boolean isTotal, Map<String, String> mapParam, Set<String> tables)
			throws Exception {
		ResultSet params = mysqlDao
				.select("select ruleid,paramid,param,param_source,param_type from rule_param where ruleid="
						+ ruleId);
		while (params.next()) {
			String paramid = params.getString(2);
			String param = params.getString(3);
			String paramTable = params.getString(4);
			String paramType = params.getString(5);
			if (isTotal) {
				mapParam.put(paramid, StringUtils.join(paramTable, ".", param));
			} else {
				mapParam.put(paramid, StringUtils.join(":", paramType, ":",
						paramTable, ".", param));
			}
			tables.add(paramTable);
		}
		params.close();
	}

	/**
	 * @author:YangLiu
	 * @date:2017年11月15日 上午9:58:42
	 */
	public static void getRuleOperateMysql(MysqlDao mysqlDao, int ruleId,
			Map<String, String> mapOper, Map<String, String> mapParam)
			throws Exception {
		ResultSet operates = mysqlDao
				.select("select ruleid,judgeid,operate from rule_operate where ruleid="
						+ ruleId);
		while (operates.next()) {
			String judgeid = operates.getString(2);
			String operate = operates.getString(3);
			Iterator<Entry<String, String>> entries = mapParam.entrySet()
					.iterator();
			while (entries.hasNext()) {
				Map.Entry entry = entries.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				operate = operate.replace(key, value);
			}
			mapOper.put(judgeid, operate);
		}
		operates.close();
	}

	public static String getTardeConditions(String[] operateArr) {
		String tardeConditions = "";
		for (int oper = 0; oper < operateArr.length; oper++) {
			if (oper == 0) {
				tardeConditions = StringUtils.join(" where ", tardeConditions
						+ operateArr[oper]);
			} else {
				tardeConditions = StringUtils.join(tardeConditions, " and ",
						operateArr[oper]);
			}

		}
		logger.debug(
				"This is the fullExpress after dealing with the date : {}",
				tardeConditions);
		return tardeConditions;
	}

}
