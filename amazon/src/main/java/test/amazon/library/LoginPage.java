package test.amazon.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import test.amazon.common.CommonClass.PageType;
import test.amazon.common.WebAttribute;

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
		webAttribute.setCurrentPage(PageType.LOGIN_PAGE);
	}

	@Override
	public void assertContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForPageFinishLoading() {
		// TODO Auto-generated method stub
		
	}
	
	public void moveToEmailPage() {
		switch (webAttribute.getCurrentPage()) {
			case LOGIN_PAGE:
			case LOGIN_EMAIL_PASSWORD_PAGE:
				break;
			case LOGIN_PASSWORD_PAGE:
				action.clickOnLocator(driver, By.xpath("//*[@id=\"ap_change_login_claim\"]"));
				webElementUtil.waitForElementVisisble(driver, By.xpath("//input[@type=\"email\"]"), 5);
				break;
			default:
				this.open();
				break;
		}
		webAttribute.setCurrentPage(PageType.LOGIN_EMAIL_PAGE);
	}
	
	public void moveToPasswordPage(String email) {
		this.moveToEmailPage();
		SubmitForm form = new SubmitForm(driver.findElement(By.xpath(loginFormXpath)));
		form.clearValue("email");
		form.inputValue("email", email);
		
		if(webElementUtil.isElementVisible(driver, By.id("signInSubmit"))) {
			webAttribute.setCurrentPage(PageType.LOGIN_EMAIL_PASSWORD_PAGE);
		}else {
			form.submit();
			webElementUtil.waitForElementVisisble(driver, By.xpath("//input[@type=\"password\"]"), 5);
			webAttribute.setCurrentPage(PageType.LOGIN_PASSWORD_PAGE);
		}
	}
	
	public void moveToWelcomePage() {
		this.moveToPasswordPage(webAttribute.getEmail());
		
		SubmitForm form = new SubmitForm(driver.findElement(By.xpath(loginFormXpath)));
		form.clearValue("password");
		form.inputValue("password", webAttribute.getPassword());
		form.submit();
		webElementUtil.waitForElementIsNotPresent(driver, By.xpath(loginFormXpath), 10);
		webAttribute.setWasLogin(true);
	}
	
	public SubmitForm getSignInForm() {
		return new SubmitForm(driver.findElement(By.xpath(loginFormXpath)));
	}
}
