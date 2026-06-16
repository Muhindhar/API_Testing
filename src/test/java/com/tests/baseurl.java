package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class baseurl {
  @Test
  public void f() {
	  WebDriver driver = new ChromeDriver();
	  driver.get("http://localhost:3000/users");
	  String cur = driver.getCurrentUrl();
	  System.out.println("Current url : "+cur);
	  driver.quit();
  }
}
