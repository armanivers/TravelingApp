package com.deap.TravellingApp.exceptions;


public class DestinationNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DestinationNotFoundException() {
        super();
    }
	
	public DestinationNotFoundException(String msg) {
		super(msg);
	}
	
	public DestinationNotFoundException(final Throwable cause) {
        super(cause);
    }
	
	public DestinationNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

}
