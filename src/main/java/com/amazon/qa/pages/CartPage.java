package com.amazon.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.TestException;

import com.amazon.qa.Utils.TestUtilAmazon;
import com.amazon.qa.base.TestBaseAmazon;

public class CartPage extends TestBaseAmazon{
	
	@FindBy(xpath = "//input[@value='Delete']") List<WebElement> deleteButton;
	
	@FindBy(xpath = "//h2[contains(text(),'Your Amazon Basket is empty')]") WebElement cartEmptyMessage ;
	
	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void verifyOnShoppingCartReviewPage(){
        String url = TestUtilAmazon.getCurrentURL();
        System.out.println("SHOPPING_CART_PAGE: Verifying that we are on SHOPPING_CART_REVIEW_PAGE.");
        if (!url.contains("cart")){
            throw new TestException("ERROR: Not on SHOPPING_CART_PAGE! URL: " + url);
        }
    }
	
	public void emptyCart() {
		System.out.println(deleteButton);
		for (WebElement delete : deleteButton) {
			TestUtilAmazon.click(delete);
			driver.navigate().refresh();
		}
	}

	public boolean isCartEmpty() {
		return cartEmptyMessage.isDisplayed();
	}
	
}
