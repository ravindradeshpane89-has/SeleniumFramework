package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	private WebDriverWait wait;
	private WebDriver driver;
	private Actions action;

	public Utils(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForVisibilityOfElement(WebElement el, long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
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
			wait.until(ExpectedConditions.invisibilityOf(el));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String getScreenshot(WebDriver driver, String testName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/reports/"+testName+".png");
		FileUtils.copyFile(src, target);
		return "/reports/"+testName+".png";
	}

	public Actions moveToElement(WebElement el) {
		action = new Actions(driver);
		try {
			action.moveToElement(el);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		return action.moveToElement(el);
	}
}
