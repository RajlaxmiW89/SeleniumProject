package com.PageEvents;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.BaseTest.BaseClass;
import com.PageObjects.ProductPage;
import com.Utilities.FetchElements;

public class ProdPageEvent extends BaseClass {

	FetchElements ele = new FetchElements();

	@Test
	public void writeToExcelTest() throws IOException {
		List<WebElement> prodDis = ele.getWebElements("XPATH", ProductPage.prodSpec);

		for (WebElement webElement : prodDis) {
			System.out.println("All spacifications are::" + webElement.getText());
		}

		/*
		 * String filePath = "/com.amazon.org/sheetwrite/file.xlsx"; String sheetName =
		 * "Sheet1"; String[] headers = { "Column1" }; Object[][] data = { { "Data1" },
		 * { "Data2" }, { "Data3" }, { "Data4" }, { "Data5" }, { "Data6" }, { "Data7" },
		 * // Add more data rows as needed };
		 * 
		 * BaseClass.writeToExcel(filePath, sheetName, headers, data);
		 */
	}
}
