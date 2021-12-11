package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserAddressPageObject;
import pageObjects.nopcommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserOrderPageObject;

public class Level_09_Dynamic_Locator_Rest_Parameter extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBaseTestDriver(browserName, appUrl);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userEmailAddress = "afc@mail.vn";
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void User_01_Login() {
		userLoginPage = userHomePage.openLoginPage();
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
	}

	@Test
	public void User_02_Dynamic_Locator() {
		userHomePage.openFooterPageByName(driver, "My account");
		userCustomerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);

		userCustomerInfoPage.openFooterPageByName(driver, "Orders");
		userOrderPage = PageGeneratorManager.getUserOrderPage(driver);

		userOrderPage.openFooterPageByName(driver, "Addresses");
		userAddressPage = PageGeneratorManager.getUserAddressPage(driver);

		userAddressPage.openFooterPageByName(driver, "My account");
		userCustomerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserOrderPageObject userOrderPage;
	private UserAddressPageObject userAddressPage;
	private String userEmailAddress, userPassword, adminEmailAddress, adminPassword;
}
