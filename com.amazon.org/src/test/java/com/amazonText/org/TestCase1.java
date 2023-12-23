package com.amazonText.org;

import java.io.IOException;

import org.testng.annotations.Test;

import com.BaseTest.BaseClass;
import com.PageEvents.HomePageEvent;
import com.PageEvents.ProdPageEvent;
import com.Utilities.FetchElements;

public class TestCase1 extends BaseClass{
	HomePageEvent homePage=new HomePageEvent();
	FetchElements fetchEle=new FetchElements();
	ProdPageEvent prodO= new ProdPageEvent();
	@Test
public void SearchProduct() throws IOException {
	homePage.searchProd();
	prodO.writeToExcelTest();
	
	
}
}
