package com.deap.TravellingApp.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.exceptions.ActivityAvailabilityNotFoundException;
import com.deap.TravellingApp.model.ActivityAvailable;
import com.deap.TravellingApp.model.Booking;
import com.deap.TravellingApp.model.BookingActivity;
import com.deap.TravellingApp.repository.ActivityAvailabilityRepository;

@Service
public class MyActivityAvailabilityService {

	@Autowired
	ActivityAvailabilityRepository activityAvailabilityRepository;
	@Autowired
	MyBookingService bookingService;
	@Autowired
	MyActivityService activityService;
	
	public List<ActivityAvailable> getAllAvailibleActivities()
    {
        List<ActivityAvailable> result = (List<ActivityAvailable>) activityAvailabilityRepository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<ActivityAvailable>();
        }
    }
     
    public ActivityAvailable getActivityAvailabilityById(Long id) throws ActivityAvailabilityNotFoundException 
    {
        Optional<com.deap.TravellingApp.model.ActivityAvailable> activityAvailability = activityAvailabilityRepository.findById(id);
         
        if(activityAvailability.isPresent()) {
            return activityAvailability.get();
        } else {
            throw new ActivityAvailabilityNotFoundException("No Aavailable Activity found by that ID");
        }
    } 
    
    //all availabilities for a specific activity
    public List<ActivityAvailable> getAvailabilitiesForActivityId(Long id,Long bookingId) 
    {
        List<ActivityAvailable> allAvailabilities = (List<ActivityAvailable>) activityAvailabilityRepository.findAll();
        
        List<ActivityAvailable> availabilities = new ArrayList<ActivityAvailable>();
        
        Booking b = bookingService.getBookingById(bookingId);
        
        if(allAvailabilities.size() > 0) {
            for(ActivityAvailable a: allAvailabilities) {
            	if(a.getActivity().getActivityId() == id && !hasBookedActivity(b,a) && a.getSlots() >= b.getPersonCount() && a.isBetweenDates(b)) {
            		availabilities.add(a);
            	}
            }
        }
        //if no availabilities for the given activity found, returns empty list
        return availabilities;
    } 
    
    public boolean hasBookedActivity(Booking b,ActivityAvailable aa) {
    	List<BookingActivity> ba = b.getBookingActivities();
    	
    	for(int i = 0; i<ba.size() ; i++) {
    		if(ba.get(i).getActivityAvailable().getActivityAvailableId() == aa.getActivityAvailableId() ||
    				!isDuringOtherActivity(ba.get(i).getActivityAvailable(),aa)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isDuringOtherActivity(ActivityAvailable a1, ActivityAvailable a2) {
    	
    	if(a2.getStartTime().before(a1.getStartTime()) && a2.getEndTime().before(a1.getStartTime())) {
    		return true;
    	}
    	
    	if(a2.getStartTime().after(a1.getEndTime()) && a2.getEndTime().after(a1.getEndTime())) {
    		return true;
    	}

    	return false;

    			
    }
    
    //Muss noch implementiert werden
     
    public com.deap.TravellingApp.model.ActivityAvailable createOrUpdateActivityAvailability(com.deap.TravellingApp.model.ActivityAvailable entity,Timestamp t1,Timestamp t2) 
    {
        if(entity.getActivityAvailableId()== null) 
        {
        	entity.setActivity(activityService.getActivityById(entity.getTemp_activityid()));
            entity.setStartTime(t1);
            entity.setEndTime(t2);
            
        	entity = activityAvailabilityRepository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<com.deap.TravellingApp.model.ActivityAvailable> activityAvail = activityAvailabilityRepository.findById(entity.getActivityAvailableId());
             
            if(activityAvail.isPresent()) 
            {
            	com.deap.TravellingApp.model.ActivityAvailable newEntity = activityAvail.get();
            	
            	newEntity.setActivityAvailableId(entity.getActivityAvailableId());
            	newEntity.setBookingActivities(entity.getBookingActivities());
            	newEntity.setActivity(entity.getActivity());
            	newEntity.setStartTime(entity.getStartTime());
            	newEntity.setEndTime(entity.getEndTime());
            	newEntity.setDate(entity.getDate());
            	newEntity.setMaxSlots(entity.getMaxSlots());
            	newEntity.setSlots(entity.getSlots());
                
                newEntity = activityAvailabilityRepository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = activityAvailabilityRepository.save(entity);
                 
                return entity;
            }
        }
    } 
//    
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
