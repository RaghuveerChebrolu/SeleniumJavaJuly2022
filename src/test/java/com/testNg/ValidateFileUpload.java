package com.testNg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

import com.pages.FileUploadPage;
import com.pages.JQueryPage;
import com.pages.NxtGenWindowsPage;
import com.pages.WebTable;
import com.utility.Constants;
import com.utility.LibraryFunctions;


public class ValidateFileUpload extends JQueryPage {
	
	public static Iterator<String> itr ;

	@Test(priority = 0)

	public void ValidateTitleOfWebFileUploadPage() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("FileUpload"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut90Sec);
		String TitleOfFileUploadPage = LibraryFunctions.driver.getTitle();
		System.out.println("TitleOfFileUploadPage:"+TitleOfFileUploadPage);
		Assert.assertEquals(TitleOfFileUploadPage, LibraryFunctions.ObjProperties.getProperty("TiTleOfFileupload"),
				"TitleOfFileUploadPage is not Validated");

	}

	@Test(priority = 1)
	public void ValidateFileUpload() throws InterruptedException {
		Actions Obj = new Actions(LibraryFunctions.driver);
		WebElement element = LibraryFunctions.driver.findElement(FileUploadPage.BrowseButton);
		/*
		 * Boolean flag = element.isEnabled(); i
		 * f(flag==true) {
		 * 
		 * }
		 */
		Obj.click(element).build().perform();
		//LibraryFunctions.driver.findElement(FileUploadPage.BrowseButton).click();
	
		StringSelection objStringSelection = new StringSelection(
				System.getProperty("user.dir") + "\\src\\test\\resources\\materials\\Sample.jpg");
		Clipboard objClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		objClipboard.setContents(objStringSelection, null);
		Transferable objTransferable = objClipboard.getContents(null);
		if (objTransferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			try {
				System.out.println(objTransferable.getTransferData(DataFlavor.stringFlavor));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Robot objRobot = new Robot();
			objRobot.keyPress(KeyEvent.VK_ENTER);
			objRobot.keyRelease(KeyEvent.VK_ENTER);
			objRobot.wait(250);
			objRobot.keyPress(KeyEvent.VK_CONTROL);
			objRobot.keyPress(KeyEvent.VK_V);
			objRobot.wait(250);
			objRobot.keyRelease(KeyEvent.VK_CONTROL);
			objRobot.keyRelease(KeyEvent.VK_V);
			objRobot.wait(2500);
			objRobot.keyPress(KeyEvent.VK_ENTER);
			objRobot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
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
