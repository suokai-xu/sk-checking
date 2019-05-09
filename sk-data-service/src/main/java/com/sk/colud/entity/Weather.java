package com.sk.colud.entity;
/**
* 2019年5月7日 下午4:30:54
* @HXing xu
* sk-data-service
* 
**/
import java.io.Serializable;

import com.sk.colud.annotation.Column;
import com.sk.colud.annotation.Id;
import com.sk.colud.annotation.Table;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@Table(value="m_weather")
public class Weather implements Serializable {
	
	private static final long serialVersionUID = -309303369805482278L;
	
	private Integer id;
	private String code;
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
	@Id(value = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(value="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(value="day")
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@Column(value = "date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Column(value = "shidu")
	public String getShidu() {
		return shidu;
	}
	public void setShidu(String shidu) {
		this.shidu = shidu;
	}
	@Column(value = "wendu")
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	@Column(value = "sunrise")
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	@Column(value = "high")
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	@Column(value = "low")
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	@Column(value = "pm25")
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	@Column(value = "pm10")
	public String getPm10() {
		return pm10;
	}
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}
	@Column(value = "quality")
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	@Column(value = "sunset")
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	@Column(value = "aqi")
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	@Column(value = "ymd")
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	@Column(value = "week")
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	@Column(value = "fx")
	public String getFx() {
		return fx;
	}
	public void setFx(String fx) {
		this.fx = fx;
	}
	@Column(value = "fl")
	public String getFl() {
		return fl;
	}
	public void setFl(String fl) {
		this.fl = fl;
	}
	@Column(value = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(value = "ganmao")
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}
	@Column(value = "notice")
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	@Column(value = "up_date_time")
	public String getUpDateTime() {
		return upDateTime;
	}
	public void setUpDateTime(String upDateTime) {
		this.upDateTime = upDateTime;
	}
	
	
	
	
}
