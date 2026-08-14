package listerners;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentReportUtil;
import utils.Utils;

public class Listeners implements ITestListener {

	private ExtentReports extent = ExtentReportUtil.getReportObject();
	private ExtentTest test;
	private WebDriver driver;
	private Utils util;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	 private static final Logger logger = LogManager.getLogger(Listeners.class);

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.wasRetried()) {
			extent.removeTest(test);
		}
	}

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		logger.info("Test: "+result.getMethod().getMethodName()+" started with thread "+Thread.currentThread().threadId());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		logger.info("Test :"+result.getMethod().getMethodName()+" with thread "+Thread.currentThread().threadId()+" is successful");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL, result.getThrowable());
		logger.info("Test :"+result.getMethod().getMethodName()+" with thread "+Thread.currentThread().threadId()+" is failed");
		String filePath = null;
		try {
			java.lang.reflect.Field field = result.getTestClass().getRealClass().getDeclaredField("driver");
			field.setAccessible(true);
			this.driver = (WebDriver) field.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		util = new Utils(this.driver);
		try {
			filePath = util.getScreenshot(this.driver, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath);
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
