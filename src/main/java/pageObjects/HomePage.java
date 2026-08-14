package pageObjects;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class HomePage extends Utils{
	
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(HomePage.class);
	
	@FindBy(css = "#sidebar b")
	private WebElement searchBtn;
	@FindBy(css = ".row .card")
	private List<WebElement> products;
	private By productName = By.tagName("b");
	private By addToCartBtn = By.xpath(".//div[@class='card-body']/button[2]");
	@FindBy(id = "toast-container")
	private WebElement addToCartSuccessMsg;
	@FindBy(className  = "ng-animating")
	private WebElement loadingIcon;
	@FindBy(xpath = "//li/button[contains(text(),'Cart')]")
	private WebElement cartTab;
	@FindBy(xpath = "//button[contains(text(),'Sign Out')]")
	private WebElement signOut;
	
	public WebElement getSignOut() {
		return signOut;
	}

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		logger.info("Initializing web elements for "+this.getClass());
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getProduct(String reqProductName,long waitTime){
		
		waitForVisibilityOfElement(searchBtn,waitTime);
		logger.info("Getting Required product having product Name "+reqProductName+ " from "+this.getClass());
		WebElement reqProduct = products.stream().filter(product->product.findElement(productName).getText().equals(reqProductName)).findFirst().orElse(null);
		return reqProduct;
	}
	
	public void addToCart(WebElement reqProduct) {
		logger.info("Adding product "+reqProduct+ " to cart");
		reqProduct.findElement(addToCartBtn).click();
	}
	
	public CartPage clickOnCartTab(long waitTime) throws InterruptedException {
		waitForVisibilityOfElement(addToCartSuccessMsg, waitTime);
		Thread.sleep(Duration.ofSeconds(2));
		//waitForInvisibilityOfElement(loadingIcon, waitTime);
		logger.info("clicking on cart tab");
		cartTab.click();
		return new CartPage(driver);
	}
	
	public LoginPage signOut() {
		logger.info("Signing out");
		signOut.click();
		return new LoginPage(driver);
	}

}
