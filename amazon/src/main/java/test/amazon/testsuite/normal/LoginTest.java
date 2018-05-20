package test.amazon.testsuite.normal;

import org.testng.annotations.Test;

import test.amazon.common.CommonClass.ResetType;
import test.amazon.library.HomePage;
import test.amazon.library.LoginPage;
import test.amazon.library.SubmitForm;
import test.amazon.testsuite.DataProviders;
import test.amazon.testsuite.Initialize;

public class LoginTest extends Initialize{
	
	@Test(enabled = true, groups = {"Full"})
	public void InvalidEmail_ErrorIsDisplayed(){
		// Move to home page
		HomePage home = new HomePage(driver, webAttribute);
		home.open();
		home.assertHeader();
		// Move to home page
		LoginPage login = new LoginPage(driver, webAttribute);
		login.moveToEmailPage();
		
		String specialKeys[] = {"~","!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "{", "}", "|", ":", "\"", "<", ">", "?", "/",
								"notfoundemail@gmail.com"};
		for(int index = 0; index < specialKeys.length; index++) {
			String invalidEmail = specialKeys[index];
			checkInvalidEmail(login, invalidEmail);
		}
		
		// TODO: Add check longest email name
	}
	
	@Test(enabled = true, groups = {"Full"})
	public void InvalidPassword_ErrorIsDisplayed(){
		String validEmail = "anhhtv.93@gmail.com";
		String invalidPassword = "invalidpassword";
		// Move to Password page
		LoginPage login = new LoginPage(driver, webAttribute);
		login.moveToPasswordPage(validEmail);
		
		SubmitForm signinForm = login.getSignInForm();
		signinForm.inputValue("password", invalidPassword);
		signinForm.submit();
		assertErrorMessage(webAttribute.getLocale().invalidPassword);
	}
	
	@Test(dataProvider = "Authentication", dataProviderClass = DataProviders.class, enabled = true, groups = {"Full", "Smoke"})
	public void ValidUser_WelcomeUserPageIsDisplayed(String email, String username, String password){
		// Move to login page and login with email & password
		webAttribute.setUsername(username);
		webAttribute.setEmail(email);
		webAttribute.setPassword(password);
		LoginPage login = new LoginPage(driver, webAttribute);
		login.moveToWelcomePage();
		
		// Assert welcome page
		HomePage home = new HomePage(driver, webAttribute);
		home.assertHeader();
		
		webAttribute.setResetType(ResetType.OK_AND_RESET);
	}
	
	private void checkInvalidEmail(LoginPage page, String invalidEmail) {
		SubmitForm signinForm = page.getSignInForm();
		signinForm.inputValue("email", invalidEmail);
		signinForm.submit();
		assertErrorMessage(webAttribute.getLocale().invalidEmail);
		signinForm = page.getSignInForm();
		signinForm.clearValue("email");
	}
	
	private void assertErrorMessage(String errorMessage) {
		assertionUtil.assertEqualByXPath(driver, "//*[@id=\"auth-error-message-box\"]/div/div/ul/li/span", errorMessage);
	}
}
