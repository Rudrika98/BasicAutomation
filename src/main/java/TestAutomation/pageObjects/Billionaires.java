package TestAutomation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import TestAutomation.helpers.PropertiesParser;
import TestAutomation.helpers.Utilities;
import TestAutomation.helpers.baseClass;

public class Billionaires {

	Utilities u1 = new Utilities();

	public Billionaires() {
		WebDriverWait wait = new WebDriverWait(baseClass.driver, 20);
		WebElement ele = baseClass.driver.findElement(By.linkText("Billionaires"));
		try {

			if (ele.isDisplayed()) {
				ele.click();
			} else {
				wait.until(ExpectedConditions.visibilityOf(ele));
			}

			String b_title = baseClass.driver.getTitle();
			Assert.assertEquals("Worlds Billionaires", b_title, "Title doesn't match");
			u1.getScreenShot(baseClass.screenShotPath, "Billionaires_Web_Page");
		} catch (NoSuchElementException e) {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}
	}

	public void verifyLocators() {

		try {
			WebElement eleTag = baseClass.driver.findElement(By.tagName("header"));
			if (eleTag.isDisplayed()) {
				System.out.println("First Locator is verified with:" + eleTag.getTagName());
			}

			WebElement eleXpath = baseClass.driver.findElement(By.xpath("//div[@class='fs-content']"));
			if (eleXpath.isDisplayed()) {
				System.out.println("Second Locator is verified with:" + eleXpath.getTagName());
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void verifyAdvertisment() {
		try {
			PropertiesParser pp = new PropertiesParser();
			pp.readXpaths();
			WebElement verifyadvertisement = baseClass.driver.findElement(By.xpath(baseClass.verifyBillionairesad));
			if (verifyadvertisement.isDisplayed()) {
				String position_ad = verifyadvertisement.getAttribute("position");
				Assert.assertEquals(position_ad, "top", "Position match");
				String pathToScreenshot = baseClass.screenShotPath + "BillionairesPageAdvertisement" + ".jpg";
				u1.getSmallerScreenshot(verifyadvertisement, pathToScreenshot);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
