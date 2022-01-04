package com.common.classes;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertPopup {

	WebDriver web;
	public String actualResult;

	//Parameterized constructor and PageFactory initialization
	public AlertPopup(WebDriver driver) 
	{
		web=driver;
		PageFactory.initElements(web,this);
	}


	public void successPopup() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(web, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = web.switchTo().alert();
		actualResult = alert.getText();
		alert.accept();
	}
}
