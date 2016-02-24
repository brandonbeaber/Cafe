package com.cafetrex.valueObjects;

public class menuItem {
	private int itemID = -1;
	private String name = "";
	private String desc = "";
	private double price = 0.0;
	

	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		return "ID: " + itemID + " Name: " + name + " Desc: " + desc + " Price: " + price;
	}
}
