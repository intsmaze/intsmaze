package com.intsmaze.redis.demo;

import java.io.Serializable;

public class ProductInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3002512957989050750L;
	private String name;
	private String description;
	private double price;
	private String catelog;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCatelog() {
		return catelog;
	}
	public void setCatelog(String catelog) {
		this.catelog = catelog;
	}
	
	@Override
	public String toString() {
		 
		return name+" "+description + " "+ catelog+ " " + price;
	}
	
	
	
}
