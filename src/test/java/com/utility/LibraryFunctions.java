package com.utility;

import java.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LibraryFunctions {

	public static Properties ObjProperties;
	public static WebDriver driver;
	public static Actions objActions;
	public static WebDriverWait Wait;
	public static HashMap<String,String> hmap = new HashMap<String,String>();
	/*
	 * Raghu : This method is used read the information provided
	 * in the properties file   
	 */
	public static void ReadPropertiesFile() throws IOException {
		try {
			//System.out.println(System.getProperty("user.dir"));
		//	File objFile = new File(System.getProperty("user.dir") + "//src//test//resources//config.properties");
			FileInputStream objFileInput = new FileInputStream(new String(System.getProperty("user.dir") + "//src//test//resources//config.properties"));
			ObjProperties = new Properties();
			ObjProperties.load(objFileInput);
			//System.out.println(ObjProperties.getProperty("GmoOnlineUrl"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void WaitingForPageToLoad(int time){
		LibraryFunctions.driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}

	/*
	 * Raghu : This method is used to get the browser information from properties file , launch 
	 * the respective browser and application URL  
	 */
	public static void LaunchBrowser() {
		String browser =ObjProperties.getProperty("browser");
		switch(browser) {
		case "ie":
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			ChromeOptions objChromeOptions = new ChromeOptions();
			objChromeOptions.setAcceptInsecureCerts(true);
			// driver= new ChromeDriver(objChromeOptions);
			Map<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.prompt_for_download", false);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
			objChromeOptions.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(objChromeOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "opeara":
			WebDriverManager.operadriver();
			driver=new OperaDriver();
			break;
		default :
			System.out.println("default case");
		}
		driver.get(ObjProperties.getProperty("GmoOnlineUrl"));
		driver.manage().window().maximize();
		//implicit wait : Global waiting mechanism which is applicable for all WebElements in we page.
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	}
	
	public static void DoubleClick(By element) {
		WebElement DoubleClick = LibraryFunctions.driver.findElement(element);
		objActions = new Actions(driver);
		objActions.doubleClick(DoubleClick).build().perform();
	}
	

	public static void RightClickonWebElement(By element) {
		WebElement RightClick = LibraryFunctions.driver.findElement(element);
		objActions = new Actions(driver);
		objActions.contextClick(RightClick).build().perform();
		
	}
	
	public static void DragAndDrop(By draggable, By droppable) {
		WebElement DragElement_Source = LibraryFunctions.driver.findElement(draggable);
		WebElement DropElement_Destination = LibraryFunctions.driver.findElement(droppable);
		objActions = new Actions(driver);
		ExplicitWaitUntilElementToBeClickable(DragElement_Source);
		objActions.clickAndHold(DragElement_Source);
		objActions.moveToElement(DropElement_Destination);
		objActions.build().perform();
		//objActions.dragAndDrop(DragElement_Source, DropElement_Destination).build().perform();
	}
	
	

	public static void ClickOnWebElement(By newBroswerWindowButton) {
		objActions = new Actions(driver);
		WebElement element = LibraryFunctions.driver.findElement(newBroswerWindowButton);
		ExplicitWaitUntilElementToBeClickable(element);
		objActions.click(element).build().perform();
	}
	
	
	
	//Raghu : Explicit wait : It will wait until the webElement is clickable
	public static void ExplicitWaitUntilElementToBeClickable(WebElement element){
		Wait = new WebDriverWait(driver,Constants.ExplicitWait60Sec); 
		Wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// explicit wait -> Applicable for one webEllement
		WebDriverWait wait = new WebDriverWait(driver, 120);// 120 seconds
		wait.until(pageLoadCondition);
	}
	
	/* Author : Raghuveer
	 * This method is used to take screen shot and store the screen shots in side ScreenShot folder
	 */
	public static void TakeScreenShot() {
		try {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String destination = System.getProperty("user.dir") + "//ScreenShots//" + dateName + "captured.jpeg";
		FileUtils.copyFile(src, new File(destination));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* Author : Raghuveer
	 * This method is used to take screen shot and store the screen shots in side ScreenShot folder
	 */
	public static String TakeScreenShot(String testcaseName) throws IOException {
		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String destination = System.getProperty("user.dir") + "//ScreenShots//" + dateName + testcaseName+"captured.jpeg";
		FileUtils.copyFile(src, new File(destination));
		return destination;
		
	}
	
	/* Author : Raghuveer
	 * This method is used to take screen shot and store the screen shots in side ScreenShot folder
	 */
	public static void TakeScreenShotofSpecifiedWebElement(WebElement element) {
		try {
		File src = element.getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String destination = System.getProperty("user.dir") + "//ScreenShots//" + dateName + "captured.jpeg";
		FileUtils.copyFile(src, new File(destination));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static HashMap<String, String> ReadExcelFile(XSSFSheet objWorkbookSheet, int row) {
		DataFormatter ObjFormatter = new DataFormatter();
		hmap.put("RunMode",objWorkbookSheet.getRow(row).getCell(0).getStringCellValue());
		hmap.put("TestCaseName",objWorkbookSheet.getRow(row).getCell(1).getStringCellValue());
		hmap.put("FirstName",objWorkbookSheet.getRow(row).getCell(2).getStringCellValue());
		hmap.put("LastName",objWorkbookSheet.getRow(row).getCell(3).getStringCellValue());
		hmap.put("Address",objWorkbookSheet.getRow(row).getCell(4).getStringCellValue());
		hmap.put("EmailAddress",objWorkbookSheet.getRow(row).getCell(5).getStringCellValue());
		
		hmap.put("PhoneNumber", ObjFormatter.formatCellValue(objWorkbookSheet.getRow(row).getCell(6)));
		
		hmap.put("Gender",objWorkbookSheet.getRow(row).getCell(7).getStringCellValue());
		hmap.put("Hobbies",objWorkbookSheet.getRow(row).getCell(8).getStringCellValue());
		hmap.put("Languages",objWorkbookSheet.getRow(row).getCell(9).getStringCellValue());
		hmap.put("Skills",objWorkbookSheet.getRow(row).getCell(10).getStringCellValue());
		hmap.put("Country",objWorkbookSheet.getRow(row).getCell(11).getStringCellValue());
		hmap.put("SelectCountry",objWorkbookSheet.getRow(row).getCell(12).getStringCellValue());
		
		hmap.put("DOB_YY",ObjFormatter.formatCellValue(objWorkbookSheet.getRow(row).getCell(13)));
		
		hmap.put("DOB_MM",objWorkbookSheet.getRow(row).getCell(14).getStringCellValue());
		
		hmap.put("DOB_DD",ObjFormatter.formatCellValue(objWorkbookSheet.getRow(row).getCell(15)));
		
		hmap.put("Password",objWorkbookSheet.getRow(row).getCell(16).getStringCellValue());
		hmap.put("confirmPassword	",objWorkbookSheet.getRow(row).getCell(17).getStringCellValue());
		
		
		return hmap;

	}
	
	public static void SelectValueFromDropDown(List<WebElement> AllElements, String DropDownValue) {
	
		for(int i =0; i<AllElements.size();i++) {
			String IndividualValue = AllElements.get(i).getText();
			System.out.println("IndividualValue at index: "+i+"is "+IndividualValue);
			if(IndividualValue.equalsIgnoreCase(DropDownValue)) {
				AllElements.get(i).click();
				break;
			}
		}
		
	}


}
