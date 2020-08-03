package com.deap.TravellingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.model.Hotel;
import com.deap.TravellingApp.repository.HotelRepository;

@Service
public class MyHotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	public List<Hotel> getAllHotels()
    {
        List<Hotel> result = (List<Hotel>) hotelRepository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Hotel>();
        }
    }
	
	//bestimmte Buchung nach BuchungsID finden
    public Hotel getHotelById(Long id)
    {
        Optional<com.deap.TravellingApp.model.Hotel> hotel = hotelRepository.findById(id);
         
        if(hotel.isPresent()) {
            return hotel.get();
        } else {
            return null;
        }
    } 
	
	public List<Hotel> findHotelsByDestinationId(Long id){
		List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();
        
        List<Hotel> hotelDestinations= new ArrayList<Hotel>();
        
        if(hotels.size() > 0) {
            for(Hotel h: hotels) {
            	if(h.getDestinationId() == id) {
            		hotelDestinations.add(h);
            	}
            }
        }
        //if no bookings for the given user found, returns empty list
        return hotelDestinations;
	}
	
}
