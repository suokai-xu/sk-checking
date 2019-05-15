package com.sk.colud.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.sk.colud.entity.Weather;
import com.sk.colud.mapper.WeatherMapper;
import com.sk.colud.service.BaseServiceClient;
import com.sk.colud.service.WeatherService;

import cn.hutool.json.JSONObject;

/**
* 2019年5月9日 下午3:17:29
* @HXing xu
* sk-data-service
* 
**/
@Service
public class WeatherServiceImpl  implements WeatherService{

	private static final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);
	@Autowired
	private WeatherMapper weatherDao;
	 
	@Autowired
	private BaseServiceClient baseServiceClient;
	
	 @SuppressWarnings("rawtypes")
	 @Autowired
	 private RedisTemplate redisTemplate;
	 
	 @SuppressWarnings("unused")
	 @Autowired
	 private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public boolean addWeather(Weather weatherInfo) {
		 if(1 == baseServiceClient.insert(weatherInfo)){
			 	String key = "weatherInfo"+weatherInfo.getCode()+weatherInfo.getDate();
		        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
				operations.set(key, weatherInfo , 1 , TimeUnit.DAYS);
	            return true;
	        } else {
	            return false;
	        }
	}

	@Override
	public Weather getUserByCode(String code , String date) {
		String key = "weatherInfo"+code+date;
		ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
		if(hasKey) {
			Weather weather = (Weather) operations.get(key);
            log.info("WeatherServiceImpl.getUserByCode() : 从缓存中获取了用户 >> " + weather.toString());
            return weather;
		}else {
			Weather weather = weatherDao.findId(code, date);
			operations.set(key, weather, 1 , TimeUnit.DAYS);
			return weather;
		}
		
	}

	@Override
	public JSONObject  findRrdis(String Key) {
		
		ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
		JSONObject str = new JSONObject();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(Key);
		if(hasKey) { 
			str = (JSONObject) operations.get(Key);
            log.info("WeatherServiceImpl.getUserByCode() : 从缓存中获取了天气数据 >> " + str.toString());
           
		}else {
            log.info("WeatherServiceImpl.getUserByCode() : 从缓存中没有数据 >> ");

		}
		 return str;
	}

}
