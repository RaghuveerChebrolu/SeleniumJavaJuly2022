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
import com.pages.WebTable;
import com.utility.Constants;
import com.utility.LibraryFunctions;


public class ValidateWebTable extends JQueryPage {
	
	public static Iterator<String> itr ;

	@Test(priority = 0)

	public void ValidateTitleOfWebTablePage() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("WebTableURL"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut90Sec);
		JavascriptExecutor js = (JavascriptExecutor) LibraryFunctions.driver;
		js.executeScript("window.scrollBy(0,400)");//to scroll Vertically down by 400// pixels
		String TitleOfWebTablePage = LibraryFunctions.driver.getTitle();
		System.out.println("TitleOfWebTablePage:"+TitleOfWebTablePage);
	//	Assert.assertEquals(TitleOfWebTablePage, LibraryFunctions.ObjProperties.getProperty("nxtgenaiacademyTitle"),
		//		"nxtgenaiacademyTitle is not Validated");

	}

	@Test(priority = 1)
	public void ValidateWebTable() {
		List<WebElement> AllRows = LibraryFunctions.driver.findElements(WebTable.RowsInWebtable);
		int NumberOfRows = AllRows.size();
		for(int rowNumber=1 ; rowNumber<=NumberOfRows ;rowNumber++) {
			String LastName = LibraryFunctions.driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+rowNumber+"]/td[3]")).getText();
			if(LastName.equals(LibraryFunctions.ObjProperties.getProperty("WebTableLastName"))){
				String FirstName = LibraryFunctions.driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+rowNumber+"]/td[2]")).getText();
				String Position = LibraryFunctions.driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+rowNumber+"]/td[4]")).getText();
				String Office = LibraryFunctions.driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+rowNumber+"]/td[5]")).getText();
				String StartDate = LibraryFunctions.driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+rowNumber+"]/td[6]")).getText();
				String Salary = LibraryFunctions.driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+rowNumber+"]/td[7]")).getText();
				System.out.println("FirstName:"+FirstName);
				System.out.println("Position:"+Position);
				System.out.println("StartDate:"+StartDate);
				System.out.println("Office:"+Office);
				System.out.println("Salary:"+Salary);
			}
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
