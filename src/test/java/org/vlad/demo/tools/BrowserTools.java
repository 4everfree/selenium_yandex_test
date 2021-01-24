package org.vlad.demo.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Path.of;

public class BrowserTools {

	public static void clearScreenshots() throws IOException {
		Path screenshots = of("screenshots");

		if(Files.exists(screenshots)) {
			File file = new File(screenshots.toAbsolutePath().toString());
			String[] files = file.list();

			for(String scrpath : files) {
				Files.delete(of(screenshots.toAbsolutePath().toString(),scrpath));
			}
		}
	}

	public static void waitElementVisibility(WebDriver driver,By by) {
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.numberOfElementsToBe(by,1));
	}
}
