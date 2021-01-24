package org.vlad.demo.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.report.AllureReportBuilderException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

	protected WebDriver driver;
	
    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        initializeDriver();
        SoftAssert softAssert = new SoftAssert();
    }

    private void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized","--start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() throws AllureReportBuilderException {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("cmd.exe");
        sb.append(" /c ");
        sb.append(System.getProperty("user.dir"));
        sb.append("\\.allure\\allure-2.8.1\\bin\\allure.bat ");
        sb.append("serve ");
        sb.append(System.getProperty("user.dir")+"\\"+"allure-results");

        Process p = Runtime.getRuntime().exec(sb.toString());
    }

    @Step("Снял скриншот")
    @Attachment(value = "Скриншот страницы",type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) throws IOException {

//        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
//
//        String nameTest = stack[3].getFileName().substring(0,stack[3].getFileName().indexOf('.'));
//        String step = stack[2].getMethodName();
//        long time = System.currentTimeMillis();
//
//        byte[] scrFile = ;
//        String timePictureEnd = nameTest+"_"+step+"_"+time+".png";
//        File newFile = new File("screenshots\\"+timePictureEnd);
//        Files. moveFile(scrFile, newFile);
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
