package pageUIs.nopcommerce.admin;

public class AdminBasePageUI {
	public static final String MENU_LINK_BY_NAME = "//ul[@role='menu']/li/a//p[contains(text(), '%s')]";
	public static final String SUB_MENU_LINK_BY_NAME = "//a[contains(@href, 'Product')]/p[contains(text(), '%s')]";
	public static final String UPPLOAD_FILE_BY_CARD_NAME = "//div[@id='product-%s']//input[@type='file']";
	public static final String NO_DATA_MESSAGE_BY_TABLE_NAME = "//table[@id='%s-grid']//td[text()='No data available in table']";
}
