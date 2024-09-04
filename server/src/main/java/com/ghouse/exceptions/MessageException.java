package com.ghouse.exceptions;

public class MessageException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	public MessageException(String message) {
        super(message);
    }
	
	public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
