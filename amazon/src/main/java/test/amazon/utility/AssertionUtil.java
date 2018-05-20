package test.amazon.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AssertionUtil {
	private WebElementUtil webElementUtil;
	
	public AssertionUtil() {
		webElementUtil = new WebElementUtil();
	}
	
	public void assertElementIsDisplay(WebDriver driver, By locator) {
		Assert.assertTrue(webElementUtil.isElementVisible(driver, locator), locator + " is not displayed");
	}
	
	public void assertElementIsNotPresent(WebDriver driver, By locator) {
		Assert.assertFalse(webElementUtil.isElementVisible(driver, locator), locator + " is presented");
	}
	
	public void assertEqualByXPath(WebDriver driver, String xPath, String expectedValue) {
		String actualValue = driver.findElement(By.xpath(xPath)).getText();
		Assert.assertTrue(actualValue.equals(expectedValue), xPath + ": [actual=" + actualValue + "][expected=" + expectedValue + "]");;
	}
}
