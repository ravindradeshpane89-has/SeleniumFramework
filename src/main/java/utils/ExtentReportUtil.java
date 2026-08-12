package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"/reports/index.html";

         ExtentSparkReporter report = new ExtentSparkReporter(path);
         report.config().setDocumentTitle("Test Results");
         report.config().setReportName("Selenium Framework Test Report");
         report.config().setTheme(Theme.STANDARD);
         ExtentReports reports = new ExtentReports();
         reports.attachReporter(report);
         reports.setSystemInfo("Tester", "Ravindra D");
         return reports;

	}

}
