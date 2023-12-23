package com.PageEvents;

import java.io.IOException;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.BaseTest.BaseClass;
import com.PageObjects.AddToCart;
import com.PageObjects.SearchPage;
import com.Utilities.FetchElements;

public class HomePageEvent extends BaseClass {

	FetchElements ele = new FetchElements();

	public void searchProd() throws IOException {
//comment 
		WebElement str1 = ele.getWebElement("XPATH", SearchPage.searchBox);
		str1.click();
		// str1.sendKeys("asin ‎‎b00vk5mcmi");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		String getDataInfo = getData(1, 0);
		str1.sendKeys(getDataInfo);

		WebElement str3 = ele.getWebElement("XPATH", SearchPage.searchBtn);
		str3.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)", "");

		WebElement str = ele.getWebElement("XPATH", SearchPage.productSerachASIN);
		str.click();

		Set<String> get = driver.getWindowHandles();
		Iterator<String> itr1 = get.iterator();
		while (itr1.hasNext()) {
			itr1.next();
			driver.switchTo().window(itr1.next());

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,1000)", "");

			WebElement str2 = ele.getWebElement("XPATH", AddToCart.addToCart);
			str2.click();
		}

	}
}
