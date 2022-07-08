package com.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowserAndVisitFlipkart {

	public static void main(String[] args) {
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//implicit wait : Global waiting mechanism which is applicable for all WebElements in we page.
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("test1@gmail.com");
		
	}

}
