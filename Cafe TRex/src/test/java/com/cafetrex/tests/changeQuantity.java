package com.cafetrex.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class changeQuantity {
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

