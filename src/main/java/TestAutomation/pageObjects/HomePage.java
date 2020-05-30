package TestAutomation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import TestAutomation.helpers.PropertiesParser;
import TestAutomation.helpers.Utilities;
import TestAutomation.helpers.baseClass;

public class HomePage {

	Utilities u1 = new Utilities();

	/***Take the value from parameters****/
	public HomePage() {

		try {
			baseClass.driver.get(baseClass.URL);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void verifyHomePage() {
		try {
			String actualTitle = u1.getTitle();
			String expectedTitle = "Forbes";
			u1.checkAssertMessage(expectedTitle, actualTitle, "Title doesn't match");

			System.out.println("Title of Home Page is:" + actualTitle);

			List<WebElement> verifyTag = u1.getListItems("tagName", "iframe");
			if (!verifyTag.isEmpty()) {
				Assert.assertTrue(!verifyTag.isEmpty());
			}

			WebElement verifyClass = u1.findLocatorLinkText("Leadership");
			if (!verifyClass.isDisplayed()) {
				WebDriverWait wait = new WebDriverWait(baseClass.driver, 30);
				wait.until(ExpectedConditions.visibilityOf(verifyClass));
				Assert.assertTrue(verifyClass.isDisplayed());
			}

			u1.getScreenShot(baseClass.screenShotPath, "Home_Page");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void verifyAdvertisment() {

		PropertiesParser pp = new PropertiesParser();
		pp.readXpaths();
		WebElement verifyadvertisement = baseClass.driver
				.findElement(By.xpath(baseClass.verifyHomePageAdvertisementXpath));
		if (verifyadvertisement.isDisplayed()) {
			String position = verifyadvertisement.getAttribute("position");
			System.out.println(position);
			String pathToScreenshot = baseClass.screenShotPath + "homePageAdvertisement" + ".jpg";
			u1.getSmallerScreenshot(verifyadvertisement, pathToScreenshot);
		}

	}

}
