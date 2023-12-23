package com.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.Utilities.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	public ExtentSparkReporter sparkReporter;

	public ExtentReports extent;

	public ExtentTest logger;
	public XSSFWorkbook Workbook;
	public XSSFSheet sheet;

	public static void writeToExcel(String filePath, String sheetName, String[] headers, Object[][] data)
			throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);

		// Create header row
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
		}

		// Fill data rows
		for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
			Row dataRow = sheet.createRow(rowIndex + 1);
			for (int cellIndex = 0; cellIndex < data[rowIndex].length; cellIndex++) {
				Cell cell = dataRow.createCell(cellIndex);
				if (data[rowIndex][cellIndex] instanceof String) {
					cell.setCellValue((String) data[rowIndex][cellIndex]);
				} else if (data[rowIndex][cellIndex] instanceof Number) {
					cell.setCellValue(((Number) data[rowIndex][cellIndex]).doubleValue());
				}
			}
		}

		// Write to the file
		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			workbook.write(fileOut);
		}

		// Close the workbook to free up resources
		workbook.close();

	}

	public static String getData(int rowIndex, int cellIndex) throws IOException {
		FileInputStream file = new FileInputStream(
				"D:\\Workspace_sele\\com.amazon.org\\src\\main\\java\\com\\TestData\\Product_search.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sheet = book.getSheet("Sheet1");

		String data = sheet.getRow(rowIndex).getCell(cellIndex).getStringCellValue();
		return data;
	}

	@BeforeTest
	public void BeforeTestMethod() {

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports"
				+ File.separator + "SDETADDAExtentReport.html");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		sparkReporter.config().setTheme(Theme.DARK);

		extent.setSystemInfo("HostName", "RHELB");

		extent.setSystemInfo("UserName", "root");

		sparkReporter.config().setDocumentTitle("Automation Report");

		sparkReporter.config().setReportName("Automation Tests Results");
	}

	@BeforeMethod
	@Parameters("browser")
	public void BeforeTestMethod(String browser, Method testMethod) {

		logger = extent.createTest(testMethod.getName());
		setupDriver(browser);
		driver.manage().window().maximize();
		driver.get(Utils.url);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
	}

	@AfterMethod

	public void Aftermethod(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "-Test Case Failed", ExtentColor.RED));

			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + "-Test Case Failed", ExtentColor.RED));
		}

		else if (result.getStatus() == ITestResult.SKIP) {

			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + "-Test Case Failed", ExtentColor.ORANGE));
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "-Test Case PASS", ExtentColor.GREEN));
		}

		// driver.quit();

	}

	@AfterTest(enabled = false)
	public void Aftertest() {

		extent.flush();
	}

	public void setupDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-notifications");

			System.setProperty("webdriver.chrome.driver",
					"D:\\Workspace_sele\\com.amazon.org\\Drivers\\chromedriver.exe");

			driver = new ChromeDriver(options);
		}

		else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

		}
	}
}
