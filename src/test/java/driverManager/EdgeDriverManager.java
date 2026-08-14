package driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager implements WebDriverManager{

	@Override
	public WebDriver setDriver(String browser) {
		switch (browser) {

		case "edge":
			return new EdgeDriver();
		default:
			throw new IllegalArgumentException("Invalid browser: " + browser);
		}
	}
	}
