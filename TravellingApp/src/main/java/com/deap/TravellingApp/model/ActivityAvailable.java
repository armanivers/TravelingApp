package com.deap.TravellingApp.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ActivityAvailable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="activity_available_id")
	private Long activityAvailableId;
	
	//Beziehung zu booking_activity
	@OneToMany(mappedBy="activityAvailable")
	private List<BookingActivity> bookingActivities;
	
	//Beziehung zu activity
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;
	
	@Transient
	private long temp_activityid;
	
	@Column(name="start_time")
	private Timestamp startTime;
	
	@Column(name="end_time")
	private Timestamp endTime;
	
	@Column(name="day")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;
	
	@Column(name="maxslots")
	private int maxSlots;
	
	@Column(name="slots")
	private int slots;

	@Transient
	private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	
	public ActivityAvailable() {
		super();
		this.activityAvailableId = null;
		this.bookingActivities = new ArrayList<BookingActivity>();
		this.activity = null;
		this.temp_activityid = 0;
		this.startTime = null;
		this.endTime = null;
		this.date = LocalDate.now();  
		this.slots = 0;
		this.maxSlots = 0;
	}

	
	public Long getActivityAvailableId() {
		return activityAvailableId;
	}

	public void setActivityAvailableId(Long activityAvailableId) {
		this.activityAvailableId = activityAvailableId;
	}

	public List<BookingActivity> getBookingActivities() {
		return bookingActivities;
	}

	public void setBookingActivities(List<BookingActivity> bookingActivities) {
		this.bookingActivities = bookingActivities;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public boolean isBetweenDates(Booking b) {
		if(this.date.isAfter(b.getDateFrom()) && this.date.isBefore(b.getDateTo())) {
			return true;
		}
		return false;
	}
	
	public long getTemp_activityid() {
		return temp_activityid;
	}

	public void setTemp_activityid(long temp_activityid) {
		this.temp_activityid = temp_activityid;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp starttime) {
		this.startTime=starttime;
	}
	
	public Timestamp getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Timestamp endtime) {
		this.endTime = endtime;
	}
	
	public String getStartTimeString() {
		return sdf.format(startTime);
	}

	public String getEndTimeString() {
		return sdf.format(endTime);
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public int getMaxSlots() {
		return maxSlots;
	}


	public void setMaxSlots(int maxSlots) {
		this.maxSlots = maxSlots;
		this.slots = maxSlots;
	}


	@Override
	public String toString() {
		return "ActivityAvailable [activityAvailableId=" + activityAvailableId + ", bookingActivities="
				+ bookingActivities + ", activity=" + activity + ", temp_activityid=" + temp_activityid + ", startTime="
				+ startTime + ", endTime=" + endTime + ", date=" + date + ", slots=" + slots + "]";
	}
	
}
