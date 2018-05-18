package org.intsmaze.groovy.exception;

public class GroovyCheckError extends Exception {

	private static final long serialVersionUID = 3193559706610623326L;

	public GroovyCheckError(String message) {
		super(message);
	}

	public GroovyCheckError(Throwable throwable) {
		super(throwable);
	}

}
