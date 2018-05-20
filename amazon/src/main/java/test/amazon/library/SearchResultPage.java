package test.amazon.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import test.amazon.common.WebAttribute;

public class SearchResultPage extends PageObject{
	
	private String searchFromXpath = "//*[@id=\"nav-search\"]/form";
	private String searchTextboxXpath = "//*[@id=\"twotabsearchtextbox\"]";

	public SearchResultPage(WebDriver driver, WebAttribute webAttribute) {
		super(driver, webAttribute);
	}
	
	public void open(String searchWord) {
		System.out.println("Search word: " + searchWord);
		switch (webAttribute.getCurrentPage()) {
			case HOME_PAGE:
				
				SubmitForm form = new SubmitForm(driver.findElement(By.xpath(searchFromXpath)));
				form.clearValue(By.xpath(searchTextboxXpath));
				form.inputValue(By.xpath(searchTextboxXpath), searchWord);
				form.submit();
				webElementUtil.waitForElementVisisble(driver, By.id("resultsCol"), 10);
				break;
	
			default:
				break;
		}
	}

	@Override
	public void waitForPageFinishLoading() {
		webElementUtil.waitForElementVisisble(driver, By.id("result_0"), 10);
	}

	@Override
	public void assertContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}
	
	public void openProduct(By locator) {
		driver.findElement(locator).click();
		webElementUtil.waitForElementVisisble(driver, By.id("dp-container"), 10);
	}

}
