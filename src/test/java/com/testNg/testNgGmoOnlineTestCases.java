package com.testNg;

import org.testng.annotations.Test;

import com.utility.Constants;
import com.utility.LibraryFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgGmoOnlineTestCases extends  LibraryFunctions{
  @Test(priority= -1)
  public void ValidateGmoOnlineLoadedSuccessfully() {
	  System.out.println("inside ValidateGmoOnlineLoadedSuccessfully");
	  String titleOfGmoOnline = driver.getTitle();
	  System.out.println("titleOfGmoOnline: "+titleOfGmoOnline);
	  Assert.assertEquals(titleOfGmoOnline,ObjProperties.getProperty("titleOfGMOOnlineApplication"));
  }
  
  @Test(priority=0,dependsOnMethods= {"ValidateGmoOnlineLoadedSuccessfully"})
  public void ValidateEnterGMOOnline() {
	  System.out.println("inside ValidateEnterGMOOnline");
	  driver.findElement(By.name("bSubmit")).click();
	  Assert.assertEquals(driver.getTitle(), ObjProperties.getProperty("titleOfOnlineCatalogPage"));
  }
  
  @Test(priority=1)
  public void ValidatePurchaseOfExternalFrameBackpack() {
	  System.out.println("inside ValidatePurchaseOfExternalFrameBackpack");
	  driver.findElement(By.name("QTY_BACKPACKS")).clear();
	  driver.findElement(By.name("QTY_BACKPACKS")).sendKeys(String.valueOf(Constants.ExternalFrameBackPackQTY));
	  driver.findElement(By.xpath("//input[@value='Place An Order']")).click();
	  //driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	  Assert.assertEquals(driver.getTitle(), ObjProperties.getProperty("TitleOfPaceOrder"));
  }

	@Test(priority = 2)
	public void ValidateCalculationOfExternalFrameBackpack() {
		System.out.println("ValidateCalculationOfExternalFrameBackpack");
		String UnitPriceOfExternalFrameBackpack = driver
				.findElement(By.xpath("//table[@cellpadding='4' and @border='1']/tbody/tr[2]/td[4]")).getText();
		System.out.println("UnitPriceOfExternalFrameBackpack:" + UnitPriceOfExternalFrameBackpack);
		int length = UnitPriceOfExternalFrameBackpack.length();
		System.out.println("length:" + length);
		System.out.println("substring:" + UnitPriceOfExternalFrameBackpack.substring(2));
		System.out.println("substringlength:" + UnitPriceOfExternalFrameBackpack.substring(2).length());
		System.out.println("substringlengthAfterTrim:" + UnitPriceOfExternalFrameBackpack.substring(2).trim().length());
		float FLoatValueOfUnitPrice = Float.parseFloat(UnitPriceOfExternalFrameBackpack.substring(2));
		float calulatedValueOfExternalFrameBackBasedOnQTY = FLoatValueOfUnitPrice * Constants.ExternalFrameBackPackQTY;
		System.out
				.println("calulatedValueOfExternalFrameBackBasedOnQTY:" + calulatedValueOfExternalFrameBackBasedOnQTY);
		
		String TotalPriceOfExternalFrameBackpack  = driver.findElement(By.xpath("//table[@cellpadding='4' and @border='1']/tbody/tr[2]/td[5]")).getText();
		System.out.println("TotalPriceOfExternalFrameBackpack:"+TotalPriceOfExternalFrameBackpack);
		float TotalPriceOfExternalFrameBackpackInFloatformat = Float.parseFloat(TotalPriceOfExternalFrameBackpack.substring(2));
		System.out.println("TotalPriceOfExternalFrameBackpackInFloatformat:"+TotalPriceOfExternalFrameBackpackInFloatformat);
		Assert.assertEquals(calulatedValueOfExternalFrameBackBasedOnQTY, TotalPriceOfExternalFrameBackpackInFloatformat);
	}
  
 
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("inside beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("inside afterMethod");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("inside beforeClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("inside afterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("inside beforeTest");
	  LibraryFunctions.LaunchBrowser();
  }



@AfterTest
  public void afterTest() {
	  System.out.println("inside afterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("inside beforeSuite");
	  try {
		LibraryFunctions.ReadPropertiesFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

 

@AfterSuite
  public void afterSuite() {
	  System.out.println("inside afterSuite");
  }
  
	/*
	 * execution order : 
	 * 1. Priority (negative to 0 to positive) 
	 * 2. UpeerCase and Ascending order 
	 * 3. LowerCase and Ascending order
	 * 
	 * Note : if priority is not assigned to individual test case then testNg by
	 * default will assign 0 priority to the respective test case
	 * 
	 * 
	 */
}
