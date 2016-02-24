package com.cafetrex.valueObjects;

public class shoppingCartItem {
	private int shoppingCartID = -1;
	private int itemID = -1;
	private int orderID = -1;
	private int quantity = 0;
	
	
	public int getShoppingCartID() {
		return shoppingCartID;
	}
	public void setShoppingCartID(int shoppingCartID) {
		this.shoppingCartID = shoppingCartID;
	}
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return "ShoppingCart ID: " + shoppingCartID + " Item ID: " + itemID + " Order ID: " + orderID + " Quantity: " + quantity;
	}
	
	
}
