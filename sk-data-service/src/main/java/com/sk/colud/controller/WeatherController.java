package com.sk.colud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sk.colud.entity.Weather;
import com.sk.colud.service.WeatherService;
import com.sk.colud.utils.JsonResult;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
* 2019年5月15日 上午11:02:18
* @HXing xu
* sk-data-service
* 
**/


@Api("WeatherController--用户API")
@RestController
public class WeatherController {

	private Logger log = LoggerFactory.getLogger(WeatherController.class);
	private DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	
	
	@Autowired
	private WeatherService weatherService;
	
	 @SuppressWarnings("rawtypes")
	 @Autowired
	 private RedisTemplate redisTemplate;
	 
	 @SuppressWarnings("unused")
	 @Autowired
	 private StringRedisTemplate stringRedisTemplate;

	 
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "findCode/{code}", method = RequestMethod.GET)
	public  ResponseEntity<JsonResult> findCode (@PathVariable(value = "code") String code) {
		log.info("根据Name获取天气信息");
		JsonResult r = null;
		Weather weather ;
		ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
		try {
			//参数url化
			if(code.isEmpty()) {
				code = "101010100";
			}
			String date = format1.format(new Date());
			
			// 验证15天 天气是否在缓存
			String key = "weather_15_"+code;
			boolean hasKey = redisTemplate.hasKey(key);
			if(hasKey) {
				log.info("今天的天气已缓存》》》》"+hasKey);
				JSONObject str = weatherService.findRrdis(key);
				r = new JsonResult(str, "ok");
			}else {
				//拼地址 http://t.weather.sojson.com/api/weather/city/101030100
				String apiUrl = String.format(" http://t.weather.sojson.com/api/weather/city/"+code);
				//开始请求
				URL url = new URL(apiUrl);
		
				URLConnection open = url.openConnection();
				InputStream input = open.getInputStream();
				//这里转换为String，带上包名，怕你们引错包
				String result = IOUtils.toString(input,"utf-8");
				JSONObject obj = new JSONObject(result);
				//输出
				log.info("result-:" +  result);
				weather = addWeather(obj);
		       /**
		        * 落地数据库和放入缓存
		        */
		        if ( weatherService.addWeather(weather)) {
		        	//缓存中存放最近15天气信息  每天缓存一次
		            operations.set(key, obj , 1 ,TimeUnit.DAYS);
		        	log.debug("当前天气"+obj.getStr("date")+"存入成功！！！！");
		        	
		        } else {
		        	log.debug("当前天气"+obj.getStr("date")+"存入失败！！！");
		        }
		        r = new JsonResult(obj, "ok");
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	@SuppressWarnings("unused")
	@ApiOperation(value="根据code修改用户数据", notes="根据url的code获取数据")
	@ApiImplicitParams(
			{
				@ApiImplicitParam(name="code",value="地市代码",required=true,paramType="path"),
				@ApiImplicitParam(name="type",value="状态",required=true,paramType="path")
			})
	@RequestMapping(value = "fingDate/{code}&{type}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> fingDate(@PathVariable(value = "code") String code ,@PathVariable(value = "type") String type) {
		String date = format1.format(new Date());
		log.info("根据Name获取天气信息");
		JsonResult r = null;
		Weather weather ;
		ValueOperations<Object, Object> operations = redisTemplate.opsForValue();

		if(code.isEmpty()) {
			code = "101010100";
		}
		// type 1 查询一天， 2 查询15天
		if(type.equals("1")) {
			// 验证今天 天气是否在缓存
			weather = weatherService.getUserByCode(code, date);
			r = new JsonResult(weather, "ok");
		}else{
			//15天天气
			String key = "weather_15_"+code;
			JSONObject obj = weatherService.findRrdis(key);
			r = new JsonResult(obj, "ok");
		}
		
		return ResponseEntity.ok(r);
		
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
