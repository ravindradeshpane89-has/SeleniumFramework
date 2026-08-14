package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import driverManager.WebDriverFactory;
import pageObjects.LoginPage;

public class BaseTest {

	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	private WebDriverFactory factory = new WebDriverFactory();

	
	public void setupDriver(String browser,String profile) throws MalformedURLException, URISyntaxException {
		
		if(profile!=null && profile.equalsIgnoreCase("Remote")) {
			tDriver.set(factory.createDriver("REMOTE").setDriver(browser));
		}
		else {
			switch (browser) {

			case "chrome":
				tDriver.set(factory.createDriver("CHROME").setDriver(browser));
				break;
			case "chrome_headless":
				tDriver.set(factory.createDriver("CHROME").setDriver(browser));
				break;
			case "firefox":
				tDriver.set(factory.createDriver("FIREFOX").setDriver(browser));
				break;
			case "edge":
				tDriver.set(factory.createDriver("EDGE").setDriver(browser));
				break;
			case "safari":
				tDriver.set(factory.createDriver("SAFARI").setDriver(browser));
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
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		return new LoginPage(driver);

	}

	public void tearDown(WebDriver driver) {
		if(driver!=null) {
			driver.quit();
		}
		
		tDriver.remove();
		
	}

}
