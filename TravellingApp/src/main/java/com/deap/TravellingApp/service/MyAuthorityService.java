package com.deap.TravellingApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.model.Authority;
import com.deap.TravellingApp.repository.AuthorityRepository;

@Service
public class MyAuthorityService {

	@Autowired
	AuthorityRepository authorityRepository;
	
	//set Role to user (bad way(!), needs to be rewritten?)
    public void setUserAuthority(com.deap.TravellingApp.model.User entity,int authorityId) {
    	List<Authority> auth = (ArrayList<Authority>) authorityRepository.findAll();
    	System.out.println("AUTHORITIES = "+auth.toString());
    	
    	//0 = admin, 1 = user, 2 = provider (*activity provider)
    	
    	//if account should be user, remove admin rights
    	if(authorityId == 2) {
    		auth.remove(2);
    		auth.remove(0);
    		entity.setAuthority(auth);
    	} else if (authorityId == 3) {
    		auth.remove(1);
    		auth.remove(0);
    	} else {
    		//else account is selected to be admin, remove user rights (just in database! spring security still works fine)
    		auth.remove(2);
    		auth.remove(1);
        	entity.setAuthority(auth);

    	}
    }
	
}
