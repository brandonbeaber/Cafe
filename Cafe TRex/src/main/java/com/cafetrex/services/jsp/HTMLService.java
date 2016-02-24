package com.cafetrex.services.jsp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.cafetrex.services.cafeService;
import com.cafetrex.valueObjects.menuItem;
import com.cafetrex.valueObjects.order;
import com.cafetrex.valueObjects.person;
import com.cafetrex.valueObjects.shoppingCartItem;

public class HTMLService extends baseJSPService {
	
	public HTMLService(HttpSession session, HttpServletRequest request,	HttpServletResponse response, JspWriter stream) throws Exception {
		super(session, request, response, stream);
		cs = new cafeService();
	}

	private cafeService cs = null;
	
	public void buildMenu() throws Exception {
		//Get menu
		List<menuItem> items = cs.getMenu();
		
		StringBuilder out = new StringBuilder();
		StringBuilder modals = new StringBuilder();
		
		//Iterate over items
		out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
		for(menuItem m : items) {
			out.append("<tr onclick=\"$('#modal-container-" + m.getItemID() + "').modal();\">");
			out.append("<td><h4>" + m.getName() + "</h4>");
			out.append("" + m.getDesc() + "<td>");
			out.append("<td style='text-align:right'>$" + m.getPrice() + "<td></tr>");
			modals.append(getModalMenu(m.getItemID(), m.getName(), m.getDesc(), m.getPrice()));
		}
		out.append("</table>");
		
		//Print output to page
		stream.print(modals.toString() + out.toString());
	}
	
	public void buildOrders() throws Exception {
		//Get menu
		Map<order,person> items = cs.getOrders();
		
		StringBuilder out = new StringBuilder();
		
		//Iterate over items
		out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
		out.append("<tr><th>Order ID</th><th>Name</th><th>Email</th><th>Type</th><th>Total</th><th>Status</th><th>Delete Order</th></tr>");
		
		for (Map.Entry<order,person> entry : items.entrySet()) {
		    order o = entry.getKey();
		    person p = entry.getValue();
			out.append("<tr onclick=\"window.document.location='vieworder.jsp?id=" + o.getOrderID() + "'\">");
			out.append("<td>" + o.getOrderID() + "</td>");
			out.append("<td>" + p.getName() + "</td>");
			out.append("<td>" + p.getEmail() + "</td>");
			out.append("<td>" + getType(o.getType()) + "</td>");
			out.append("<td>$" + String.format( "%.2f", o.getTotal() ) + "</td>");
			
			if (o.getStatus() == 1)
				out.append("<td><form method='post' action='orders.jsp'><input type='hidden' name='completeOrder' value='" + o.getOrderID() + "'> <input type='image' src='../images/pending.png' alt='Complete Order' /> Pending</form></td>");
			else
				 out.append("<td><img src='../images/checkmark.png' alt='completed' /> Completed</td>");
		    
		    out.append("<td><form method='post' action='orders.jsp'><input type='hidden' name='delOrder' value='" + o.getOrderID() + "'> <input type='image' src='../images/del.png' alt='Delete Order' onclick=\"return confirm('Are you sure you want to delete this order?')\" /></form></td>");
			out.append("</tr>");

		}
		
			//modals.append(getModalMenu(m.getItemID(), m.getName(), m.getDesc(), m.getPrice()));
		out.append("</table>");
		
		//Print output to page
		stream.print(out.toString());
	}
	
	public void getOrderDetails() throws SQLException, IOException {
		if (request.getParameter("id") == null)
			return;
		
		int order = Integer.parseInt(request.getParameter("id"));
		order o = cs.getOrder(order);
		person p = cs.getPerson(o.getPersonID());
		
		StringBuilder out = new StringBuilder();
		
		out.append("<dl>");
	
		out.append("<dt>Order ID</dt>");
		out.append("<dd>" + o.getOrderID() + "</dd>");
		
		out.append("<dt>Order Type</dt>");
		out.append("<dd>" + getType(o.getType()) + "</dd>");
		
		out.append("<dt>Order Status</dt>");
		
		if (o.getStatus() == 1)
			out.append("<dd><img src='../images/pending.png' alt='Pending'/> " + getStatus(o.getStatus()) + "</dd>");
		else
			out.append("<dd><img src='../images/checkmark.png' alt='Completed'/> " + getStatus(o.getStatus()) + "</dd>");
		
		out.append("<dt>Name</dt>");
		out.append("<dd>" + p.getName() + "</dd>");
		
		out.append("<dt>Email</dt>");
		out.append("<dd>" + p.getEmail() + "</dd>");
		
		out.append("<dt>Phone</dt>");
		out.append("<dd>" + p.getPhone() + "</dd>");
		
		if (o.getType() == 1) {
			out.append("<dt>Address</dt>");
			out.append("<dd>" + p.getAddress() + "</dd>");
			out.append("<dt>City</dt>");
			out.append("<dd>" + p.getCity() + "</dd>");
			out.append("<dt>State</dt>");
			out.append("<dd>" + p.getState() + "</dd>");
			out.append("<dt>Zip</dt>");
			out.append("<dd>" + p.getZip() + "</dd>");
		}
		
		out.append("<dt>Special Instructions</dt>");
		out.append("<dd>" + o.getInstructions() + "</dd>");
		
		out.append("</dl>");
		
		//Print output to page
		stream.print(out.toString());
	}
	
	public String getType(int type) {
		if (type == 1)
			return "Delivery";
		else
			return "Pickup";
	}
	
	private String getStatus(int status) {
		if (status == 1) {
			return "Placed";
		}
		else
			return "Completed";
	}
	
	
	public void showAlert() throws IOException {
		if (request.getParameter("at") == null || request.getParameter("amessage") == null) {
			return;
		}
		
		StringBuilder out = new StringBuilder();
		String type = request.getParameter("at");
		String message = request.getParameter("amessage");
		
		if (type.equals("1")) {
			out.append("<div class='alert alert-success'>");
		}
		else {
			out.append("<div class='alert alert-danger'>");
		}
		out.append(" <a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>");
		out.append(message);
		out.append("</div>");
		stream.print(out.toString());
	}
	
	public void buildShoppingCart(List<shoppingCartItem> cart) throws Exception {
		String redirectPage = getPageName();
		
		StringBuilder out = new StringBuilder();
		
		if (cart == null) {
			out.append("<h4>No items in cart</h4>");
		}
		else if (cart.size() == 0) {
			out.append("<h4>No items in cart</h4>");
		}
		else {
			out.append("<form action='" + redirectPage + "' method='post'>");
			
			out.append("<input type='hidden' name='cartUpdate' value='true'>");
			out.append("<input type='hidden' name='deleteFromCart' id='deleteFromCart'>");
			
			//Create table
			out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
			out.append("<thead><tr><th>Item</th><th>Quantity</th><th>Price</th><th></th></tr></thead>");
			double total = 0.0;
			for (shoppingCartItem item: cart) {
			    menuItem m = cs.getMenuItem(item.getItemID());
			    out.append("<tr><td>" + m.getName() + "</td>");
			    out.append("<td><input name='sQuan" + item.getItemID() + "' style='width:50px' value='" + item.getQuantity() + "'></td>");
			    
			    if (item.getQuantity() > 1) {
			    	double calc = m.getPrice() * item.getQuantity();
				    out.append("<td>$" +  String.format( "%.2f", calc ) + " (" + item.getQuantity() + " @ $" + m.getPrice() + ")</td>");
				    total += calc;
			    }
			    else {
				    out.append("<td>$" + m.getPrice() + "</td>");
				    total += m.getPrice();
			    }
			    //Add delete button
			    out.append("<td>");
			   
			    out.append("<input type='image' src='../images/del.png' onclick=\"document.getElementById('deleteFromCart').value = '" + item.getItemID() + "';\" alt='Delete Item' /></td></form></tr>");

			}
			out.append("<tr><td></td><td><input type='submit' value='Update' class='btn btn-info btn-xs'></button></td><td></td><td></td></tr>");
			out.append("</table>");
			out.append("<h5>Tax: Included</h5>");
			out.append("<b>Total: $" + String.format( "%.2f", total ) + "</b>");

			out.append("</form>");
		}
		
		//Print output to page
		stream.print(out.toString());
	}
	
	public void getStaticShoppingCart() throws Exception {
		StringBuilder out = new StringBuilder();
		
		if (request.getParameter("id") == null)
			return;
		
		int order = Integer.parseInt(request.getParameter("id"));
		
		List<shoppingCartItem> cart = cs.getShoppingCart(order);
		System.out.println("Cart=" + cart.size());
		out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
		out.append("<thead><tr><th>Item</th><th>Quantity</th><th>Price</th></tr></thead>");
		double total = 0.0;
		for (shoppingCartItem item: cart) {
		    menuItem m = cs.getMenuItem(item.getItemID());
		    out.append("<tr><td>" + m.getName() + "</td>");
		    out.append("<td>" + item.getQuantity() + "</td>");
		    
		    if (item.getQuantity() > 1) {
		    	double calc = m.getPrice() * item.getQuantity();
			    out.append("<td>$" +  String.format( "%.2f", calc ) + " (" + item.getQuantity() + " @ $" + m.getPrice() + ")</td>");
			    total += calc;
		    }
		    else {
			    out.append("<td>$" + m.getPrice() + "</td>");
			    total += m.getPrice();
		    }
		    out.append("<td>");

		}
		out.append("</tr></table>");
		out.append("<h5>Tax: Included</h5>");
		out.append("<b>Total: $" + String.format( "%.2f", total ) + "</b>");
		stream.print(out.toString());
		
	}
	
	public void getAdminMenuItem() throws Exception {
		if (request.getParameter("id") == null)
			return;
		
		menuItem m = cs.getMenuItem(Integer.parseInt(request.getParameter("id")));
		StringBuilder out = new StringBuilder();
		
		out.append("<dl>");
	
		out.append("<dt>ID:</dt>");
		out.append("<dd>" + m.getItemID() + "</dd>");
		
		out.append("<dt>Name:</dt>");
		out.append("<dd><input name='txtName' value='" + m.getName() + "' width='300px'></dd>");
		
		out.append("<dt>Price:</dt>");
		out.append("<dd><input name='txtPrice' value='" + m.getPrice() + "'  width='300px'></dd>");
		
		out.append("<dt>Description:</dt>");
		out.append("<dd><textarea name='txtDesc' rows='5' cols='150'>" + m.getDesc() + "</textarea></dd>");
		
		out.append("</dl>");
		stream.print(out.toString());
	}
	
	public void buildAdminMenu() throws Exception {
		StringBuilder out = new StringBuilder();
		out.append("<table class='table table-hover menuitems' style='margin: 0px; width='100%';'>");
		out.append("<tr><th>ID</th><th>Name</th><th>Price</th><th>Description</th><th>Delete</th></tr>");
		
		List<menuItem> items = cs.getMenu();

		for(menuItem m : items) {
			out.append("<tr onclick=\"window.document.location='editmenu.jsp?id=" + m.getItemID() + "'\">");
			out.append("<td>" + m.getItemID() + "</td><td>" + m.getName() + "</td><td>" + m.getPrice()+ "</td><td>" + m.getDesc() + "</td>");
		    out.append("<td><form method='post' action='menu.jsp'><input type='hidden' name='delItem' value='" + m.getItemID() + "'> <input type='image' src='../images/del.png' alt='Delete Item' onclick=\"return confirm('Are you sure you want to delete this item?')\" /></form></td>");
		    out.append("</tr>");
		}
		
		out.append("</table>");
		
		//Print output to page
		stream.print(out.toString());
	}
	
	private String getModalMenu(int id, String name, String desc, double price) {
		return "<div class='modal fade' id='modal-container-" + id + "'>" + 
				"   <div class='modal-dialog'>" + 
				"      <div class='modal-content'>" + 
				"         <div class='modal-header'>" + 
				"            <button type='button' class='close' data-dismiss='modal' aria-hidden='true'>" + 
				"x" + 
				"            </button>" + 
				"            <h4 class='modal-title' id='myModalLabel'>" + 
				"               " + name + "" + 
				"            </h4>" + 
				"         </div>" + 
				"         <form action='order.jsp' method='post'>" + 
				"         <input type='hidden' name='addToCart' value=" + id + ">" +
				"			<div class='modal-body'>" +    
				"            " + desc + "" + 
				"            <div class='pull-right'>" + 
				"               <br/><span class='media-body'>Quantity:</span>	" + 
	            "				    <select class='form-control' name='dropdownMenu" + id + "'> " + 
				"			    <option selected>1</option> " + 
				"			    <option>2</option>" + 
				"  		        <option>3</option>" + 
				"  		        <option>4</option>" + 
				"  		        <option>5</option>" + 
				"  		        <option>6</option>" + 
				"  		        <option>7</option>" + 
				"  		        <option>8</option>" + 
				"  		        <option>9</option>" + 
				"  		        <option>10</option>" + 
				"			  </select>" + 
				"            </div>" + 
				"            <br />" + 
				"            <br />" + 
				"            <br />" + 
				"            Price: $" + price + "" +
				"         </div>" + 
				"         <div class='modal-footer'>" + 
				"            <button type='submit' class='btn btn-primary'>" + 
				"            Add" + 
				"            </button>" + 
				"            <button type='button' class='btn btn-danger' data-dismiss='modal'>" + 
				"            Cancel" + 
				"            </button> " + 
				"         </div></form>" + 
				"      </div>" + 
				"   </div>" + 
				"</div>";
	}
}
