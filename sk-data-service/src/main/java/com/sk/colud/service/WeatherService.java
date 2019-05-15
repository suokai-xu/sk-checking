package com.sk.colud.service;

import com.sk.colud.entity.Weather;

import cn.hutool.json.JSONObject;

/**
* 2019年5月9日 下午3:15:38
* @HXing xu
* sk-data-service
* 
**/

public interface WeatherService {

	/**
     * 新增天气
     *
     * @param userInfo
     */
    public boolean addWeather(Weather weatherInfo);

	/**
	 * 通CODE查询
	 * @param CODE
	 * @return
	 */
    Weather getUserByCode(String code , String date);
    
    /**
     * 获取缓存中数据
     * @param Key
     * @return
     */
    JSONObject findRrdis(String Key);
}
