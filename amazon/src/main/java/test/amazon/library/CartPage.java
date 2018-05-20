package test.amazon.library;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.amazon.common.CommonClass.PageType;
import test.amazon.common.WebAttribute;
import test.amazon.testsuite.Product;

public class CartPage extends PageObject{

	public CartPage(WebDriver driver, WebAttribute webAttribute) {
		super(driver, webAttribute);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void open() {
		action.clickOnLocator(driver, By.id("nav-cart"));
		webElementUtil.waitForElementVisisble(driver, By.id("sc-active-cart"), 5);
		webAttribute.setCurrentPage(PageType.CART_PAGE);
	}

	@Override
	public void waitForPageFinishLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assertContent() {
		// TODO Auto-generated method stub
		
	}
	
	public void assertProductInCart(Product product, int number) {
		List<WebElement> products = driver.findElements(By.xpath("//*[@id=\"activeCartViewForm\"]/div[2]/div"));
		for (WebElement p : products) {
			String id = p.getAttribute("data-asin");
			if(product.getId().equals(id)) {
				System.out.println(product.getId() + " is found");
				assertion.assertEqualByXPath(driver, "//div[@data-asin='"+product.getId()+"']//*[contains(@class, 'product-title')]", product.getName());
				assertion.assertEqualByXPath(driver, "//div[@data-asin='"+product.getId()+"']//*[contains(@class, 'product-price')]", 
						(product.getPrice().getCurrency()) + product.getPrice().getPriceWhole() + "."+product.getPrice().getPriceFractional());
				return;
			}
		}
		Assert.fail(product.getId() + " is not added to cart");
	}

}
