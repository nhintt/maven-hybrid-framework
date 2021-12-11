package com.hrm.employee;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.data.hrm.Employee;

import commons.BaseTest;
import commons.GlobalContants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.PageGenerator;

public class Level_20_Data_Test_Out_Class extends BaseTest {
	String employeeID, statusValue, imagePath;
	String editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		imagePath = GlobalContants.UPLOAD_FOLDER_PATH + "avatar.png";

		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBaseTestDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);

		log.info("Pre-Condition - Step 02: Login with 'Admin Role'");
		dashboardPage = loginPage.loginToSystem(driver, Employee.Role.ADMIN_USERNAME, Employee.Role.ADMIN_PASSWORD);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Employee_01_Add_New_Employee - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Employee_01_Add_New_Employee - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Employee_01_Add_New_Employee - Step 03: Input valid info to 'First Name' textbox");
		addEmployeePage.inputToTextboxByID(driver, "firstName", Employee.PersonalDetail.FIRSTNAME);

		log.info("Employee_01_Add_New_Employee - Step 04: Input valid info to 'Last Name' textbox");
		addEmployeePage.inputToTextboxByID(driver, "lastName", Employee.PersonalDetail.LASTNAME);

		log.info("Employee_01_Add_New_Employee - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		log.info("Employee_01_Add_New_Employee - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Employee_01_Add_New_Employee - Step 07: Input valid info to 'User Name' textbox");
		addEmployeePage.inputToTextboxByID(driver, "user_name", Employee.PersonalDetail.USERNAME);

		log.info("Employee_01_Add_New_Employee - Step 08: Input valid info to 'Password' textbox");
		addEmployeePage.inputToTextboxByID(driver, "user_password", Employee.PersonalDetail.PASSWORD);

		log.info("Employee_01_Add_New_Employee - Step 09: Input valid info to 'Confirm Password' textbox");
		addEmployeePage.inputToTextboxByID(driver, "re_password", Employee.PersonalDetail.PASSWORD);

		log.info("Employee_01_Add_New_Employee - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
		employeeListPage.selectToDropdownByID(driver, "status", "Enabled");

		log.info("Employee_01_Add_New_Employee - Step 11: Click to 'Save' button");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Employee_01_Add_New_Employee - Step 12: Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Employee_01_Add_New_Employee - Step 13: Input valid Employee Name to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.inputToTextboxByID(driver, "empsearch_employee_name_empName", Employee.PersonalDetail.FULLNAME);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Employee_01_Add_New_Employee - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Employee_01_Add_New_Employee - Step 15: Verify employee info in 'Result table'");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), Employee.PersonalDetail.LASTNAME);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Post-Condition: Close browser");
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private LoginPO loginPage;
	private DashboardPO dashboardPage;
	private EmployeeListPO employeeListPage;
	private AddEmployeePO addEmployeePage;
	private MyInfoPO myInfoPage;
}
