package com.eagleeye.authorization.constant;

public class AuthorizationException extends Exception {

	private static final long serialVersionUID = 8524390450035584895L;

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(String message, Throwable exception) {
		super(message + " thrown from " + (new Throwable()).getStackTrace()[1].getClassName() + "." + (new Throwable()).getStackTrace()[1].getMethodName() + "("
				+ (new Throwable()).getStackTrace()[1].getLineNumber() + ")", exception);
	}
}
