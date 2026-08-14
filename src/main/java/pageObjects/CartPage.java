package pageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class CartPage extends Utils {
	
	private  WebDriver driver;
	private static final Logger logger = LogManager.getLogger(CartPage.class);
	
	@FindBy(css = "div .cart")
	private List<WebElement> cartProducts;
	By cartProductName = By.tagName("h3");
	@FindBy(css = ".subtotal .btn-primary")
	private WebElement checkoutBtn;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		logger.info("Initializing web elements for "+this.getClass());
		PageFactory.initElements(driver, this);
	}
	
	public boolean isRequiredCartProductPresent(String productName) {
		boolean isProductPresent =cartProducts.stream().anyMatch(cartproduct->cartproduct.findElement(cartProductName).getText().equals(productName));
		logger.info("Product with product name "+productName+ " is present on cartPage "+isProductPresent);
		return isProductPresent;
	}
	
	public PersonalInfoPage clickOnProductCheckout() {
		logger.info("clicking on personal info page");
		checkoutBtn.click();
		return new PersonalInfoPage(driver);
	}
	

}
