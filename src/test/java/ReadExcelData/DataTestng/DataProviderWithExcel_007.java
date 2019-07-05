package ReadExcelData.DataTestng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ReadExcelData.DataTestng.ExcelUtils;

public class DataProviderWithExcel_007 {

	private String sTestCaseName;

	private int iTestCaseRow;

	WebDriver driver;

	@Test(dataProvider = "Authentication")

	public void f(String sUserName, String sPassword) throws InterruptedException {
		
		driver = ExcelUtils.UsingChrome();
		
		System.out.println("TestCase007");

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(sUserName);

		System.out.println("UserName = " + sUserName);

		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(sPassword);

		System.out.println("Password = " + sPassword);

		driver.findElement(By.xpath("//*[@type='submit' or @class='btn-default butt sub-butt']")).click();

		Thread.sleep(1000);

		String temp3 = driver.findElement(By.className("message")).getText();

			Assert.assertEquals(temp3, "Invalid Username or Password.");
			
			System.out.println("Error,Showing Invalid Username or Password");
	}

	@AfterMethod

	public void afterMethod() {

		driver.close();

	}

	@DataProvider

	public Object[][] Authentication() throws Exception {

		// Setting up the Test Data Excel file

		ExcelUtils.setExcelFile("/home/admin-pc/Downloads/TestData.xlsx", "Sheet1");

		sTestCaseName = this.toString();

		// From above method we get long test case name including package and class name
		// etc.

		// The below method will refine your test case name, exactly the name use have
		// used

		sTestCaseName = ExcelUtils.getTestCaseName(this.toString());

		// Fetching the Test Case row number from the Test Data Sheet

		// Getting the Test Case name to get the TestCase row from the Test Data Excel
		// sheet

		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, 0);

		Object[][] testObjArray = ExcelUtils.getTableArray("/home/admin-pc/Downloads/TestData.xlsx",
				"Sheet1", iTestCaseRow);

		return (testObjArray);

	}

}