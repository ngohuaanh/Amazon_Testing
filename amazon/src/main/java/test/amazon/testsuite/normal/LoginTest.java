package test.amazon.testsuite.normal;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.amazon.library.LoginPage;
import test.amazon.testsuite.Initialize;

public class LoginTest extends Initialize{
	
	@Test(dataProvider = "Authentication", enabled = true, groups = {"Test"})
	public void ValidUser_WelcomeUserPageIsDisplayed(String email, String username, String password){
		webAttribute.setUsername(username);
		webAttribute.setEmail(email);
		webAttribute.setPassword(password);
		// Move to login page and login with email & password
		LoginPage login = new LoginPage(driver, webAttribute);
		login.moveToWelcomePage();
		webAttribute.setWasLogin(true);
		
		// Assert welcome page
		
		
	}
	
	@DataProvider(name = "Authentication")
	public static Object[][] credentials(){
		return new Object[][] {{"anhhtv.93@gmail.com", "Hoang Thi Van Anh", "Abcd1234"}};
	}
}
