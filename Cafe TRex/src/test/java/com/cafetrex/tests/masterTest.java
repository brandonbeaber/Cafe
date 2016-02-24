package com.cafetrex.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class masterTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://cafetrex.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testmasterTest() throws Exception {
	  
	  testRemoveItem2();
	  testAddAnItem();
	  testChangeQuantity();
	  testCheckout();
	  
  }
  
  
  public void testAddAnItem() throws Exception {
	    driver.get(baseUrl + "/");
	    Thread.sleep(3000);
	    driver.findElement(By.linkText("Order")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("td")).click();
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector("td")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    
	    String value = driver.findElement(By.cssSelector("td")).getText();
	    System.out.print(value);
	    assertEquals("T-Rexadillas", value);
	    
	    driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
	    Thread.sleep(2000);
	  }
  
  
  public void testChangeQuantity() throws Exception {
		Thread.sleep(200);
	    driver.get(baseUrl);
	    Thread.sleep(1500);
	    driver.findElement(By.linkText("Order")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//tr[4]/td")).click();
	    Thread.sleep(500);
	    new Select(driver.findElement(By.name("dropdownMenu4"))).selectByVisibleText("7");
	    driver.findElement(By.cssSelector("#modal-container-4 > div.modal-dialog > div.modal-content > form > div.modal-footer > button.btn.btn-primary")).click();
	    Thread.sleep(500);
	    
	    String val1Initial = driver.findElement(By.name("sQuan4")).getAttribute("value");
	    
	    driver.findElement(By.name("sQuan4")).clear();
	    driver.findElement(By.name("sQuan4")).sendKeys("2");
	    
	    String val1Final = driver.findElement(By.name("sQuan4")).getAttribute("value");
	  
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//input[@value='Update']")).click();
	    driver.findElement(By.xpath("//div[2]/table/tbody/tr/td")).click();
	    Thread.sleep(700);
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    Thread.sleep(700);
	    
	    String val2Initial = driver.findElement(By.name("sQuan1")).getAttribute("value");
	    
	    driver.findElement(By.name("sQuan1")).clear();
	    driver.findElement(By.name("sQuan1")).sendKeys("9");
	    
	    String val2Final = driver.findElement(By.name("sQuan1")).getAttribute("value");
	    
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//input[@value='Update']")).click();
	    driver.findElement(By.xpath("//input[@value='Update']")).click();
	    Thread.sleep(500);
	    
	    System.out.println(val1Initial + "\n");
	    System.out.println(val1Final + "\n");
	    System.out.println(val2Initial + "\n");
	    System.out.println(val2Final + "\n");
	    
	    assertEquals("7", val1Initial);
	    assertEquals("2", val1Final);
	    assertEquals("1", val2Initial);
	    assertEquals("9", val2Final);
	    
	    driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
	    Thread.sleep(2000);
	  }
  
  
  public void testCheckout() throws Exception {
	    driver.get(baseUrl );
	    Thread.sleep(1000);
	    driver.findElement(By.linkText("Order")).click();
	    driver.findElement(By.cssSelector("td")).click();

	    String I2 = driver.findElement(By.cssSelector("td")).getText();

	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    driver.findElement(By.xpath("//tr[2]/td/h4")).click();
	    
	    String I1 = driver.findElement(By.xpath("//tr[2]/td/h4")).getText();
	    
	    Thread.sleep(300);
	    driver.findElement(By.cssSelector("#modal-container-2 > div.modal-dialog > div.modal-content > form > div.modal-footer > button.btn.btn-primary")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Test");
	    
	    String name1 = driver.findElement(By.name("name")).getAttribute("value");
	    
	    Thread.sleep(300);
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("test@gmail.com");
	    
	    String email1 = driver.findElement(By.name("email")).getAttribute("value");

	    Thread.sleep(300);
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("phone")).sendKeys("402-111-1111");
	    
	    String phone1 = driver.findElement(By.name("phone")).getAttribute("value");

	    Thread.sleep(300);
	    driver.findElement(By.name("phone")).clear();
	    driver.findElement(By.id("address")).sendKeys("111 dodge st.");
	    
	    String address1 = driver.findElement(By.id("address")).getAttribute("value");

	    Thread.sleep(300);
	    driver.findElement(By.id("address")).clear();
	    driver.findElement(By.id("city")).sendKeys("Omaha");
	    
	    String city1 = driver.findElement(By.id("city")).getAttribute("value");

	    Thread.sleep(300);
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("state")).sendKeys("NE");
	    
	    String state1 = driver.findElement(By.id("state")).getAttribute("value");

	    Thread.sleep(300);
	    driver.findElement(By.id("state")).clear();
	    driver.findElement(By.id("zip")).sendKeys("68100");
	    
	    String zip1 = driver.findElement(By.id("zip")).getAttribute("value");

	    Thread.sleep(300);
	    driver.findElement(By.id("special")).clear();
	    driver.findElement(By.id("special")).sendKeys("none");
	    
	    String special1 = driver.findElement(By.id("special")).getAttribute("value");

	    Thread.sleep(300);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    System.out.print(I1 + "\n");
	    System.out.print(I2 + "\n");
	    System.out.print(name1 + "\n");
	    System.out.print(email1 + "\n");
	    System.out.print(phone1 + "\n");
	    System.out.print(address1 + "\n");
	    System.out.print(state1 + "\n");
	    System.out.print(city1 + "\n");
	    System.out.print(zip1 + "\n");
	    System.out.print(special1 + "\n");

	    assertEquals("Dexter's Dual Dip", I1);
	    assertEquals("Test", name1);
	    assertEquals("test@gmail.com", email1);
	    assertEquals("402-111-1111", phone1);
	    assertEquals("111 dodge st.", address1);
	    assertEquals("NE", state1);
	    assertEquals("Omaha", city1);
	    assertEquals("68100", zip1);
	    assertEquals("none", special1);
	    Thread.sleep(2000);

	  }
  
  public void testRemoveItem2() throws Exception {
	    driver.get(baseUrl + "/");
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Order")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//tr[3]/td")).click();
	    
	    String value1 = driver.findElement(By.xpath("//tr[3]/td")).getText();
	    
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector("#modal-container-3 > div.modal-dialog > div.modal-content > form > div.modal-footer > button.btn.btn-primary")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("//div[2]/table/tbody/tr/td")).click();
	    
	    String value2 = driver.findElement(By.xpath("//div[2]/table/tbody/tr")).getText();
	    
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    Thread.sleep(500);
	    driver.findElement(By.xpath("(//input[@type='image'])[2]")).click();
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
	    Thread.sleep(500);
	    
	    System.out.print(value1);
	    System.out.print(value2);

	    assertEquals("Trixie's Fried Cheese" + "\n" + "Mozzarella sticks lightly breaded, fried and served with our marinara sauce.", value1);
	    assertEquals("T-Rexadillas" + "\n" + "Chicken, roasted red bell peppers, green onions, Monterey Jack and Cheddar cheeses grilled in flour tortillas. Served with pico de galla and T-Rex avocado cream sauce. $9.99", value2);
	  }
  
  

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

