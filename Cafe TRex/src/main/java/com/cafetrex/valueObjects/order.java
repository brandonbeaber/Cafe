package com.cafetrex.valueObjects;

public class order {
	private int orderID = -1;
	private int personID = -1;
	private int type = 0;
	private double total = 0.0D;
	private String instructions = "";
	private int status = -1;
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
