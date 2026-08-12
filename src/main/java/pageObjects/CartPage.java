package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class CartPage extends Utils {
	
	private  WebDriver driver;
	
	@FindBy(css = "div .cart")
	private List<WebElement> cartProducts;
	By cartProductName = By.tagName("h3");
	@FindBy(css = ".subtotal .btn-primary")
	private WebElement checkoutBtn;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isRequiredCartProductPresent(String productName) {
		boolean isProductPresent =cartProducts.stream().anyMatch(cartproduct->cartproduct.findElement(cartProductName).getText().equals(productName));
		return isProductPresent;
	}
	
	public PersonalInfoPage clickOnProductCheckout() {
		checkoutBtn.click();
		return new PersonalInfoPage(driver);
	}
	

}
