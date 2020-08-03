package com.deap.TravellingApp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Destination {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="destination_id")
	private Long destinationId;
	
	@Column(name="continent")
	private String continent;
	@Column(name="country")
	private String country;
	@Column(name="city")
	private String city;
	@Column(name="abr")
	private String abbreviation;
	@Column(name="description")
	private String description;
	@OneToMany(mappedBy="destination")
	private List<Booking> bookings;
	
	@OneToMany(mappedBy="destinationId")
	private List<Hotel> hotels;
	
	@OneToMany(mappedBy="activityId")
	private List<Activity> activities;
	
	public Long getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
	}
	
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public List<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinationId == null) ? 0 : destinationId.hashCode());
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
		Destination other = (Destination) obj;
		if (destinationId == null) {
			if (other.destinationId != null)
				return false;
		} else if (!destinationId.equals(other.destinationId))
			return false;
		return true;
	}
}
