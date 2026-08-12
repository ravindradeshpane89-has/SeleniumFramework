package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class SummaryPage extends Utils {
	
	private WebDriver driver;
	
	@FindBy(css = "h1.hero-primary")
	private WebElement orderSuccessMsg;

	public SummaryPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
     PageFactory.initElements(driver, this);
	}
	
	public String getOrderSuccessMessage() {
		return orderSuccessMsg.getText();
	}

}
