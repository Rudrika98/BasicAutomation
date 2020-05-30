package TestAutomation.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParser {

	Properties properties = new Properties();
	public void readProperty() {
		
		try {
			properties.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\config\\project-path.properties"));
			baseClass.URL = properties.getProperty("url");
			baseClass.screenShotPath = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\";
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void readXpaths() {
		try {
			properties.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\config\\xpath.properties"));
			baseClass.verifyHomePageAdvertisementXpath= properties.getProperty("verifyHomePageAdvertisement");
			baseClass.verifyBillionairesad= properties.getProperty("verifyBillionairesAd");
			baseClass.AdvisorXpath= properties.getProperty("AdvisorXpath");
			baseClass.checkAdvisorLabel= properties.getProperty("checkAdvisorLabel");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
