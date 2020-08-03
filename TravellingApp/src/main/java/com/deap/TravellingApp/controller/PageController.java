package com.deap.TravellingApp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.deap.TravellingApp.model.Rating;
import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyRatingService;
import com.deap.TravellingApp.service.MyUserDetailsService;

@Controller
public class PageController {

	@Autowired
	MyUserDetailsService userService;
	@Autowired
	MyRatingService ratingService;
	
	@GetMapping("/")
	public String index(Model model) {
		Rating withPic = ratingService.getRandomRating(true);
		//System.out.println(withPic.getPictureName());
		Rating withoutPic = ratingService.getRandomRating(false);
		
		model.addAttribute("withPic",withPic);
		model.addAttribute("withoutPic",withoutPic);
		
		return "index";
	}
	
	@GetMapping("/main")
	public String indexMain() {
		return "indexMain";
	}
	
	@GetMapping("/user")
	public String user(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		return "user/user";
	}

	@GetMapping("/admin")
	public String admin(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		return "admin/admin";
	}
	
	@GetMapping("/provider")
	public String provider(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		return "provider/provider";
	}

	@GetMapping({"/user/myProfile","/admin/myProfile","/provider/myProfile"})
	public String myProfile(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("message", false);
		return "myProfile";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("message",false);
		return "register";
	}

	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping({ "/logout" })
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "infoPages/aboutUs";
	}
	
	@GetMapping("/contactUs")
	public String contactUs() {
		return "infoPages/contactUs";
	}
	
	@GetMapping("/news")
	public String news() {
		return "infoPages/news";
	}
	
	@GetMapping("/jobs")
	public String jobs() {
		return "infoPages/jobs";
	}
	
	@GetMapping("/support")
	public String support() {
		return "infoPages/support";
	}
	
	@GetMapping("/faq")
	public String faq() {
		return "infoPages/faq";
	}
}
