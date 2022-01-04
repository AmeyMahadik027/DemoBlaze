package com.common.classes;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport extends BrowserInit
{

	public static ExtentTest test;
	public static ExtentReports report;
	ExtentHtmlReporter htmlReporter;
	String val ="true";
	
	@BeforeSuite
	public void setUp()
	{
		htmlReporter =  new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReportResults.html");
		report = new ExtentReports(); //report initialization
		report.attachReporter(htmlReporter); //passing the ExtentHTMLReporter instance variable to report.
		//GUI setup for HTML reporter
		htmlReporter.config().setDocumentTitle("DemoBlaze"); 
		htmlReporter.config().setReportName("DemoBlaze Automation Testing");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{

			test.log(Status.FAIL, result.getName()+ " Test case gets failed."); //Logged the method name in the reports if test case gets failed
			test.log(Status.FAIL, result.getThrowable() + " Test Case gets failed." ); //Logged the exception in the reports if test case gets failed
			if(val.equalsIgnoreCase("true")) //if flag is true then screenshot will get added into the report.
			{
			String screenshotPath = TakingScreenshot.captureScreenShot(driver, result.getName());
			test.fail("Screen Shot: " + test.addScreenCaptureFromPath(screenshotPath)); //This will add the screenshot on report if condition is passed.
			}

		}
		else if(result.getStatus() == ITestResult.SKIP)
		{	
			test.log(Status.SKIP, result.getName()+  " Test Case gets skipped." ); //Logged the method name in the reports if test case gets skipped
			test.log(Status.SKIP,   result.getThrowable()+ " Test case gets skipped."); //Logged the exception in the reports if test case gets skipped
		}
		else if(result.getStatus()== ITestResult.SUCCESS)
		{
			test.log(Status.PASS, result.getName()+ " Test Case Gets Passed." );//Logged the method name in the reports if test case gets passed
			if(val.equalsIgnoreCase("false"))  //if flag is true then screenshot will get added into the report.
			{
			String screenshotPath = TakingScreenshot.captureScreenShot(driver, result.getName());
			test.pass("Screen Shot: " + test.addScreenCaptureFromPath(screenshotPath)); //This will add the screenshot on report if condition is passed. 
			}

		}
		report.flush();
	}

}
