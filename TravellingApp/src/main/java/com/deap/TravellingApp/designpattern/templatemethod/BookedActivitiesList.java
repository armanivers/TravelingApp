package com.deap.TravellingApp.designpattern.templatemethod;

import java.time.LocalDate;
import java.util.List;

import com.deap.TravellingApp.model.BookingActivity;

public abstract class BookedActivitiesList {

	public BookedActivitiesList(List<BookingActivity> list) {
		this.list = list;
	}
	
	private List<BookingActivity> list;
	
	public void sort() {	
		list.sort( 
				(a1, a2) -> 
		compare(a1.getActivityAvailable().getDate(),a2.getActivityAvailable().getDate()));
		
	}
	
	public abstract int compare(LocalDate t1, LocalDate t2);

	public List<BookingActivity> getList() {
		return list;
	}
	
	public void setList(List<BookingActivity> list) {
		this.list = list;
	}
}
