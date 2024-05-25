package com.app.Weather.beans;


@SuppressWarnings("serial")
public class ValidationResponse extends RuntimeException{
	   private final String message;

	    public ValidationResponse(String message) {
	        this.message = message;
	    }

	    @Override
	    public String getMessage() {
	        return message;
	    }
	}
