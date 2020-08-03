package com.deap.TravellingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.exceptions.UserDisabledException;
import com.deap.TravellingApp.exceptions.UserNotFoundException;
import com.deap.TravellingApp.model.Authority;
import com.deap.TravellingApp.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MyAuthorityService authorityService;
	
	public List<com.deap.TravellingApp.model.User> getAllUsers()
    {
        List<com.deap.TravellingApp.model.User> result = (List<com.deap.TravellingApp.model.User>) userRepository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<com.deap.TravellingApp.model.User>();
        }
    }
     
    public com.deap.TravellingApp.model.User getUserById(Long id)
    {
        Optional<com.deap.TravellingApp.model.User> user = userRepository.findById(id);
         
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
     
	public Optional<com.deap.TravellingApp.model.User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<com.deap.TravellingApp.model.User> findUserByResetToken(String resetToken) {
		return userRepository.findByResetToken(resetToken);
	}
    
	public void save(com.deap.TravellingApp.model.User user) {
		userRepository.save(user);
	}
	
    public boolean registerUser(com.deap.TravellingApp.model.User newUser) 
    {
        	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        	if(newUser.getPassword().equals(newUser.getConfirmPassword())) {
        		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        		newUser.setEnabled(true);
        		newUser.setProfilePicture("user.png");
        		authorityService.setUserAuthority(newUser, 2);
            	newUser = userRepository.save(newUser);
            	//maybe return user instead of boolean
            	return true;
        	}else {
        		return false;
        	}
    } 
    
    public boolean changePassword(String password, String passwordConfirm,Long id) {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
    	com.deap.TravellingApp.model.User u = getUserById(id);
    	
    	if(password.equals(passwordConfirm)) {
    		u.setPassword(bCryptPasswordEncoder.encode(password));
    		u.setResetToken(null);
    		userRepository.save(u);
        	return true;
    	}else {
    		return false;
    	}
    }
    
    public com.deap.TravellingApp.model.User createOrUpdateUser(com.deap.TravellingApp.model.User entity) 
    {
        if(entity.getId() == null) 
        {
        	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        	entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
            entity = userRepository.save(entity);
            return entity;
        } 
        else
        {
            Optional<com.deap.TravellingApp.model.User> user = userRepository.findById(entity.getId());
             
            if(user.isPresent()) 
            {
            	com.deap.TravellingApp.model.User newEntity = user.get();
                newEntity.setId(entity.getId());
                newEntity.setUsername(entity.getUsername());
                newEntity.setEnabled(entity.isEnabled());
                newEntity.setAuthority(entity.getAuthority());
                newEntity.setEmail(entity.getEmail());
                newEntity.setProfilePicture(entity.getProfilePicture());
                
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
                newEntity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
                
                newEntity = userRepository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = userRepository.save(entity);
                 
                return entity;
            }
        }
    } 
    
    
    public void deleteUserById(Long id) throws UserNotFoundException 
    {
        Optional<com.deap.TravellingApp.model.User> employee = userRepository.findById(id);
         
        if(employee.isPresent()) 
        {
        	userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("No user exists for the given id");
        }
    } 
	
    public com.deap.TravellingApp.model.User getUserByUsername(String username) throws UsernameNotFoundException{
    	// Search for user with this repository, if it doesnt exist, throw an exception
    	com.deap.TravellingApp.model.User user = userRepository.findByUsername(username)
    			.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    	return user;
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws AuthenticationException {
		
		// Search for user with this repository, if it doesnt exist, throw an exception
		com.deap.TravellingApp.model.User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
		
		if (appUser != null && appUser.isEnabled()) {
			// Map our authority list to spring security authority list
			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			for (Authority authority : appUser.getAuthority()) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
				grantList.add(grantedAuthority);
			}

			// Create user object, log it and return it
			UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
			return user;
		}else {
			//If user exists but isn't enabled, throw account is disabled error
			throw new UserDisabledException("Account is disabled");
		}
		
	}

}