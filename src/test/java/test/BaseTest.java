package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import pageObjects.LoginPage;

public class BaseTest {

	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	WebDriver driver;

	public void setupDriver(String browser) {

		switch (browser) {

		case "chrome":
			driver = new ChromeDriver();
			tDriver.set(driver);
			break;
		case "chrome_headless":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
			tDriver.set(driver);
			break;
		case "firefox":
		  driver = new FirefoxDriver();
			tDriver.set(driver);
			break;

		case "edge":
			driver = new EdgeDriver();
			tDriver.set(driver);
			break;
		case "safari":
			driver = new SafariDriver();
			tDriver.set(driver);
			break;

		default:
			System.out.println("Invalid browser");

		}
		getDriver().manage().window().maximize();
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
