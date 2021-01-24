package com.vlad.demo.selenium_test_ui;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMarketPage extends Page{

	public String getWindowHandle() {
		return windowHandle;
	}

	String windowHandle;
	
	@FindBy(xpath = "//img[contains(@src,'gifts.svg')]")
	private WebElement wePopup;

	@FindBy(xpath = "//img[contains(@src,'gifts.svg')]/../../../../child::div[2]/button")
	private WebElement wePopupCloseButton;

	@FindBy(xpath = "/html/body/div[3]/div[3]/noindex/div/div/div[2]/div[1]/div/button")
	private WebElement weCatalog;

	@FindBy(xpath = "//div[@data-zone-name='menu']//button/a[contains(@href,'catalog--elektronika')]")
	private WebElement weElectronics;

	@FindBy(xpath = "//div[@data-zone-name='menu']//a[contains(@href,'catalog--televizory/')]")
	private WebElement weTvSet;

	public MainMarketPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		windowHandle = driver.getWindowHandle();
	}

	public void clickCatalog() {
		weCatalog.click();
	}

	public void closePopupIfOpen() {
		if (wePopup.isDisplayed()) {
			wePopupCloseButton.click();
		}
	}

	@Step("Загружена страница Яндекс.Маркет")
	public void loadMarket() {
		driver.get("https://market.yandex.ru");
	}

	@Step("Наведен курсор на раздел Электроника")
	public void moveToElectronics() {
		Actions builder = new Actions(driver);
		builder.moveToElement(weElectronics).build().perform();
	}

	@Step("Нажата ссылка Телевизоры")
	public void clickTvSet() {
		weTvSet.click();
	}
}
