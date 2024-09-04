package com.ghouse.exceptions;

public class ChatException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	public ChatException(String message) {
        super(message);
    }
	
	public ChatException(String message, Throwable cause) {
        super(message, cause);
    }
}
