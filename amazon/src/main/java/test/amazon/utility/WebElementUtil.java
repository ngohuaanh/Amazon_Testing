package test.amazon.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementUtil {
	
	public boolean isElementVisible(WebDriver driver, By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		} catch (StaleElementReferenceException stale_ex) {
			return false;
		}
	}
	
	public boolean isElementPresent(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
	
	public boolean isElementNotPresent(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return false;
		} catch (NoSuchElementException ex) {
			return true;
		}
	}
	
	public void waitForElementVisisble(WebDriver driver, By locator, int waitingTime, String... errorMsg){
		String errorMessage = "";
		if(0 != errorMsg.length){
			errorMessage = errorMsg[0];
		}else{
			errorMessage = locator + "is not visible after " + waitingTime + "s";
		}
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, waitingTime)
				                                 .ignoring(StaleElementReferenceException.class)
				                                 .ignoring(NoSuchElementException.class);
		wait.withMessage(errorMessage).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementIsNotPresent(WebDriver driver, By locator, int waitingTime, String... errorMsg){
		String errorMessage = "";
		if(0 != errorMsg.length){
			errorMessage = errorMsg[0];
		}else{
			errorMessage = locator + "is still present after " + waitingTime + "s";
		}
		try {
			WebElement e = driver.findElement(locator);
			new WebDriverWait(driver, waitingTime).withMessage(errorMessage).until(ExpectedConditions.stalenessOf(e));
		} catch (NoSuchElementException e) {
			return;
		}
	}
	
	public void waitForElementIsClickable(WebDriver driver, By locator, int waitingTime, String... errorMsg){
		String errorMessage = "";
		if(0 != errorMsg.length){
			errorMessage = errorMsg[0];
		}else{
			errorMessage = locator + "is not clickable after " + waitingTime + "s";
		}
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, waitingTime)
								                .ignoring(StaleElementReferenceException.class)
								                .ignoring(NoSuchElementException.class);
		wait.withMessage(errorMessage).until(ExpectedConditions.elementToBeClickable(locator));
        
	}
	
	public void waitForAttributeChangedTo(WebDriver driver, By locator, String attribute, String value, int waitingTime, String... errorMsg) {
		String errorMessage = "";
		if(0 != errorMsg.length){
			errorMessage = errorMsg[0];
		}else{
			errorMessage = "[" + locator + "]:"+attribute+" is not changed to " + value + " after " + waitingTime + "s";
		}
		WebDriverWait wait = new WebDriverWait(driver,waitingTime);
		wait.withMessage(errorMessage).until(new ExpectedCondition<Boolean>() {
			private By locator;
	        private String attribute;
	        private String value;

	        private ExpectedCondition<Boolean> init( By locator, String attribute, String value) {
	            this.locator = locator;
	            this.attribute = attribute;
	            this.value = value;
	            return this;
	        }
	        
		    public Boolean apply(WebDriver driver) {
		        WebElement button = driver.findElement(locator);
		        String enabled = button.getAttribute(attribute);
		        if(enabled.equals(value)) 
		            return true;
		        else
		            return false;
		    }
		}.init(locator, attribute, value));
	}
	
	public void waitForAttributeFromTo(WebDriver driver, By locator, String attribute, String value, int waitingTime, String... errorMsg) {
		String errorMessage = "";
		if(0 != errorMsg.length){
			errorMessage = errorMsg[0];
		}else{
			errorMessage = "[" + locator + "]:"+attribute+" is not changed from " + value + " after " + waitingTime + "s";
		}
		WebDriverWait wait = new WebDriverWait(driver,waitingTime);
		wait.withMessage(errorMessage).until(new ExpectedCondition<Boolean>() {
			private By locator;
	        private String attribute;
	        private String value;

	        private ExpectedCondition<Boolean> init( By locator, String attribute, String value) {
	            this.locator = locator;
	            this.attribute = attribute;
	            this.value = value;
	            return this;
	        }
	        
		    public Boolean apply(WebDriver driver) {
		        WebElement button = driver.findElement(locator);
		        String enabled = button.getAttribute(attribute);
		        if(enabled.equals(value)) 
		            return false;
		        else
		            return true;
		    }
		}.init(locator, attribute, value));
	}
}
