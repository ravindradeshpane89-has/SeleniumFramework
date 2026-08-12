package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class LoginPage extends Utils {
	
	private  WebDriver driver;
	
	@FindBy(id = "userEmail")
	private WebElement userId;
	@FindBy(id = "userPassword")
	private WebElement password;
	@FindBy(id = "login")
	private WebElement login;
	@FindBy(css ="div[aria-label*='Incorrect']")
	private WebElement errorLoginMsg;
	
	public WebElement getErrorLoginMsg() {
		return errorLoginMsg;
	}

	public LoginPage(WebDriver driver){
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUserName(String username) {
		userId.sendKeys(username);
	}
	
	public void enterpassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public HomePage loginToApp() {
		login.click();
		return new HomePage(driver);
	}
	
	public String getErrorLoginMsgText() {
		return errorLoginMsg.getText();
	}

}
