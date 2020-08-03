package com.deap.TravellingApp.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.deap.TravellingApp.model.Destination;

public interface DestinationRepository extends CrudRepository<Destination,Long>  {

	List<Destination> findAll(Sort sort);
}
