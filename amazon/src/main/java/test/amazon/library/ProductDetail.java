package test.amazon.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import test.amazon.common.WebAttribute;

public class ProductDetail extends PageObject{

	public ProductDetail(WebDriver driver, WebAttribute webAttribute) {
		super(driver, webAttribute);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForPageFinishLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertContent() {
		// TODO Auto-generated method stub
		
	}
	
	public void addToCard() {
		action.clickOnLocator(driver, By.xpath("//*[@id=\"add-to-cart-button\"]"));
		webElementUtil.waitForElementVisisble(driver, By.xpath("//div[contains(@id, 'order-row-container')]"), 10);
	}
}
