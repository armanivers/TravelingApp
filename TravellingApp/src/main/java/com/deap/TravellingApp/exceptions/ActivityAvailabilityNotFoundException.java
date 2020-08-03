package com.deap.TravellingApp.exceptions;

public class ActivityAvailabilityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ActivityAvailabilityNotFoundException() {
        super();
    }
	
	public ActivityAvailabilityNotFoundException(String msg) {
		super(msg);
	}
	
	public ActivityAvailabilityNotFoundException(final Throwable cause) {
        super(cause);
    }
	
	public ActivityAvailabilityNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

}