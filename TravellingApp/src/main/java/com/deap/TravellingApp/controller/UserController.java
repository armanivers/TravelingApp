package com.deap.TravellingApp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyAuthorityService;
import com.deap.TravellingApp.service.MyEmailService;
import com.deap.TravellingApp.service.MyUserDetailsService;
import com.deap.TravellingApp.util.GeneratePdfReport;

@Controller
public class UserController {

	@Autowired
	private MyUserDetailsService userService;
	
	@Autowired
	private MyAuthorityService authorityService;
	
	@Autowired
	private MyEmailService emailService;
	
	@RequestMapping(value = "/admin/manageUsers")
	public String getAllLocations(Model model,Principal p) {
		User user = userService.getUserByUsername(p.getName());
		model.addAttribute("user", user);
		model.addAttribute("users", userService.getAllUsers());
		return "admin/manageUsers";
	}

	@RequestMapping(path = { "/admin/manageUsers/edit", "/admin/manageUsers/edit/{id}" })
	public String editUserById(Model model, @PathVariable("id") Optional<Long> id,Principal p) {
		User currentUser = userService.getUserByUsername(p.getName());
		model.addAttribute("user", currentUser);
		if (id.isPresent()) {
			User user = userService.getUserById(id.get());
			model.addAttribute("userNew", user);
		} else {
			model.addAttribute("userNew", new User());
		}
		return "admin/add-edit-user";
	}

	@RequestMapping(path = "/admin/manageUsers/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id){
		userService.deleteUserById(id);
		model.addAttribute("user",userService.getUserById(id));
		model.addAttribute("users", userService.getAllUsers());
		return "admin/manageUsers";
	}

	@RequestMapping(path = "/admin/manageUsers/createUser", method = RequestMethod.POST)
	public String createOrUpdateUser(Model model,User user, @RequestParam("authority") int authority,Principal p) {
		//System.out.println("SELECTED AUTHORITY ROLE = "+authority);
		User currentUser = userService.getUserByUsername(p.getName());
		model.addAttribute("user", currentUser);
		authorityService.setUserAuthority(user, authority);
		userService.createOrUpdateUser(user);
		model.addAttribute("users", userService.getAllUsers());
		return "admin/manageUsers";
	}
	
	@RequestMapping(path = "/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model,@RequestParam("password") String password,
			@RequestParam("passwordConfirm") String passwordConfirm,@RequestParam("userid") Long userid) {
		
		boolean result = userService.changePassword(password, passwordConfirm, userid);
		model.addAttribute("user", userService.getUserById(userid));
		model.addAttribute("showMessage", true);
		if(result) {
			model.addAttribute("success", "passTrue");
		}else {
			model.addAttribute("success", "passFalse");
		}

		return "myProfile";
	
	}
	
	@RequestMapping(path = "/changeProfilePicture", method = RequestMethod.POST, headers=("content-type=multipart/*"))
	public String changeProfilePicture(Model model,@RequestParam("userid") Long userid,@RequestParam("profilePicture") MultipartFile picture) {
		
		User user = userService.getUserById(userid);
		
		model.addAttribute("showMessage", true);
		model.addAttribute("user", user);
		
		if(picture.isEmpty()) {
			System.out.println("no pic");
			model.addAttribute("success", "picFalse");
		}else {
		try {
			int l = picture.getOriginalFilename().length();
			String format = "."+picture.getOriginalFilename().substring(l-3, l);

			Path path = Paths.get("src\\main\\resources\\static\\img\\userProfilePictures\\"+user.getUsername()+format);
			Files.write(path, picture.getBytes());
			System.out.println("picture uploaded to"+path.toString());
			
			user.setProfilePicture(user.getUsername()+format);
			userService.createOrUpdateUser(user);
			
			System.out.println(user.getUsername()+format);
			model.addAttribute("success", "picTrue");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		}
		return "myProfile";
	
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registerUser(Model model, User user) {

		model.addAttribute("message", true);
		model.addAttribute("textMessage", userService.registerUser(user));
		
		return "register";
	
	}
	
	@RequestMapping(value="/forgot-password")
	public String forgotUserPasswordPage(Model model) {
		model.addAttribute("error",false);
		return "forgotPassword";
	}
	
	@RequestMapping(value="/forgot-password", method = RequestMethod.POST)
	public String forgotUserPassword(Model model,@RequestParam("email") String email) {
		
		//find user by email
		Optional<User> userOptional = userService.findUserByEmail(email);
		
		if(!userOptional.isPresent()) {
		model.addAttribute("error",true);
		model.addAttribute("errorMessage","No user found by the given E-Mail.");

		}else {
			//generate token and save it to it's user's db
			User user = userOptional.get();
			user.setResetToken(UUID.randomUUID().toString());
			userService.save(user);
			
			//Send E-Mail
			SimpleMailMessage resetEmail = new SimpleMailMessage();
			String resetString = "To reset your password, please visit the link: \n http://localhost:8080/reset?token="+user.getResetToken();
			resetEmail.setFrom("support@deap.com");
			resetEmail.setTo(user.getEmail());
			resetEmail.setSubject("Password Reset");
			resetEmail.setText(resetString);
			System.out.println(resetString);
			
			//Sending email not working right now, I haven't set up a smtp-activated google account
			//emailService.sendEmail(resetEmail);
			
			model.addAttribute("success",true);
			model.addAttribute("successMessage","Password reset successful! Check your E-Mail for further instructions.");
		}
		
		return "forgotPassword";
	}
	
	@RequestMapping(value="/reset")
	public String resetPasswordPage(Model model,@RequestParam("token") String token) {
		
		Optional<User> user = userService.findUserByResetToken(token);
		
		if(user.isPresent()) {
			model.addAttribute("user",user.get());
			model.addAttribute("token",token);
			System.out.println(user.get().getUsername());
			System.out.println(token);
			return "resetPassword";
		} else {
			model.addAttribute("error",true);
			model.addAttribute("errorMessage","You tried accessing an invalid or expired token, please try again.");
			return "forgotPassword";
		}
	}
	
	@RequestMapping(value="/reset",method = RequestMethod.POST)
	public String resetPassword(Model model,@RequestParam("token") String token,@RequestParam("password") String password) {
		
		Optional<User> user = userService.findUserByResetToken(token);
		
		if(user.isPresent()) {
			userService.changePassword(password, password, user.get().getId());
			return "login";
		} else {
			model.addAttribute("error",true);
			model.addAttribute("errorMessage","You tried accessing an invalid or expired token, pelase try agian.");
			return "forgotPassword";
		}
	}
	
	// Generate pdf file of all users in the database
	@RequestMapping(value = "/admin/manageUsers/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> usersReport() throws IOException {
		List<User> users = userService.getAllUsers();

		ByteArrayInputStream bis = GeneratePdfReport.usersReport(users);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=usersreport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

}
