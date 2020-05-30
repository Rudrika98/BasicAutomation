package TestAutomation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestAutomation.helpers.baseClass;

public class Search implements searchPage {

	public void clickSearchIcon() {
		try {
			// WebElement mapObject =
			// baseClass.driver.findElement(By.xpath("//*[name()='svg']/*[name()='circle']"));
			baseClass.driver.navigate().refresh();
			Thread.sleep(2000);
			WebElement newEle = baseClass.driver
					.findElement(By.xpath("//*[local-name()='svg']//*[name()='circle' and contains(@fill,'none')]"));

			WebDriverWait wait = new WebDriverWait(baseClass.driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[local-name()='svg']//*[name()='circle' and contains(@fill,'none')]")));
			Actions builder = new Actions(baseClass.driver);
			builder.moveToElement(newEle).click(newEle).perform();

		} catch (Exception e) {
			System.out.println("Rudrika:" + e);
		}

	}

	public void verifyTitle() {
		System.out.println("Current Title is:" + baseClass.driver.getTitle());

	}

	public void verifyLocators() {
		// WebElement eleTag =
		// baseClass.driver.findElement(By.id("advertiser-disclosure-block"));

		WebElement eleTag = baseClass.driver.findElement(By.className("search-modal__close"));

		if (eleTag.isDisplayed()) {
			System.out.println("First Element is verified:" + eleTag.getTagName());
		}

		WebElement eleXpath = baseClass.driver.findElement(By.xpath("//div[@class='site-wrapper sticky']"));
		if (eleXpath.isDisplayed()) {
			System.out.println("Second Element is verified:" + eleXpath.getTagName());
		}

	}

	public void sendData() {

		try {
			WebElement searchEle = baseClass.driver.findElement((By.xpath("//input[@placeholder='Search']")));

			if (searchEle.isDisplayed()) {
				searchEle.sendKeys("Apple");
				Thread.sleep(3000);
				System.out.println(searchEle.getText());
				if (searchEle.getText().equalsIgnoreCase("apple")) {
					Actions actions = new Actions(baseClass.driver);
					actions.sendKeys(Keys.ENTER).build().perform();
				}

			}

			else {
				WebDriverWait w = new WebDriverWait(baseClass.driver, 10);

				WebElement searchButton = w.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
				searchButton.sendKeys("apple");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
