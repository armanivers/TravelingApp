package com.deap.TravellingApp.designpattern.templatemethod;

import java.time.LocalDate;
import java.util.List;

import com.deap.TravellingApp.model.BookingActivity;

public class DescendingSortedList extends BookedActivitiesList{

	public DescendingSortedList(List<BookingActivity> list) {
		super(list);
	}

	@Override
	public int compare(LocalDate t1, LocalDate t2) {
		return t2.isBefore(t1) ? -1 : t2.isBefore(t1) ? 1 : 0;
	}

}
