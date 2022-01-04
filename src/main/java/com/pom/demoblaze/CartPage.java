package com.pom.demoblaze;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	WebDriver web;
	public String actualResult;


	//Parameterized constructor and PageFactory initialization
	public CartPage(WebDriver driver) 
	{
		web=driver;
		PageFactory.initElements(web,this);
	}

	//All elements are defined on page level and loading at the time of accessing as per PageFactory logic.
	@FindBy(xpath = ".//a[normalize-space(text())='Add to cart']")
	WebElement addToCartBTN;

	public void addsProToCart() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.visibilityOf(addToCartBTN));
		addToCartBTN.click(); //Click action on Add To Cart button
	}

}
