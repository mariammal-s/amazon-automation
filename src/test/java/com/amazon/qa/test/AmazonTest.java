package com.amazon.qa.test;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.qa.Utils.TestUtilAmazon;
import com.amazon.qa.base.TestBaseAmazon;
import com.amazon.qa.pages.CartPage;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.ProductResultPage;

public class AmazonTest extends TestBaseAmazon {

	HomePage homepage;
	ProductResultPage productresultpage;
	CartPage cartpage;

	public AmazonTest() {
		super();
	}

	@BeforeTest
	public void initialSetUp() {
		setUp();
		homepage = new HomePage();
	}

	@Test(priority = 1)
	public void homePageNavigation() throws Exception {
		String url = prop.getProperty("url");
		homepage.navigateToHomePage(url);
		String title = TestUtilAmazon.getPageTitle();
		Assert.assertEquals(title,
			"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
		TestUtilAmazon.getscreenshot("HomePage");
		String Product = "Toy Car";
		productresultpage = homepage.searchItem(Product);
		TestUtilAmazon.sleep(1000);
		TestUtilAmazon.getscreenshot("ResultPage");
		productresultpage.selectPrimeOption();
	}
	

	@Test(priority = 2)
	public void addProductToCart() throws Exception {
		List<WebElement> searchResults = productresultpage.getProductsList();
		TestUtilAmazon.sleep(3000);
		for (WebElement result : searchResults) {
			System.out.println("* " + result.getAttribute("alt"));
		}
		String parent = driver.getWindowHandle();
		productresultpage.selectFirstProduct();
		TestUtilAmazon.sleep(3000);
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		try {
			while (it.hasNext()) {
				String child = it.next();
				if (!parent.equals(child)) {
					driver.switchTo().window(child);
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		TestUtilAmazon.getscreenshot("ProductPage");
		productresultpage.addToCart();
		driver.close();
		driver.switchTo().window(parent);
		driver.navigate().refresh();
		cartpage = homepage.selectShoppingCartIcon();
		cartpage.verifyOnShoppingCartReviewPage();
		TestUtilAmazon.getscreenshot("CartWithProduct");
		cartpage.emptyCart();
		TestUtilAmazon.sleep(3000);
		if(cartpage.isCartEmpty()) {
			System.out.println("Cart is empty");
			TestUtilAmazon.getscreenshot("EmptyCart");
		}
	}
	
	@Test (priority = 3)
	public void productFromMenu() throws Exception {
		homepage.selectMenuOption();
		homepage.selectEchoFromMenu();
		TestUtilAmazon.getscreenshot("MenuOption");
		homepage.chooseEchoDot();
		productresultpage.addToCart();
		driver.navigate().refresh();
		cartpage = homepage.selectShoppingCartIcon();
		cartpage.verifyOnShoppingCartReviewPage();
		cartpage.emptyCart();
		if(cartpage.isCartEmpty()) {
			System.out.println("Cart is empty");
		}
	}
	
	@Test(priority = 5)
	public void amazonLogoTest() throws Exception {
		homepage.clickAmazonLogo();
		TestUtilAmazon.sleep(2000);
		System.out.println("Back to Homeapage again");
		TestUtilAmazon.getscreenshot("HomePage_from_logo");
		
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
