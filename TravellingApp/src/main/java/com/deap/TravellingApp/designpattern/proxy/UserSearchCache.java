package com.deap.TravellingApp.designpattern.proxy;

import java.util.HashMap;
import java.util.Map;

import com.deap.TravellingApp.model.User;

public class UserSearchCache extends UserProfileSearch{
	
	private UserSearch us;
	
	private Map<String,User> searchCache;
	
	public UserSearchCache() {
		us = new UserSearch();
		searchCache = new HashMap<String,User>();
	}

	@Override
	public User searchFor(String username) {
		User u;
		if(searchCache.containsKey(username)) {
			u = searchCache.get(username);
		}else {
			u = us.searchFor(username);
			searchCache.put(username, u);
		}
		return u;
	}
}
