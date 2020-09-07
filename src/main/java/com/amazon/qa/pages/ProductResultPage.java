package com.amazon.qa.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.Utils.TestUtilAmazon;
import com.amazon.qa.base.TestBaseAmazon;

public class ProductResultPage extends TestBaseAmazon{
	
	@FindBy(xpath = "//div[@class='a-section']//span[@class='rush-component']") WebElement primeOption;
	
	@FindBy(xpath = "//div[@class='a-section a-spacing-medium']//img") List<WebElement> searchResults;
	
	@FindBy(id = "add-to-cart-button") WebElement addToCartButton ;
	
	//Initialize
	public ProductResultPage() { 
		PageFactory.initElements(driver, this);
	}
	
	public void selectPrimeOption() {
		TestUtilAmazon.click(primeOption);
	}
	
	public List<WebElement> getProductsList() {
		return searchResults;
	}
	
	public void selectFirstProduct() {
		TestUtilAmazon.click(searchResults.get(0));
	}
	
	public void addToCart() {
		TestUtilAmazon.click(addToCartButton);
	}
	
}
