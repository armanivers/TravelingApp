package com.deap.TravellingApp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.deap.TravellingApp.model.Booking;
import com.deap.TravellingApp.model.Rating;
import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyBookingService;
import com.deap.TravellingApp.service.MyDestinationService;
import com.deap.TravellingApp.service.MyHotelService;
import com.deap.TravellingApp.service.MyUserDetailsService;
import com.deap.TravellingApp.util.GeneratePdfReport;

@Controller
public class BookingController {

	@Autowired
	MyUserDetailsService userService;

	@Autowired
	MyBookingService bookingService;

	@Autowired
	MyDestinationService destinationService;

	@Autowired
	MyHotelService hotelService;
	
	@RequestMapping(value = "admin/manageBookings")
	public String getAllBookings(Model model,Principal p) {

		List<Booking> userBookings = bookingService.getAllBookings();
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("bookings", userBookings);

		return "admin/manageBookings";

	}

	@GetMapping({ "/user/myBookings", "/admin/myBookings" , "/provider/myBookings"})
	public String getMyBookings(Model model, Principal p) {
		// Principal benutzt um den eingeloggten user rauszufinden
		User user = userService.getUserByUsername(p.getName());

		List<Booking> userBookings = bookingService.getUserBookingsById(user.getId());
		model.addAttribute("user", user);
		model.addAttribute("bookings", userBookings);
		model.addAttribute("showMessage", false);
		return "user/myBookings";
	}

	@RequestMapping(path ="/user/displayBooking/{id}")
	public String displayBookingById(Model model, Principal p,@PathVariable("id") Optional<Long> id) {
		if (id.isPresent()) {
			User user = userService.getUserByUsername(p.getName());
			
			model.addAttribute("user", user);
			model.addAttribute("booking", bookingService.getBookingById(id.get()));
			return "user/displayBooking";
			} else {
			return "user/myBookings";
		}
	}
	
	@RequestMapping(path = "/user/createBooking/{destinationId}/{personCount}/{dateFrom}/{vacationLength}/{hotelid}")
	public String editUserById(Model model, Principal p,
			@PathVariable("destinationId") Optional<Long> destinationId,
			@PathVariable("personCount") int perCount,
			@PathVariable("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
			@PathVariable("vacationLength") int vacationLength,
			@PathVariable("hotelid") Long hotelid) {
		User user = userService.getUserByUsername(p.getName());
		if (destinationId.isPresent()) {
			Booking newBooking = new Booking(user,perCount,destinationService.getDestinationById(destinationId.get()),hotelService.getHotelById(hotelid),dateFrom,dateFrom.plusDays(vacationLength));
			bookingService.createOrUpdateService(newBooking);
		} else {
			System.out.println("Doesnt Exist");
		}
		model.addAttribute("user",user);
		model.addAttribute("bookings",bookingService.getUserBookingsById(user.getId()));
		model.addAttribute("showMessage", true);
		return "user/myBookings";
	}
	
	@RequestMapping(path = "/user/displayBooking/bookingSummary/{bookingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> bookingSummary(@PathVariable("bookingId") Optional<Long> bookingId) throws IOException {
		
		Booking b = bookingService.getBookingById(bookingId.get());
		
		ByteArrayInputStream bis = GeneratePdfReport.bookingSummary(b);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=bookingSummary"+b.getDestination().getCity()+".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
		
	}

}