package test.amazon.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class AssertionUtil {
	private WebElementUtil webElementUtil;
	
	public AssertionUtil() {
		webElementUtil = new WebElementUtil();
	}
	
	public void assertElementIsDisplay(WebDriver driver, By locator) {
		Assert.assertTrue(locator + " is not displayed", webElementUtil.isElementVisible(driver, locator));
	}
	
	public void assertElementIsNotPresent(WebDriver driver, By locator) {
		Assert.assertFalse(locator + " is presented", webElementUtil.isElementVisible(driver, locator));
	}
	
	public void assertEqualByXPath(WebDriver driver, String xPath, String expectedValue) {
		Assert.assertEquals(expectedValue, driver.findElement(By.xpath(xPath)).getText());
	}
}
