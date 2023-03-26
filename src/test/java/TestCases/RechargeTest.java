package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.BaseTest;
import pages.MobileRcgPage;
import pages.SignInPage;
import utility.ExcelDataReader;

@Listeners(utility.Listener.class)
public class RechargeTest extends BaseTest {

	MobileRcgPage recharge = PageFactory.initElements(driver, MobileRcgPage.class);

	@DataProvider(name = "Recharge")
	public Object[][] getData() throws Exception {
		Object data[][] = ExcelDataReader.getDataFromExcel("MyBl.xlsx", "Recharge");
		return data;
	}

	@Test(enabled = true, priority = 0, dataProvider = "Recharge", groups = { "regression" })
	public void recharge(String mobileNumber, String amount, String email) throws Exception {

		testLogger = report.createTest("Test Case: Mobile Recharge.");

		recharge.clickOnHomeMenu();
		testLogger.info("Go to Mobile Recharge Page");
		recharge.insertNumber1(mobileNumber);
		testLogger.info("Insert Mobile Number: " + mobileNumber);

		recharge.insertAmount1(amount);
		testLogger.info("Insert Amounts: " + amount);

		recharge.insertEmail(email);
		testLogger.info("Insert email address: " + email);

		recharge.clickOnrechargeYourAccountButton();
		testLogger.info("Click on Recharge Amount Button");

	}
}
