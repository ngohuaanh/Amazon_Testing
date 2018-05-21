package test.amazon;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.uncommons.reportng.HTMLReporter;
import org.uncommons.reportng.JUnitXMLReporter;

/**
 * Hello world!
 *
 */
public class RunTest 
{
	private static String configPath = "Resource/Configuration/config.properties";
	private static String group = "Smoke";
	private static String output="test-ouput";
	
	private static void loadParameters(String[] args) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		for (int index = 0; index < args.length; index+=2) {
			String key = args[index];
			if (index+1 > args.length) {
				throw new Exception("Missing value at " + key);
			}
			String value = args[index+1];
			params.put(key, value);
		}
		for (Map.Entry<String, String> entry : params.entrySet()) {
		    String key = entry.getKey();
		    switch (key) {
				case "--config":
				case "-c":
					RunTest.configPath = entry.getValue();
					break;
				case "--group":
				case "-g":
					RunTest.group = entry.getValue();
					break;
				case "--output":
				case "-o":
					RunTest.output = entry.getValue();
					break;
				default:
					break;
			}
		}
	}
	
    public static void main( String[] args ) throws Exception{
    	RunTest.loadParameters(args);
    	
        XmlSuite suite = new XmlSuite();
		suite.setName("Amazon Suite");
		Map<String, String> parameters = suite.getParameters();
		parameters.put("configFile", configPath);
		suite.setParameters(parameters);
		

		// create a test case for the suite
		XmlTest xmltest = new XmlTest(suite);
		xmltest.setName("Amazon Test");
		xmltest.setXmlClasses(Arrays.asList(new XmlClass("test.amazon.testsuite.normal.LoginTest"),
											new XmlClass("test.amazon.testsuite.normal.PurchaseOrderTest")));
		
		xmltest.setIncludedGroups(Arrays.asList(group));
		suite.setListeners(Arrays.asList(HTMLReporter.class.getName(), JUnitXMLReporter.class.getName()));
		
		File outDir = new File(output);
		if(!outDir.exists()) {
			outDir.mkdirs();
		}
		
		TestNG tng = new TestNG();
    	tng.setOutputDirectory(output);
    	tng.setUseDefaultListeners(false);
    	tng.setXmlSuites(Arrays.asList(suite));
    	tng.run();
    }
}
