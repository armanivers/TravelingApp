package com.deap.TravellingApp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rating_id")
	private Long ratingId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;
	
	@Column(name="rating_value")
	private int rating;
	
	@Column(name="comment")
	private String comment;

	@Column(name="rating_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate ratingDate;

	@Column(name="picture_name")
	private String pictureName;

	public Rating(User user, Activity activity, LocalDate ratingDate) {
		this.ratingId = new Long(999);
		this.user = user;
		this.activity = activity;
		this.rating = -1;
		this.comment = "";
		this.ratingDate = ratingDate;
		this.pictureName = "";
	}

	public Rating() {
		
	}

	public Long getRatingId() {
		return ratingId;
	}

	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public LocalDate getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(LocalDate ratingDate) {
		this.ratingDate = ratingDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", user=" + user + ", activity=" + activity + ", rating=" + rating
				+ ", comment=" + comment + "]";
	}
}
