package test.amazon.testsuite.normal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import test.amazon.common.CommonClass.PageType;
import test.amazon.library.CartPage;
import test.amazon.library.LoginPage;
import test.amazon.library.ProductDetail;
import test.amazon.library.SearchResultPage;
import test.amazon.testsuite.DataProviders;
import test.amazon.testsuite.Initialize;
import test.amazon.testsuite.Product;

public class PurchaseOrderTest extends Initialize{
	@Test(dataProvider = "Authentication", dataProviderClass = DataProviders.class, enabled = true, groups = {"Full", "Smoke"})
	public void Order1Product_ProductIsAddedToCart(String email, String username, String password){
		// Move to login page and login with email & password
		webAttribute.setUsername(username);
		webAttribute.setEmail(email);
		webAttribute.setPassword(password);
		LoginPage login = new LoginPage(driver, webAttribute);
		login.moveToWelcomePage();
		webAttribute.setCurrentPage(PageType.HOME_PAGE);
		
		// Search iphone
		String searchWord = "iphone x 64 gb";
		SearchResultPage search = new SearchResultPage(driver, webAttribute);
		search.open(searchWord);
		search.waitForPageFinishLoading();
		
		
		Product firstProduct = actionUtil.getProduct(driver.findElement(By.id("result_0")));
		search.openProduct(By.xpath("//*[@id=\"result_0\"]//a[contains(@class, 'title-link')]"));
		
		// Add to card
		ProductDetail productDetail = new ProductDetail(driver, webAttribute);
		productDetail.addToCard();
		
		// Check product is added to cart
		CartPage cart = new CartPage(driver, webAttribute);
		cart.open();
		cart.assertProductInCart(firstProduct, 1);
		
		// Logout
		actionUtil.mouseHover(driver, By.id("nav-link-accountList"));
		actionUtil.clickOnLocator(driver, By.id("nav-item-signout-sa"));
		
		webElementUtil.waitForElementVisisble(driver, By.xpath("//form[@name=\"signIn\"]"), 5);
		webAttribute.setCurrentPage(PageType.LOGIN_PAGE);
		
		// Re-login
		login.moveToWelcomePage();
		cart.open();
		cart.assertProductInCart(firstProduct, 1);
	}
}
