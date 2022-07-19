package com.testNg;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
import com.utility.LibraryFunctions;

public class ValidateMouseOperations extends JQueryPage{

	@Test(priority = 0)

	public void ValidateLoadingTheJQueryPage() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("mouseOpeartionRightClick"));
		LibraryFunctions.WaitingForPageToLoad(90);
		String titleOFJquery = LibraryFunctions.driver.getTitle();
		Assert.assertEquals(titleOFJquery, LibraryFunctions.ObjProperties.getProperty("JQeryTitle"),
				"Tile of Jquery Is not Validated");

	}
	
	@Test(priority=1)
	public void ValidateRightClick() {
		RightCLickonWebElement();
		
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
