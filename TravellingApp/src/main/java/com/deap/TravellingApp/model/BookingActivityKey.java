package com.deap.TravellingApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookingActivityKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="activity_available_id")
	private Long activityAvailableId;

	@Column(name="booking_id")
	private Long bookingId;

	public BookingActivityKey(Long activityAvailableId, Long bookingId) {
		super();
		this.activityAvailableId = activityAvailableId;
		this.bookingId = bookingId;
	}

	public BookingActivityKey() {
		super();
	}
	
	public Long getActivityAvailableId() {
		return activityAvailableId;
	}

	public void setActivityAvailableId(Long activityAvailableId) {
		this.activityAvailableId = activityAvailableId;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityAvailableId == null) ? 0 : activityAvailableId.hashCode());
		result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingActivityKey other = (BookingActivityKey) obj;
		if (activityAvailableId == null) {
			if (other.activityAvailableId != null)
				return false;
		} else if (!activityAvailableId.equals(other.activityAvailableId))
			return false;
		if (bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!bookingId.equals(other.bookingId))
			return false;
		return true;
	}
}
