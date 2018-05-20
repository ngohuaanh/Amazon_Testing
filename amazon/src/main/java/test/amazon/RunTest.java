package test.amazon;

import java.io.File;
import java.util.Arrays;
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
    public static void main( String[] args )
    {
        XmlSuite suite = new XmlSuite();
		suite.setName("Amazon Suite");
		Map<String, String> parameters = suite.getParameters();
		parameters.put("configFile", "Resource/Configuration/config.properties");
		suite.setParameters(parameters);
		

		// create a test case for the suite
		XmlTest xmltest = new XmlTest(suite);
		xmltest.setName("Amazon Test");
		xmltest.setXmlClasses(Arrays.asList(new XmlClass("test.amazon.testsuite.normal.LoginTest"),
											new XmlClass("test.amazon.testsuite.normal.PurchaseOrderTest")));
		
		xmltest.setIncludedGroups(Arrays.asList("Smoke"));
		suite.setListeners(Arrays.asList(HTMLReporter.class.getName(), JUnitXMLReporter.class.getName()));
		
		String outDirPath = "test-output";
		File outDir = new File(outDirPath);
		if(!outDir.exists()) {
			outDir.mkdirs();
		}
		
		TestNG tng = new TestNG();
    	tng.setOutputDirectory(outDirPath);
    	tng.setUseDefaultListeners(false);
    	tng.setXmlSuites(Arrays.asList(suite));
    	tng.run();
    }
}
