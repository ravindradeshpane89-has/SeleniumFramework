package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverManager implements WebDriverManager {

	@Override
	public WebDriver setDriver(String browser) {

		switch (browser) {

		case "firefox":
			return new FirefoxDriver();
		default:
			throw new IllegalArgumentException("Invalid browser: " + browser);
		}
	}

}
