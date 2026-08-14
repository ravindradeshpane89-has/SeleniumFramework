package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import driverManager.WebDriverFactory;
import listerners.Listeners;
import pageObjects.LoginPage;

public class BaseTest {

	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	private WebDriverFactory factory = new WebDriverFactory();
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	public void setupDriver(String browser,String profile) throws MalformedURLException, URISyntaxException {
		
		if(profile!=null && profile.equalsIgnoreCase("Remote")) {
			tDriver.set(factory.createDriver("REMOTE").setDriver(browser));
			 logger.info("Remote WebDriver with browser "+browser+" is initialized with thread "+Thread.currentThread().threadId());
		}
		else {
			switch (browser) {

			case "chrome":
				tDriver.set(factory.createDriver("CHROME").setDriver(browser));
				 logger.info("WebDriver with browser "+browser+" is initialized with thread "+Thread.currentThread().threadId());
				break;
			case "chrome_headless":
				tDriver.set(factory.createDriver("CHROME").setDriver(browser));
				 logger.info("WebDriver with browser "+browser+" is initialized with thread "+Thread.currentThread().threadId());
				break;
			case "firefox":
				tDriver.set(factory.createDriver("FIREFOX").setDriver(browser));
				 logger.info("WebDriver with browser "+browser+" is initialized with thread "+Thread.currentThread().threadId());
				break;
			case "edge":
				tDriver.set(factory.createDriver("EDGE").setDriver(browser));
				 logger.info("WebDriver with browser "+browser+" is initialized with thread "+Thread.currentThread().threadId());
				break;
			case "safari":
				tDriver.set(factory.createDriver("SAFARI").setDriver(browser));
				 logger.info("WebDriver with browser "+browser+" is initialized with thread "+Thread.currentThread().threadId());
				break;
			default:
				throw new IllegalArgumentException("Invalid Browser: "+browser);

			}
		}
		
		if(!browser.equalsIgnoreCase("chrome_headless")) {
			getDriver().manage().window().maximize();
		}
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	
	public static WebDriver getDriver() {
		return tDriver.get();
	}

	public String getProperty(String property) {

		if(System.getProperty(property)!=null) {
			return System.getProperty(property);
		}
		else {
			Properties prop = new Properties();
			ClassLoader classLoader = getClass().getClassLoader();

			try {
				FileInputStream file = new FileInputStream(classLoader.getResource("config.properties").getFile());
				prop.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return prop.getProperty(property);
		}
		
		

	}

	public LoginPage launchUrl(WebDriver driver) {
		logger.info("launching the url");
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		return new LoginPage(driver);

	}

	public void tearDown(WebDriver driver) {
		if(driver!=null) {
			logger.info("closing the driver instance");
			driver.quit();
		}
		
		tDriver.remove();
		
	}

}
