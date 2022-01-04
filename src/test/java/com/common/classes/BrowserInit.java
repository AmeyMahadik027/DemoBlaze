package com.common.classes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.reporters.jq.TimesPanel;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserInit {

	public WebDriver driver;

	/* Starts the Web browser and enter the given url portal */
	
	@BeforeTest
	public void browserOpen()
	{
		WebDriverManager.chromedriver().setup(); //Chrome driver setup via WebDriver Manager abstract class
		driver = new ChromeDriver(); //chrome driver initialization 
		driver.manage().window().maximize(); //maximizing the browser window
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); 
		driver.navigate().to("https://demoblaze.com/index.html"); //moving to given url.
	}
}
