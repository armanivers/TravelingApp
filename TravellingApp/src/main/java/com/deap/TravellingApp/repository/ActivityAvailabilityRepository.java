package com.deap.TravellingApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.deap.TravellingApp.model.ActivityAvailable;

public interface ActivityAvailabilityRepository extends CrudRepository<ActivityAvailable,Long>  {

}