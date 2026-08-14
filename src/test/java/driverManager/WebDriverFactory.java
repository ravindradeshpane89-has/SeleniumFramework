package driverManager;

public class WebDriverFactory {

	public WebDriverManager createDriver(String driverType) {

		if (driverType == null || driverType.isEmpty()) {
			return null;
		}
		switch (driverType.toUpperCase()) {
		case "FIREFOX":
			return new FireFoxDriverManager();
		case "CHROME":
			return new ChromeDriverManager();
		case "EDGE":
			return new EdgeDriverManager();
		case "SAFARI":
			return new SafariDriverManager();
		case "REMOTE":
			return new RemoteWebDriverManager();
		default:
			throw new IllegalArgumentException("Unknown driver type: " + driverType);
		}
	}
}
