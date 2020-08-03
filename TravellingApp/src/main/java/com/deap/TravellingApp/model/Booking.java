package com.deap.TravellingApp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.deap.TravellingApp.designpattern.templatemethod.AscendingSortedList;
import com.deap.TravellingApp.designpattern.templatemethod.DescendingSortedList;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="booking_id")
	private Long bookingId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private User user;

	@Column(name="person_count")
	private int personCount;
	
	@ManyToOne
	@JoinColumn(name="destination_id")
	private Destination destination;
	
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@Column(name="date_from")
	private LocalDate dateFrom;

	@Column(name="date_to")
	private LocalDate dateTo;
	
	@OneToMany(mappedBy="booking")
	private List<BookingActivity> bookingActivities;
	
	public Booking() {
		super();
	}

	public Booking(User user, int personCount, Destination destination, Hotel hotel, LocalDate dateFrom,
			LocalDate dateTo) {
		this.user = user;
		this.personCount = personCount;
		this.destination = destination;
		this.hotel = hotel;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.bookingActivities = new ArrayList<BookingActivity>();
	}

	public double getPrice() {
		double price = 0;
		
		for(BookingActivity ba : bookingActivities) {
			price+=ba.getActivityAvailable().getActivity().getPrice();
		}
		
		return price + hotel.getPrice();
		
	}
	
	public List<BookingActivity> getSortedActivitiesAsc(){
		AscendingSortedList a = new AscendingSortedList(bookingActivities);
		a.sort();
		return a.getList();
	}
	
	public List<BookingActivity> getSortedActivitiesDes(){
		DescendingSortedList a = new DescendingSortedList(bookingActivities);
		a.sort();
		return a.getList();
	}
	
	public boolean isExpired() {
		return dateTo.isBefore(LocalDate.now());
	}
	
	public boolean isActive() {
		return (dateFrom.isBefore(LocalDate.now()) && dateTo.isAfter(LocalDate.now()));		
	}
	
	public boolean isUpcoming() {
		return dateFrom.isAfter(LocalDate.now());
	}
	
	public String returnStatus() {
		if(this.isActive()) {
			return "current";
		} else if(this.isExpired()) {
			return "expired";
		} else {
			return "upcoming";
		}
	}
	public List<BookingActivity> getBookingActivities() {
		return bookingActivities;
	}

	public void setBookingActivities(List<BookingActivity> bookingActivities) {
		this.bookingActivities = bookingActivities;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Booking other = (Booking) obj;
		if (bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!bookingId.equals(other.bookingId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Booking [bookingid=" + bookingId + ", user=" + user + ", "+ "dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + "]";
	}

}
