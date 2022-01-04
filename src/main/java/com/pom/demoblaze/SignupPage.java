package com.pom.demoblaze;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.classes.ExcelDataConfig;
import com.common.classes.RandomName;

public class SignupPage {

	WebDriver web;
	public String actualResult;

	//Parameterized constructor and PageFactory initialization
	public SignupPage(WebDriver driver) 
	{
		web=driver;
		PageFactory.initElements(web,this);
	}

	//All elements are defined on page level and loading at the time of accessing as per PageFactory logic.
	@FindBy(xpath = ".//input[contains(@id, 'sign-username')]")
	WebElement username;

	@FindBy(xpath = ".//input[contains(@id, 'sign-password')]")
	WebElement password;

	@FindBy(xpath = ".//button[normalize-space(text())='Sign up']")
	WebElement signUpBtn;

	@FindBy(xpath = ".//button[normalize-space(text())='Sign up']/preceding-sibling::button")
	WebElement closeBtn;

	//Sign up process completion method.
	public void enterSignUpDetails() throws IOException
	{
		WebDriverWait wait = new WebDriverWait(web, 10);
		wait.until(ExpectedConditions.visibilityOf(username));
		String path = System.getProperty("user.dir");
		//Data read from excel sheet
		ExcelDataConfig edc = new ExcelDataConfig(path + "/DataFiles/DataFile.xlsx");
		edc.getData("Credentials",1, 1);
		//Used Random class for generating always new user name whenever the script will start their execution. 
		username.sendKeys(RandomName.generateRandomName(edc.excelStr)); //Passing a value to user name field
		edc.getData("Credentials",1, 2); 
		password.sendKeys(edc.excelStr);//Passing a value to password field
		signUpBtn.click(); //Click action on SignUP button.
	}

}

