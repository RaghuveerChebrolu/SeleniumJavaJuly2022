package com.testNg;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.JQueryPage;
import com.pages.NxtGenWindowsPage;
import com.utility.Constants;
import com.utility.LibraryFunctions;


public class ValidateMultipleWindows extends JQueryPage {
	
	public static Iterator<String> itr ;

	@Test(priority = 0)

	public void ValidateLoadingNxtGenAIAcademyMultipleWindows() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("nxtgenaiacademyURL"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut90Sec);
		String titleOfNxtGenAIAcademy = LibraryFunctions.driver.getTitle();
		System.out.println("titleOfNxtGenAIAcademy:"+titleOfNxtGenAIAcademy);
		Assert.assertEquals(titleOfNxtGenAIAcademy, LibraryFunctions.ObjProperties.getProperty("nxtgenaiacademyTitle"),
				"nxtgenaiacademyTitle is not Validated");

	}

	@Test(priority = 1)
	public void ValidateNewBrowserWindow() {
		String ParentWindowHandle = LibraryFunctions.driver.getWindowHandle();
		LibraryFunctions.ClickOnWebElement(NxtGenWindowsPage.NewBroswerWindowButton);
		Set<String> AllWindows = LibraryFunctions.driver.getWindowHandles();

		for (String IndividualWindow : AllWindows) {
			LibraryFunctions.driver.switchTo().window(IndividualWindow);
			String Title = LibraryFunctions.driver.getTitle();
			System.out.println("Title:" + Title);
			if (Title.equals(LibraryFunctions.ObjProperties.getProperty("newBrowserWindowTitle"))) {
				LibraryFunctions.ClickOnWebElement(NxtGenWindowsPage.NewBroswerWindowMenu);
				LibraryFunctions.ClickOnWebElement(NxtGenWindowsPage.NewBroswerWindowMenuQaAutomation);
				LibraryFunctions.driver.close();

			}
		}

		LibraryFunctions.driver.switchTo().window(ParentWindowHandle);

		/*
		for(int i=1;i<=AllWindows.size();i++) {
			itr= AllWindows.iterator();
			LibraryFunctions.driver.switchTo().window(itr.next());
			String Title = LibraryFunctions.driver.getTitle();
			System.out.println("Title:"+Title);
			if(Title.equals(LibraryFunctions.ObjProperties.getProperty("newBrowserWindowTitle"))){
				LibraryFunctions.ClickOnWebElement(NxtGenWindowsPage.NewBroswerWindowMenu);
				LibraryFunctions.ClickOnWebElement(NxtGenWindowsPage.NewBroswerWindowMenuQaAutomation);
				LibraryFunctions.driver.close();
			
			}
		}
		*/
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
}
