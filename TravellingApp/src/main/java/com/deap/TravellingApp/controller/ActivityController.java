package com.deap.TravellingApp.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.deap.TravellingApp.model.Activity;
import com.deap.TravellingApp.model.Booking;
import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyActivityAvailabilityService;
import com.deap.TravellingApp.service.MyActivityService;
import com.deap.TravellingApp.service.MyBookingService;
import com.deap.TravellingApp.service.MyDestinationService;
import com.deap.TravellingApp.service.MyRatingService;
import com.deap.TravellingApp.service.MySeasonService;
import com.deap.TravellingApp.service.MyUserDetailsService;

@Controller
public class ActivityController {

	@Autowired
	MyActivityService activityService;
	@Autowired
	MyBookingService bookingService;
	@Autowired
	MyDestinationService destinationService;
	@Autowired
	MySeasonService seasonService;
	@Autowired
	MyActivityAvailabilityService activityAvailabilityService;
	@Autowired
	MyRatingService ratingService;
	@Autowired
	MyUserDetailsService userService;
	
	@RequestMapping(value = "admin/manageActivities")
	public String getAllActivities(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("activities", activityService.getAllEnabledActivities());
		return "admin/manageActivities";

	}
	
	@RequestMapping(value = "admin/activityRequests")
	public String activityRequests(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("activities", activityService.getAllDisabledActivities());
		return "admin/activityRequests";

	}
	
	@RequestMapping(value = "provider/myActivities")
	public String myActivities(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("activities", activityService.getAllActivitiesByProvider(p.getName()));
		return "provider/myActivities";

	}
	
	@RequestMapping(value = "/admin/activityRequests/enable/{activityId}")
	public String enableActivity(Model model,Principal p,@PathVariable("activityId") Optional<Long> activityId) {
		if(activityId.isPresent()) {
			User user = userService.getUserByUsername(p.getName());
			model.addAttribute("user", user);
			activityService.getActivityById(activityId.get()).setEnabled(true);
			activityService.createOrUpdateActivity(activityService.getActivityById(activityId.get()));
			model.addAttribute("activities", activityService.getAllEnabledActivities());
		}
		return "admin/manageActivities";

	}
	
	@RequestMapping(value = "/user/showAvailableActivities/{bookingId}")
	public String getAvailableActivitiesForDestination(Model model,@PathVariable("bookingId") Optional<Long> bookingId) {
		if(bookingId.isPresent()) {
			Booking b = bookingService.getBookingById(bookingId.get());
			model.addAttribute("bookingId",bookingId.get());
			model.addAttribute("activitiesAvailable", activityService.getAvailableActivitiesForBooking(b));
		}
		return "user/showAvailableActivities";

	}
	
	@RequestMapping(value = "/user/displayActivity/{bookingId}/{activityId}")
	public String displayActivity(Model model,Principal p,
			@PathVariable("bookingId") Optional<Long> bookingId,
			@PathVariable("activityId") Optional<Long> activityId) {
		
		if(activityId.isPresent() && bookingId.isPresent()) {
			Activity a = activityService.getActivityById(activityId.get());
			User user = userService.getUserByUsername(p.getName());
			model.addAttribute("user", user);
			model.addAttribute("ratings",ratingService.getRatingByActivityId(activityId.get()));
			model.addAttribute("bookingId", bookingId.get());
			model.addAttribute("activity", a);
			model.addAttribute("activitiesAvailabilities", activityAvailabilityService.getAvailabilitiesForActivityId(activityId.get(),bookingId.get()));
		}
		return "user/displayActivity";

	}
	
	@RequestMapping(value = "/provider/createActivity")
	public String createActivity(Model model,Principal p) {
		
		model.addAttribute("name",p.getName());
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("activity",new Activity());
		model.addAttribute("destinations",destinationService.getAllDestinations());
		model.addAttribute("seasons",seasonService.getAllSeasons());
		return "/provider/createActivity";
	}
	
	@RequestMapping(value = "/provider/createActivity", method = RequestMethod.POST)
	public String createActivityPost(Model model, Activity activity,Principal p ) {
		
		model.addAttribute("name",p.getName());
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user",user);
		System.out.println(activity.toString());
		
		activityService.createOrUpdateActivity(activity);
		
		return "/provider/provider";
	}
	
}
