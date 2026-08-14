package driverManager;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;

public interface WebDriverManager {
	
	public WebDriver setDriver(String browser) throws MalformedURLException, URISyntaxException;

}
