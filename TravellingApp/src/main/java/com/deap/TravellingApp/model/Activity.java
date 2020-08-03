package com.deap.TravellingApp.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_id")
	private Long activityId;

	@ManyToOne
	@JoinColumn(name = "destination_id")
	private Destination destination;

	@Column(name = "name")
	private String name;
	@Column(name = "provider")
	private String provider;
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "videolink")
	private String videoLink;
	
	@ManyToOne
	@JoinColumn(name = "season_id")
	private Season season;

	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private double price;
	@Column(name = "difficulty")
	private int difficulty;

	@Transient
	private Long temp_destinationid;
	@Transient
	private Long temp_seasonid;
	
	@OneToMany(mappedBy="activity")
	private List<ActivityAvailable> activititiesAvailable;

	@OneToMany(mappedBy = "activity")
	private List<Rating> ratings;

	public Activity() {
		this.activityId = null;
		this.destination = null;
		this.name = "";
		this.provider = "";
		this.enabled = false;
		this.videoLink = "";
		this.season = null;
		this.description = "";
		this.price = 25;
		this.difficulty = 0;
		this.temp_destinationid = new Long(0);
		this.temp_seasonid = new Long(0);
		this.activititiesAvailable = new LinkedList<ActivityAvailable>();
		this.ratings = new LinkedList<Rating>();
	}

	public double getRatingsMedian() {
		
		double median = 0;
		int amount = this.ratings.size();
		
		if(amount == 0) {
			return 0.0;
		}
		
		for(Rating r : this.ratings) {
			median += r.getRating();
		}
		return median/amount;
	}

	public Long getTemp_destinationid() {
		return temp_destinationid;
	}

	public void setTemp_destinationid(Long temp_destinationid) {
		this.temp_destinationid = temp_destinationid;
	}

	public Long getTemp_seasonid() {
		return temp_seasonid;
	}

	public void setTemp_seasonid(Long temp_seasonid) {
		this.temp_seasonid = temp_seasonid;
	}

	public List<ActivityAvailable> getActivititiesAvailable() {
		return activititiesAvailable;
	}

	public void setActivititiesAvailable(List<ActivityAvailable> activititiesAvailable) {
		this.activititiesAvailable = activititiesAvailable;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficilty) {
		this.difficulty = difficilty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
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
		Activity other = (Activity) obj;
		if (activityId == null) {
			if (other.activityId != null)
				return false;
		} else if (!activityId.equals(other.activityId))
			return false;
		return true;
	}

}
