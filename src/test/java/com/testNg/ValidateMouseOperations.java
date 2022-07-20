package com.testNg;

import java.io.IOException;
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
import com.utility.Constants;
import com.utility.LibraryFunctions;


public class ValidateMouseOperations extends JQueryPage {

	@Test(priority = 0)

	public void ValidateLoadingTheJQueryPage() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("mouseOpeartionRightClick"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut90Sec);
		String titleOFJquery = LibraryFunctions.driver.getTitle();
		Assert.assertEquals(titleOFJquery, LibraryFunctions.ObjProperties.getProperty("JQeryTitle"),
				"Tile of Jquery Is not Validated");

	}

	@Test(priority = 1)
	public void ValidateRightClick() {
		LibraryFunctions.RightClickonWebElement(RightClickMe);
		clickAction(JQueryPage.Copy);
		Alert ObjAlert = LibraryFunctions.driver.switchTo().alert();
		String AlertText = ObjAlert.getText();
		Assert.assertEquals(AlertText, LibraryFunctions.ObjProperties.getProperty("RigtClickCopy"));
		ObjAlert.accept();
	}

	@Test(priority = 2)
	public void ValidateDoubleClick() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("mouseOpeartionDoubleClick"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut60Sec);
		JavascriptExecutor js = (JavascriptExecutor) LibraryFunctions.driver;
		// js.executeScript("window.scrollBy(0,500)");//to scroll Vertically down by 500
		// pixels
		// js.executeScript("window.scrollBy(0,-350)");//to scroll Vertically up by 350
		// pixels
		// js.executeScript("window.scrollBy(320,0)");//to scroll right side by 320
		// pixels
		// js.executeScript("window.scrollBy(-240,0)");//to scroll right side by 240
		// pixel
		WebElement FrameElement = LibraryFunctions.driver.findElement(JQueryPage.Frame);
		js.executeScript("arguments[0].scrollIntoView(true);", FrameElement);
		LibraryFunctions.driver.switchTo().frame(FrameElement);
		LibraryFunctions.DoubleClick(DoubleClickBox);

		Color BackGroundColor = Color.fromString(LibraryFunctions.driver.findElement(DoubleClickBox).getCssValue("background-color"));
		System.out.println("BackGroundColor from WebPage:" + BackGroundColor);
		String ActualBackGroundColor = BackGroundColor.asRgba();
		System.out.println("ActualBackGroundColor in Rgba Format:" + ActualBackGroundColor);
		Assert.assertEquals(ActualBackGroundColor, "rgba(255, 255, 0, 1)");
		LibraryFunctions.driver.switchTo().defaultContent();// to come out of frame

	}
	
	@Test(priority = 3)
	
	public void ValidateDragAndDrop() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("mouseOperationDragAndDrop"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut60Sec);
		WebElement FrameElement = LibraryFunctions.driver.findElement(Frame);
		LibraryFunctions.driver.switchTo().frame(FrameElement);
		LibraryFunctions.DragAndDrop(JQueryPage.Draggable,JQueryPage.Draggable);
		//write a code to validate colour
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
}
