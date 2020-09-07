package com.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.amazon.qa.Utils.TestUtilAmazon;
import com.amazon.qa.base.TestBaseAmazon;

public class HomePage extends TestBaseAmazon{
	
	//PageFactory
	@FindBy(className = "nav-logo-link") WebElement amazonLogo;
	
	@FindBy(id = "twotabsearchtextbox") WebElement searchBox;
	
	@FindBy(xpath = "//div[@class='nav-search-submit nav-sprite']//input[@class='nav-input']") WebElement searchButton;
	
	@FindBy(id = "nav-hamburger-menu") WebElement menuList;
	
	@FindBy(linkText = "Echo & Alexa") WebElement echoMenu;
	
	@FindBy(xpath = "//a[contains(text(),'Echo Dot')]") WebElement echoDotOption;
	
	@FindBy(id = "nav-cart") WebElement cartIcon;
	
	
	//Initializing Elements
	public HomePage() { 
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToHomePage(String url) {
		System.out.println("Navigating to Amazon.in: " + url);
        TestUtilAmazon.navigateToURL(url);
    }
	
	public CartPage selectShoppingCartIcon(){
		TestUtilAmazon.click(cartIcon);
		return new CartPage();
    }
	
	public ProductResultPage searchItem(String product) {
		TestUtilAmazon.sendKeys(searchBox, product);
		TestUtilAmazon.click(searchButton);
		return new ProductResultPage();	
		}
	
	
	public void selectMenuOption() {
		TestUtilAmazon.click(menuList);
	}
	
	public void selectEchoFromMenu() {
		TestUtilAmazon.click(echoMenu);
	}
	
	public void chooseEchoDot() {
		TestUtilAmazon.click(echoDotOption);
	}
	
	public void clickAmazonLogo() {
		TestUtilAmazon.click(amazonLogo);
	}
	
}
