package com.testNg;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pages.FramesPage;
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

public class testNgFramesPOM extends FramesPage {
	@Test(priority = -1)
	public void ValidatingSingleFrame() {
		System.out.println("inside ValidatingSingleFrame");
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("FramesURL"));
		LibraryFunctions.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		String titleofFrames = LibraryFunctions.driver.getTitle();
		System.out.println("titleofFrames: " + titleofFrames);
		Assert.assertEquals(titleofFrames, LibraryFunctions.ObjProperties.getProperty("TitleOfFrames"));

		// identifying the single frame and switching to it
		LibraryFunctions.driver.switchTo().frame("singleframe");
		// locating the webElement inside Frame
		EnterTextInSideTesxtBoxInsideSingleFrame();

		LibraryFunctions.driver.switchTo().defaultContent(); // to give back the control to normal we page ie. to come
																// out of the frame
		ClickOnFrameWithInIframeButton();

	}

	@Test(priority = 2, dependsOnMethods = { "ValidatingSingleFrame" })
	public void validatingIframeWithInIframe() {
		System.out.println("inside validatingIframeWithInIframe");
		WebElement OuterFrameElement =FindMultipleFrameWebElement();
		LibraryFunctions.driver.switchTo().frame(OuterFrameElement);
		WebElement InnerFrameElement = FindSingleFrameWebElement();
		LibraryFunctions.driver.switchTo().frame(InnerFrameElement);
		EnterTextInSideTesxtBoxInsideMultipleFrames();
		LibraryFunctions.driver.switchTo().defaultContent();
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
	 * execution order : 1. Priority (negative to 0 to positive) 2. UpeerCase and
	 * Ascending order 3. LowerCase and Ascending order
	 * 
	 * Note : if priority is not assigned to individual test case then testNg by
	 * default will assign 0 priority to the respective test case
	 * 
	 * 
	 */
}
