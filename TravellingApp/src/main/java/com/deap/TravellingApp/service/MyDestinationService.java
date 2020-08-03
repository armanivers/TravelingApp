package com.deap.TravellingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.exceptions.DestinationNotFoundException;
import com.deap.TravellingApp.model.Destination;
import com.deap.TravellingApp.repository.DestinationRepository;

@Service
public class MyDestinationService {

	@Autowired
	DestinationRepository destinationRepository;
	
	public List<Destination> getAllDestinations()
    {
        List<Destination> result = (List<Destination>) destinationRepository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Destination>();
        }
    }
	
	public List<Destination> getAllDestinations(Optional<String> sortDir, Optional<String> sortField)
    {
		List<Destination> result;
		
		if(sortDir.isPresent() && sortField.isPresent()) {
			Sort sort = sortDir.get().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField.get()).ascending() 
	    			: Sort.by(sortField.get()).descending();
			result = (List<Destination>) destinationRepository.findAll(sort);
		}else {
			result = (List<Destination>) destinationRepository.findAll();
		}

         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Destination>();
        }
    }
	
	//bestimmte Destination nach DestinationID finden
    public com.deap.TravellingApp.model.Destination getDestinationById(Long id) 
    {
        Optional<com.deap.TravellingApp.model.Destination> destination = destinationRepository.findById(id);
         
        if(destination.isPresent()) {
            return destination.get();
        } else {
            return null;
        }
    } 
    
    //variable category um sich 3 unterschiedliche methoden fuer kontinent/land/stadt zu sparen
    public ArrayList<Destination> getDestinationBycategoryAndName(int category,String location,String sortField, String sortDirection) 
    {
    	ArrayList<Destination> result = new ArrayList<Destination>();
    	
    	Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() 
    			: Sort.by(sortField).descending();
    	//1 = Kontinent, 2 = Land, 3 = Stadt
    	switch(category) {
    	case 1:
    		for(Destination d :destinationRepository.findAll(sort)) {
    			if(d.getContinent().equals(location)) {
    				result.add(d);
    			}
    		}
    	case 2:
    		for(Destination d :destinationRepository.findAll(sort)) {
    			if(d.getCountry().equals(location)) {
    				result.add(d);
    			}
    		}
    	case 3:
    		for(Destination d :destinationRepository.findAll(sort)) {
    			if(d.getCity().equals(location)) {
    				result.add(d);
    			}
    		}
    	default:
    	}
    	
    	if(result.size()>0) {
    		return result;
    	}else {
    		return new ArrayList<Destination>();
    	}
    } 
    
    //Should only be accessed by an admin 
    public com.deap.TravellingApp.model.Destination createOrUpdateDestination(com.deap.TravellingApp.model.Destination entity) 
    {
        if(entity.getDestinationId()== null) 
        {
            entity = destinationRepository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<com.deap.TravellingApp.model.Destination> destination = destinationRepository.findById(entity.getDestinationId());
             
            if(destination.isPresent()) 
            {
            	com.deap.TravellingApp.model.Destination newEntity = destination.get();
                newEntity.setDestinationId(entity.getDestinationId());
                newEntity.setCountry(entity.getCountry());
                newEntity.setCity(entity.getCity());
                newEntity.setHotels(entity.getHotels());
                
                newEntity = destinationRepository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = destinationRepository.save(entity);
                 
                return entity;
            }
        }
    } 
    
    //Do we need do be able to delete a destination?
	//-> should only be accessed by an admin to delete + add new destinations
	public void deleteDestinationById(Long id) 
    {
    	Optional<com.deap.TravellingApp.model.Destination> destination = destinationRepository.findById(id);
        
        if(destination.isPresent()) {
        	destinationRepository.deleteById(id);
        } else {
            throw new DestinationNotFoundException("No destination found by that ID");
        }
    }  
	
}
