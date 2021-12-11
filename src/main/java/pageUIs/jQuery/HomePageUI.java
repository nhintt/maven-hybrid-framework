package pageUIs.jQuery;

public class HomePageUI {
	public static final String PAGING_BY_NUMBER = "//a[@class='qgrd-pagination-page-link' and text()='%s']";
	public static final String PAGING_ACTIVE_BY_NUMBER = "//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String LABEL_TEXTBOX_BY_NAME = "//div[text()='%s']/parent::div/following-sibling::input";
	public static final String ICON_BY_COUNTRY_NAME = "//td[text()='%s']/preceding-sibling::td[@class='qgrd-actions']//button[@class='qgrd-%s-row-btn']";
	public static final String ROW_VALUE_BY_FEMALE_COUNTRY_MALE_TOTAL = "//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']";
	public static final String HEADER_NAME_INDEX = "//th[text()='%s']/preceding-sibling::th";
	public static final String TEXTBOX_BY_COLUMN_ROW_INDEX = "//tr[%s]/td[%s]/input";
	public static final String ACRION_BUTTON_BY_ROW_INDEX = "//tr[@id][%s]//p/button[@title='%s']";
}
