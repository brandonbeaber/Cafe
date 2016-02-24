package com.cafetrex.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cafetrex.databaseConnectors.mySqlConnector;
import com.cafetrex.valueObjects.menuItem;
import com.cafetrex.valueObjects.order;
import com.cafetrex.valueObjects.person;
import com.cafetrex.valueObjects.shoppingCartItem;

public class cafeService {

	private mySqlConnector conn = null;

	public cafeService() throws SQLException {
		conn = new mySqlConnector();
	}

	public List<menuItem> getMenu() throws Exception {
		ResultSet results = null;
		List<menuItem> list = new ArrayList<menuItem>();

		results = conn.runQuery("SELECT * FROM Menu ORDER BY 'item_name'");

		if (results != null) {
			while (results.next()) {
				menuItem item = new menuItem();

				item.setItemID(results.getInt("menu_id"));
				item.setName(results.getString("item_name"));
				item.setDesc(results.getString("description"));
				item.setPrice(results.getDouble("price"));

				list.add(item);
			}
		}

		return list;
	}
	
	public menuItem getMenuItem(int id) throws Exception {
		ResultSet results = null;
		results = conn.runQuery("SELECT * FROM Menu where menu_id=" + id);

		if (results != null) {
			while (results.next()) {
				menuItem item = new menuItem();

				item.setItemID(results.getInt("menu_id"));
				item.setName(results.getString("item_name"));
				item.setDesc(results.getString("description"));
				item.setPrice(results.getDouble("price"));

				return item;
			}
		}
		return null;

	}


	public List<shoppingCartItem> getShoppingCart(int orderID) throws SQLException {
		ResultSet results = null;
		List<shoppingCartItem> list = new ArrayList<shoppingCartItem>();
		
		results = conn.runQuery("SELECT * FROM `Cart` WHERE order_id=" + orderID + " ORDER BY 'menu_id'");
		
		if (results != null) {
			while (results.next()) {
				shoppingCartItem item = new shoppingCartItem();

				item.setShoppingCartID(results.getInt("shoppingcart_id"));
				item.setItemID(results.getInt("menu_id"));
				item.setOrderID(results.getInt("order_id"));
				item.setQuantity(results.getInt("quantity"));

				list.add(item);
			}
		}
		
		return list;
	}


	
	public void removeShoppingCart(int orderID) throws SQLException {
		conn.runUpdate("DELETE FROM `Cart` WHERE 'order_id'=" + orderID);
	}

	public order getOrder(int orderID) throws SQLException {
		ResultSet results = null;
		order item = new order();
		
		results = conn.runQuery("SELECT * FROM `Order` WHERE order_id=" + orderID + "");
		
		if (results.next()) {
			item = new order();
			
			item.setInstructions(results.getString("instructions"));
			item.setOrderID(results.getInt("order_id"));
			item.setPersonID(results.getInt("person_id"));
			item.setTotal(results.getDouble("totprice"));
			item.setType(results.getInt("type"));
			item.setStatus(results.getInt("status"));
		}
		
		return item;
	}

	public int storePerson(person p) throws SQLException {
		//Remove person goes here
		
		//Insert person
		String query = "INSERT INTO `Person` (name, email, address, city, state, zip, phone) VALUES ( ";
		
		query += "'" + p.getName()	+ "', ";
		query += "'" + p.getEmail()	+ "', ";
		query += "'" + p.getAddress()	+ "', ";
		query += "'" + p.getCity()	+ "', ";
		query += "'" + p.getState()	+ "', ";
		query += "'" + p.getZip()	+ "', ";
		query += "'" + p.getPhone()	+ "'";
		query += ")";
		
		return conn.runUpdate(query);
	}
	
	public int storeOrder(order order) throws SQLException {
		removeOrder(order.getOrderID());
		
		String query = "INSERT INTO `Order` (person_id, type, totprice, instructions, status) VALUES ( ";
		
		query += "" + order.getPersonID()	+ ", ";
		query += "" + order.getType()			+ ", ";
		query += "" + order.getTotal()		+ ", ";
		query += "'" + order.getInstructions()		+ "',";
		query += "'" + order.getStatus()		+ "'";
		query += ")";
		
		return conn.runUpdate(query);
	}
	
	public void storeShoppingCart(List<shoppingCartItem> list, int orderID) throws SQLException {
		removeShoppingCart(list.get(0).getOrderID());
		
		String query = "";
		for( shoppingCartItem cart : list ) {
			query = "INSERT INTO Cart (menu_id, order_id, quantity) VALUES ( ";
			
			query += "" + cart.getItemID()			+ ", ";
			query += "" + orderID		+ ", ";
			query += "" + cart.getQuantity()		+ " ";
			query += ")";
						
			//Run insert for row
			conn.runUpdate(query);
		}
		
	}
	
	public void removeOrder(int orderID) throws SQLException {
		conn.runUpdate("DELETE FROM `Order` WHERE order_id='" + orderID + "'");
	}	
	
	public Map<order, person> getOrders() throws SQLException {
		ResultSet results = null;
		Map<order,person> list = new LinkedHashMap<order,person>();
		
		results = conn.runQuery("SELECT * FROM `Order` inner join Person on Order.person_id=Person.person_id");
		
		if (results != null) {
			while (results.next()) {
				order item = new order();

				item.setInstructions(results.getString("instructions"));
				item.setOrderID(results.getInt("order_id"));
				item.setPersonID(results.getInt("Order.person_id"));
				item.setTotal(results.getDouble("totprice"));
				item.setType(results.getInt("type"));
				item.setStatus(results.getInt("status"));

				person p = new person();
				p.setPersonId(results.getInt("Order.person_id"));
				p.setAddress(results.getString("address"));
				p.setCity(results.getString("city"));
				p.setZip(results.getString("zip"));
				p.setName(results.getString("name"));
				p.setState(results.getString("state"));
				p.setEmail(results.getString("email"));
				p.setPhone(results.getString("phone"));
				
				list.put(item,p);
			}
		}
		
		return list;
	}
	
	public double getOrderTotalByListCart(List<shoppingCartItem> list) throws SQLException {
		double total = 0.0;
		
		for( shoppingCartItem cart : list ) {
			String query = "SELECT price FROM `Menu` WHERE 'menu_id'=" + cart.getItemID();
			
			ResultSet result = conn.runQuery(query);
			
			if( result.next() ) {
				total += cart.getQuantity() * result.getDouble("price");
			}
		}
		
		
		return total;
	}
	
	public double getOrderTotalByOrderID(int orderID) throws SQLException {
		List<shoppingCartItem> list = getShoppingCart(orderID);
		return getOrderTotalByListCart(list);
	}
	
	public person getPerson(int personID) throws SQLException {
		String query = "select * from Person where person_id='" + personID + "'";
		ResultSet result = conn.runQuery(query);
		person p = new person();
		if( result.next() ) {
			p.setAddress(result.getString("address"));
			p.setCity(result.getString("city"));
			p.setZip(result.getString("zip"));
			p.setName(result.getString("name"));
			p.setState(result.getString("state"));
			p.setEmail(result.getString("email"));
			p.setPhone(result.getString("phone"));
		}
		
		return p;
	}
	
		public boolean validateLogin( String user, String pass) throws SQLException {
		
		String query = "select * from Users where user='" + user + "' and pass='" + pass + "'";
		
		ResultSet result = conn.runQuery(query);
		
		if( result.next() ) {
			return true;
		}
		return false;
	}
	
	public void updateOrderStatus(int orderID, int status) throws SQLException {
		String query = "UPDATE `Order` SET status=\"" + status + "\" WHERE order_id=\"" + orderID + "\"";
		
		conn.runUpdate(query);
	}
	
	public void updateOrderType(int orderID, int type) throws SQLException {
		String query = "UPDATE `Order` SET type=\"" + type + "\" WHERE order_id=\"" + orderID + "\"";
		
		conn.runUpdate(query);
	}
		
	public void changePassword( String user, String pass ) throws SQLException {
		String query = "UPDATE `Users` SET pass=\"" + pass + "\" WHERE user=\"" + user + "\"";
		
		conn.runUpdate(query);
	}
	
	public int insertMenuItem( menuItem item ) throws SQLException {
		String query = "INSERT INTO `Menu` (item_name, description, price) VALUES (\""
				+ item.getName() + "\", \""
				+ item.getDesc() + "\", \""
				+ item.getPrice() + "\")";
		
		return conn.runUpdate(query);
	}
	
	public void updateMenuItem( menuItem item ) throws SQLException {
		String query = "UPDATE `Menu` SET "
				+ " item_name=\"" + item.getName() + "\", "
				+ " description=\"" + item.getDesc() + "\", "
				+ " price=\"" + item.getPrice() + "\" "
				+ " WHERE menu_id=\"" + item.getItemID() + "\"";
		
		conn.runUpdate(query);		
	}
	
	public void deleteMenuItem( menuItem item ) throws SQLException {
		conn.runUpdate("DELETE FROM `Menu` WHERE menu_id='" + item.getItemID() + "'");
	}
	
}
