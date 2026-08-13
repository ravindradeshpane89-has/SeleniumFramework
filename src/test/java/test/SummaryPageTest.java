package test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launchUrl(String browserName) throws MalformedURLException, URISyntaxException {
		String profile = getProperty("active.profile");
		if(profile.equalsIgnoreCase("Remote")) {
			setUpRemoteDriver(browserName, System.getProperty("hub_url"));
		}
		else {
			String browser = getProperty("browser");
	    	setupDriver(browser);
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
