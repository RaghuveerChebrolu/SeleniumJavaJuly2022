package com.utility;

import java.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
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

}
