package com.pom.demoblaze;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.classes.ExcelDataConfig;
import com.common.classes.RandomName;

public class LoginPage {
	
	WebDriver web;
	public String actualResult;

	//Parameterized constructor and PageFactory initialization
	public LoginPage(WebDriver driver) 
	{
		web=driver;
		PageFactory.initElements(web,this);
	}

	//All elements are defined on page level and loading at the time of accessing as per PageFactory logic.
		@FindBy(xpath = ".//input[contains(@id, 'loginusername')]")
		WebElement username;

		@FindBy(xpath = ".//input[contains(@id, 'loginpassword')]")
		WebElement password;

		@FindBy(xpath = ".//button[normalize-space(text())='Log in']")
		WebElement loginBtn;

		@FindBy(xpath = ".//button[normalize-space(text())='Log in']/preceding-sibling::button")
		WebElement closeBtn;

		public void loginCredentials() throws IOException
		{
			WebDriverWait wait = new WebDriverWait(web, 10);
			wait.until(ExpectedConditions.elementToBeClickable(username));
			//passing a stored value of user name to user name field which basically comes from RandomName class. 
			username.sendKeys(RandomName.str1);
			String path = System.getProperty("user.dir");
			//Read data from excel sheet.
			ExcelDataConfig edc = new ExcelDataConfig(path + "/DataFiles/DataFile.xlsx");
			edc.getData("Credentials",1, 2); 
			password.sendKeys(edc.excelStr); //Passing a value to password field
			loginBtn.click(); //Click action on Login button.
		}

}
