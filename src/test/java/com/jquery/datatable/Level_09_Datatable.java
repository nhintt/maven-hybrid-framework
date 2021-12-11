package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

public class Level_09_Datatable extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBaseTestDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	// @Test
	public void DataTable_01_Paging() {
		homePage.openPageByNumber("5");
		Assert.assertTrue(homePage.isPageActiveByNumber("5"));

		homePage.openPageByNumber("12");
		Assert.assertTrue(homePage.isPageActiveByNumber("12"));

		homePage.openPageByNumber("10");
		Assert.assertTrue(homePage.isPageActiveByNumber("10"));
	}

	// @Test
	public void DataTable_02_Input() {
		homePage.inputToHeaderTextboxByName("Females", "384187");
		homePage.sleepInSecond(2);
		homePage.refreshCurrentPage(driver);

		homePage.inputToHeaderTextboxByName("Country", "Afghanistan");
		homePage.sleepInSecond(2);
		homePage.refreshCurrentPage(driver);

		homePage.inputToHeaderTextboxByName("Total", "791312");
		homePage.sleepInSecond(2);
		homePage.refreshCurrentPage(driver);
	}

	// @Test
	public void DataTable_03_Icon() {
		homePage.clickToIconByCountryName("AFRICA", "remove");
		homePage.sleepInSecond(2);

		homePage.clickToIconByCountryName("Aruba", "remove");
		homePage.sleepInSecond(2);

		homePage.clickToIconByCountryName("Angola", "edit");
		homePage.sleepInSecond(2);
		homePage.refreshCurrentPage(driver);

		homePage.clickToIconByCountryName("Armenia", "edit");
		homePage.sleepInSecond(2);
		homePage.refreshCurrentPage(driver);
	}

	// @Test
	public void DataTable_04_Verify_Row_Value() {
		homePage.inputToHeaderTextboxByName("Country", "Afghanistan");
		Assert.assertTrue(homePage.isRowValueDisplayed("384187", "Afghanistan", "407124", "791312"));
		homePage.refreshCurrentPage(driver);

		homePage.inputToHeaderTextboxByName("Country", "AFRICA");
		Assert.assertTrue(homePage.isRowValueDisplayed("12253515", "AFRICA", "12599691", "24853148"));
		homePage.refreshCurrentPage(driver);
	}

	// @Test
	public void DataTable_05_Input_Table_Textbox() {
		homePage.inputToTextboxByRowNumber("F Com", "Company", "1");
		homePage.sleepInSecond(1);

		homePage.inputToTextboxByRowNumber("C Person", "Contact Person", "2");
		homePage.sleepInSecond(1);

		homePage.inputToTextboxByRowNumber("34", "Order Placed", "3");
		homePage.sleepInSecond(1);

		homePage.inputToTextboxByRowNumber("2021-11-09", "Member Since", "3");
		homePage.sleepInSecond(1);
	}

	@Test
	public void DataTable_06_Icon_Action() {
		homePage.inputToTextboxByRowNumber("34", "Order Placed", "3");
		homePage.sleepInSecond(1);

		homePage.clickToIconActionByRowNumber("Move Up", "3");
		homePage.clickToIconActionByRowNumber("Move Up", "2");
		homePage.clickToIconActionByRowNumber("Move Down", "1");

		homePage.clickToIconActionByRowNumber("Remove Current Row", "3");
		homePage.clickToIconActionByRowNumber("Remove Current Row", "2");
		homePage.clickToIconActionByRowNumber("Remove Current Row", "1");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
}
