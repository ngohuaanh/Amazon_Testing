package test.amazon.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
}
