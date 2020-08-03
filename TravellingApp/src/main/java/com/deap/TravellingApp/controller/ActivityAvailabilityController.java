package com.deap.TravellingApp.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.deap.TravellingApp.model.ActivityAvailable;
import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyActivityAvailabilityService;
import com.deap.TravellingApp.service.MyActivityService;
import com.deap.TravellingApp.service.MyBookingActivityService;
import com.deap.TravellingApp.service.MyBookingService;
import com.deap.TravellingApp.service.MyUserDetailsService;

@Controller
public class ActivityAvailabilityController {

	@Autowired
	MyActivityAvailabilityService activityAvailabilityService;
	@Autowired
	MyBookingService bookingService;
	@Autowired
	MyBookingActivityService bookingActivityService;
	@Autowired
	MyActivityService actvityService;
	@Autowired
	MyUserDetailsService userService;
	
	@RequestMapping(value = "admin/manageActivityAvailabilities")
	public String getAllAvailableActivities(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("activitiesAvailabilities", activityAvailabilityService.getAllAvailibleActivities());
		return "admin/manageActivityAvailabilities";

	}
	
	@RequestMapping(value="/user/activityAvailable/add/{bookingId}/{activityAvailId}")
	public String addActivityToBooking(
			@PathVariable("bookingId") Optional<Long> bookingId,
			@PathVariable("activityAvailId") Optional<Long> activityAvailId) {
		
		bookingActivityService.createBookingActivity(activityAvailId.get(),bookingId.get());

		
		return "redirect:/user/showAvailableActivities/"+bookingId.get();
		
	}

	@RequestMapping(value = "/provider/createAvailableActivity")
	public String createAvailableActivity(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("activityAvailable",new ActivityAvailable());
		model.addAttribute("activities", actvityService.getAllActivitiesByProvider(p.getName()));
		return "/provider/createAvailableActivity";
	}
	
	@RequestMapping(value = "/provider/createAvailableActivity", method = RequestMethod.POST)
	public String createAvailableActivityPost(Model model, ActivityAvailable activity,Principal p,
			@RequestParam("st") String startTimeS,
			@RequestParam("et") String endTimeS) {

		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		
		//keine gute methode, funktionen deprecated(!)-> vllt date statt Timestamp??
		Timestamp startTime = new Timestamp(System.currentTimeMillis());
		startTime.setHours(Integer.parseInt(startTimeS.substring(0, 2)));
		startTime.setMinutes(Integer.parseInt(startTimeS.substring(3, 5)));
		
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		endTime.setHours(Integer.parseInt(endTimeS.substring(0, 2)));
		endTime.setMinutes(Integer.parseInt(endTimeS.substring(3, 5)));
		
		activityAvailabilityService.createOrUpdateActivityAvailability(activity, startTime,endTime);

		return "/provider/provider";
	}
}