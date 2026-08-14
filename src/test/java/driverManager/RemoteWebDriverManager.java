package driverManager;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteWebDriverManager implements WebDriverManager {
	
	private String hub_url = System.getProperty("hub_url");

	@Override
	public WebDriver setDriver(String browser) throws MalformedURLException, URISyntaxException {

		switch (browser.toLowerCase()) {
        case "chrome_headless":
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");	
			options.addArguments("--disable-gpu");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			return new RemoteWebDriver(new URI(hub_url).toURL(), options);

        case "firefox":
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-private");         // Open in private mode
            return new RemoteWebDriver(new URI(hub_url).toURL(), firefoxOptions);

        case "edge":
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--inprivate");         // Open Edge InPrivate
            return new RemoteWebDriver(new URI(hub_url).toURL(), edgeOptions);

        default:
            throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
	}

}
