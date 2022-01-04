package com.common.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

	XSSFSheet sheet;
	XSSFWorkbook wb;
	public String excelStr;

	public ExcelDataConfig(String excelPath) throws IOException
	{
		//String path = System.getProperty("user.dir");
		//File file = new File(path + "/DataFiles/DataFile.xlsx");
		File file = new File(excelPath);
		FileInputStream	fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis); 
		// please use HSSFWorkbook if file extension is .xls instead of .xlsx	
	}

	public String getData(String sheetName, int row, int column)
	{
		sheet = wb.getSheet(sheetName);	
		//we can use getSheetAt(0) method as well instead of this one, just use integer value in the given method.

		try
		{
			excelStr = sheet.getRow(row).getCell(column).getStringCellValue();
		}
		catch (IllegalStateException e) 
		{
			XSSFCell cell = sheet.getRow(row).getCell(column);

			if(cell.getCellTypeEnum()== CellType.STRING)
			{
				excelStr = cell.getStringCellValue(); 

			}
			else if(cell.getCellTypeEnum()== CellType.NUMERIC) 
			{
				excelStr = String.valueOf((int)cell.getNumericCellValue());
				//System.out.println(excelStr);
			}	

		}
		return excelStr;
	}

}
