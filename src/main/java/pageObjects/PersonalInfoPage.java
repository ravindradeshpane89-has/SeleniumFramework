package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class PersonalInfoPage extends Utils {
	
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(PersonalInfoPage.class);
	
	@FindBy(css = "input[placeholder='Select Country']")
	private WebElement selectCountry;
	@FindBy(css = ".ta-results")
	private WebElement countryResults;
	@FindBy(xpath = "//span[text()=' India']")
	private WebElement reqCountry;
	@FindBy(css = ".action__submit ")
	private WebElement submitBtn;

	public PersonalInfoPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		logger.info("Initializing web elements of "+this.getClass());
	PageFactory.initElements(driver, this);
	}
	
	public void enterPersonalInfo(String countryName,long waitTime) {
		logger.info("Entering the country "+countryName+" on personal info page");
		moveToElement(selectCountry).click().sendKeys(countryName).build().perform();
		waitForVisibilityOfElement(countryResults, waitTime);
		moveToElement(reqCountry).click().build().perform();
	}
	
	public SummaryPage clickOnSubmit() {
		logger.info("Submitting the details on personal info page");
		submitBtn.click();
		return new SummaryPage(driver);
		
	}
	

}
