package org.intsmaze.groovy.exception;

public class GroovyException extends Exception {

	private static final long serialVersionUID = 3193559706610623326L;

	public GroovyException(String message) {
		super(message);
	}

	public GroovyException(Throwable throwable) {
		super(throwable);
	}

}
