package test.amazon.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import test.amazon.testsuite.Price;
import test.amazon.testsuite.Product;

public class ActionUtil {
	private Actions actions;
	
	public void sendKey(Keys key){
		actions.sendKeys(key);
		System.out.println(key.name());
	}
	
	public void sendKey(String key){
		actions.sendKeys(key);
		System.out.println(key);
	}
	
	public void sendKeysLoop(Keys key, int loop){
		for(int count = 0; count < loop; count++){
			actions.sendKeys(key);
			System.out.println(key.name());
		}
	}
	
	public void sendKeysLoop(String key, int loop){
		for(int count = 0; count < loop; count++){
			actions.sendKeys(key);
			System.out.println(key);
		}
	}
	
	public void clickOnLocator(WebDriver driver, By locator){
		driver.findElement(locator).click();
		System.out.println(locator + ": Click on");
	}
	
	public void sendKeys(WebDriver driver, By locator, String text){
		driver.findElement(locator).sendKeys(text);
		System.out.println(locator + ": Sendkeys - " + text);
	}
	
	public void selectText(WebDriver driver, By locator, String text){
		Select selectMaxPrice = new Select(driver.findElement(locator));
		selectMaxPrice.selectByVisibleText(text);
		System.out.println(locator + ": Select - " + text );
	}
	
	public void mouseHover(WebDriver driver, By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
	}
	
	public Product getProduct(WebElement e) {
		String id = e.getAttribute("data-asin");
		String name = e.findElement(By.xpath("//a[contains(@class, 'title-link')]")).getAttribute("title");
		String currency = e.findElement(By.xpath("//*[contains(@class, 'currency')]")).getText();
		String priceWhole = e.findElement(By.xpath("//*[contains(@class, 'price-whole')]")).getText();
		String priceFractional = e.findElement(By.xpath("//*[contains(@class, 'price-fractional')]")).getText();
		return new Product(id, name, new Price(currency, priceWhole, priceFractional));
	}
}
