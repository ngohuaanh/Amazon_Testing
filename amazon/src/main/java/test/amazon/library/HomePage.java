package test.amazon.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import test.amazon.common.CommonClass.PageType;
import test.amazon.common.WebAttribute;

public class HomePage extends PageObject{

	public HomePage(WebDriver driver, WebAttribute webAttribute) {
		super(driver, webAttribute);
		pageType = PageType.HOME_PAGE;
	}

	@Override
	public void open() {
		if(webAttribute.getCurrentPage() == PageType.HOME_PAGE)
			return;
		else{
			// TODO: do sth
		} 
	}

	@Override
	public void assertContent() {
		assertion.assertElementIsDisplay(driver, By.xpath("//*[@id=\"desktop-banner\"]"));
	}

	@Override
	public void waitForPageFinishLoading() {
		// TODO Auto-generated method stub
	}
	
	public void assertHeader() {
		
	}
	
}
