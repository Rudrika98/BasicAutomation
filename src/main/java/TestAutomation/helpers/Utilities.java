package TestAutomation.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utilities {

	/****
	 * This class is created for all generic functions in this script
	 * 
	 * 
	 */
	public void openBrowser() {

		DesiredCapabilities capabilities = null;

		System.setProperty("webdriver.chrome.driver",
				"src\\test\\resources\\drivers\\" + File.separator + "chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("start-fullscreen");
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		// capabilities.setCapability("version", browserVersion);
		chromeOptions.merge(capabilities);

		try {
			baseClass.driver = new ChromeDriver(chromeOptions);
			baseClass.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (WebDriverException e) {
			System.out.println("[Browser.openBrowser] WebDriverException in first time, so trying again...");
			baseClass.driver = new ChromeDriver(chromeOptions);
		}

	}

	public void checkAssertMessage(String actual, String expected, String Message) {

		Assert.assertEquals(expected, actual, "Title doesn't match");
	}

	public List<WebElement> getListItems(String locator, String path) {

		if (locator.equalsIgnoreCase("tagName")) {
			List<WebElement> listTag = baseClass.driver.findElements(By.tagName(path));
			return listTag;
		} else if (locator.equalsIgnoreCase("ID")) {
			List<WebElement> listTag = baseClass.driver.findElements(By.id(path));
			return listTag;

		}
		return null;
	}

	public void sendData(WebElement element, String text) {
		element.click();
		if (element.isDisplayed()) {
			element.sendKeys(text);
		}

	}

	public void getScreenShot(String pathToStore,String screenshotName) {
		String newPath=pathToStore + screenshotName +".png";
		TakesScreenshot scrShot = ((TakesScreenshot) baseClass.driver);
		File src = scrShot.getScreenshotAs(OutputType.FILE);
		try {
			File DestFile = new File(newPath);
			FileUtils.copyFile(src, DestFile);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static String getTitle() {
		return baseClass.driver.getTitle();
		// return driver.getCurrentUrl();
	}

	public void getSmallerScreenshot(WebElement verifyadvertisement, String pathToScreenshot) {

		try {
			File screenshot = ((TakesScreenshot) baseClass.driver).getScreenshotAs(OutputType.FILE);
			BufferedImage fullImg = ImageIO.read(screenshot);

			// Get the location of element on the page
			Point point = verifyadvertisement.getLocation();

			// Get width and height of the element
			int eleWidth = verifyadvertisement.getSize().getWidth();
			int eleHeight = verifyadvertisement.getSize().getHeight();

			// Crop the entire page screenshot to get only element
			// screenshot
			BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, "png", screenshot);

			// Copy the element screenshot to disk
			File screenshotLocation = new File(pathToScreenshot);
			FileUtils.copyFile(screenshot, screenshotLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public WebElement findLocatorLinkText(String linkText) {
		return baseClass.driver.findElement(By.linkText(linkText));

	}

	public WebElement findLocatorXpath(String xpath) {
		return baseClass.driver.findElement(By.xpath(xpath));

	}

	/**
	 * public WebElement findLocatorLinkText(String linkText) { return
	 * baseClass.driver.findElement(By.linkText(linkText));
	 * 
	 * }
	 * 
	 * public WebElement findLocatorLinkText(String linkText) { return
	 * baseClass.driver.findElement(By.linkText(linkText));
	 * 
	 * }
	 * 
	 * public WebElement findLocatorLinkText(String linkText) { return
	 * baseClass.driver.findElement(By.linkText(linkText));
	 * 
	 * }
	 **/

}
