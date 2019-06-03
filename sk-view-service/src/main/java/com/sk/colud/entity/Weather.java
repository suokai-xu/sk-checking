package com.sk.colud.entity;
/**
* 2019年5月7日 下午4:30:54
* @HXing xu
* sk-data-service
* 
**/
import java.io.Serializable;

 public class Weather implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String code;
	private String name;
	private String day;  // 当前日期
	private String date;  // 当前日期
	private String shidu;    //湿度
	private String wendu;    //温度
	private String sunrise; //日出时间
	private String high; //高温
	private String low; //低温
	private String pm25;   //pm2.5
	private String pm10;   //pm10
	private String quality; //空气质量
	private String sunset; //日落时间
	private String aqi;
	private String ymd;  //年月日  （新增）
	private String week; //星期 （新增）
	private String fx; // 风向
	private String fl;//风力
	private String type; // 天气状态 ： 多云 、晴
	private String ganmao;  //"各类人群可自由活动",//感冒提醒（指数）
	private String notice;
	private String upDateTime; //天气更新时间
	
	
	
 	public Weather() {
	}
 	
	public Weather(String name, String high, String low, String quality) {
		super();
		this.name = name;
		this.high = high;
		this.low = low;
		this.quality = quality;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
 	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
 	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getShidu() {
		return shidu;
	}
	public void setShidu(String shidu) {
		this.shidu = shidu;
	}
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public String getPm10() {
		return pm10;
	}
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getFx() {
		return fx;
	}
	public void setFx(String fx) {
		this.fx = fx;
	}
	public String getFl() {
		return fl;
	}
	public void setFl(String fl) {
		this.fl = fl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	public String getUpDateTime() {
		return upDateTime;
	}
	public void setUpDateTime(String upDateTime) {
		this.upDateTime = upDateTime;
	}
	
	
	
	
}
