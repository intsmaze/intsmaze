package org.intsmaze.groovy.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.nustaq.serialization.FSTConfiguration;

public class DataSerializer implements Serializable {

	private static final long serialVersionUID = -5286985724029020883L;

	private static FSTConfiguration jsonConfiguration = FSTConfiguration.createJsonConfiguration();

	public static FSTConfiguration getJsonFSTConfiguration() {
		return jsonConfiguration;
	}

	public static Object asObjectForJson(byte[] b) {
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

	public static byte[] asByteArrayForJson(Object o) {
		return jsonConfiguration.asByteArray(o);
	}

}
