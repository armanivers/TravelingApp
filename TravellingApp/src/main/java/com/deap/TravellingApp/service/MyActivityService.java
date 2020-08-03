package com.deap.TravellingApp.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.model.Activity;
import com.deap.TravellingApp.model.Booking;
import com.deap.TravellingApp.model.BookingActivity;
import com.deap.TravellingApp.repository.ActivityRepository;

@Service
public class MyActivityService {

	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	MyDestinationService destinationService;
	
	@Autowired
	MySeasonService seasonService;
	
	public List<Activity> getAllActivities()
    {
        List<Activity> result = (List<Activity>) activityRepository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Activity>();
        }
    }
	
	public List<Activity> getAllDisabledActivities()
    {
        List<Activity> result = (List<Activity>) activityRepository.findAll();
        List<Activity> disabled = new ArrayList<Activity>();
        if(result.size() > 0) {
        	for(Activity a : result) {
        		if(!a.isEnabled()) {
        			disabled.add(a);
        		}
        	}
            return disabled;
        } else {
            return new ArrayList<Activity>();
        }
    }
	
	public List<Activity> getAllEnabledActivities()
    {
        List<Activity> result = (List<Activity>) activityRepository.findAll();
        List<Activity> disabled = new ArrayList<Activity>();
        if(result.size() > 0) {
        	for(Activity a : result) {
        		if(a.isEnabled()) {
        			disabled.add(a);
        		}
        	}
            return disabled;
        } else {
            return new ArrayList<Activity>();
        }
    }
	
	public List<Activity> getAllActivitiesByProvider(String provider)
    {
        List<Activity> all = (List<Activity>) activityRepository.findAll();
        List<Activity> result = new ArrayList<Activity>();
        if(all.size() > 0) {
        	for(Activity a : all) {
        		if(a.getProvider().equals(provider)) {
        			result.add(a);
        		}
        	}
            return result;
        } else {
            return new ArrayList<Activity>();
        }
    }
     
	//bestimmte Buchung nach BuchungsID finden
    public List<Activity> getAvailableActivitiesForBooking(Booking b){
    	
    	List<Activity> activities = (List<Activity>) activityRepository.findAll();
        List<Activity> availableActivities = new ArrayList<Activity>();
        
    	for(Activity a: activities) {

    		if(a.getDestination().equals(b.getDestination()) && a.isEnabled()){   //&& !activityBooked(b,a)
    			availableActivities.add(a);
    		}

    	}
    	
    	return availableActivities;

    } 

    public com.deap.TravellingApp.model.Activity getActivityById(Long id) 
    {
        Optional<com.deap.TravellingApp.model.Activity> activity = activityRepository.findById(id);
         
        if(activity.isPresent()) {
            return activity.get();
        } else {
            return null;
        }
    } 
    
     
    public com.deap.TravellingApp.model.Activity createOrUpdateActivity(com.deap.TravellingApp.model.Activity entity) 
    {
        if(entity.getActivityId() == null) 
        {

        	entity.setDestination(destinationService.getDestinationById(entity.getTemp_destinationid()));
        	entity.setSeason(seasonService.getSeasonById(entity.getTemp_seasonid()));
        	System.out.println(entity.toString());
            entity = activityRepository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<com.deap.TravellingApp.model.Activity> activity = activityRepository.findById(entity.getActivityId());
             
            if(activity.isPresent()) 
            {
            	com.deap.TravellingApp.model.Activity newEntity = activity.get();
            	
                newEntity.setActivityId(entity.getActivityId());
                newEntity.setDestination(entity.getDestination());
                newEntity.setName(entity.getName());
                newEntity.setEnabled(entity.isEnabled());
                newEntity.setVideoLink(entity.getVideoLink());
                newEntity.setSeason(entity.getSeason());
                newEntity.setDescription(entity.getDescription());
                newEntity.setPrice(entity.getPrice());
                newEntity.setDifficulty(entity.getDifficulty());
                newEntity.setActivititiesAvailable(entity.getActivititiesAvailable());
                newEntity.setRatings(entity.getRatings());
                
                newEntity = activityRepository.save(newEntity);
                 
                return newEntity;
            } else {
            	entity.setDestination(destinationService.getDestinationById(entity.getTemp_destinationid()));
            	entity.setSeason(seasonService.getSeasonById(entity.getTemp_seasonid()));
                entity = activityRepository.save(entity);
                 
                return entity;
            }
        }
    } 

    //falls wir nicht erlauben wollen, dass eine Aktivitat 2 mal gebucht wird
//	public boolean activityBooked(Booking b,Activity a) {
//		
//		List<BookingActivity> ba = b.getBookingActivities();
//		
//    	for(int i = 0; i < ba.size() ; i++) {
//    		if(ba.get(i).getActivityAvailable().getActivity().getActivityId() == a.getActivityId()){
//    			return true;
//    		}
//    }
//    	return false;
//	}
    
//    //Maybe add another Parameter user ID, check if the user owns this booking before deleting?? ("fake permissions"?)
//    public void deleteBookingById(Long id) 
//    {
//    	Optional<com.deap.TravellingApp.model.Booking> booking = bookingRepository.findById(id);
//        
//        if(booking.isPresent()) {
//            bookingRepository.deleteById(id);
//        } else {
//            throw new BookingNotFoundException("No booking found by that ID");
//        }
//    } 
	
}
