package com.common.classes;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakingScreenshot 
{ 
	public static String destination;

	public static String captureScreenShot(WebDriver driver ,String screenshotName) throws IOException
	{	
		TakesScreenshot scrshot = ((TakesScreenshot) driver); //TakesScreenshot interface is calling here.
		File scrFile = scrshot.getScreenshotAs(OutputType.FILE); //defined the source location
		String path = System.getProperty("user.dir"); 
		destination = path + "/Screenshots/" + screenshotName + System.currentTimeMillis() + ".png"; //screenshot destination path is defined.
		File finalDest = new File(destination); //passing the file destination location.
		FileUtils.copyFile(scrFile, finalDest); //copy files from source to destination.
		return destination;
	}	



}
