package test.amazon.common;

public class CommonClass {
	public static enum PageType{
		HOME_PAGE, LOGIN_PAGE, LOGIN_EMAIL_PASSWORD_PAGE, LOGIN_EMAIL_PAGE, LOGIN_PASSWORD_PAGE,
		CART_PAGE, SEARCH_RESULT_PAGE, PRODUCT_DETAIL_PAGE
	}
	
	public static enum Language{
		EN
	}
	
	public static enum ResetType{
		NONE, OK_AND_RESET, SKIP_AND_RESET
	}
}
