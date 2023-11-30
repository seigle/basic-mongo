package com.mongoexample.basicmongo.dto;

public class CatalogItem {
	private String name;
	private String description;
	private double price;
	private String imageUrl;
	
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "CatalogItem [name=" + name + ", description=" + description + ", price=" + price + ", imageUrl="
				+ imageUrl + "]";
	}
	
	
}
