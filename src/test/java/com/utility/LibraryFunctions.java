package com.utility;

import java.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
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
			WebDriverManager.chromedriver().setup();;
			driver=new ChromeDriver();
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


}
