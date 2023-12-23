package com.Utilities;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.BaseTest.BaseClass;

public class FetchElements {

	public WebElement getWebElement(String indentifierType, String identifierValue) {

		switch (indentifierType) {
		case "XPATH":
			return BaseClass.driver.findElement(By.xpath(identifierValue));

		case "CSS":
			return BaseClass.driver.findElement(By.cssSelector(identifierValue));

		case "ID":
			return BaseClass.driver.findElement(By.id(identifierValue));

		case "NMAE":
			return BaseClass.driver.findElement(By.name(identifierValue));

		case "TAGNAME":
			return BaseClass.driver.findElement(By.tagName(identifierValue));

		case "LINKTEXT":
			return BaseClass.driver.findElement(By.linkText(identifierValue));

		case "PARTIALLINKTEXT":
			return BaseClass.driver.findElement(By.partialLinkText(identifierValue));

		default:
			return null;

		}

	}

	public List<WebElement> getWebElements(String indentifierType, String identifierValue) {

		switch (indentifierType) {
		case "XPATH":
			return BaseClass.driver.findElements(By.xpath(identifierValue));

		case "CSS":
			return BaseClass.driver.findElements(By.cssSelector(identifierValue));

		case "ID":
			return BaseClass.driver.findElements(By.id(identifierValue));

		case "NMAE":
			return BaseClass.driver.findElements(By.name(identifierValue));

		case "TAGNAME":
			return BaseClass.driver.findElements(By.tagName(identifierValue));

		case "LINKTEXT":
			return BaseClass.driver.findElements(By.linkText(identifierValue));

		case "PARTIALLINKTEXT":
			return BaseClass.driver.findElements(By.partialLinkText(identifierValue));

		default:
			return null;
		}
	}
}