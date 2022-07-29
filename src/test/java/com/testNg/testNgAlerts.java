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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgAlerts extends  LibraryFunctions{
  @Test(priority= -1)
  public void LoadingDemoQaAlertsPage() {
	  System.out.println("inside ValidateAlerts");
	  driver.navigate().to(ObjProperties.getProperty("AlertURL"));
	  driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	  String titleOfDemoQaAlerts = driver.getTitle();
	  System.out.println("titleOfDemoQaAlerts: "+titleOfDemoQaAlerts);
	  int a = Constants.ExternalFrameBackPackQTY;
	  Assert.assertEquals(titleOfDemoQaAlerts,ObjProperties.getProperty("DemoQaAlertsTitle"));
  }
  
  @Test(priority=0)
  public void ValidateAlertsInsideDemoQA() throws InterruptedException {
	  System.out.println("inside ValidateAlertsInsideDemoQA");
	  
	  //Normal ALert
	  driver.findElement(By.id("alertButton")).click();
	  Alert ObjAlert1 = driver.switchTo().alert();
	  String Alert1 = ObjAlert1.getText();
	  Assert.assertEquals(Alert1, ObjProperties.getProperty("Alert1Test"), "Alert1 is not handled properly");
	  ObjAlert1.accept();
	  
	  Thread.sleep(5000);
	  
	  //Timer Alert
	  driver.findElement(By.id("timerAlertButton")).click();
	 // Thread.sleep(5000); //This is static wait .script is going to wait for the specified milli seconds.
	  
	  /*Explicit Wait : Waiting mechanism applicable for only one webElement.
	   * we can able to take the expected condition with time out duration
	   */
	  WebDriverWait wait = new WebDriverWait(driver,60);
	  wait.until(ExpectedConditions.alertIsPresent());
	  Alert ObjAlert2 = driver.switchTo().alert();
	  String Alert2 = ObjAlert2.getText();
	  Assert.assertEquals(Alert2, ObjProperties.getProperty("Alert2Test"), "Alert2 is not handled properly");
	  ObjAlert2.accept();
	  
	  //conform Alert
	  driver.findElement(By.id("confirmButton")).click();
	  Alert ObjAlert3 = driver.switchTo().alert();
	  String Alert3 = ObjAlert3.getText();
	  Assert.assertEquals(Alert3, ObjProperties.getProperty("Alert3Test"), "Alert3 is not handled properly");
	  ObjAlert3.dismiss();
	  String confirmResultText = driver.findElement(By.id("confirmResult")).getText();
	  SoftAssert SoftAssert = new SoftAssert();
	  SoftAssert.assertEquals(confirmResultText, ObjProperties.getProperty("TextOfconfirmResultWhenUserSelectedCancel"), "You Selected Cancel messsgae is not validated");
	  
	  
	  //PromptBox Alert
	  driver.findElement(By.id("promtButton")).click();
	  Alert ObjAlert4 = driver.switchTo().alert();
	  ObjAlert4.sendKeys(ObjProperties.getProperty("PromptBoxAlertMessage"));
	  ObjAlert4.accept();
	  String  promptResultText = driver.findElement(By.id("promptResult")).getText();
	  SoftAssert.assertEquals(promptResultText, "You entered "+ ObjProperties.getProperty("PromptBoxAlertMessage"), "You Entered User Message is not vaidated");
	  
	  try {
		TakeScreenShot("ValidateAlertsInsideDemoQA");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  SoftAssert.assertAll();
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
