package com.deap.TravellingApp.exceptions;

public class BookingNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BookingNotFoundException() {
        super();
    }
	
	public BookingNotFoundException(String msg) {
		super(msg);
	}
	
	public BookingNotFoundException(final Throwable cause) {
        super(cause);
    }
	
	public BookingNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

}
