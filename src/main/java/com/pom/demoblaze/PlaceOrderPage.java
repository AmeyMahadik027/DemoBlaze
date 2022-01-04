package com.pom.demoblaze;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.classes.ExcelDataConfig;
import com.common.classes.RandomName;

public class PlaceOrderPage 
{
	WebDriver web;
	public String details;
	
	//Parameterized constructor and PageFactory initialization
	public PlaceOrderPage(WebDriver driver) 
	{
		web=driver;
		PageFactory.initElements(web,this);
	}

	//All elements are defined on page level and loading at the time of accessing as per PageFactory logic.
	@FindBy(xpath = ".//button[normalize-space(text())='Place Order']")
	WebElement placeOrderBTN;

	@FindBy(xpath = ".//button[normalize-space(text())='Purchase']")
	WebElement purchaseBTN;

	@FindBy(xpath = ".//button[normalize-space(text())='Purchase']/preceding-sibling::button")
	WebElement closeBtn;

	@FindBy(xpath = ".//input[@id='name']")
	WebElement name;

	@FindBy(xpath = ".//input[@id='country']")
	WebElement country;

	@FindBy(xpath = ".//input[@id='city']")
	WebElement city;

	@FindBy(xpath = ".//input[@id='card']")
	WebElement creditCard;

	@FindBy(xpath = ".//input[@id='month']")
	WebElement month;

	@FindBy(xpath = ".//input[@id='year']")
	WebElement year;

	@FindBy(xpath = ".//button[normalize-space(text())='OK']")
	WebElement OkBTN;

	@FindBy(xpath = ".//p[contains(@class, 'lead')]")
	List <WebElement> paymentInfo;

	@FindBy(xpath = ".//h2[normalize-space(text())='Thank you for your purchase!']")
	WebElement message;
	
	@FindBy(xpath = ".//a[normalize-space(text())='Delete']/../..")
	WebElement tBody;
	
	public void placeOrder() throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.visibilityOf(tBody));
		placeOrderBTN.click();
		wait.until(ExpectedConditions.elementToBeClickable(name));
		String path = System.getProperty("user.dir");
		//Excel Sheet data are accessing from different class via below methods.
		ExcelDataConfig edc = new ExcelDataConfig(path + "/DataFiles/DataFile.xlsx");
		edc.getData("PlaceOrder",1, 0); 
		name.sendKeys(edc.excelStr);
		edc.getData("PlaceOrder",1, 1);
		country.sendKeys("India");
		edc.getData("PlaceOrder",1, 2);
		city.sendKeys("Pune");
		edc.getData("PlaceOrder",1, 3);
		creditCard.sendKeys("4444 4444 4444 4444");
		edc.getData("PlaceOrder",1, 4);
		month.sendKeys("02");
		edc.getData("PlaceOrder",1, 5);
		year.sendKeys("2024");
		purchaseBTN.click();
	}

	public void successfulTransaction() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.elementToBeClickable(OkBTN));
		System.out.println();
		System.out.println("Payment Details: ");
		System.out.println( message.getText());
		for(WebElement paymentDetail: paymentInfo )
		{
			details = paymentDetail.getText();
			System.out.println(details);
		}
		System.out.println();
		OkBTN.click();
		wait.until(ExpectedConditions.visibilityOf(closeBtn));
		closeBtn.click();
	}
}
