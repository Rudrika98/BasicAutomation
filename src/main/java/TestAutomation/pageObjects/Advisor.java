package TestAutomation.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import TestAutomation.helpers.baseClass;

public class Advisor {

	public Advisor() {
		System.out.println("Navigating To Advisor webpage");
		WebElement element = baseClass.driver.findElement(By.linkText("Advisor"));
		if (element.isDisplayed())
			element.click();
		baseClass.driver.navigate().refresh();
		String a_title = baseClass.driver.getTitle();
		System.out.println("a_title" + a_title);
		Assert.assertEquals("Forbes Advisor", a_title, "Title doesn't match");
	}

	public void verifyLocators() {
		try {
			WebElement eleTag = baseClass.driver.findElement(By.id("top-level-menu-item-2"));
			if (eleTag.isDisplayed()) {
				System.out.println("First Element is verified with :" + eleTag.getTagName());
			}

			WebElement eleXpath = baseClass.driver.findElement(By.xpath(baseClass.AdvisorXpath));
			if (eleXpath.isDisplayed()) {
				System.out.println("Second Element is verified:" + eleXpath.getTagName());
			}
			
			/**** Move down screen ***/
			/**Actions actions = new Actions(baseClass.driver);
			actions.sendKeys(Keys.PAGE_DOWN).build().perform();
			Thread.sleep(2000);**/

			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void validateAdvisorPage() {
		
		try{
			System.out.println("Validating Advisor Label .. ");
			WebElement checkLabel = baseClass.driver.findElement(By.xpath(baseClass.checkAdvisorLabel));

			JavascriptExecutor executor = (JavascriptExecutor) baseClass.driver;
			executor.executeScript("arguments[0].click();", checkLabel);

			if (checkLabel.isDisplayed()) {
				System.out.println("Validating Color of Label ..");
				String color = checkLabel.getCssValue("color");
				System.out.println("Current Color of text is"+color);
				String color_hex[];
				color_hex = color.replace("rgba(", "").split(",");
				// #395bb6 is RGBA color for Blue
				String actual_hex = String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()),
						Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));
				System.out.println("actual_hex" + actual_hex);
				Assert.assertEquals("#395bb6", actual_hex);

			}

			
		}catch(Exception e)
		{
			System.out.println(e);
		}

	}
}