package test.amazon.testsuite;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "Authentication")
	public static Object[][] credentials(){
		return new Object[][] {{"anhhtv.93@gmail.com", "Hoang Thi Van Anh", "Abcd1234"}};
	}
	
}
