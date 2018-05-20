package test.amazon.testsuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import test.amazon.common.CommonClass.PageType;
import test.amazon.common.CommonClass.ResetType;
import test.amazon.common.WebAttribute;
import test.amazon.utility.ActionUtil;
import test.amazon.utility.AssertionUtil;
import test.amazon.utility.Configuration;
import test.amazon.utility.MovementUtil;
import test.amazon.utility.WebElementUtil;

public class Initialize{
	//private final String configPath="Resource/Configuration/config.properties";
	private final String outputPath="test-output/screenshot";
	protected WebAttribute webAttribute;
	protected MovementUtil movementUtil;
	protected WebElementUtil webElementUtil;
	protected AssertionUtil assertionUtil;
	protected ActionUtil actionUtil;
	protected String accessUrl;
	protected WebDriver driver;
	protected String browser;
	protected String testCaseName;
	protected Configuration config;
	
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(){
		System.out.println("================Before Suite==============");
	}
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest(){
		System.out.println("Before Test");
	}
	
	@Parameters({"configFile"})
	@BeforeClass(alwaysRun=true)
	public void beforeClass(String configPath){
		System.out.println("Before class");
		loadConfiguation(configPath);
		initAction();
		webAttribute = new WebAttribute();
		launchBrowser();
		// Use implicit wait = 500s 
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	}
	
	private void loadConfiguation(String configPath) {
		config = new Configuration(configPath);
		config.load();
		this.accessUrl = config.getUrl();
		this.browser = config.getBrowser();
	}
	
	private void initAction() {
		actionUtil = new ActionUtil();
		movementUtil = new MovementUtil();
		assertionUtil = new AssertionUtil();
		webElementUtil = new WebElementUtil();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod(Method method){
		System.out.println("Before method");
		if(webAttribute.getResetType() == ResetType.OK_AND_RESET) {
			driver.quit();
			launchBrowser();
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		}
		this.testCaseName = method.getName();
		printWithTestcaseName("======================Start testcase======================");
	}
	
	@AfterMethod(alwaysRun=true)
	public void AfterMethod(ITestResult result){
		switch (result.getStatus()) {
			case ITestResult.SUCCESS:
				//TODO: add sth
				break;
			case ITestResult.FAILURE:
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(scrFile, new File(outputPath + testCaseName + ".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.quit();
				break;
			case ITestResult.SKIP:
				//TODO: add sth
				break;
			default:
				break;
		}
		printWithTestcaseName("======================End testcase======================");
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass(){
		System.out.println("After class");
		driver.quit();
	}
	
	private void launchBrowser(){
		if("chrome".equals(browser)){
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("test-type");
			//options.addArguments("--enable-precise-memory-info");
			options.addArguments("--disable-popup-blocking"); // Disable pop-up blocking
			// Disables installation of default apps on first run. This is used during automated testing.
			options.addArguments("--disable-default-apps");
			// Type of the current test harness ("browser" or "ui")
			options.addArguments("test-type=browser");
			// Prevent info bars display
			options.addArguments("disable-infobars");
			
			// Add the WebDriver proxy capability.
			/*Proxy proxy = new Proxy();
			proxy.setHttpProxy("127.0.0.1:0000");
			capabilities.setCapability("proxy", proxy);*/

			// Add ChromeDriver-specific capabilities through ChromeOptions.
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			try {
				driver = new RemoteWebDriver(new URL(config.getSelenium_node()) ,capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			driver.navigate().to(accessUrl);
			driver.manage().deleteAllCookies();
			driver.manage().window().setSize(new Dimension(1400,900));
			webAttribute.setCurrentPage(PageType.HOME_PAGE);
		}else{
			// .. Add more type of browser: chrome, ie, etc
		}
	}
	
	protected void printWithTestcaseName(String message) {
		System.out.println(testCaseName + ":" + message);
	}
}

