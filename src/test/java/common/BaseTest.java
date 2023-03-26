package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.Configure_Browser;
import utility.Helper;

public class BaseTest {

	public WebDriverWait wait;
	public WebDriver driver;
	public ExtentReports report;
	public ExtentTest testLogger;

	public String projectPath = System.getProperty("user.dir");

	private Properties prop;
	private FileInputStream input;

	private String browser;
	private String baseUrl;

	// @BeforeSuite(alwaysRun=true)
	public BaseTest() {

		prop = new Properties();
		try {
			input = new FileInputStream(projectPath + "/Configurations/Configration.properties");
			prop.load(input);
		} catch (Exception e) {
			System.out.println("Unable to find Property File" + e.getMessage());
		}

		// import data from property File
		this.browser = prop.getProperty("browser");
		this.baseUrl = prop.getProperty("baseUrl");

		Configure_Browser Conf_browser = new Configure_Browser();
		this.driver = Conf_browser.config(browser);

		// Extent HTML Report
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				projectPath + "\\Reports\\Test Report (" + Helper.GetCurrentTime() + ").html");
		// extent.loadConfig(projectPath +"\\Configurations\\extentReport_config.xml");
		extent.config().setDocumentTitle("Test Report");
		extent.config().setReportName("Regression Test Report");
		extent.config().setTheme(Theme.DARK);
		extent.config().setEncoding("UTF-8");
		this.report = new ExtentReports();
		report.attachReporter(extent);

		// Passing General information in HTML report
		report.setSystemInfo("URL:", baseUrl);
		report.setSystemInfo("Environment:", "Peoduction Environment.");
	}

	@BeforeTest
	public void setUp() throws Exception {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {

		if (ITestResult.FAILURE == result.getStatus()) {

			String screenshotPath = Helper.CaptureScreenshot(driver, result.getName().toUpperCase()); // Capture
																										// Screenshot
			testLogger.log(Status.FAIL, "FAILED TEST CASE IS " + result.getName().toUpperCase()); // to add name in
																									// extent report
			testLogger.log(Status.FAIL, "ERROR  LOG:  " + result.getThrowable()); // to add error/exception in extent
			testLogger.addScreenCaptureFromPath(screenshotPath); // adding screen shot
		} else if (ITestResult.SKIP == result.getStatus()) {
			testLogger.log(Status.SKIP, "SKIPPED TEST CASE IS: " + result.getName().toUpperCase()); // to add name in
																									// extent report
		} else if (ITestResult.SUCCESS == result.getStatus()) {
			testLogger.log(Status.PASS, "PASSED TEST CASE IS: " + result.getName().toUpperCase()); // to add name in
																									// extent report
		}

		report.flush();
	}
	
	@AfterSuite(alwaysRun = true)
	public void CloseBrowser() {
		// report.flush();
		driver.quit();
		System.out.println("Test Completed");
	}

}
