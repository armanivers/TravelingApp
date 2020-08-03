package com.deap.TravellingApp.designpattern.proxy;

import org.springframework.beans.factory.annotation.Autowired;

import com.deap.TravellingApp.model.User;
import com.deap.TravellingApp.service.MyUserDetailsService;

public class UserSearch extends UserProfileSearch{

	@Autowired
	MyUserDetailsService userService;
	
	@Override
	public User searchFor(String username) {
		return userService.getUserByUsername(username);
	}

}
