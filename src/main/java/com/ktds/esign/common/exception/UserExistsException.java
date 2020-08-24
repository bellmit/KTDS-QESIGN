package com.ktds.esign.common.exception;

public class UserExistsException extends Exception {

	private static final long serialVersionUID = -3916578978998573170L;

	public UserExistsException() {
	}

	public UserExistsException(String message) {
		super(message);
	}

}
