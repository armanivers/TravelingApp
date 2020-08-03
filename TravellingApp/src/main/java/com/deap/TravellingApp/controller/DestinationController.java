package com.deap.TravellingApp.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.deap.TravellingApp.api.newsAPI.NewsResponse;
import com.deap.TravellingApp.api.newsAPI.NewsService;
import com.deap.TravellingApp.api.openWeatherAPI.WeatherService;
import com.deap.TravellingApp.model.Destination;
import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyDestinationService;
import com.deap.TravellingApp.service.MyUserDetailsService;

@Controller
public class DestinationController {

	@Autowired
	private MyDestinationService destinationService;

	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private MyUserDetailsService userService;

	@RequestMapping(value = "admin/manageDestinations")
	public String getAllBookings(Model model,Principal p) {

		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		
		// store all destinations
		List<Destination> destinations = destinationService.getAllDestinations();
		// store weather temperatures
		List<Long> temperatures = new ArrayList<>();
		// store icon names
		List<String> icons = new ArrayList<>();

		for (Destination d : destinations) {
			// System.out.println(weatherService.getForecast(d.getCity()).getMain());

			// get temperature of a city, convert it to celsius (-273,15), round it and add
			// it to the list with weather temps
			temperatures.add(Math.round(weatherService.getCurrentWeather(d.getCity()).getMain().get("temp")));

			// get corresponding weather icon
			// System.out.println(weatherService.getForecast(d.getCity()).getWeather().get(0).get("icon"));
			icons.add("img/weatherIcons/" + weatherService.getCurrentWeather(d.getCity()).getWeather().get(0).get("icon")
					+ "@2x.png");
		}

		model.addAttribute("icon", icons);
		model.addAttribute("weather", temperatures);
		model.addAttribute("destinations", destinations);

		return "admin/manageDestinations";

	}

	@GetMapping("user/findDestination")
	public String findDestination(Model model) {

		List<Destination> destinations = destinationService.getAllDestinations();
		List<Long> temperatures = new ArrayList<>();
		List<String> icons = new ArrayList<>();

		for (Destination d : destinations) {
			temperatures.add(Math.round(weatherService.getCurrentWeather(d.getCity()).getMain().get("temp")));
			icons.add("img/weatherIcons/" + weatherService.getCurrentWeather(d.getCity()).getWeather().get(0).get("icon")
					+ "@2x.png");
		}

		model.addAttribute("icon", icons);
		model.addAttribute("weather", temperatures);
		model.addAttribute("destinations", destinations);

		return "user/findDestination";
	}
	
	@RequestMapping(path = "user/showDestinations", method = RequestMethod.POST)
	public String showDestinations(Model model, 
			@RequestParam("continent") String continent,
			@RequestParam("country") String country,
			@RequestParam("city") String city,
			@RequestParam("personCount") int personCount,
			@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
			@RequestParam("vacationLength") int vacationLength,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir) {

		List<Destination> destinations;
		List<Long> temperatures = new ArrayList<>();
		List<String> icons = new ArrayList<>();
		List<NewsResponse> newsHealth = new ArrayList<>();
		List<NewsResponse> newsEntertainment = new ArrayList<>();
		List<NewsResponse> newsGeneral = new ArrayList<>();
		
		//Handle selected Destination
		if(!city.equals("null")) {
			destinations = destinationService.getDestinationBycategoryAndName(3, city,sortField,sortDir);
			//System.out.println("City = "+city+"------");
		}else if(!country.equals("null")) {
			destinations = destinationService.getDestinationBycategoryAndName(2, country,sortField,sortDir);
			//System.out.println("Country = "+country+"------");
		}else if(!continent.equals("null")) {
			destinations = destinationService.getDestinationBycategoryAndName(1, continent,sortField,sortDir);
			//System.out.println("Continent = "+continent+"------");
		} else {
			destinations = destinationService.getAllDestinations(Optional.of(sortDir),Optional.of(sortField));
			//System.out.println("Nothing Selected -------");
		}
		
		//Handle icons and temp data from the filtered destinations
		for (Destination d : destinations) {
			temperatures.add(Math.round(weatherService.getCurrentWeather(d.getCity()).getMain().get("temp_min")));
			icons.add("img/weatherIcons/" + weatherService.getCurrentWeather(d.getCity()).getWeather().get(0).get("icon")+ "@2x.png");
			newsHealth.add(newsService.getNews(d.getAbbreviation(),"health"));
			newsEntertainment.add(newsService.getNews(d.getAbbreviation(),"entertainment"));
			newsGeneral.add(newsService.getNews(d.getAbbreviation(),"general"));
		}
		
		//System.out.println(newsHealth.get(0).getArticles().get(0).get);
		//Handle post data such as: person count, dates (from-to) etc 
		//(not good way to pass data from form to form maybe, need to optimize it)
		model.addAttribute("newsHealth",newsHealth);
		model.addAttribute("newsEntertainment",newsEntertainment);
		model.addAttribute("newsGeneral",newsGeneral);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("sortField",sortField);
		model.addAttribute("continent",continent);
		model.addAttribute("country",country);
		model.addAttribute("city",city);
		model.addAttribute("pCount",personCount);
		model.addAttribute("vacationL",vacationLength);
		model.addAttribute("dateF",dateFrom);
		model.addAttribute("icon", icons);
		model.addAttribute("weather", temperatures);
		model.addAttribute("destinations", destinations);
		
		return "user/showDestinations";
	}
	
	
}
