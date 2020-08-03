package com.deap.TravellingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.exceptions.BookingNotFoundException;
import com.deap.TravellingApp.model.Booking;
import com.deap.TravellingApp.repository.BookingRepository;

@Service
public class MyBookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	public List<Booking> getAllBookings()
    {
        List<Booking> result = (List<Booking>) bookingRepository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Booking>();
        }
    }
     
	//bestimmte Buchung nach BuchungsID finden
    public Booking getBookingById(Long id)
    {
        Optional<com.deap.TravellingApp.model.Booking> booking = bookingRepository.findById(id);
         
        if(booking.isPresent()) {
            return booking.get();
        } else {
            return null;
        }
    } 
    
    //alle Buchungen von einem User(id) finden
    public List<Booking> getUserBookingsById(Long id) 
    {
        List<Booking> bookings = (List<Booking>) bookingRepository.findAll();
        
        List<Booking> userBookings = new ArrayList<Booking>();
        
        if(bookings.size() > 0) {
            for(Booking b: bookings) {
            	if(b.getUser().getId() == id) {
            		userBookings.add(b);
            	}
            }
        }
        //if no bookings for the given user found, returns empty list
        return userBookings;
    } 
    
    //Muss noch implementiert werden
     
    public com.deap.TravellingApp.model.Booking createOrUpdateService(com.deap.TravellingApp.model.Booking entity) 
    {
        if(entity.getBookingId()== null) 
        {
            entity = bookingRepository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<com.deap.TravellingApp.model.Booking> booking = bookingRepository.findById(entity.getBookingId());
             
            if(booking.isPresent()) 
            {
            	com.deap.TravellingApp.model.Booking newEntity = booking.get();
                newEntity.setBookingId(entity.getBookingId());
                newEntity.setUser(entity.getUser());
                newEntity.setDestination(entity.getDestination());
                newEntity.setDateFrom(entity.getDateFrom());
                newEntity.setDateTo(entity.getDateTo());
                
                newEntity = bookingRepository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = bookingRepository.save(entity);
                 
                return entity;
            }
        }
    } 
    
    //Maybe add another Parameter user ID, check if the user owns this booking before deleting?? ("fake permissions"?)
    public void deleteBookingById(Long id) 
    {
    	Optional<com.deap.TravellingApp.model.Booking> booking = bookingRepository.findById(id);
        
        if(booking.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new BookingNotFoundException("No booking found by that ID");
        }
    } 
	
}
