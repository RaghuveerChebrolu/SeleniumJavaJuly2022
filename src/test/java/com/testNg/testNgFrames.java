package com.testNg;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utility.Constants;
import com.utility.LibraryFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgFrames extends  LibraryFunctions{
  @Test(priority= -1)
  public void ValidatingSingleFrame() {
	  System.out.println("inside ValidatingSingleFrame");
	  driver.navigate().to(ObjProperties.getProperty("FramesURL"));
	  driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	  String titleofFrames = driver.getTitle();
	  System.out.println("titleofFrames: "+titleofFrames);
	  Assert.assertEquals(titleofFrames,ObjProperties.getProperty("TitleOfFrames"));
	  
	  //identifying the single frame and switching to it
	  driver.switchTo().frame("singleframe");
	  //locating the webElement inside Frame
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys(ObjProperties.getProperty("SingleFrameText"));
	  driver.switchTo().defaultContent(); //to give back the control to normal we page ie. to come out of the frame
	  
	  //we can also idetify using //a[text()='Iframe with in an Iframe'] or //a[@href='#Multiple']
	  driver.findElement(By.xpath("//a[contains(text(),'an Iframe')]")).click();
	  
  }
  
  @Test(priority = 2,dependsOnMethods= {"ValidatingSingleFrame"})
  public void validatingIframeWithInIframe() {
	  System.out.println("inside validatingIframeWithInIframe");
	  WebElement OuterFrameElement = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
	  driver.switchTo().frame(OuterFrameElement);
	  
	  WebElement InnerFrameElement = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
	  driver.switchTo().frame(InnerFrameElement);
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys(ObjProperties.getProperty("IframeWithinIFrameText"));
	  driver.switchTo().defaultContent();
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
