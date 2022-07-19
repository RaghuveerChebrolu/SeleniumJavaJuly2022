package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utility.LibraryFunctions;

public class FramesPage extends LibraryFunctions {

	static By singleFrame = By.id("singleframe");
	static By TextBoxInsideFrame_s = By.xpath("//input[@type='text']");
	static By FrameWithInIframeButton = By.xpath("//a[contains(text(),'an Iframe')]");
	static By MultipleFrames = By.xpath("//iframe[@src='MultipleFrames.html']");
	static By SingleFrame = By.xpath("//iframe[@src='SingleFrame.html']");

	public static void EnterTextInSideTesxtBoxInsideSingleFrame() {
		// TODO Auto-generated method stub
		driver.findElement(TextBoxInsideFrame_s)
				.sendKeys(LibraryFunctions.ObjProperties.getProperty("SingleFrameText"));
	}

	public void ClickOnFrameWithInIframeButton() {
		// we can also idetify using //a[text()='Iframe with in an Iframe'] or
		// //a[@href='#Multiple']
		LibraryFunctions.driver.findElement(FrameWithInIframeButton).click();
	}

	public static void EnterTextInSideTesxtBoxInsideMultipleFrames() {
		// TODO Auto-generated method stub
		driver.findElement(TextBoxInsideFrame_s)
				.sendKeys(LibraryFunctions.ObjProperties.getProperty("IframeWithinIFrameText"));
	}

	public static WebElement FindMultipleFrameWebElement() {
		return driver.findElement(MultipleFrames);

	}

	public static WebElement FindSingleFrameWebElement() {
		return driver.findElement(SingleFrame);
	}
}
