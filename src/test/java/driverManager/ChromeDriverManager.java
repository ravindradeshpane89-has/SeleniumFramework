package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager implements WebDriverManager {

	@Override
	public WebDriver setDriver(String browser) {

		switch (browser) {

		case "chrome":
			return new ChromeDriver();

		case "chrome_headless":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			return new ChromeDriver(options);
		default:
			throw new IllegalArgumentException("Invalid browser: " + browser);
		}

	}
}
