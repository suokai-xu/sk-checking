package com.sk.colud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.colud.entity.Weather;
import com.sk.colud.mapper.WeatherMapper;
import com.sk.colud.service.BaseServiceClient;
import com.sk.colud.service.WeatherService;

/**
* 2019年5月9日 下午3:17:29
* @HXing xu
* sk-data-service
* 
**/
@Service
public class WeatherServiceImpl  implements WeatherService{

	@Autowired
	private WeatherMapper weatherDao;
	 
	@Autowired
	private BaseServiceClient baseServiceClient;
	
	@Override
	public boolean addWeather(Weather weatherInfo) {
		 if(1 == baseServiceClient.insert(weatherInfo)){
	            return true;
	        } else {
	            return false;
	        }
	}

	@Override
	public Weather getUserByCode(String code) {
		Weather weather = weatherDao.findId(code);
		return weather;
	}

}
