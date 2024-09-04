package com.ghouse.exceptions;

public class StoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public StoryException(String message) {
        super(message);
    }
    public StoryException(String message, Throwable cause) {
        super(message, cause);
    }
}