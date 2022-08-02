package com.DataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.pages.RegisterDataDriven;
import com.pages.WebTable;
import com.utility.Constants;
import com.utility.LibraryFunctions;


public class ValidateDataDriven extends JQueryPage {
	HashMap<String,String> hmap = new HashMap<String,String>();
	
	@Test(priority = 0)

	public void ValidateTitleOfWebTablePage() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("AutomationRegister"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut90Sec);
		//JavascriptExecutor js = (JavascriptExecutor) LibraryFunctions.driver;
		//js.executeScript("window.scrollBy(0,400)");//to scroll Vertically down by 400// pixels
		String TitleOfRegisterPage = LibraryFunctions.driver.getTitle();
		System.out.println("TitleOfRegisterPage:"+TitleOfRegisterPage);
		Assert.assertEquals(TitleOfRegisterPage, LibraryFunctions.ObjProperties.getProperty("TitleOfRegisterPage"),
				"TitleOfRegisterPage is not Validated");
	}

	@Test(priority = 1)
	public void ValidateDataDriven() throws IOException {
		try {
			File ObjFile = new File(System.getProperty("user.dir") + "//src//test//resources//materials//AutomationDemoSite.xlsx");

			FileInputStream ObjFileInputStream = new FileInputStream(ObjFile);
			XSSFWorkbook objWorkBook = new XSSFWorkbook(ObjFileInputStream);
			XSSFSheet objSheet = objWorkBook.getSheet("TestData");
			int NumberOfRows = objSheet.getLastRowNum();
			for(int row =1 ; row<=NumberOfRows ; row++) {
				hmap = LibraryFunctions.ReadExcelFile(objSheet, row);
				LibraryFunctions.driver.findElement(RegisterDataDriven.FristName).clear();
				LibraryFunctions.driver.findElement(RegisterDataDriven.FristName).sendKeys(hmap.get("FirstName"));
				
				LibraryFunctions.driver.findElement(RegisterDataDriven.LastName).clear();
				LibraryFunctions.driver.findElement(RegisterDataDriven.LastName).sendKeys(hmap.get("LastName"));
				
				LibraryFunctions.driver.findElement(RegisterDataDriven.Adress).clear();
				LibraryFunctions.driver.findElement(RegisterDataDriven.Adress).sendKeys(hmap.get("Address"));
				
				LibraryFunctions.driver.findElement(RegisterDataDriven.Emailadress).clear();
				LibraryFunctions.driver.findElement(RegisterDataDriven.Emailadress).sendKeys(hmap.get("EmailAddress"));
				
				LibraryFunctions.driver.findElement(RegisterDataDriven.PhoneNum).clear();
				LibraryFunctions.driver.findElement(RegisterDataDriven.PhoneNum).sendKeys(hmap.get("PhoneNumber"));
				
				if(hmap.get("Gender").equals("Male")) {
					LibraryFunctions.driver.findElement(RegisterDataDriven.GenderMale).click();
				}else {
					LibraryFunctions.driver.findElement(RegisterDataDriven.GenderFeMale).click();
				}
				
				if(hmap.get("Hobbies").equals("Cricket")) {
					LibraryFunctions.driver.findElement(RegisterDataDriven.cricketChecbox).click();
				} else if(hmap.get("Hobbies").equals("Movies")) {
					LibraryFunctions.driver.findElement(RegisterDataDriven.MoviesChecbox).click();
				} else if(hmap.get("Hobbies").equals("Hockey")) {
					LibraryFunctions.driver.findElement(RegisterDataDriven.hockeyChecbox).click();
				}
				
				LibraryFunctions.driver.findElement(RegisterDataDriven.Languages).click();
				List<WebElement> AllLanguages = LibraryFunctions.driver.findElements(RegisterDataDriven.All_languages);
				LibraryFunctions.SelectValueFromDropDown(AllLanguages, hmap.get("Languages"));
				
				LibraryFunctions.driver.findElement(RegisterDataDriven.Tag_skills).click();
				
				LibraryFunctions.driver.findElement(RegisterDataDriven.Skills).click();
				List<WebElement> AllSkills = LibraryFunctions.driver.findElements(RegisterDataDriven.Allskills);
				LibraryFunctions.SelectValueFromDropDown(AllSkills, hmap.get("Skills"));
				
				
				
				//LibraryFunctions.driver.findElement(RegisterDataDriven.Adress).sendKeys(hmap.get("Address"));
				//LibraryFunctions.driver.findElement(RegisterDataDriven.Adress).sendKeys(hmap.get("Address"));
				//LibraryFunctions.driver.findElement(RegisterDataDriven.Adress).sendKeys(hmap.get("Address"));
			}
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
