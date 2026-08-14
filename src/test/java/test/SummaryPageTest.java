package test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.PersonalInfoPage;
import pageObjects.SummaryPage;

public class SummaryPageTest extends BaseTest{
	
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private String reqProductName ="ZARA COAT 3";
	private CartPage cartPage;
	private PersonalInfoPage personalInfo;
	private SummaryPage summaryPage;
	private static final Logger logger = LogManager.getLogger(SummaryPageTest.class);
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launchUrl(@Optional("Chrome")String browserName) throws MalformedURLException, URISyntaxException {
		String profile = getProperty("active.profile");
		if(profile!=null && profile.equalsIgnoreCase("Remote")) {
			logger.info("Setting Up Remote Web Driver of browser "+browserName+" for "+this.getClass());
			setupDriver(browserName,profile);
		}
		else {
			String browser = getProperty("browser");
			logger.info("Setting Up Web Driver of browser "+browser+" for "+this.getClass());
	    	setupDriver(browser,null);
		}
	    this.driver = BaseTest.getDriver();
		loginPage = launchUrl(this.driver);
	}
	
	@Test
	public void orderSuccessTest() throws InterruptedException {
		loginPage.enterUserName("ravi.d@hotmail.com");
		loginPage.enterpassword("Selenium1@3");
		homePage=loginPage.loginToApp();
		WebElement reqProduct = homePage.getProduct(reqProductName, 5);
		homePage.addToCart(reqProduct);
		cartPage = homePage.clickOnCartTab(5);
		personalInfo = cartPage.clickOnProductCheckout();
		personalInfo.enterPersonalInfo("Ind", 5);
		summaryPage = personalInfo.clickOnSubmit();
		Assert.assertEquals(summaryPage.getOrderSuccessMessage(), "THANKYOU FOR THE ORDER.");
	}
	
	@AfterMethod
	public void closeBrowser() {
		tearDown(this.driver);
	}

}
