package test.amazon.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SubmitForm {
	private WebElement e;
	
	public SubmitForm(WebElement e) {
		this.e = e;
	}
	
	public void InputValue(String type, String value) {
		By locator = By.xpath("//input[@type=\""+type+"\"]");
		System.out.println(locator+":input text '" + value + "'");
		e.findElement(locator).sendKeys(value);
		
	}
	
	public void submit() {
		System.out.println("Submit");
		e.findElement(By.xpath("//input[@type='submit']")).submit();
	}
}
