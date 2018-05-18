package test.amazon.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private String path;
	private String url;
	private String selenium_node;
	public String getSelenium_node() {
		return selenium_node;
	}

	public void setSelenium_node(String selenium_node) {
		this.selenium_node = selenium_node;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	private String browser;
	
	public Configuration(String path) {
		this.path = path;
	}
	
	public void load() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);
			url = prop.getProperty("url");
			browser = prop.getProperty("browser");
			selenium_node = prop.getProperty("selenium_hub");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
