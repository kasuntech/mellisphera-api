package com.mellisphera.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mellisphera.weather.DailyWeather;


@Service
@Repository
public interface DailyWeatherRepository extends MongoRepository<DailyWeather,String>{
	
	List<DailyWeather> findDailyWeatherByIdApiary(String idApiary);
	
}