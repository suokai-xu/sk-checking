package com.sk.colud.controller.job;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.sk.colud.entity.Weather;
import com.sk.colud.service.WeatherService;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;


 
/**
* 2019年5月9日 下午3:14:23
* @HXing xu
* sk-data-service
* 
**/
public class RunChart2 extends QuartzJobBean {
	
	private Logger log = LoggerFactory.getLogger(RunChart2.class);
	
	@Autowired
	private WeatherService weatherService;
	
	 @SuppressWarnings("rawtypes")
	 @Autowired
	 private RedisTemplate redisTemplate;
	 
	 @SuppressWarnings("unused")
	 @Autowired
	 private StringRedisTemplate stringRedisTemplate;
	 
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		log.info("执行---RunChart.runRunChart()");
		// 执行定时任务方法
		runProm();
		
	}

	public void runProm(){
		
		ThreadChart T1 = new ThreadChart( "ThreadChart-1" , weatherService , redisTemplate);
	     T1.start();
	}

}

class ThreadChart extends Thread {
	   private Thread t;
	   private String threadName;
	   private WeatherService weatherService;
	   private RedisTemplate redisTemplate;
	   private Logger log = LoggerFactory.getLogger(ThreadChart.class);
	   private DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		
		

	   
	   ThreadChart(String name , WeatherService service , RedisTemplate redis) {
	      threadName = name;
	      weatherService = service;
	      redisTemplate = redis;
	      
	      log.debug("Creating " +  threadName );
	   }
	   
	   public void run() {
	      log.debug("Running " +  threadName);
 
    	//参数url化
			String city = "101010100";
			
			Map<String, String> map = weatherService.getAll();
			
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
			    try {
			    	String key = iterator.next();
				    System.out.println(key + "　：" + map.get(key));
				    city = map.get(key);
				    log.debug("Running ---------" +  city );
				    runRunChart(city);
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	      log.debug("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start () {
	      log.debug("Starting " +  threadName);
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
	   
		 
		public synchronized void runRunChart(String city) {
			
			try {
				
				
				String date = format1.format(new Date());
				String key = "weatherInfo"+city+date;
				boolean hasKey = redisTemplate.hasKey(key);
				if(hasKey) {
					log.info("今天的时间已缓存》》》》"+hasKey);
					return;
				}else {
					//拼地址 http://t.weather.sojson.com/api/weather/city/101030100
					String apiUrl = String.format(" http://t.weather.sojson.com/api/weather/city/"+city);
					//开始请求
					URL url = new URL(apiUrl);
			
					URLConnection open = url.openConnection();
					InputStream input = open.getInputStream();
					//这里转换为String，带上包名，怕你们引错包
					String result = IOUtils.toString(input,"utf-8");
//					String result = "{\"time\":\"2019-05-07 16:17:44\",\"cityInfo\":{\"city\":\"天津市\",\"cityId\":\"101030100\",\"parent\":\"天津\",\"updateTime\":\"15:46\"},\"date\":\"20190507\",\"message\":\"Success !\",\"status\":200,\"data\":{\"shidu\":\"7%\",\"pm25\":23.0,\"pm10\":26.0,\"quality\":\"优\",\"wendu\":\"26\",\"ganmao\":\"各类人群可自由活动\",\"yesterday\":{\"date\":\"06\",\"sunrise\":\"05:09\",\"high\":\"高温 25.0℃\",\"low\":\"低温 16.0℃\",\"sunset\":\"19:08\",\"aqi\":49.0,\"ymd\":\"2019-05-06\",\"week\":\"星期一\",\"fx\":\"西北风\",\"fl\":\"3-4级\",\"type\":\"晴\",\"notice\":\"愿你拥有比阳光明媚的心情\"},\"forecast\":[{\"date\":\"07\",\"sunrise\":\"05:08\",\"high\":\"高温 28.0℃\",\"low\":\"低温 17.0℃\",\"sunset\":\"19:08\",\"aqi\":65.0,\"ymd\":\"2019-05-07\",\"week\":\"星期二\",\"fx\":\"西风\",\"fl\":\"3-4级\",\"type\":\"晴\",\"notice\":\"愿你拥有比阳光明媚的心情\"},{\"date\":\"08\",\"sunrise\":\"05:07\",\"high\":\"高温 27.0℃\",\"low\":\"低温 16.0℃\",\"sunset\":\"19:09\",\"aqi\":68.0,\"ymd\":\"2019-05-08\",\"week\":\"星期三\",\"fx\":\"西南风\",\"fl\":\"<3级\",\"type\":\"多云\",\"notice\":\"阴晴之间，谨防紫外线侵扰\"},{\"date\":\"09\",\"sunrise\":\"05:06\",\"high\":\"高温 28.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:10\",\"aqi\":100.0,\"ymd\":\"2019-05-09\",\"week\":\"星期四\",\"fx\":\"南风\",\"fl\":\"<3级\",\"type\":\"多云\",\"notice\":\"阴晴之间，谨防紫外线侵扰\"},{\"date\":\"10\",\"sunrise\":\"05:05\",\"high\":\"高温 29.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:11\",\"aqi\":111.0,\"ymd\":\"2019-05-10\",\"week\":\"星期五\",\"fx\":\"东南风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"11\",\"sunrise\":\"05:04\",\"high\":\"高温 26.0℃\",\"low\":\"低温 16.0℃\",\"sunset\":\"19:12\",\"aqi\":101.0,\"ymd\":\"2019-05-11\",\"week\":\"星期六\",\"fx\":\"东南风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"12\",\"sunrise\":\"05:03\",\"high\":\"高温 24.0℃\",\"low\":\"低温 14.0℃\",\"sunset\":\"19:13\",\"aqi\":124.0,\"ymd\":\"2019-05-12\",\"week\":\"星期日\",\"fx\":\"西北风\",\"fl\":\"4-5级\",\"type\":\"晴\",\"notice\":\"愿你拥有比阳光明媚的心情\"},{\"date\":\"13\",\"sunrise\":\"05:02\",\"high\":\"高温 23.0℃\",\"low\":\"低温 15.0℃\",\"sunset\":\"19:14\",\"ymd\":\"2019-05-13\",\"week\":\"星期一\",\"fx\":\"西南风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"14\",\"sunrise\":\"05:01\",\"high\":\"高温 30.0℃\",\"low\":\"低温 19.0℃\",\"sunset\":\"19:15\",\"ymd\":\"2019-05-14\",\"week\":\"星期二\",\"fx\":\"西南风\",\"fl\":\"4-5级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"15\",\"sunrise\":\"05:00\",\"high\":\"高温 30.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:16\",\"ymd\":\"2019-05-15\",\"week\":\"星期三\",\"fx\":\"南风\",\"fl\":\"<3级\",\"type\":\"小雨\",\"notice\":\"雨虽小，注意保暖别感冒\"},{\"date\":\"16\",\"sunrise\":\"04:59\",\"high\":\"高温 27.0℃\",\"low\":\"低温 16.0℃\",\"sunset\":\"19:17\",\"ymd\":\"2019-05-16\",\"week\":\"星期四\",\"fx\":\"南风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"17\",\"sunrise\":\"04:58\",\"high\":\"高温 28.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:18\",\"ymd\":\"2019-05-17\",\"week\":\"星期五\",\"fx\":\"东北风\",\"fl\":\"<3级\",\"type\":\"晴\",\"notice\":\"愿你拥有比阳光明媚的心情\"},{\"date\":\"18\",\"sunrise\":\"04:57\",\"high\":\"高温 28.0℃\",\"low\":\"低温 20.0℃\",\"sunset\":\"19:19\",\"ymd\":\"2019-05-18\",\"week\":\"星期六\",\"fx\":\"西北风\",\"fl\":\"4-5级\",\"type\":\"小雨\",\"notice\":\"雨虽小，注意保暖别感冒\"},{\"date\":\"19\",\"sunrise\":\"04:57\",\"high\":\"高温 33.0℃\",\"low\":\"低温 19.0℃\",\"sunset\":\"19:20\",\"ymd\":\"2019-05-19\",\"week\":\"星期日\",\"fx\":\"西风\",\"fl\":\"3-4级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"20\",\"sunrise\":\"04:56\",\"high\":\"高温 28.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:20\",\"ymd\":\"2019-05-20\",\"week\":\"星期一\",\"fx\":\"东南风\",\"fl\":\"3-4级\",\"type\":\"多云\",\"notice\":\"阴晴之间，谨防紫外线侵扰\"},{\"date\":\"21\",\"sunrise\":\"04:55\",\"high\":\"高温 29.0℃\",\"low\":\"低温 19.0℃\",\"sunset\":\"19:21\",\"ymd\":\"2019-05-21\",\"week\":\"星期二\",\"fx\":\"东风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"}]}}\r\n" + 
//							"";
					//输出
					log.info("result-:" +  result);
					
					JSONObject obj = new JSONObject(result);
					
			        
					Weather weather =  addWeather(obj);
			       /**
			        * 
			        */
			        if ( weatherService.addWeather(weather)) {
			        	//缓存中存放最近15天气信息  每天缓存一次
			        	String key1 = "weatherInfo"+city;
			        	boolean objbool =  redisTemplate.hasKey(key);
			        			
			        	if(objbool) {
			        		
			        	}else {
			                ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
			                operations.set(key1, obj,1,TimeUnit.DAYS);
			        	}
			        	log.debug("当前天气"+obj.getStr("date")+"存入成功！！！！");
			        } else {
			        	log.debug("当前天气"+obj.getStr("date")+"存入失败！！！");
			        }
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		 /**
		  * 字符串转换为实体
		  * @param obj
		  * @return
		  */
		 public Weather addWeather(JSONObject obj) {

	       JSONObject cityInfo = obj.getJSONObject("cityInfo");
	       JSONObject data = obj.getJSONObject("data");
	       JSONArray forecast = data.getJSONArray("forecast");
	       JSONObject jsonObject = forecast.getJSONObject(0);

	       Weather weather = new Weather();
	       weather.setCode(cityInfo.getStr("cityId"));
	       weather.setName(cityInfo.getStr("city"));
	       weather.setDay((String) jsonObject.get("date"));
	       weather.setDate(obj.getStr("date"));
	       weather.setShidu(data.getStr("shidu"));
	       weather.setWendu(data.getStr("wendu"));
	       weather.setSunrise((String) jsonObject.get("sunrise"));
	       weather.setHigh((String) jsonObject.get("high"));
	       weather.setLow((String) jsonObject.get("low"));
	       weather.setPm25(data.getStr("pm25"));
	       weather.setPm10(data.getStr("pm10"));
	       weather.setQuality(data.getStr("quality"));
	       weather.setSunset((String) jsonObject.get("sunset"));
	       weather.setAqi("");
	       weather.setYmd((String) jsonObject.get("ymd"));
	       weather.setWeek((String) jsonObject.get("week"));
	       weather.setFx((String) jsonObject.get("fx"));
	       weather.setFl((String) jsonObject.get("fl"));
	       weather.setType((String) jsonObject.get("type"));
	       weather.setGanmao(data.getStr("ganmao"));
	       weather.setNotice((String) jsonObject.get("notice"));
	       weather.setUpDateTime(cityInfo.getStr("updateTime"));
	       return weather;
		 }
	}
