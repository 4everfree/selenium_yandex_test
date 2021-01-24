package org.vlad.demo.tests;

import com.vlad.demo.selenium_test_ui.MainMarketPage;
import com.vlad.demo.selenium_test_ui.ProductPage;
import com.vlad.demo.selenium_test_ui.SearchPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.vlad.demo.base.BaseTest;

import java.io.IOException;

@Epic("Тестирование Яндекс.Маркета")
@Story("Негативная проверка")
public class SeleniumPriceTest extends BaseTest {

		MainMarketPage mainPage;
		SearchPage searchPage;
		ProductPage productPage;
	SoftAssertions softAssertions;

		@BeforeMethod
		private void init() throws IOException {
 			mainPage = new MainMarketPage(driver);
 			searchPage = new SearchPage(driver);
 			productPage = new ProductPage(driver,mainPage);
			softAssertions = new SoftAssertions();

		}

	@Test(description="Тест с некорректной суммой и корректным производителем")
	    public void main() throws IOException, InterruptedException {
	        step1();
	        step2();
	        step3();
	        step4();
			softAssertions.assertAll();
	    }


		@Step("Зашел на market.yandex.ru")
		public void step1() throws IOException {
			mainPage.loadMarket();
			mainPage.closePopupIfOpen();
			takeScreenshot(driver);
		}

	@Step("Выбрал раздел Электроника -> Телевизоры")
		public void step2() throws IOException {

	    	mainPage.clickCatalog();
	    	mainPage.moveToElectronics();
	    	mainPage.clickTvSet();
			takeScreenshot(driver);
		}

	    @Step("Задал параметры поиска")
		public void step3() throws IOException {

			searchPage.closePopupIfOpen();

			By samsung = By.xpath("//div[@data-zone-name='search-filters-aside']//input[@type='checkbox' and @name='Производитель Samsung']");
			By LG = By.xpath("//div[@data-zone-name='search-filters-aside']//input[@type='checkbox' and @name='Производитель LG']");
			By[] weBy = {samsung,LG};
			searchPage.fillVendor(weBy);

			String price = "40000";
			searchPage.setPriceFrom(price);
	    	searchPage.waitForStaleElement();
			takeScreenshot(driver);
			searchPage.clickToTitle();
		}

	    @Step("Проверил название и цену")
	    private void step4() throws IOException {
			productPage.switchToProductPage(mainPage);
			takeScreenshot(driver);

			if(!productPage.getWeArticleVendor().getText().contains("LG")&&!productPage.getWeArticleVendor().getText().contains("Samsung")) softAssertions.fail("Производитель не Samsung/LG");
			softAssertions.assertThat(Integer.parseInt(productPage.getWeArticlePrice().getText().replace(" ",""))).isLessThan(20000);
		}
}
