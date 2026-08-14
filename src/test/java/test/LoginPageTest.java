package test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import listerners.RetryTest;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginPageTest extends BaseTest {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;

	@Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
	public void launchUrl(@Optional("Chrome")String browserName) throws MalformedURLException, URISyntaxException {
		String profile = getProperty("active.profile");
		if(profile!=null && profile.equalsIgnoreCase("Remote")) {
			setupDriver(browserName,profile);
		}
		else {
			String browser = getProperty("browser");
	    	setupDriver(browser,null);
		}
    	
		this.driver = BaseTest.getDriver();
		loginPage = launchUrl(this.driver);
	}
    
    @Test
    public void loginSuccessTest() {
    	System.out.println("Login Success Test Started");
    	loginPage.enterUserName("ravi.d@hotmail.com");
		loginPage.enterpassword("Selenium1@3");
		homePage = loginPage.loginToApp();
		homePage.waitForVisibilityOfElement(homePage.getSignOut(), 5);
		Assert.assertTrue(homePage.getSignOut().isDisplayed());
    }
    
    @Test(groups = {"errorValidation"},retryAnalyzer = RetryTest.class)
    public void errorLoginTest() {
    	loginPage.enterUserName("ravi.d@hotmail.com");
    	loginPage.enterpassword("Selenium1@");
    	loginPage.loginToApp();
    	loginPage.waitForVisibilityOfElement(loginPage.getErrorLoginMsg(), 5);
    	Assert.assertEquals(loginPage.getErrorLoginMsgText().trim(), "Incorrect email or password.");
    }
    
    @AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		tearDown(this.driver);
	}

}
