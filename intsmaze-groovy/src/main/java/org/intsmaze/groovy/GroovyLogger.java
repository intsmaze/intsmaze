package org.intsmaze.groovy;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Groovy 日志记录器
 * 
 * @author WJ
 *
 */
public class GroovyLogger implements Serializable {

	private static final long serialVersionUID = 5558773939790314428L;

	private static final Logger logger = LoggerFactory.getLogger(GroovyLogger.class);

	/**
	 * 记录脚本DEBUG日志
	 * 
	 * @param message
	 * @param params
	 */
	public static void debug(Object... params) {
		try {
			StringBuilder str = new StringBuilder();
			if (params != null) {
				str = parse(params);
			}
			logger.info("[Groovy shell DEBUG] {}", str);
		} catch (Exception e) {
			logger.error("Groovy shell DEBUG message format error. ", e);
		}
	}

	/**
	 * 格式化文本内容
	 * 
	 * @param params
	 * @return String
	 */
	public static String text(Object... params) {
		StringBuilder str = new StringBuilder();

		try {
			if (params != null) {
				str = parse(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Groovy shell text format error. ", e);
		}
		return str.toString();
	}

	private static StringBuilder parse(Object... params) {
		StringBuilder str = new StringBuilder();
		int index = 0;
		for (int i = 0; i < params.length; i++) {
			if (index + i >= params.length) {
				break;
			}
			str.append(params[index + i]);

			if (index + i + 1 >= params.length) {
				break;
			}
			Object val = params[index + i + 1];
			if (val == null) {
				str.append(":");
				str.append("null");
			} else {
				str.append(":");
				str.append(val);
			}
			str.append(", ");
			index++;
		}
		return str;
	}


}
