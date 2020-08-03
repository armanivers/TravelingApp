package com.deap.TravellingApp.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deap.TravellingApp.model.Hotel;
import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyHotelService;
import com.deap.TravellingApp.service.MyUserDetailsService;

@Controller
public class HotelController {

	@Autowired
	MyHotelService hotelService;
	@Autowired
	MyUserDetailsService userService;
	
	@GetMapping("admin/manageHotels")
	public String getAllHotels(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("hotels",hotelService.getAllHotels());
		return "admin/manageHotels";
	}
	
	@RequestMapping(path = "/user/showAvailableHotels/{destinationId}/{personCount}/{dateFrom}/{vacationLength}")
	public String editUserById(Model model, 
			@PathVariable("destinationId") Optional<Long> destinationId,
			@PathVariable("personCount") int personCount,
			@PathVariable("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
			@PathVariable("vacationLength") int vacationLength) {
		
		if (destinationId.isPresent()) {
			System.out.println("Destination Exists");
			List<Hotel> hotels = hotelService.findHotelsByDestinationId(destinationId.get());
			model.addAttribute("hotels",hotels);
			model.addAttribute("pCount",personCount);
			model.addAttribute("vacationL",vacationLength);
			model.addAttribute("dateF",dateFrom);
		} else {
			System.out.println("Doesnt Exist");
		}
		return "user/showAvailableHotels";
	}

}
