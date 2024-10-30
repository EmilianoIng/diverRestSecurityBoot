package com.diver.main.exception;

import java.util.Date;

public class ExceptionGeneric {
	private Date timestamp;
	private String message;
	private String details;
	public ExceptionGeneric(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	

}
