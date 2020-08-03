package com.deap.TravellingApp.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class BookingActivity {

	@EmbeddedId
	private BookingActivityKey id;
	
	@ManyToOne
	@MapsId("activity_available_id")
	@JoinColumn(name="activity_available_id")
	private ActivityAvailable activityAvailable;
	
	@ManyToOne
	@MapsId("booking_id")
	@JoinColumn(name="booking_id")
	private Booking booking;
	
	@Column(name="booked_slots")
	private int bookedSlots;
	
	public BookingActivity(BookingActivityKey bak,ActivityAvailable activityAvailable, Booking b, int bookedSlots) {
		this.id = bak;
		this.activityAvailable = activityAvailable;
		this.booking = b;
		this.bookedSlots = bookedSlots;
	}

	public BookingActivity() {
		
	}
	
	public BookingActivityKey getId() {
		return id;
	}

	public void setId(BookingActivityKey id) {
		this.id = id;
	}

	public ActivityAvailable getActivityAvailable() {
		return activityAvailable;
	}

	public void setActivityAvailable(ActivityAvailable activityAvailable) {
		this.activityAvailable = activityAvailable;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public int getBookedSlots() {
		return bookedSlots;
	}

	public void setBookedSlots(int bookedSlots) {
		this.bookedSlots = bookedSlots;
	}
	
	
	
}
