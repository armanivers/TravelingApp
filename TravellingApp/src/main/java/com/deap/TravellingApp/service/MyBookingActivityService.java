package com.deap.TravellingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.model.ActivityAvailable;
import com.deap.TravellingApp.model.Booking;
import com.deap.TravellingApp.model.BookingActivity;
import com.deap.TravellingApp.model.BookingActivityKey;
import com.deap.TravellingApp.repository.BookingActivityRepository;

@Service
public class MyBookingActivityService {

	@Autowired
	BookingActivityRepository bookingActivityRepository;
	@Autowired
	MyBookingService bookingService;
	@Autowired
	MyActivityAvailabilityService activityAvailabilityService;
	
	public void createBookingActivity(Long a,Long b) {
		
		Booking booking = bookingService.getBookingById(b);
		ActivityAvailable activityAvailable = activityAvailabilityService.getActivityAvailabilityById(a);
		
		BookingActivityKey bak = new BookingActivityKey(activityAvailable.getActivityAvailableId(),booking.getBookingId());
		
		BookingActivity ba = new BookingActivity(bak,activityAvailable,booking,booking.getPersonCount());
		
		activityAvailable.setSlots(activityAvailable.getSlots()-booking.getPersonCount());
		
		ba = bookingActivityRepository.save(ba);
		booking.getBookingActivities().add(ba);

	}
	
}
