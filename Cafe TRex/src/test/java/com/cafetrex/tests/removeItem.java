package com.cafetrex.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class removeItem {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.cafetrex.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
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
    //System.out.print(value2);

    assertEquals("Trixie's Fried Cheese" + "\n" + "Mozzarella sticks lightly breaded, fried and served with our marinara sauce.", value1);
    assertEquals("T-Rexadillas" + "\n" + "Chicken, roasted red bell peppers, green onions, Monterey Jack and Cheddar cheeses grilled in flour tortillas. Served with pico de galla and T-Rex avocado cream sauce. $9.99", value2);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
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