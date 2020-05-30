package TestAutomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestAutomation.helpers.PropertiesParser;
import TestAutomation.helpers.Utilities;
import TestAutomation.helpers.baseClass;
import TestAutomation.pageObjects.Advisor;
import TestAutomation.pageObjects.Billionaires;
import TestAutomation.pageObjects.HomePage;
import TestAutomation.pageObjects.Search;

public class executor2 {

	Utilities u1 = new Utilities();

	@BeforeTest(description = "This testcase is verifying loading of config file and opening browser")
	public void loadConfigFile() {
		// This method will load properties file and base Class
		PropertiesParser pp = new PropertiesParser();
		pp.readProperty();
		u1.openBrowser();
	}

	@Test(description = "This testcase is validating homepage")
	public void testHomePage() {

		HomePage h1 = new HomePage();
		h1.verifyHomePage();
		h1.verifyAdvertisment();
	}

	@Test(dependsOnMethods = { "testHomePage" })
	public void testBillionairesPage() {

		Billionaires b1 = new Billionaires();
		b1.verifyLocators();
		b1.verifyAdvertisment();
	}

	@Test(dependsOnMethods = { "testHomePage" ,"testBillionairesPage"})
	public void testAdvisorPage() {

		Advisor a1 = new Advisor();
		a1.verifyLocators();
		a1.validateAdvisorPage();
	}

	@Test(dependsOnMethods = { "testAdvisorPage" })
	public void testSearchPage() {
		Search s1= new Search();
		s1.clickSearchIcon();
		s1.verifyTitle();
		s1.verifyLocators();
		s1.sendData();
	}
	
	@AfterTest
	public void closeTest() {
		/******
		 * To automatically refresh your screenshots go to below Window -->
		 * Preferences --> General --> Workspace --> Refresh using native hooks
		 * or polling
		 * 
		 */
		baseClass.driver.quit();
	}

}
