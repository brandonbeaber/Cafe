package com.cafetrex.services.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.cafetrex.valueObjects.shoppingCartItem;

public class ShoppingCartService extends baseJSPService {
	
	private List<shoppingCartItem> cart;
	private int cartType = 1; //1=delivery, 2=pickup
	
	public ShoppingCartService(HttpSession session, HttpServletRequest request,	HttpServletResponse response, JspWriter stream) throws Exception {
		super(session, request, response, stream);
		restoreShoppingCart();
	}
	
	private void restoreShoppingCart() {
		if (session.isNew()) {
			cart = new ArrayList<shoppingCartItem>();
		}
		else {
			cart = (List<shoppingCartItem>) session.getAttribute("cart");
		}
		
		if (session.getAttribute("cartType") != null)
			cartType = (int)session.getAttribute("cartType");
		else
			cartType = 1;
		
		saveShoppingCart();
	}
	
	public int getCartType() {
		return cartType;
	}
	
	public String getDeliveryType() {
		if (cartType == 1)
			return "active";
		else
			return "";
	}
	
	public String getPickupType() {
		if (cartType == 2)
			return "active";
		else
			return "";
	}
	
	public void setCartType(int cartType) {
		this.cartType = cartType;
	}
	
	private void saveShoppingCart() {
		session.setAttribute("cart", cart);
		session.setAttribute("cartType", cartType);
	}
	
	public void addToShoppingCart(int id, int quantity) {

		if (inCart(id)) {
			//If already in cart increase quantity
			quantity = quantity + getQuantity(id);
			updateShoppingCart(id,quantity);
		}
		else {
			//Add new item to shopping cart
			shoppingCartItem sc = new shoppingCartItem();
			sc.setItemID(id);
			sc.setQuantity(quantity);
			cart.add(sc);
		}
		saveShoppingCart();
	}
	
	public void updateShoppingCart(int id, int quantity) {
		shoppingCartItem sc = getSingleshoppingCart(id);
		sc.setQuantity(quantity);
		int pos = getShoppingcartPOS(id);
		cart.remove(sc);
		cart.add(pos, sc);
		saveShoppingCart();
	}
	
	public void removeFromShoppingCart(int id) {
		int index = getShoppingcartPOS(id);
		cart.remove(index);
		saveShoppingCart();
	}
	
	public void clearShoppingCart() {
		cart = new ArrayList<shoppingCartItem>();
		saveShoppingCart();
	}
	
	public boolean inCart(int id) {
		for(shoppingCartItem sc : cart) {
			if (sc.getItemID() == id)
				return true;
		}
		return false;
	}
	
	public shoppingCartItem getSingleshoppingCart(int id) {
		for(shoppingCartItem sc : cart) {
			if (sc.getItemID() == id)
				return sc;
		}
		return null;
	}
	
	public int getShoppingcartPOS(int id) {
		int i = 0;
		for(shoppingCartItem sc : cart) {
			if (sc.getItemID() == id)
				return i;
			i++;
		}
		return -1;
	}
	
	public int getQuantity(int id) {
		if (inCart(id)) {
			shoppingCartItem sc = getSingleshoppingCart(id);
			if (sc != null)
				return sc.getQuantity();
		}
		return 0;

	}
	
	public List<shoppingCartItem> getShoppingCart() {
		return cart;
	}
	
	public void executeOrderService() throws IOException {
		//Add item to shopping cart
		if (request.getParameter("addToCart") != null) {			   
			int id = Integer.parseInt(request.getParameter("addToCart"));
			int quantity = Integer.parseInt(request.getParameter("dropdownMenu" + id));
			addToShoppingCart(id, quantity);
		}
		
		if (request.getParameter("cartUpdate") != null) {
			
			//Delete item from shopping cart
			if (request.getParameter("deleteFromCart") != null) {		
				System.out.println("Got delete");
				String delId = request.getParameter("deleteFromCart");
				if (delId.length() != 0) {
					int id = Integer.parseInt(delId);
					removeFromShoppingCart(id);
					System.out.println(getPageName());

				}
			}
			
			//Update quantities in cart
			for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			    String key = entry.getKey();
			    String[] value = entry.getValue();
			    System.out.println("Key=" + key);
			    if (key.startsWith("sQuan")) {
			    	System.out.println("Found quan = " + key);
			    	int id = Integer.parseInt(key.substring(5));
			    	
			    	try {
			    		int newQuantity = Integer.parseInt(value[0]);
			    		if (newQuantity == 0)
			    			removeFromShoppingCart(id);
		    			else
			    			updateShoppingCart(id,newQuantity);
			    	}
			    	catch(Exception e) {
			    		e.printStackTrace();
			    	}
			    
			    	
			    }
			    
			}
			
		}
		
		//User wants to clear the shopping cart
		if (request.getParameter("reset") != null) {
			System.out.println("Cleared Shopping Cart");
			clearShoppingCart();
		}
		
		//Redirect if in checkout page and no items are in cart
		if (cart.size() == 0 && getPageName().equalsIgnoreCase("checkout.jsp")) {
			redirect("order.jsp");
		}
		
		//Change cart type
		if (request.getParameter("type") != null) {
			if (request.getParameter("type").equals("pickup"))
				cartType = 2;
			else if (request.getParameter("type").equals("delivery"))
				cartType = 1;
		}
		
		saveShoppingCart();
		
	}
}
