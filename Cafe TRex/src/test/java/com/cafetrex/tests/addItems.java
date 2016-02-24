package com.cafetrex.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class addItems {
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

