package com.deap.TravellingApp.designpattern.proxy;

import com.deap.TravellingApp.model.User;

public abstract class UserProfileSearch {
	public abstract User searchFor(String username);
}
