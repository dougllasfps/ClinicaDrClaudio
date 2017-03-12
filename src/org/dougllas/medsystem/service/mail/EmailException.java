package org.dougllas.medsystem.service.mail;

public class EmailException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmailException() {}
	
	public EmailException(String message) {
		super(message);
	}
	
	public EmailException(Throwable throwable) {
		super(throwable);
	}
	
	public EmailException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
