package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	private WebDriverWait wait;
	private WebDriver driver;
	private Actions action;
	 private static final Logger logger = LogManager.getLogger(Utils.class);

	public Utils(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForVisibilityOfElement(WebElement el, long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
			logger.info("waiting for visisbility of web element "+el+" for "+waitTime+ " sec");
			wait.until(ExpectedConditions.visibilityOf(el));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void waitForElementToBeClickable(WebElement el,long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
			logger.info("waiting for element to be clickable for element "+el+" for "+waitTime+ " sec");
			wait.until(ExpectedConditions.elementToBeClickable(el));
		} catch (ElementClickInterceptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForInvisibilityOfElement(WebElement el, long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
			logger.info("waiting for invisisbility of web element "+el+" for "+waitTime+ " sec");
			wait.until(ExpectedConditions.invisibilityOf(el));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getScreenshot(WebDriver driver, String testName) throws IOException {
		logger.info("Getting screenshot for test "+testName);
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/reports/"+testName+".png");
		FileUtils.copyFile(src, target);
		return "/reports/"+testName+".png";
	}

	public Actions moveToElement(WebElement el) {
		action = new Actions(driver);
		try {
			logger.info("Moving to web element "+el);
			action.moveToElement(el);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		return action.moveToElement(el);
	}
}
