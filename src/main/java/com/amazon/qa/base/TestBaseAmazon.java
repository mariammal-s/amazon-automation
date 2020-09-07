package com.amazon.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.qa.Utils.TestUtilAmazon;

public class TestBaseAmazon {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;

	public TestBaseAmazon() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("src\\main\\java\\com\\amazon\\qa\\config\\config.properties");

			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void setUp() {
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Chrome test starting ...");
			System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver();
						
		} else if (browser.equalsIgnoreCase("firefox")){
			System.out.println("Firefox starting");
			System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			
		}else {
			try {
				throw new Exception("Browser is not supported");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtilAmazon.PAGE_LOAD_TIMEOUT , TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtilAmazon.IMPLICIT_WAIT , TimeUnit.SECONDS);

	}

	/*
	 * ChromeDriver for window and mac
	 */
	public static String getChromeDriverPath() {
		String OS = System.getProperty("os.name").toLowerCase();

		if (OS.contains("window")) {
			return "drivers/chromedriver_win.exe";
		} else {
			return "drivers/chromedriver_mac.exe";
		}
	}

}
