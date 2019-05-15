package com.sk.colud.test;
/**
* 2019年5月7日 下午3:56:25
* @HXing xu
* sk-data-service
* 
**/


import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;





public class Weather {
	
	public static void main(String[] args) {
		try {
			
//			{"time":"2019-05-07 16:17:44","cityInfo":{"city":"天津市","cityId":"101030100","parent":"天津","updateTime":"15:46"},"date":"20190507","message":"Success !","status":200,"data":{"shidu":"7%","pm25":23.0,"pm10":26.0,"quality":"优","wendu":"26","ganmao":"各类人群可自由活动","yesterday":{"date":"06","sunrise":"05:09","high":"高温 25.0℃","low":"低温 16.0℃","sunset":"19:08","aqi":49.0,"ymd":"2019-05-06","week":"星期一","fx":"西北风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},"forecast":[{"date":"07","sunrise":"05:08","high":"高温 28.0℃","low":"低温 17.0℃","sunset":"19:08","aqi":65.0,"ymd":"2019-05-07","week":"星期二","fx":"西风","fl":"3-4级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"08","sunrise":"05:07","high":"高温 27.0℃","low":"低温 16.0℃","sunset":"19:09","aqi":68.0,"ymd":"2019-05-08","week":"星期三","fx":"西南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"09","sunrise":"05:06","high":"高温 28.0℃","low":"低温 18.0℃","sunset":"19:10","aqi":100.0,"ymd":"2019-05-09","week":"星期四","fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"10","sunrise":"05:05","high":"高温 29.0℃","low":"低温 18.0℃","sunset":"19:11","aqi":111.0,"ymd":"2019-05-10","week":"星期五","fx":"东南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"11","sunrise":"05:04","high":"高温 26.0℃","low":"低温 16.0℃","sunset":"19:12","aqi":101.0,"ymd":"2019-05-11","week":"星期六","fx":"东南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"12","sunrise":"05:03","high":"高温 24.0℃","low":"低温 14.0℃","sunset":"19:13","aqi":124.0,"ymd":"2019-05-12","week":"星期日","fx":"西北风","fl":"4-5级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"13","sunrise":"05:02","high":"高温 23.0℃","low":"低温 15.0℃","sunset":"19:14","ymd":"2019-05-13","week":"星期一","fx":"西南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"14","sunrise":"05:01","high":"高温 30.0℃","low":"低温 19.0℃","sunset":"19:15","ymd":"2019-05-14","week":"星期二","fx":"西南风","fl":"4-5级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"15","sunrise":"05:00","high":"高温 30.0℃","low":"低温 18.0℃","sunset":"19:16","ymd":"2019-05-15","week":"星期三","fx":"南风","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"16","sunrise":"04:59","high":"高温 27.0℃","low":"低温 16.0℃","sunset":"19:17","ymd":"2019-05-16","week":"星期四","fx":"南风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"17","sunrise":"04:58","high":"高温 28.0℃","low":"低温 18.0℃","sunset":"19:18","ymd":"2019-05-17","week":"星期五","fx":"东北风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"},{"date":"18","sunrise":"04:57","high":"高温 28.0℃","low":"低温 20.0℃","sunset":"19:19","ymd":"2019-05-18","week":"星期六","fx":"西北风","fl":"4-5级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"19","sunrise":"04:57","high":"高温 33.0℃","low":"低温 19.0℃","sunset":"19:20","ymd":"2019-05-19","week":"星期日","fx":"西风","fl":"3-4级","type":"阴","notice":"不要被阴云遮挡住好心情"},{"date":"20","sunrise":"04:56","high":"高温 28.0℃","low":"低温 18.0℃","sunset":"19:20","ymd":"2019-05-20","week":"星期一","fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"21","sunrise":"04:55","high":"高温 29.0℃","low":"低温 19.0℃","sunset":"19:21","ymd":"2019-05-21","week":"星期二","fx":"东风","fl":"<3级","type":"阴","notice":"不要被阴云遮挡住好心情"}]}}
			
			String str = "{\"time\":\"2019-05-07 16:17:44\",\"cityInfo\":{\"city\":\"天津市\",\"cityId\":\"101030100\",\"parent\":\"天津\",\"updateTime\":\"15:46\"},\"date\":\"20190507\",\"message\":\"Success !\",\"status\":200,\"data\":{\"shidu\":\"7%\",\"pm25\":23.0,\"pm10\":26.0,\"quality\":\"优\",\"wendu\":\"26\",\"ganmao\":\"各类人群可自由活动\",\"yesterday\":{\"date\":\"06\",\"sunrise\":\"05:09\",\"high\":\"高温 25.0℃\",\"low\":\"低温 16.0℃\",\"sunset\":\"19:08\",\"aqi\":49.0,\"ymd\":\"2019-05-06\",\"week\":\"星期一\",\"fx\":\"西北风\",\"fl\":\"3-4级\",\"type\":\"晴\",\"notice\":\"愿你拥有比阳光明媚的心情\"},\"forecast\":[{\"date\":\"07\",\"sunrise\":\"05:08\",\"high\":\"高温 28.0℃\",\"low\":\"低温 17.0℃\",\"sunset\":\"19:08\",\"aqi\":65.0,\"ymd\":\"2019-05-07\",\"week\":\"星期二\",\"fx\":\"西风\",\"fl\":\"3-4级\",\"type\":\"晴\",\"notice\":\"愿你拥有比阳光明媚的心情\"},{\"date\":\"08\",\"sunrise\":\"05:07\",\"high\":\"高温 27.0℃\",\"low\":\"低温 16.0℃\",\"sunset\":\"19:09\",\"aqi\":68.0,\"ymd\":\"2019-05-08\",\"week\":\"星期三\",\"fx\":\"西南风\",\"fl\":\"<3级\",\"type\":\"多云\",\"notice\":\"阴晴之间，谨防紫外线侵扰\"},{\"date\":\"09\",\"sunrise\":\"05:06\",\"high\":\"高温 28.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:10\",\"aqi\":100.0,\"ymd\":\"2019-05-09\",\"week\":\"星期四\",\"fx\":\"南风\",\"fl\":\"<3级\",\"type\":\"多云\",\"notice\":\"阴晴之间，谨防紫外线侵扰\"},{\"date\":\"10\",\"sunrise\":\"05:05\",\"high\":\"高温 29.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:11\",\"aqi\":111.0,\"ymd\":\"2019-05-10\",\"week\":\"星期五\",\"fx\":\"东南风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"11\",\"sunrise\":\"05:04\",\"high\":\"高温 26.0℃\",\"low\":\"低温 16.0℃\",\"sunset\":\"19:12\",\"aqi\":101.0,\"ymd\":\"2019-05-11\",\"week\":\"星期六\",\"fx\":\"东南风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"12\",\"sunrise\":\"05:03\",\"high\":\"高温 24.0℃\",\"low\":\"低温 14.0℃\",\"sunset\":\"19:13\",\"aqi\":124.0,\"ymd\":\"2019-05-12\",\"week\":\"星期日\",\"fx\":\"西北风\",\"fl\":\"4-5级\",\"type\":\"晴\",\"notice\":\"愿你拥有比阳光明媚的心情\"},{\"date\":\"13\",\"sunrise\":\"05:02\",\"high\":\"高温 23.0℃\",\"low\":\"低温 15.0℃\",\"sunset\":\"19:14\",\"ymd\":\"2019-05-13\",\"week\":\"星期一\",\"fx\":\"西南风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"14\",\"sunrise\":\"05:01\",\"high\":\"高温 30.0℃\",\"low\":\"低温 19.0℃\",\"sunset\":\"19:15\",\"ymd\":\"2019-05-14\",\"week\":\"星期二\",\"fx\":\"西南风\",\"fl\":\"4-5级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"15\",\"sunrise\":\"05:00\",\"high\":\"高温 30.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:16\",\"ymd\":\"2019-05-15\",\"week\":\"星期三\",\"fx\":\"南风\",\"fl\":\"<3级\",\"type\":\"小雨\",\"notice\":\"雨虽小，注意保暖别感冒\"},{\"date\":\"16\",\"sunrise\":\"04:59\",\"high\":\"高温 27.0℃\",\"low\":\"低温 16.0℃\",\"sunset\":\"19:17\",\"ymd\":\"2019-05-16\",\"week\":\"星期四\",\"fx\":\"南风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"17\",\"sunrise\":\"04:58\",\"high\":\"高温 28.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:18\",\"ymd\":\"2019-05-17\",\"week\":\"星期五\",\"fx\":\"东北风\",\"fl\":\"<3级\",\"type\":\"晴\",\"notice\":\"愿你拥有比阳光明媚的心情\"},{\"date\":\"18\",\"sunrise\":\"04:57\",\"high\":\"高温 28.0℃\",\"low\":\"低温 20.0℃\",\"sunset\":\"19:19\",\"ymd\":\"2019-05-18\",\"week\":\"星期六\",\"fx\":\"西北风\",\"fl\":\"4-5级\",\"type\":\"小雨\",\"notice\":\"雨虽小，注意保暖别感冒\"},{\"date\":\"19\",\"sunrise\":\"04:57\",\"high\":\"高温 33.0℃\",\"low\":\"低温 19.0℃\",\"sunset\":\"19:20\",\"ymd\":\"2019-05-19\",\"week\":\"星期日\",\"fx\":\"西风\",\"fl\":\"3-4级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"},{\"date\":\"20\",\"sunrise\":\"04:56\",\"high\":\"高温 28.0℃\",\"low\":\"低温 18.0℃\",\"sunset\":\"19:20\",\"ymd\":\"2019-05-20\",\"week\":\"星期一\",\"fx\":\"东南风\",\"fl\":\"3-4级\",\"type\":\"多云\",\"notice\":\"阴晴之间，谨防紫外线侵扰\"},{\"date\":\"21\",\"sunrise\":\"04:55\",\"high\":\"高温 29.0℃\",\"low\":\"低温 19.0℃\",\"sunset\":\"19:21\",\"ymd\":\"2019-05-21\",\"week\":\"星期二\",\"fx\":\"东风\",\"fl\":\"<3级\",\"type\":\"阴\",\"notice\":\"不要被阴云遮挡住好心情\"}]}}\r\n" + 
					"";
			
			//{"data": {"title": "Pool Party", "date": {"start": "2018-08-14T15:44:44.625Z","end": "2018-08-14T18:44:44.625Z"}}}  
					
			String str1 = "{\"data\": [{\"title\": \"Pool Party\", \"date\": {\"start\": \"2018-08-14T15:44:44.625Z\",\"end\": \"2018-08-14T18:44:44.625Z\"}}]}\r\n";	
			JSON2Object(str);
//			json();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void json() throws Exception{
		
		//参数url化
		String city = java.net.URLEncoder.encode("北京", "utf-8");
		
		//拼地址 http://t.weather.sojson.com/api/weather/city/101030100
		String apiUrl = String.format(" http://t.weather.sojson.com/api/weather/city/"+101030100);
		//开始请求
		URL url= new URL(apiUrl);
		URLConnection open = url.openConnection();
		InputStream input = open.getInputStream();
		System.out.println(input);
		//这里转换为String，带上包名，怕你们引错包
		String result = IOUtils.toString(input,"utf-8");
		//输出
		System.out.println(result);
		
	}
	@SuppressWarnings("unused")
	public static Weather JSON2Object(String jsonStr) {  
		JSONObject obj = new JSONObject(jsonStr);
        
        JSONObject cityInfo = obj.getJSONObject("cityInfo");
       
		String time = obj.getStr("time");
        String date = obj.getStr("date");
        
        String title = cityInfo.getStr("cityId");
        String start = cityInfo.getStr("updateTime");
        JSONObject data = obj.getJSONObject("data");
        String shidu = data.getStr("shidu");
        String pm25 = data.getStr("pm25");
        String pm10 = data.getStr("pm10");
        String quality = data.getStr("quality");
        String wendu = data.getStr("wendu");
        String ganmao = data.getStr("ganmao");
        
        
        
        JSONArray forecast = data.getJSONArray("forecast");
        for(int i = 0; i < forecast.size(); i++ ) {
        	JSONObject jsonObject = forecast.getJSONObject(i);
        	String day = (String) jsonObject.get("date");
        	String sunrise = (String) jsonObject.get("sunrise");
        	String high = (String) jsonObject.get("high");
        	String low = (String) jsonObject.get("low");
        	String sunset = (String) jsonObject.get("sunset");
        	String aqi = (String) jsonObject.get("aqi");
        	String ymd = (String) jsonObject.get("ymd");
        	String week = (String) jsonObject.get("week");
        	String fx = (String) jsonObject.get("fx");
        	String fl = (String) jsonObject.get("fl");
        	String type = (String) jsonObject.get("type");
        	String notice = (String) jsonObject.get("notice");
        }
     
        
        return null;
    }
}
