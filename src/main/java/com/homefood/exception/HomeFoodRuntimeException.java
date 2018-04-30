package com.homefood.exception;

import com.homefood.codetype.NotificationInfo;

public class HomeFoodRuntimeException extends RuntimeException {

	private ExceptionMessage exceptionMessage;
	private static final long serialVersionUID = -706783001803490307L;

	public HomeFoodRuntimeException(String message, NotificationInfo notificationinfo, int errorCode) {
		super(message);
		this.exceptionMessage = new ExceptionMessage(message, notificationinfo, errorCode);
	}

	public ExceptionMessage getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(ExceptionMessage exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}