package com.deap.TravellingApp.designpattern.templatemethod;

import java.time.LocalDate;
import java.util.List;

import com.deap.TravellingApp.model.BookingActivity;

public class AscendingSortedList extends BookedActivitiesList{

	public AscendingSortedList(List<BookingActivity> list) {
		super(list);
	}

	@Override
	public int compare(LocalDate t1, LocalDate t2) {
		return t1.isBefore(t2) ? -1 : t1.isBefore(t2) ? 1 : 0;
	}

}
