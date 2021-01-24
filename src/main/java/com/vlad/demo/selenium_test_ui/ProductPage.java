package com.vlad.demo.selenium_test_ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class ProductPage extends Page {
	
	 @FindBy(xpath="//div[@data-apiary-widget-name='@MarketNode/RecommendedOffers']/div/div/div/div/div/span/span")
	 private WebElement weArticlePrice;

	public WebElement getWeArticlePrice() {
		return weArticlePrice;
	}

	public WebElement getWeArticleVendor() {
		return weArticleVendor;
	}

	@FindBy(css="div > h1")
	 private WebElement weArticleVendor;

	 public ProductPage(WebDriver driver, MainMarketPage marketPage) {
	 	super(driver);
	 	PageFactory.initElements(driver, this);
	 }

	public void switchToProductPage(MainMarketPage marketPage) {
		Set<String> window = driver.getWindowHandles();
		window.remove(marketPage.getWindowHandle());
		driver.switchTo().window(window.stream().findFirst().get());
	}
}
