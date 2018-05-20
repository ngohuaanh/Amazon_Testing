package test.amazon.common;

import test.amazon.common.CommonClass.PageType;
import test.amazon.common.CommonClass.ResetType;

public class WebAttribute {
	private PageType currentPage;
	private String username;
	private Locale locale;
	private ResetType resetType;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String email;
	private String password;
	private boolean wasLogin;
	public boolean isWasLogin() {
		return wasLogin;
	}
	public void setWasLogin(boolean wasLogin) {
		this.wasLogin = wasLogin;
	}
	public PageType getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(PageType currentPage) {
		this.currentPage = currentPage;
	}
	public WebAttribute(){
		// Define all default value of attribute of web
		this.currentPage = PageType.HOME_PAGE;
		this.locale = new Locale(CommonClass.Language.EN);
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public ResetType getResetType() {
		return resetType;
	}
	public void setResetType(ResetType resetType) {
		this.resetType = resetType;
	}
	
}
