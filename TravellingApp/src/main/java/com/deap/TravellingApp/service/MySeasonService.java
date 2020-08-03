package com.deap.TravellingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deap.TravellingApp.model.Season;
import com.deap.TravellingApp.repository.SeasonRepository;

@Service
public class MySeasonService {

	@Autowired
	SeasonRepository seasonRepository;
	
	public List<Season> getAllSeasons()
    {
        List<Season> result = (List<Season>) seasonRepository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Season>();
        }
    }
	
	public com.deap.TravellingApp.model.Season getSeasonById(Long id) 
    {
        Optional<com.deap.TravellingApp.model.Season> season = seasonRepository.findById(id);
         
        if(season.isPresent()) {
            return season.get();
        } else {
            return null;
        }
    } 
}
