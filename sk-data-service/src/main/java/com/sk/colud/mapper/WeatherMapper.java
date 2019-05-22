package com.sk.colud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sk.colud.entity.CityCode;
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

	@Select("select * from m_weather where code=#{code}  and date = #{date}")
	Weather findId(@Param("code") String code,@Param("date") String date );
	
	@Select("SELECT * FROM `m_city_code`  where pid = 1  OR id = 1")
	List<CityCode> getAll();
	
}
