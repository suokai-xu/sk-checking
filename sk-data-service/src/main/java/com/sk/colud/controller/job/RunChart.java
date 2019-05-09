package com.sk.colud.controller.job;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
 
import com.sk.colud.entity.Weather;
import com.sk.colud.service.WeatherService;

import io.swagger.annotations.Api;
 
/**
* 2019年5月9日 下午3:14:23
* @HXing xu
* sk-data-service
* 
**/
@Api("RunChart--定时任务API")
@RestController
public class RunChart {
	
	private Logger log = LoggerFactory.getLogger(RunChart.class);

	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Autowired
	private WeatherService weatherService;

	public synchronized void runRunChart() {
		
		try {
			//参数url化
			String city = "101010100";
			//拼地址 http://t.weather.sojson.com/api/weather/city/101030100
			String apiUrl = String.format(" http://t.weather.sojson.com/api/weather/city/"+city);
			//开始请求
			URL url = new URL(apiUrl);
	
			URLConnection open = url.openConnection();
			InputStream input = open.getInputStream();
			System.out.println(input);
			//这里转换为String，带上包名，怕你们引错包
			String result = IOUtils.toString(input,"utf-8");
			//输出
			log.debug("result" +  result);
			
			JSONObject obj = new JSONObject(result);
	        
	        JSONObject cityInfo = obj.getJSONObject("cityInfo");
	        JSONObject data = obj.getJSONObject("data");
	        JSONArray forecast = data.getJSONArray("forecast");
	        JSONObject jsonObject = forecast.getJSONObject(0);

	        Weather weather = new Weather();
	        weather.setCode(cityInfo.getString("cityId"));
	        weather.setDay((String) jsonObject.get("date"));
	        weather.setDate(obj.getString("date"));
	        weather.setShidu(data.getString("shidu"));
	        weather.setWendu(data.getString("wendu"));
	        weather.setSunrise((String) jsonObject.get("sunrise"));
	        weather.setHigh((String) jsonObject.get("high"));
	        weather.setLow((String) jsonObject.get("low"));
	        weather.setPm25(data.getString("pm25"));
	        weather.setPm10(data.getString("pm10"));
	        weather.setQuality(data.getString("quality"));
	        weather.setSunset((String) jsonObject.get("sunset"));
	        weather.setAqi("");
	        weather.setYmd((String) jsonObject.get("ymd"));
	        weather.setWeek((String) jsonObject.get("week"));
	        weather.setFx((String) jsonObject.get("fx"));
	        weather.setFl((String) jsonObject.get("fl"));
	        weather.setType((String) jsonObject.get("type"));
	        weather.setGanmao(data.getString("ganmao"));
	        weather.setNotice((String) jsonObject.get("notice"));
	        weather.setUpDateTime(cityInfo.getString("updateTime"));
	       /**
	        * 
	        */
	        if ( weatherService.addWeather(weather)) {
	        	log.debug("当前天气"+obj.getString("date")+"存入成功！！！！");
	        } else {
	        	log.debug("当前天气"+obj.getString("date")+"存入失败！！！");
	        }

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
