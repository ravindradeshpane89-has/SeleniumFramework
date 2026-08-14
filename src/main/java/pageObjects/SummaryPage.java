package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class SummaryPage extends Utils {
	
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(SummaryPage.class);
	@FindBy(css = "h1.hero-primary")
	private WebElement orderSuccessMsg;

	public SummaryPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		logger.info("Initializing web elements of "+this.getClass());
     PageFactory.initElements(driver, this);
	}
	
	public String getOrderSuccessMessage() {
		logger.info("Getting success order message from Summary Page");
		return orderSuccessMsg.getText();
	}

}
