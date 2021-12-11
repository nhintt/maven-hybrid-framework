package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.admin.AdminProductDetailPageObject;
import pageObjects.nopcommerce.admin.AdminProductSearchPageObject;

public class Level_10_Upload extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBaseTestDriver(browserName, appUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";

		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);

		adminDashboardPage.openSubMenuPageByName(driver, "Catalog", "Product");

		adminProductSearchPage = PageGeneratorManager.getAdminProductSearchPage(driver);
		adminProductSearchPage.inputToProductNameTextBox(productName);
		adminProductSearchPage.clickToSearchButton();

		adminProductDetailPage = adminProductSearchPage.clickToEditButtonByProductName(productName);
	}

	@Test
	public void Admin_01_Upload() {
		adminProductDetailPage.clickToExpandPanelByName("Pictures");
		adminProductDetailPage.uploadFileAtCardName(driver, "pictures", productAvatarImg);
		Assert.assertTrue(adminProductDetailPage.isPictureLoadedSuccesfully(productAvatarImg));

		adminProductDetailPage.inputToAltTextbox(productAvatarAlt);
		adminProductDetailPage.inputToTitleTextbox(productAvatarTitle);
		adminProductDetailPage.inputToDisplayOrderTextbox("Increase");
		adminProductDetailPage.clickToAddProductPicture();
		Assert.assertTrue(adminProductDetailPage.isPictureDisplayed(productName, productAvatarOrder, productAvatarAlt, productAvatarTitle));

		adminProductSearchPage = adminProductDetailPage.clickToSaveButton();
		Assert.assertTrue(adminProductSearchPage.isSuccessMessageDisplayed("The product has been updated successfully."));
		adminProductSearchPage.inputToProductNameTextBox(productName);
		adminProductSearchPage.clickToSearchButton();
		Assert.assertTrue(adminProductSearchPage.isPictureUpdated(productName, productName));

		adminProductDetailPage = adminProductSearchPage.clickToEditButtonByProductName(productName);
		adminProductDetailPage.clickToExpandPanelByName("Pictures");
		adminProductDetailPage.clickToDeleteButton(productAvatarTitle);
		Assert.assertTrue(adminProductDetailPage.isMessageDisplayedInEmptyTable(driver, "productpictures"));

		adminProductSearchPage = adminProductDetailPage.clickToSaveButton();
		adminProductSearchPage.inputToProductNameTextBox(productName);
		adminProductSearchPage.clickToSearchButton();
		Assert.assertTrue(adminProductSearchPage.isPictureUpdated("default-image", productName));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminProductSearchPageObject adminProductSearchPage;
	private AdminProductDetailPageObject adminProductDetailPage;
	String productName = "Adobe Photoshop CS4";
	String productAvatarImg = "pic1.jpg";
	String productAvatarAlt = "Avatar Alt";
	String productAvatarTitle = "Avatar Title";
	String productAvatarOrder = "1";
	private String adminEmailAddress, adminPassword;
}
