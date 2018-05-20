package test.amazon.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import test.amazon.common.CommonClass.PageType;
import test.amazon.common.Locale;
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
		Locale locale = webAttribute.getLocale();
		// Assert title
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-logo\"]/a[1]/span[1]", locale.amazon);
		// Assert search box
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-search\"]//span[@class=\"nav-search-label\"]", locale.all);
		assertion.assertElementIsDisplay(driver, By.xpath("//*[@id=\"nav-search\"]//i[@class=\"nav-icon\"]"));
		assertion.assertElementIsDisplay(driver, By.xpath("//*[@id=\"nav-search\"]//div[@class=\"nav-fill\"]"));
		// Assert menu
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-shop\"]", locale.departments);
		int index = 2;
		String accountName = ". " + locale.signin;
		if(webAttribute.isWasLogin()) {
			assertion.assertEqualByXPath(driver, "//*[@id=\"nav-recently-viewed\"]", locale.browsingHistory);
			index = 3;
			accountName = ", " + webAttribute.getUsername().split(" ")[0];
		}
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-xshop\"]/a["+index+"]", locale.todayDeals);
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-xshop\"]/a["+(index+1)+"]", locale.giftCards);
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-xshop\"]/a["+(index+2)+"]", locale.registry);
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-xshop\"]/a["+(index+3)+"]", locale.sell);
		//assertion.assertEqualByXPath(driver, "//*[@id=\"nav-xshop\"]/a["+(index+4)+"]", locale.help);
		
		// Assert user
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-link-accountList\"]/span[1]", locale.hello + accountName);
		// Assert orders
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-orders\"]/span[2]", webAttribute.getLocale().orders);
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-link-prime\"]", locale.tryPrime);
		
		assertion.assertEqualByXPath(driver, "//*[@id=\"nav-cart\"]/span[2]", locale.cart);
	}
	
}
