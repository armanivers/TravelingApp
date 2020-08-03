package com.deap.TravellingApp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.deap.TravellingApp.model.Activity;
import com.deap.TravellingApp.model.Rating;
import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyActivityService;
import com.deap.TravellingApp.service.MyRatingService;
import com.deap.TravellingApp.service.MyUserDetailsService;

@Controller
public class RatingController {

	@Autowired
	MyRatingService ratingService;
	@Autowired
	MyActivityService activityService;
	@Autowired
	MyUserDetailsService userService;

	@RequestMapping(value = "admin/manageRatings")
	public String getAllRatings(Model model, Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("ratings", ratingService.getAllRatings());
		return "admin/manageRatings";
	}

	@RequestMapping({ "/user/myRatings", "/admin/myRatings", "/provider/myRatings" })
	public String getAllRatingsOfUser(Model model, Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("userRatings", ratingService.getAllRatingsByUserId(user.getId()));
		return "myRatings";

	}

	// fuer das erstellen von bewertung
	@RequestMapping(value = "user/activity/rate/{activityId}")
	public String rateActivity(Model model, Principal p, @PathVariable("activityId") Optional<Long> activityId) {
		if (activityId.isPresent()) {
			User user = userService.getUserByUsername(p.getName());
			Activity activity = activityService.getActivityById(activityId.get());

			Rating rating = new Rating(user, activity, LocalDate.now());

			model.addAttribute("user", user);
			model.addAttribute("rating", rating);
			model.addAttribute("activity", activity);
		}
		return "user/rateActivity";
	}

	// wichtig fuer nach dem update/erstellen
	@RequestMapping(value = "user/activity/rate/completed", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public String completeActivityRating(Rating rating, @RequestParam("file") MultipartFile picture) {

		if (picture.isEmpty()) {
			System.out.println("no pic");
		} else {
			try {
				int l = picture.getOriginalFilename().length();
				String format = "." + picture.getOriginalFilename().substring(l - 3, l);
				rating.setPictureName(rating.getRatingId() + format);

				Path path = Paths.get("src\\main\\resources\\static\\img\\ratingPictures\\" + rating.getPictureName());
				Files.write(path, picture.getBytes());
				System.out.println("picture uploaded to" + path.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ratingService.createOrUpdateRating(rating);
		return "redirect:/user/myBookings";
	}

	// falls man update will
	@RequestMapping(value = "user/rating/show/{userId}/{activityId}")
	public String showOrUpdateRating(Model model, @PathVariable("activityId") Long activityId, Principal p,
			@PathVariable("userId") Long userId) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("rating", ratingService.getRatingByUseridAndActivityid(userId, activityId));
		model.addAttribute("activity", activityService.getActivityById(activityId));

		return "user/updateRating";

	}

	// falls man update will
	@RequestMapping(value = "user/rating/select/{ratingId}")
	public String showOrUpdateRatingByRatingId(Model model,Principal p,
			@PathVariable("ratingId") Long ratingId) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		Rating rating = ratingService.getRatingByRatingId(ratingId);
		model.addAttribute("rating", rating);
		model.addAttribute("activity", activityService.getActivityById(rating.getActivity().getActivityId()));

		return "user/updateRating";

	}
}
