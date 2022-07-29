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
import java.io.File;
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

import com.pages.FileDownloadPage;
import com.pages.FileUploadPage;
import com.pages.JQueryPage;
import com.pages.NxtGenWindowsPage;
import com.pages.WebTable;
import com.utility.Constants;
import com.utility.LibraryFunctions;


public class ValidateFileDownload extends JQueryPage {
	
	
	@Test(priority = 0)

	public void ValidateTitleOfFileDownloadPage() {
		LibraryFunctions.driver.navigate().to(LibraryFunctions.ObjProperties.getProperty("FileDownload"));
		LibraryFunctions.WaitingForPageToLoad(Constants.PageLoadTimeOut90Sec);
		String TitleOfFileDownloadPage = LibraryFunctions.driver.getTitle();
		System.out.println("TitleOfFileDownloadPage:"+TitleOfFileDownloadPage);
		//Assert.assertEquals(TitleOfFileDownloadPage, LibraryFunctions.ObjProperties.getProperty(""),
			//	"TitleOfFileUploadPage is not Validated");

	}

	@Test(priority = 1)
	public void ValidateFileDownLoad() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) LibraryFunctions.driver;
		js.executeScript("window.scrollBy(0,400)");//to scroll Vertically down by 400// pixels
		
		LibraryFunctions.driver.findElement(FileDownloadPage.sample100kbFile).click();
		Thread.sleep(8000);
		File objFile = new File(System.getProperty("user.dir"));
		File[] Allfiles = objFile.listFiles();
		
		for(File IndidvidualFile : Allfiles) {
			String FileNane = IndidvidualFile.getName();
			System.out.println("FileNane:"+FileNane);
			Boolean fileFoundFlag = false;
			if(FileNane.contains("100kB")) {
				fileFoundFlag=true;
				File IdentifiedDownloadedFile = new File(FileNane)	;	
				Assert.assertTrue(fileFoundFlag, "File Download  is not va;idated");
				IdentifiedDownloadedFile.deleteOnExit();
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
