package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import dataUtils.DataUtils;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SummaryPage;

public class CartPageTest extends BaseTest {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private CartPage cartPage;
	private static final Logger logger = LogManager.getLogger(CartPageTest.class);
	
	@Parameters({"browser"})
	@BeforeMethod(alwaysRun = true)
	public void launchUrl(@Optional("Chrome") String browserName) throws MalformedURLException, URISyntaxException {
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
	
	
	@Test(dataProvider = "getData",groups = {"dataDrivenTests"})
	public void addToCartTest(Map<String,String> data) throws InterruptedException {
		logger.info("Entering username as "+data.get("email"));
		loginPage.enterUserName(data.get("email"));
		logger.info("Entering password as "+data.get("password"));
		loginPage.enterpassword(data.get("password"));
		homePage=loginPage.loginToApp();
		WebElement reqProduct = homePage.getProduct(data.get("product"), 5);
		homePage.addToCart(reqProduct);
		cartPage = homePage.clickOnCartTab(5);
		Assert.assertTrue(cartPage.isRequiredCartProductPresent(data.get("product")));
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		tearDown(this.driver);
	}
	
	@DataProvider
	public Object[][] getData() throws StreamReadException, DatabindException, IOException{
		
		List<Map<String,String>> dataSet =DataUtils.getData("cartPageTestData");
		Object[][] dataMatrix = new Object[dataSet.size()][1];
        for (int i = 0; i < dataSet.size(); i++) {
            dataMatrix[i][0] = dataSet.get(i);
        }
        return dataMatrix;
		
	}
	

}
