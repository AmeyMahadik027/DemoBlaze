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

public class ContactPage {

	WebDriver web;
	public String actualResult;
	
	//Parameterized constructor and PageFactory initialization
	public ContactPage(WebDriver driver) 
	{
		web=driver;
		PageFactory.initElements(web,this);
	}

	//All elements are defined on page level and loading at the time of accessing as per PageFactory logic.
	@FindBy(xpath = ".//input[contains(@id, 'recipient-email')]")
	WebElement contactEmail;

	@FindBy(xpath = ".//input[contains(@id, 'recipient-name')]")
	WebElement contactName;
	
	@FindBy(xpath = ".//textarea[@id='message-text']")
	WebElement contactMessage;
	
	@FindBy(xpath = ".//button[normalize-space(text())='Send message']")
	WebElement sendMsgBTN;

	@FindBy(xpath = ".//button[normalize-space(text())='Send message']/preceding-sibling::button")
	WebElement closeBtn;
	
	public void contactDetailsProcess() throws IOException
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.elementToBeClickable(contactEmail));
		String path = System.getProperty("user.dir");
		//Read data from excel sheet.
		ExcelDataConfig edc = new ExcelDataConfig(path + "/DataFiles/DataFile.xlsx");
		edc.getData("ContactInfo",1, 0); //Passing a value to contact email field
		contactEmail.sendKeys(edc.excelStr);
		edc.getData("ContactInfo",1, 1); //Passing a value to Contact Name field
		contactName.sendKeys(edc.excelStr);
		edc.getData("ContactInfo",1, 2); //Passing a value to contact message field
		contactMessage.sendKeys(edc.excelStr);
		sendMsgBTN.click(); //Click action on send message button
	}
	
}
