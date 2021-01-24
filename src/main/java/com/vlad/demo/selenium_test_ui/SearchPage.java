package com.vlad.demo.selenium_test_ui;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class SearchPage extends Page {

	@FindBy(xpath = "//img[contains(@src,'calc.svg')]")
	private WebElement wePopup;

	@FindBy(xpath = "//img[contains(@src,'calc.svg')]/../../../../child::div[2]/button")
	private WebElement wePopupCloseButton;

	@FindBy(xpath = "//*[@id='search-prepack']//input[@name='Цена до']")
	private WebElement wePriceTo;

	@FindBy(xpath = "//*[@id='search-prepack']//input[@name='Цена от']")
	private WebElement wePriceFrom;

	@FindBy(xpath = "//div[@data-apiary-widget-name='@MarketNode/SearchResults']//article[1]//h3/a")
	private WebElement weFirstArticle;

	@FindBy(xpath = "//div[@data-zone-name='snippetList']/following-sibling::*/div")
	private WebElement weGhostDiv;

	public SearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@Step("Задал фильтр по цене")
	public void setPriceTo(String price) {
		wePriceTo.sendKeys(price);
	}

	@Step("Задал фильтр по цене")
	public void setPriceFrom(String price) {
		wePriceFrom.sendKeys(price);
	}

	public void closePopupIfOpen() {
		if (wePopup.isDisplayed()) {
			wePopupCloseButton.click();
		}
	}

	@Step("Получил название товара и цену и перешел в карточку товара")
	public void waitForStaleElement() throws IOException {
		waitForStaleElement(driver, By.xpath("//div[@data-zone-name='snippetList']/following-sibling::*/div"));

	}

	public void clickToTitle() {
		weFirstArticle.click();
	}

	public static void waitForStaleElement(WebDriver driver,By by) {
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.numberOfElementsToBe(by,0));
	}

	@Step("Задал фильтр по производителю")
	public void fillVendor(By[] weByList) {
		for(By by : weByList) {
			clickJS(driver,by);
		}
	}

	public static void clickJS(WebDriver driver,By by) {
		WebElement el = driver.findElement(by);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", el);
	}
}
