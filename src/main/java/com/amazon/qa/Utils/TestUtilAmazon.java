package com.amazon.qa.Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.TestException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.qa.base.TestBaseAmazon;

public class TestUtilAmazon extends TestBaseAmazon {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	private static int timeout = 20;
	static Actions actions;
	
	public static void navigateToURL(String URL) {
		try {
			driver.get(URL);		
		} catch (Exception e) {
			System.out.println("FAILURE: URL did not load: " + URL);
			System.out.println(e.getMessage());
			throw new TestException("URL did not load");
			
		}
	}

	public static void navigateBack() {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			System.out.println("FAILURE: Could not navigate back to previous page.");
			throw new TestException("Could not navigate back to previous page.");
		}
	}

	public static String getPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
		}
	}

	public static String getCurrentURL() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			throw new TestException(String.format("Current URL is: %s", driver.getCurrentUrl()));
		}
	}

	public static void click(WebElement element) {
		waitForElementToBeClickable(element);
		try {
			element.click();
		} catch (Exception e) {
			throw new TestException(String.format("The following element is not clickable: [%s]", element.toString()));
		}
	}

	public static void sendKeys(WebElement element, String value) {
		clearField(element);
		try {
			element.sendKeys(value);
		} catch (Exception e) {
			throw new TestException(
					String.format("Error in sending [%s] to the following element: [%s]", value, element.toString()));
		}
	}

	public static void clearField(WebElement element) {
		try {
			element.clear();
		} catch (Exception e) {
			System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
		}
	}
	
	public static void waitForElementToBeVisible(WebElement element) {
		try {
			wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			throw new NoSuchElementException(String.format("The following element was not visible: %s", element));
		}
	}

	public static void waitForElementToBeClickable(WebElement element) {
		try {
			wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			throw new TestException("The following element is not clickable: " + element);
		}
	}

	public static void sleep(final long millis) {
		System.out.println((String.format("sleeping %d ms", millis)));
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void scrollToThenClick(WebElement element) {
        actions = new Actions(driver);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            actions.moveToElement(element).perform();
            actions.click(element).perform();
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", element.toString()));
        }
    }
	
	public static void getscreenshot(String fileName) throws Exception {
		TakesScreenshot takescreenshot= (TakesScreenshot)driver;
		File srcFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(".\\Screenshots\\"+fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
		

}
