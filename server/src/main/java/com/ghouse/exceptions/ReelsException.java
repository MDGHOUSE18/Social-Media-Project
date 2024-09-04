package com.ghouse.exceptions;

public class ReelsException extends RuntimeException {
    
	
	private static final long serialVersionUID = 1L;
	
	public ReelsException(String message) {
        super(message);
    }
    
	public ReelsException(String message, Throwable cause) {
        super(message, cause);
    }
}
