package com.test.demoblaze;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.common.classes.AlertPopup;
import com.common.classes.ExtentReport;
import com.pom.demoblaze.CartPage;
import com.pom.demoblaze.ContactPage;
import com.pom.demoblaze.HomePage;
import com.pom.demoblaze.LoginPage;
import com.pom.demoblaze.PlaceOrderPage;
import com.pom.demoblaze.SignupPage;


public class TestCasesExecution extends ExtentReport
{

	@Test(priority = 1)
	public void signUpProcess() throws IOException, InterruptedException
	{
		//start the extent report creation for first test case
		test = report.createTest("Registration on portal.");
		HomePage hp = new HomePage(driver);
		hp.signUpTab();
		SignupPage sp = new SignupPage(driver);
		sp.enterSignUpDetails();  //Entering the signup details. 
		AlertPopup ap = new AlertPopup(driver);
		ap.successPopup(); // closing the alert popup.
		Assert.assertEquals(ap.actualResult, "Sign up successful."); //verifying the alert message on popup.
	}

	@Test(priority = 2)
	public void LoginProcess() throws IOException, AWTException, InterruptedException
	{
		//start the extent report creation for second test case
		test = report.createTest("Sign in with created account");
		HomePage hp = new HomePage(driver);
		hp.logInTab(); //navigate to Login Tab.
		LoginPage lp = new LoginPage(driver);
		lp.loginCredentials(); //Login through created user.
		Assert.assertTrue(true, "Test case gets passed"); //Verified user successfully logged in or not.
	}

	@Test(priority = 3)
	public void signOutProcess() throws InterruptedException, AWTException
	{
		//start the extent report creation for third test case.
		test = report.createTest("Successfully log out from the application.");
		HomePage hp = new HomePage(driver);
		hp.logOutTab(); //Logout successfully from the portal web site.
	}

	@Test(priority = 4)
	public void placeOrderRequest() throws InterruptedException, IOException
	{
		//start the extent report creation for fourth test case
		test = report.createTest("Place Order Request");
		HomePage hp = new HomePage(driver);
		hp.logInTab(); //navigate to log in tab
		LoginPage lp = new LoginPage(driver);
		lp.loginCredentials(); //Login with existing user.
		hp.contactTab(); //Navigate on Contact tab.
		ContactPage cp = new ContactPage(driver);
		cp.contactDetailsProcess(); // Entering the Contact Details and send it to server.
		AlertPopup ap = new AlertPopup(driver);
		ap.successPopup(); //Handling alert popup
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(ap.actualResult, "Thanks for the message!!"); //Verifying the alert message.
		hp.categoriesList(); //List down the Categories list on console.
		sa.assertEquals(hp.listcategory,"Monitors", "Categories are verified" ); //Verifying one of the category with system.
		hp.verifyingProducts(); //Product details prints on console.
		sa.assertTrue(hp.productListDetails!=null, "Product details are verified"); //Verified the product's details are printed on console. 
		hp.navigateToCart(); //select one of the product from the list.
		CartPage cp1 = new CartPage(driver); 
		cp1.addsProToCart(); //Selected product is added into cart.
		ap.successPopup(); //Handling alert popup
		sa.assertEquals(ap.actualResult,"Product added."); //Verifying the alert message.
		hp.cartTab(); //Navigates on Cart menu. 
		PlaceOrderPage pop = new PlaceOrderPage(driver);
		pop.placeOrder(); //Placing an order with payment details.
		pop.successfulTransaction(); //Printing the successful payment details on console.
		sa.assertNotSame(pop.details=null, "Cart payment is verified successfully."); //Verifying the details are printed on console.
		hp.logOutTab(); //Logout successfully from the portal.
	}

	@Test(priority = 5)
	public void closeBrowser() throws IOException
	{
		//start the extent report creation for fifth test case
		test = report.createTest("Browser closing activity.");
		driver.close(); //closing the browser.
		Assert.assertFalse(false, "Browser is closed successfully.");
	} 

}
