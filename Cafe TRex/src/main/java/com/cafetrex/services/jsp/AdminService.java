package com.cafetrex.services.jsp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.cafetrex.services.cafeService;
import com.cafetrex.valueObjects.menuItem;
import com.cafetrex.valueObjects.order;

public class AdminService extends baseJSPService {
	
	private cafeService cs = null;

	private String authKey = "9WzqIT1jjD9x5MttSS7Fz83cMmlA0Z74";
	
	public AdminService(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, JspWriter stream) throws Exception {
		super(session, request, response, stream);
		cs = new cafeService();
	}
	
	public void executeAdminService() throws Exception {
		//Admin authentication
		if (request.getParameter("adminLogin") != null) {			   
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			if (cs.validateLogin(user, pass) ) {
				loginAdmin();
				redirect("orders.jsp");
				return;
			}
		}
		//Order delete
		if (request.getParameter("delOrder") != null) {
			String order = request.getParameter("delOrder");
			cs.removeOrder(Integer.parseInt(order));
		}
		
		//Change Password
		if (request.getParameter("adminSettings") != null) {
			String pass = request.getParameter("pass");
			cs.changePassword("admin", pass);
		}
		
		//Complete Order (Change status)
		if (request.getParameter("completeOrder") != null) {
			String value = request.getParameter("completeOrder");
			cs.updateOrderStatus(Integer.parseInt(value), 2);
		}
		
		//Logout
		if (request.getParameter("logout") != null) {
			logoutAdmin();
			redirect("index.jsp");
			return;
		}
		
		//Change status
		if (request.getParameter("updateStatus") != null) {
			int order = Integer.parseInt(request.getParameter("id"));
			order o = cs.getOrder(order);
			int old = o.getStatus();
			if (old == 1)
				cs.updateOrderStatus(order, 2);
			else
				cs.updateOrderStatus(order, 1);
		}
		
		//Change Type
		if (request.getParameter("updateType") != null) {
			int order = Integer.parseInt(request.getParameter("id"));
			order o = cs.getOrder(order);
			int old = o.getType();
			if (old == 1)
				cs.updateOrderType(order, 2);
			else
				cs.updateOrderType(order, 1);
		}
		
		//Edit Menu Item
		if (request.getParameter("editItem") != null) {
			int item = Integer.parseInt(request.getParameter("id"));
			menuItem m = cs.getMenuItem(item);
			
			m.setName(request.getParameter("txtName"));
			m.setPrice(Double.parseDouble(request.getParameter("txtPrice")));
			m.setDesc(request.getParameter("txtDesc"));
			cs.updateMenuItem(m);
			redirect("editmenu.jsp?id=" + item + "&at=1&amessage=Item Updated");
			return;
		}
		
		//Add Menu Item
		if (request.getParameter("newItem") != null) {
			menuItem m = new menuItem();
			m.setName(request.getParameter("txtName"));
			m.setPrice(Double.parseDouble(request.getParameter("txtPrice")));
			m.setDesc(request.getParameter("txtDesc"));
			m.setItemID(cs.insertMenuItem(m));
			redirect("editmenu.jsp?id=" + m.getItemID() + "&at=1&amessage=Item Added");
			return;
		
		}
		
		//Delete Menu Item
		if (request.getParameter("delItem") != null) {
			int item = Integer.parseInt(request.getParameter("delItem"));
			menuItem m = cs.getMenuItem(item);
			cs.deleteMenuItem(m);
			
		}
		
		
		
	}
	
	public void loginAdmin() {
		session.setAttribute("adminLogin", authKey);
	}
	
	public void logoutAdmin() {
		session.removeAttribute("adminLogin");
	}

	public boolean isLoggedIn() {
		if (session.getAttribute("adminLogin") == null)
			return false;
		if (session.getAttribute("adminLogin").equals(authKey)) {
			return true;
		}
		return false;
	}

	public String getMenuAction() {
		if (request.getParameter("id") != null)
			return "editmenu.jsp?id=" + request.getParameter("id") + "";
		else
			return "editmenu.jsp";
	}
}
