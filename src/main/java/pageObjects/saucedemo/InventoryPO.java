package pageObjects.saucedemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.saucedemo.InventoryPageUI;

public class InventoryPO extends BasePage {
	private WebDriver driver;

	public InventoryPO(WebDriver driver) {
		this.driver = driver;
	}

	public void selectToProductSortDropdown(String value) {
		waitForElementClickable(driver, InventoryPageUI.SORT_PRODUCT_DROPDOWN);
		selectItemInDefaultDropdown(driver, InventoryPageUI.SORT_PRODUCT_DROPDOWN, value);
	}

	public boolean isProductNameSortedAscending() {
		List<WebElement> productList = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_LIST);
		List<String> productNameList = new ArrayList<String>();

		for (WebElement product : productList) {
			productNameList.add(product.getText());
		}

		List<String> productNameListClone = new ArrayList<String>(productNameList);
		Collections.sort(productNameList);
		return productNameList.equals(productNameListClone);
	}

	public boolean isProductNameSortedDescending() {
		List<WebElement> productList = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_LIST);
		List<String> productNameList = new ArrayList<String>();

		for (WebElement product : productList) {
			productNameList.add(product.getText());
		}

		List<String> productNameListClone = new ArrayList<String>(productNameList);
		Collections.sort(productNameList);
		Collections.reverse(productNameList);
		return productNameList.equals(productNameListClone);
	}

	public boolean isProductPriceSortedAscending() {
		List<WebElement> productList = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_LIST);
		List<Float> productPriceList = new ArrayList<Float>();

		for (WebElement product : productList) {
			productPriceList.add(Float.parseFloat(product.getText().replace("$", "")));
		}

		List<Float> productPriceListClone = new ArrayList<Float>(productPriceList);
		Collections.sort(productPriceList);
		return productPriceList.equals(productPriceListClone);
	}

	public boolean isProductPriceSortedDescending() {
		List<WebElement> productList = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_LIST);
		List<Float> productPriceList = new ArrayList<Float>();

		for (WebElement product : productList) {
			productPriceList.add(Float.parseFloat(product.getText().replace("$", "")));
		}

		List<Float> productPriceListClone = new ArrayList<Float>(productPriceList);
		Collections.sort(productPriceList);
		Collections.reverse(productPriceList);
		return productPriceList.equals(productPriceListClone);
	}
}
