package com.cafetrex.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cafetrex.valueObjects.menuItem;
import com.cafetrex.valueObjects.order;
import com.cafetrex.valueObjects.person;
import com.cafetrex.valueObjects.shoppingCartItem;

public class cafeServiceTest {

	static cafeService service;

	@BeforeClass
	public static void setUp() {
		try {
			System.out.println("Creating cafeService instance...");
			service = new cafeService();
			System.out.println("Instance initialized!");
		} catch (SQLException e) {
			System.out.println("Instance failed to initialize...");
			e.printStackTrace();
		}

	}

	@AfterClass
	public static void tearDown() {

	}

	@Test
	public void testGetMenu() {
		List<menuItem> list = null;

		try {
			list = service.getMenu();
			Assert.assertTrue(list != null);

			for (menuItem item : list) {
				System.out.println(item);
			}
		} catch (Exception e) {
			Assert.assertFalse(true);
			e.printStackTrace();
		}
	}

	@Test
	public void testGetShoppingCart() {
		// Change value to get different carts back
		int shoppingCartID = 0;
		List<shoppingCartItem> list = null;

		try {
			list = service.getShoppingCart(shoppingCartID);
			Assert.assertTrue(list != null);

			for (shoppingCartItem cart : list) {
				System.out.println(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		}
	}

	@Test
	public void testGetOrder() {
		// Change value to get different carts back
		// With order, you will always get an instance back, to see if it
		// is a valid response, make sure none of the IDs are -1
		int orderID = 0;
		order order = null;

		try {
			order = service.getOrder(orderID);
			Assert.assertTrue(order != null);

			System.out.println(order);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		}
	}
	
	@Test
	public void testGetOrders() {
		// Change value to get different carts back
		// With order, you will always get an instance back, to see if it
		// is a valid response, make sure none of the IDs are -1
		Map<order, person> list = null;

		try {
			list = service.getOrders();
			Assert.assertTrue(list != null);

			for( Map.Entry order : list.entrySet() ) {
				System.out.println(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		}
	}
	
	@Test
	public void testValidateLogin() {
		try {
			if(service.validateLogin("admin", "password")) {
				Assert.assertTrue(true);				
			} else {
				Assert.assertFalse(true);
			}
		} catch (SQLException e) {
			System.out.println("Error occurred while testing admin login.");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChangePassword() {
		try {
			service.changePassword("admin", "password1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertMenuItem() {
		menuItem item = new menuItem();
		
		item.setName("Demo Item");
		item.setDesc("Demo Desc");
		item.setPrice(69.00);
		
		List<menuItem> list = null;
		try {
			list = service.getMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for( menuItem iter : list ) {
			if( iter.getName().equals(item.getName()) ) {
				try {
					service.deleteMenuItem(iter);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			service.insertMenuItem(item);
			Assert.assertTrue(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateMenuItem() {
		
		List<menuItem> list = null;
		try {
			list = service.getMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for( menuItem iter : list ) {
			if( iter.getName().equals("Demo Item") ) {
				try {
					iter.setPrice(iter.getPrice() - 1);
					service.updateMenuItem(iter);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void testDeleteMenuItem() {
		List<menuItem> list = null;
		try {
			list = service.getMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for( menuItem iter : list ) {
			if( iter.getName().equals("Demo Item") ) {
				try {
					service.deleteMenuItem(iter);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
