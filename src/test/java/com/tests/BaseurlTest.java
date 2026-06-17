package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BaseurlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver =  new ChromeDriver();
		driver.get("https://lms-server-3-wedg.onrender.com/");
		String curr = driver.getCurrentUrl();
		System.out.println(curr);
		Assert.assertEquals(curr, driver.getCurrentUrl());
		driver.quit();

	}

}
