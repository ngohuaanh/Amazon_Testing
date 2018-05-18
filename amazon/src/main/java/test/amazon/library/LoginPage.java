package test.amazon.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import test.amazon.common.WebAttribute;
import test.amazon.common.CommonClass.PageType;

public class LoginPage extends PageObject{
	private final String loginFormXpath = "//form[@name=\"signIn\"]";
	
	public LoginPage(WebDriver driver, WebAttribute webAttribute) {
		super(driver, webAttribute);
		pageType = PageType.HOME_PAGE;
	}

	@Override
	public void open() {
		switch (webAttribute.getCurrentPage()) {
			case HOME_PAGE:
				webElementUtil.waitForElementIsClickable(driver, By.xpath("//*[@id=\"nav-link-accountList\"]/span[1]"), 5);
				action.clickOnLocator(driver, By.xpath("//*[@id=\"nav-link-accountList\"]/span[1]"));
				webElementUtil.waitForElementVisisble(driver, By.xpath(loginFormXpath), 5);
				break;
			case LOGIN_PAGE:
				break;
			default:
				break;
		}
	}

	@Override
	public void assertContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForPageFinishLoading() {
		// TODO Auto-generated method stub
		
	}
	
	public void moveToWelcomePage() {
		this.open();
		SubmitForm form = new SubmitForm(driver.findElement(By.xpath(loginFormXpath)));
		form.InputValue("email", webAttribute.getEmail());
		form.InputValue("password", webAttribute.getPassword());
		form.submit();
		webElementUtil.waitForElementIsNotPresent(driver, By.xpath(loginFormXpath), 10);
	}
}
