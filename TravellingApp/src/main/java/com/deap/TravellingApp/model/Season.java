package com.deap.TravellingApp.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Season {

	@Id
	@Column(name="season_id")
	private Long id;
	
	@Column(name="season_name")
	private String name;
	
	@Column(name="season_start")
	private LocalDate seasonStart;
	
	@Column(name="season_end")
	private LocalDate seasonEnd;
	
	@OneToMany(mappedBy="season")
	private List<Activity> destinations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getSeasonStart() {
		return seasonStart;
	}

	public void setSeasonStart(LocalDate seasonStart) {
		this.seasonStart = seasonStart;
	}

	public LocalDate getSeasonEnd() {
		return seasonEnd;
	}

	public void setSeasonEnd(LocalDate seasonEnd) {
		this.seasonEnd = seasonEnd;
	}

	public List<Activity> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Activity> destinations) {
		this.destinations = destinations;
	}

	@Override
	public String toString() {
		return "Name=" + name + ", season Start Monat =" + seasonStart.getMonth() + ", Ende = " + seasonEnd.getMonth();
	}
	
	
}
