package com.testNg;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
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
import com.pages.TutorialsHutPage;
import com.utility.Constants;
import com.utility.LibraryFunctions;


public class ValidateDropDown extends JQueryPage {
	
	public static Iterator<String> itr ;

	@Test(priority = 0)

	public void ValidateLoadingTutorialsHut() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("DropDownUrl"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut90Sec);
		String titleOfTutorialsHut = LibraryFunctions.driver.getTitle();
		System.out.println("titleOfTutorialsHut:"+titleOfTutorialsHut);
		Assert.assertEquals(titleOfTutorialsHut, LibraryFunctions.ObjProperties.getProperty("titleOfTutorialsHut"),
				"titleOfTutorialsHut is not Validated");

	}

	@Test(priority = 1)
	public void ValidateDropDownAvailable() {
		WebElement element = LibraryFunctions.driver.findElement(TutorialsHutPage.DropDownInTutorialsHut);
		
		JavascriptExecutor js = (JavascriptExecutor) LibraryFunctions.driver;
		js.executeScript("window.scrollBy(0,250)");//to scroll Vertically down by 250
		element.click();
		Select objSelect = new Select(element);
		//objSelect.selectByValue("Etl");
		//objSelect.selectByVisibleText("Performance Testing");
		objSelect.selectByIndex(Constants.DropDownfromTutorialsHutFunctionalTesting);
		List<WebElement>AllDropDownItems = objSelect.getOptions();
		System.out.println("number of drop down values:"+AllDropDownItems.size());
		for(int i=0;i<AllDropDownItems.size();i++) {
			System.out.println(AllDropDownItems.get(i).getText());
		}
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
