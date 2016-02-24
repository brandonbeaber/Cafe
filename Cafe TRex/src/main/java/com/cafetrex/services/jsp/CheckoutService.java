package com.cafetrex.services.jsp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.cafetrex.services.cafeService;
import com.cafetrex.valueObjects.order;
import com.cafetrex.valueObjects.person;
import com.cafetrex.valueObjects.shoppingCartItem;

public class CheckoutService extends baseJSPService {
	
	private cafeService cs = null;
	
	public CheckoutService(HttpSession session, HttpServletRequest request, HttpServletResponse response, JspWriter stream) throws Exception {
		super(session, request, response, stream);
	}
	
	public void executeCheckoutService() throws Exception {
		cs = new cafeService();
		if (request.getParameter("checkout") != null) {
			placeOrder();
		}
	}
	
	public void placeOrder() throws Exception {
		int type = getOrderType();
		String name = request.getParameter("name");
		String email =  request.getParameter("email");
		String phone =  request.getParameter("phone");
		String special =  request.getParameter("special");
		List<shoppingCartItem> cart = (List<shoppingCartItem>) session.getAttribute("cart");
		
		person newPerson = new person();
		
		//Delivery Only
		if (type == 1) {
			String address =  request.getParameter("address");
			newPerson.setAddress(address);
			String city =  request.getParameter("city");
			newPerson.setCity(city);
			String state =  request.getParameter("state");
			newPerson.setState(state);
			String zip =  request.getParameter("zip");
			newPerson.setZip(zip);
		}
		
		//Insert person
		newPerson.setEmail(email);
		newPerson.setName(name);
		newPerson.setPhone(phone);
		newPerson.setPersonId(cs.storePerson(newPerson)); //must be last

		
		//Insert Order
		order newOrder = new order();
		newOrder.setPersonID(newPerson.getPersonId());
		newOrder.setTotal(calculateTotal(cart));
		newOrder.setType(type);
		newOrder.setStatus(1);
		newOrder.setInstructions(special);
		newOrder.setOrderID(cs.storeOrder(newOrder)); //must be last

		//Insert Shopping Cart items
		cs.storeShoppingCart(cart, newOrder.getOrderID());
		
		response.sendRedirect("completed.jsp?at=1&amessage=Your Order has been placed!");
	}
	
	public double calculateTotal(List<shoppingCartItem> cart) throws Exception {
		double total = 0.0;
		for(shoppingCartItem sci:  cart) {
			total += (cs.getMenuItem(sci.getItemID()).getPrice() * sci.getQuantity());
		}
		return total;
	}
	
	private int getOrderType() {
		if (session.getAttribute("cartType") != null)
			return (int)session.getAttribute("cartType");
		return 1;
	}
	
}
