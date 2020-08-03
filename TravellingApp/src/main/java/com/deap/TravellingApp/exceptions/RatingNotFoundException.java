package com.deap.TravellingApp.exceptions;

public class RatingNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RatingNotFoundException() {
        super();
    }
	
	public RatingNotFoundException(String msg) {
		super(msg);
	}
	
	public RatingNotFoundException(final Throwable cause) {
        super(cause);
    }
	
	public RatingNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

}