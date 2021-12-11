package com.saucedemo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.saucedemo.InventoryPO;
import pageObjects.saucedemo.LoginPO;
import pageObjects.saucedemo.PageGenerator;

public class Level_18_Sort_Data extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBaseTestDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-Condition - Step 02: Login to system");
		loginPage.inputToUserNameTextbox("standard_user");
		loginPage.inputToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clickToLoginButton();
	}

	@Test
	public void Sauce_Demo_01_Sort_Title() {
		log.info("Sauce_Demo_01_Sort_A_To_Z - Step 01: Select 'Name (A to Z)' option");
		inventoryPage.selectToProductSortDropdown("az");

		log.info("Sauce_Demo_01_Sort_A_To_Z - Step 02: Verify product list is ascending sorted correctly");
		verifyTrue(inventoryPage.isProductNameSortedAscending());
		inventoryPage.sleepInSecond(3);

		log.info("Sauce_Demo_01_Sort_A_To_Z - Step 03: Select 'Name (Z to A)' option");
		inventoryPage.selectToProductSortDropdown("za");

		log.info("Sauce_Demo_01_Sort_A_To_Z - Step 04: Verify product list is descending sorted correctly");
		verifyTrue(inventoryPage.isProductNameSortedDescending());
		inventoryPage.sleepInSecond(3);
	}

	@Test
	public void Sauce_Demo_02_Sort_Price() {
		log.info("Sauce_Demo_02_Sort_Price - Step 01: Select 'Price (low to high)' option");
		inventoryPage.selectToProductSortDropdown("lohi");

		log.info("Sauce_Demo_02_Sort_Price - Step 02: Verify product list is sorted by asceding price correctly");
		verifyTrue(inventoryPage.isProductPriceSortedAscending());
		inventoryPage.sleepInSecond(3);

		log.info("Sauce_Demo_02_Sort_Price - Step 03: Select 'Price (high to low)' option");
		inventoryPage.selectToProductSortDropdown("hilo");

		log.info("Sauce_Demo_02_Sort_Price - Step 04: Verify product list is sorted by descending price correctly");
		verifyTrue(inventoryPage.isProductPriceSortedDescending());
		inventoryPage.sleepInSecond(3);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private LoginPO loginPage;
	private InventoryPO inventoryPage;
}
