package com.pom.demoblaze;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver web;
	public String listcategory;
	public String productListDetails;

	//Parameterized constructor and PageFactory initialization
	public HomePage(WebDriver driver) 
	{
		web=driver;
		PageFactory.initElements(web,this);
	}

	//All elements are defined on page level and loading at the time of accessing as per PageFactory logic.
	@FindBy(xpath = ".//a[normalize-space(text())='Sign up']")
	WebElement signupOpt;

	@FindBy(xpath = ".//a[normalize-space(text())='Log in']")
	WebElement logInOpt;

	@FindBy(xpath = ".//a[normalize-space(text())='Log out']")
	WebElement logOutOpt;

	@FindBy(xpath = ".//a[normalize-space(text())='Contact']")
	WebElement contactOpt;

	@FindBy(xpath = ".//a[normalize-space(text())='Cart']")
	WebElement cartOpt;

	@FindBy(xpath = ".//a[@id='itemc']")
	List<WebElement> categories;

	@FindBy(xpath = ".//div[@id='tbodyid']")
	List<WebElement> productDetails;

	@FindBy(xpath = ".//p[@id='article']//preceding-sibling:: h4")
	WebElement productName;

	@FindBy(xpath = ".//a[contains(text(), 'Pho')]")
	WebElement phoneMenu;

	@FindBy(xpath = "//h5[contains(text(), 'Place')]")
	WebElement placeOrderName;

	public void signUpTab() 
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.elementToBeClickable(signupOpt));
		signupOpt.click(); //Click action on sign up option.
	}

	public void logInTab() 
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.visibilityOf(logInOpt));
		logInOpt.click(); //Click action on Log In option.
	}

	public void logOutTab() throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.elementToBeClickable(logOutOpt));
			try 
			{
				logOutOpt.click();  //Click action on Log out option
			} catch(Exception e)
			{
				System.out.println(e);
			}
		

	}

	public void contactTab() throws InterruptedException 
	{
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.visibilityOf(contactOpt));
		contactOpt.click(); //click action on Contact option.
	}

	public void cartTab() 
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.elementToBeClickable(cartOpt));
		cartOpt.click(); //Click action on Cart option
	}

	public void categoriesList()
	{
		System.out.println("All list of categories in the system:");
		for(WebElement category : categories)
		{
			listcategory = category.getText();
			System.out.println(listcategory); //List down all categories available on UI.
		}
	}

	public void verifyingProducts() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.visibilityOf(phoneMenu));
		phoneMenu.click();
		System.out.println();
		System.out.println(phoneMenu.getText()); //Category phone should be printed on console
		System.out.println("Product List Details: ");
		wait.until(ExpectedConditions.visibilityOfAllElements(productDetails));
		for(WebElement product: productDetails)
		{	Thread.sleep(1000);
		try
		{
			productListDetails = product.getText();
			System.out.println(productListDetails); //List down all product's details on console for Phone category. 
		} catch (Exception e) 
		{
			System.out.println(e);
		}

		}
	}

	public void navigateToCart()
	{
		WebDriverWait wait = new WebDriverWait(web, 20);
		wait.until(ExpectedConditions.elementToBeClickable(productName));
		productName.click(); //Click action on product name to add the given product on cart.

	}

}
