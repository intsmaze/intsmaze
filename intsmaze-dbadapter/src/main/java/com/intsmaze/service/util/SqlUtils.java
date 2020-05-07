package com.intsmaze.service.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @author:YangLiu
 * @date:2017年12月25日 下午3:10:11 
 * @describe: 
 */
public class SqlUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(SqlUtils.class);
	
	public static String getInsertSql(String tableName ,String[] names)
	{
		String insertSql = StringUtils.join("insert into ",tableName ," (#{field_name}) values (#{field_value})");
		String fieldName="";
		String fieldValue="";
		for(int j = 0; j < names.length; j++)
		{
			if(j==names.length-1)
			{
				fieldName=StringUtils.join(fieldName,names[j]);
				fieldValue=StringUtils.join(fieldValue,"?");
			}
			else
			{
				fieldName=StringUtils.join(fieldName,names[j],",");
				fieldValue=StringUtils.join(fieldValue,"?",",");
			}
		}
		insertSql=insertSql.replace("#{field_name}", fieldName).replace("#{field_value}", fieldValue);
		logger.debug("the insert sql is :{}",insertSql);
		return insertSql;
	}
}
