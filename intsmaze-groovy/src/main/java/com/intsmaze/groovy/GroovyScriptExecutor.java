package com.intsmaze.groovy;

import com.intsmaze.groovy.exception.GroovyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class GroovyScriptExecutor {

	private static final Logger logger = LoggerFactory.getLogger(GroovyScriptExecutor.class);

	private static GroovyShell groovyShell;

	public static synchronized GroovyShell getDefaultShell() {
		if (groovyShell == null) {
			groovyShell = new GroovyShell(new Binding());
		}
		return groovyShell;
	}

	public static synchronized GroovyShell getShell(Binding binding) {
		if (groovyShell == null) {
			groovyShell = new GroovyShell(binding);
		}
		return groovyShell;
	}

	public boolean execute(Script obj) throws GroovyException {
		boolean result = false;
		try {
			if (obj != null) {
				Object res = obj.run();
				result = Boolean.valueOf(String.valueOf(res)).booleanValue();
			}
		} catch (Exception e) {
			logger.error("Execute script error: " + e);
			throw new GroovyException(e);
		}
		return result;
	}

}
