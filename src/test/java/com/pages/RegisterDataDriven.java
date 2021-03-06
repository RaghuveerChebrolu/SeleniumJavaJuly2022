package com.pages;

import org.openqa.selenium.By;

public class RegisterDataDriven {

	public static By FristName= By.xpath("//input[@placeholder='First Name']");
	public static By LastName= By.xpath("//input[@placeholder='Last Name']");
	public static By Adress= By.xpath("//textarea[@ng-model='Adress']");
	public static By Emailadress= By.xpath("//input[@ng-model='EmailAdress']");
	public static By PhoneNum= By.xpath("//input[@ng-model='Phone']");
	public static By GenderMale= By.xpath("//input[@value='Male']");
	public static By GenderFeMale= By.xpath("//input[@value='FeMale']");
	public static By cricketChecbox= By.id("checkbox1");
	public static By MoviesChecbox= By.id("checkbox2");
	public static By hockeyChecbox= By.id("checkbox3");
	
	
	public static By closeIconOFLanguageSelected= By.xpath("//span[@class='ui-icon ui-icon-close']");
	public static By Languages= By.id("msdd");
	public static By All_languages= By.xpath("//div[@id='msdd']/following-sibling::div/ul/li/a");
	
	
	public static By Tag_skills= By.xpath("//*[text()='Skills']");
	public static By Skills= By.id("Skills");
	public static By Allskills= By.xpath("//select[@id='Skills']/option");
	
	public static By selectCountry = By.xpath("//span[@role='combobox']");
	public static By AllCountries = By.xpath("//ul[@id='select2-country-results']/li");
	
	public static By DOB_Year = By.xpath("//select[@id='yearbox']");
	public static By AllYears = By.xpath("//select[@id='yearbox']/option");
	public static By DOB_Month = By.xpath("//select[@placeholder='Month']");
	public static By AllMonths = By.xpath("//select[@placeholder='Month']/option");
	public static By DOB_Day = By.xpath("//select[@id='daybox']");
	public static By AllDays = By.xpath("//select[@id='daybox']/option");
	public static By firstpassword = By.id("firstpassword");
	public static By secondpassword = By.id("secondpassword");



}
