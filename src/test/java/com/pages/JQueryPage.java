package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.utility.LibraryFunctions;

public class JQueryPage {
	public static Actions objActions;
	
	static By RightClickMe = By.xpath("//span[contains(text(),'right click me')]");
	
	
	public static void RightCLickonWebElement() {
		objActions = new Actions(LibraryFunctions.driver);
		WebElement RightClick = LibraryFunctions.driver.findElement(RightClickMe);
		objActions.contextClick(RightClick).build().perform();
		
	}

}
