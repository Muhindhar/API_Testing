package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTest {
  @Test
  public void f() {
	  WebDriver driver =  new ChromeDriver();
	  try {
		  driver.get("https://jsonplaceholder.typicode.com/posts");
		  String exp = driver.getCurrentUrl();
		  Assert.assertTrue(exp.contains("jsonplaceholder"));
		  System.out.println("done");
	  }
	  finally {
		  driver.quit();
	  }
		  
  }
}
