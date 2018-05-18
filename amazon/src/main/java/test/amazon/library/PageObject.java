package test.amazon.library;

import org.openqa.selenium.WebDriver;

import test.amazon.common.CommonClass.PageType;
import test.amazon.common.WebAttribute;
import test.amazon.utility.ActionUtil;
import test.amazon.utility.AssertionUtil;
import test.amazon.utility.MovementUtil;
import test.amazon.utility.WebElementUtil;

public abstract class PageObject {
	protected WebDriver driver;
	protected PageType pageType;
	protected WebAttribute webAttribute;
	protected ActionUtil action;
	protected AssertionUtil assertion;
	protected MovementUtil movement;
	protected WebElementUtil webElementUtil;
	
	public PageObject(WebDriver driver, WebAttribute webAttribute) {
		this.driver = driver;
		this.webAttribute = webAttribute;
		action = new ActionUtil();
		assertion = new AssertionUtil();
		movement = new MovementUtil();
		webElementUtil = new WebElementUtil();
	}
	
	public abstract void open();
	
	public abstract void waitForPageFinishLoading();
	
	public abstract void assertContent();
}
