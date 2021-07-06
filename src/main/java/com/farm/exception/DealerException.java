package com.farm.exception;

public class DealerException extends Exception {
	private static final long serialVersionUID = 1L;

	public DealerException() {
		super();
	}

	public DealerException(String message) {
		super(message);
	}

	public DealerException(String message, Throwable e) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
