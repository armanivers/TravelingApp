package com.deap.TravellingApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.deap.TravellingApp.model.Booking;

public interface BookingRepository extends CrudRepository<Booking,Long>  {

}
