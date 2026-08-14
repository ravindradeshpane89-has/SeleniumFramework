package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverManager implements WebDriverManager {

	@Override
	public WebDriver setDriver(String browser) {
		switch (browser) {

		case "safari":
			return new SafariDriver();
		default:
			throw new IllegalArgumentException("Invalid browser: " + browser);
		}
	}
}
