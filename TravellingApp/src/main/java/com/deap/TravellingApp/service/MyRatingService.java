package com.deap.TravellingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.exceptions.RatingNotFoundException;
import com.deap.TravellingApp.model.Rating;
import com.deap.TravellingApp.repository.RatingRepository;

@Service
public class MyRatingService {

	@Autowired
	RatingRepository ratingRepository;
	
	public List<Rating> getAllRatings()
    {
        List<Rating> result = (List<Rating>) ratingRepository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Rating>();
        }
    }
	
	public List<Rating> getAllRatingsByUserId(Long id)
    {
        List<Rating> result = (List<Rating>) ratingRepository.findAll();
        List<Rating> userRatings = new ArrayList<Rating>();
        if(result.size() > 0) {
        	for(Rating r: result) {
        		if(r.getUser().getId() == id) {
        			userRatings.add(r);
        		}
        	}
        	return userRatings;
        } else {
            return new ArrayList<Rating>();
        }
    }
	
	public Rating getRandomRating(boolean withPicture)
    {
        List<Rating> result = (List<Rating>) ratingRepository.findAll();
        if(result.size() > 0) {
        	Random random = new Random();
        	List<Rating> withPic = new ArrayList<Rating>();
        	List<Rating> withoutPic = new ArrayList<Rating>();
        	if(withPicture) {
        		for(Rating r: result) {
        			if(r.getPictureName() != null) {
        				withPic.add(r);
        			}
        		}
        		return withPic.get(random.nextInt((withPic.size()-1)+1)+0);
        	}else {
        		for(Rating r: result) {
        			if(r.getPictureName() == null) {
        				withoutPic.add(r);
        			}
        		}
        		return withoutPic.get(random.nextInt((withoutPic.size()-1)+1)+0);
        	}
        	
        } else {
            return null;
        }
    }
     
	//bestimmte Buchung nach BuchungsID finden
    public Rating getRatingByRatingId(Long id) throws RatingNotFoundException 
    {
    	List<Rating> rating = (List<Rating>) ratingRepository.findAll();
         
    	if(rating.size() > 0) {
            for(Rating r : rating) {
            	if(r.getRatingId() == id) {
            		return r;
            	}
            }
        }
    	throw new RatingNotFoundException("Rating for given User and Activity id not found");
    }
    
    public List<Rating> getRatingByActivityId(Long id)
    {
    	List<Rating> rating = (List<Rating>) ratingRepository.findAll();
    	List<Rating> foundRatings = new ArrayList<Rating>();
         
    	if(rating.size() > 0) {
            for(Rating r : rating) {
            	if(r.getActivity().getActivityId() == id) {
            		foundRatings.add(r);
            	}
            }
            return foundRatings;
        }
    	
    	return foundRatings;
    }
	
	//bestimmte Buchung nach BuchungsID finden
    public Rating getRatingByUseridAndActivityid(Long userId,Long activityId) throws RatingNotFoundException 
    {
    	List<Rating> rating = (List<Rating>) ratingRepository.findAll();
         
    	if(rating.size() > 0) {
            for(Rating r : rating) {
            	if(r.getUser().getId() == userId && r.getActivity().getActivityId() == activityId) {
            		return r;
            	}
            }
        }
    	throw new RatingNotFoundException("Rating for given User and Activity id not found");
    } 
   
    public Rating createOrUpdateRating(Rating entity) 
    {
        if(entity.getRatingId() == null) 
        {
        	System.out.println("keine rating gefunden, also speichern");
        	System.out.println("VOR DEM SAVEN "+entity);
        	System.out.println(ratingRepository.count());
            entity = ratingRepository.save(entity);
            System.out.println("nach dem SAVEN  " +entity);
            return entity;
        } 
        else
        {
            Optional<com.deap.TravellingApp.model.Rating> rating = ratingRepository.findById(entity.getRatingId());
             
            if(rating.isPresent()) 
            {
            	System.out.println("id gefunden, also updaten");
            	com.deap.TravellingApp.model.Rating newEntity = rating.get();

                newEntity.setRatingId(entity.getRatingId());
                newEntity.setRating(entity.getRating());
                newEntity.setUser(entity.getUser());
                newEntity.setComment(entity.getComment());
                newEntity.setRating(entity.getRating());
                newEntity.setRatingDate(entity.getRatingDate());
                newEntity.setPictureName(entity.getPictureName());
                newEntity = ratingRepository.save(newEntity);
                return newEntity;
            } else {
            	System.out.println("keine id also speichern");;
                entity = ratingRepository.save(entity);
                return entity;
            }
        }
    } 

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
