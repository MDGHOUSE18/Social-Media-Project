package com.ghouse.exceptions;

public class PostException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	public PostException(String message) {
        super(message);
    }
	
	public PostException(String message, Throwable cause) {
        super(message, cause);
    }
}
