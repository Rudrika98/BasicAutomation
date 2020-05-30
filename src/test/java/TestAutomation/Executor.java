package TestAutomation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestAutomation.helpers.Utilities;

public class Executor {
	WebDriver driver = null;
	Utilities u1= new Utilities();

	@BeforeTest
	public void loadConfigFile() {
		// This method will load properties file and base Class
	}

	@Test
	public void testHomePage() {

	}

	@Test
	public void testBillionairesPage() {

	}

	@Test
	public void testAdvisorPage() {

	}

	@Test
	public void testSearchPage() {

	}

	@Test
	public void testLogIn()

	{

		/**** Use Key word Driven approach in this framework ****/
		// ctrl+Alt+G
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
			driver = new ChromeDriver(chromeOptions);
			driver.get("https://www.forbes.com/");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			/*
			 * List<WebElement> list =
			 * driver.findElements(By.tagName("iframe")); System.out.println(
			 * "get size of list" + list.size()); for (int i = 0; i <
			 * list.size(); i++) {
			 * 
			 * System.out.println(list.get(i)); } if (list.isEmpty()) {
			 * System.out.println("Entered into if loop"); WebDriverWait wait =
			 * new WebDriverWait(driver, 30); WebElement element =
			 * driver.findElement(By.className(
			 * "main-content main-content--universal-header"));
			 * wait.until(ExpectedConditions.visibilityOf(element));
			 * 
			 * }
			 **/
			String expectedTitle = "Forbes";
			String title = driver.getTitle();
			Assert.assertEquals(expectedTitle, title, "Title doesn't match");
			System.out.println(driver.getTitle());

			List<WebElement> verifyTag = driver.findElements(By.tagName("iframe"));
			if (!verifyTag.isEmpty()) {
				// WebDriverWait wait = new WebDriverWait(driver, 30);
				// wait.until(ExpectedConditions.visibilityOf(verifyTag));
				Assert.assertTrue(!verifyTag.isEmpty());
			}

			WebElement verifyClass = driver.findElement(By.linkText("Leadership"));
			if (!verifyClass.isDisplayed()) {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOf(verifyClass));
				Assert.assertTrue(verifyClass.isDisplayed());
			}

			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String pathToStore = "E://Eclipse_Workspace//BasicAutomation//src//test//resources//screenshots//"
					+ "ForbesWebpage" + ".png";
			File DestFile = new File(pathToStore);

			try {
				FileUtils.copyFile(file, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/***********************
			 * End of First Test Case
			 *******************************************/

			WebElement verifyadvertisement = driver.findElement(By.xpath("//fbs-ad[@position='top']"));
			String position = verifyadvertisement.getAttribute("position");
			System.out.println(position);
			String pathToScreenshot = "E://Eclipse_Workspace//BasicAutomation//src//test//resources//screenshots//"
					+ "fullimage" + ".jpg";

			try {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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

			/*****************************
			 * End of Second Test Case
			 *********************************************/
			WebElement ele = driver.findElement(By.linkText("Billionaires"));
			if (ele.isDisplayed())
				ele.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String b_title = driver.getTitle();
			System.out.println("b_title" + b_title);
			Assert.assertEquals("Worlds Billionaires", b_title, "Title doesn't match");

			try {
				WebElement eleTag = driver.findElement(By.tagName("header"));
				if (eleTag.isDisplayed()) {
					System.out.println("Second Element is verified:" + eleTag);
				}

				WebElement eleXpath = driver.findElement(By.xpath("//div[@class='fs-content']"));
				if (eleXpath.isDisplayed()) {
					System.out.println("First Element is verified:" + eleXpath);
				}

			} catch (Exception e) {
				System.out.println(e);
			}

			File file2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String pathToStoreN = "E://Eclipse_Workspace//BasicAutomation//src//test//resources//screenshots//"
					+ "BillionariesWebpage" + ".png";
			File DestFile2 = new File(pathToStoreN);

			try {
				FileUtils.copyFile(file2, DestFile2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/***********************************
			 * End of Third Test Case
			 ************************************************/
			WebElement verifyad = driver.findElement(By.xpath(
					"//div[@class='fbs-ad--top-wrapper fbs-ad--top-wrapper--desktop fbs-ad--top-wrapper-with-label']//fbs-ad"));
			if (verifyad.getAttribute("position").equalsIgnoreCase("top")) {
				String position_ad = verifyad.getAttribute("position");
				Assert.assertEquals(position_ad, "top", "Position match");
				String pathScreenshot = "E://Eclipse_Workspace//BasicAutomation//src//test//resources//screenshots//"
						+ "advertisement_image" + ".jpg";
				
				u1.getSmallerScreenshot(verifyad, pathScreenshot);

				/**********************************
				 * End of FOurth Test Case
				 ********************************/
				WebElement element = driver.findElement(By.linkText("Advisor"));
				if (element.isDisplayed())
					element.click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.navigate().refresh();
				String a_title = driver.getTitle();
				System.out.println("a_title" + a_title);
				Assert.assertEquals("Forbes Advisor", a_title, "Title doesn't match");

				try {
					WebElement eleTag = driver.findElement(By.id("top-level-menu-item-2"));
					if (eleTag.isDisplayed()) {
						System.out.println("First Element is verified:" + eleTag);
					}

					WebElement eleXpath = driver.findElement(By.xpath("//h2[@class='m-md-b-16 m-b-12']"));
					if (eleXpath.isDisplayed()) {
						System.out.println("Second Element is verified:" + eleXpath);
					}

					/**** Move down screen ***/
				} catch (Exception e) {
					System.out.println(e);
				}

				File file3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String pathToStoreA = "E://Eclipse_Workspace//BasicAutomation//src//test//resources//screenshots//"
						+ "AdvisorWebpage" + ".png";
				File DestFile3 = new File(pathToStoreA);

				try {
					FileUtils.copyFile(file3, DestFile3);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/*******************************
				 * End of Fifth Test Case
				 **************************************/

				WebElement checkLabel = driver.findElement(By.xpath("//span[contains(text(),'Advisor Now')]"));

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", checkLabel);

				if (checkLabel.isDisplayed()) {
					String color = checkLabel.getCssValue("color");
					System.out.println(color);
					String color_hex[];
					color_hex = color.replace("rgba(", "").split(",");
					// #395bb6
					String actual_hex = String.format("#%02x%02x%02x", Integer.parseInt(color_hex[0].trim()),
							Integer.parseInt(color_hex[1].trim()), Integer.parseInt(color_hex[2].trim()));
					System.out.println("actual_hex" + actual_hex);
					Assert.assertEquals("#395bb6", actual_hex);

				}

				/*****************************
				 * End Till Ninth Test Case
				 ************************/

				/// div[@class='header__search']//span//*[local-name()='svg']

				try {
/***************************************Search Page Started*****************************************************************/
					// String attr =
					// driver.switchTo().activeElement().getAttribute("title");
					//// *[name()='svg']/*[name()='circle']
//					WebElement mapObject = driver.findElement(By.xpath("//*[name()='svg']/*[name()='circle']"));

					WebElement newEle = driver.findElement(
							By.xpath("//*[local-name()='svg']//*[name()='circle' and contains(@fill,'none')]"));

					Actions builder = new Actions(driver);

					builder.moveToElement(newEle).click(newEle).perform();

					System.out.println("Current Title is:" + driver.getTitle());

					WebElement eleTag = driver.findElement(By.tagName("iframe"));
					if (eleTag.isDisplayed()) {
						System.out.println("First Element is verified:" + eleTag);
					}

					WebElement eleXpath = driver.findElement(By.xpath("//div[@class='fs-content']"));
					if (eleXpath.isDisplayed()) {
						System.out.println("First Element is verified:" + eleXpath);
					}

					WebElement searchEle = driver.findElement((By.xpath("//input[@placeholder='Search']")));

					if (searchEle.isDisplayed()) {
						searchEle.sendKeys("Apple");
						Thread.sleep(8000);
					}

					else {
						WebDriverWait w = new WebDriverWait(driver, 10);

						WebElement searchButton = w.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
						searchButton.sendKeys("apple");

					}
				} catch (NoSuchElementException e) {
					System.out.println(e);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (WebDriverException e) {
			System.out.println(e);

		}
	}

	@AfterTest
	public void closeTest() {
		/******
		 * To automatically refresh your screenshots go to below Window -->
		 * Preferences --> General --> Workspace --> Refresh using native hooks
		 * or polling
		 * 
		 */
		driver.quit();
	}
}
