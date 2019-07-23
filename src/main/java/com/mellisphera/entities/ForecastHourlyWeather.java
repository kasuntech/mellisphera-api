package com.mellisphera.entities;

import java.util.Date;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ForecastHourlyWeather")
public class ForecastHourlyWeather {
	
	private String _id;
	private String rain;
	private Date date;
	private String user;
	private Object snow;
	private Object weather;
	private String city;
	private Object main;
	private String apiary;
	private String idApiary;
	private String _origin;
	private Object wind;
	
	
	
	public ForecastHourlyWeather(String _id, String rain, Date date, String user, Object snow,
			Object weather, String city, Object main, String apiary, String _origin,
			Object wind, String idApiary) {
		this._id = _id;
		this.rain = rain;
		this.date = date;
		this.user = user;
		this.snow = snow;
		this.weather = weather;
		this.city = city;
		this.main = main;
		this.apiary = apiary;
		this._origin = _origin;
		this.wind = wind;
		this.idApiary = idApiary;
	}
	public String get_id() {
		return _id;
	}
	
	
	public String getIdApiary() {
		return idApiary;
	}
	public void setIdApiary(String idApiary) {
		this.idApiary = idApiary;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getRain() {
		return rain;
	}
	public void setRain(String rain) {
		this.rain = rain;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Object getsnow() {
		return snow;
	}
	public void setsnow(Object snow) {
		this.snow = snow;
	}
	public Object getWeather() {
		return weather;
	}
	public void setWeather(Object weather) {
		this.weather = weather;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Object getMain() {
		return main;
	}
	public void setMain(Object main) {
		this.main = main;
	}
	public String getApiary() {
		return apiary;
	}
	public void setApiary(String apiary) {
		this.apiary = apiary;
	}
	public String get_origin() {
		return _origin;
	}
	public void set_origin(String _origin) {
		this._origin = _origin;
	}
	public Object getWind() {
		return wind;
	}
	public void setWind(Object  wind) {
		this.wind = wind;
	}
	
	
	
}
