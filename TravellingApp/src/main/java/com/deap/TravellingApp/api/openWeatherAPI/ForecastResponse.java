package com.deap.TravellingApp.api.openWeatherAPI;

import java.util.List;

public class ForecastResponse {

	private String id;
	private String name;
	private String cod;
	private List<ForecastObject> list;
	private List<CityObject> city;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public List<ForecastObject> getList() {
		return list;
	}
	public void setList(List<ForecastObject> list) {
		this.list = list;
	}
	public List<CityObject> getCity() {
		return city;
	}
	public void setCity(List<CityObject> city) {
		this.city = city;
	}
	
}
