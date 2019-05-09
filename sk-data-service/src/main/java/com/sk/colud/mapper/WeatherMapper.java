package com.sk.colud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sk.colud.entity.Weather;
import com.sk.colud.utils.MyMapper;

/**
* 2019年5月9日 下午3:04:27
* @HXing xu
* sk-data-service
* 
**/
@Mapper
public interface WeatherMapper extends MyMapper<Weather> {

	@Select("select * from m_weather where code=#{code} ")
	Weather findId(String code);
	
	
	
}
