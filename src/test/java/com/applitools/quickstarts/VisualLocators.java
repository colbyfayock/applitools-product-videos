package com.applitools.quickstarts;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.locators.VisualLocator;
import com.applitools.eyes.Region;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


public class VisualLocators {

	@Test
  public void test() {
		WebDriver driver = new ChromeDriver(new ChromeOptions());
		EyesRunner runner = new ClassicRunner();
		Eyes eyes = new Eyes(runner);
		Configuration config = new Configuration();
	
		config.setBatch(new BatchInfo("Demo Batch - Selenium for Java - Visual Locators"));
		eyes.setConfiguration(config);
		
		try {
			eyes.open(driver, "Applitools.com - Selenium for Java - Visual Locators", "Smoke Test - Selenium for Java - Visual Locators", new RectangleSize(800, 600));

			Map<String, List<Region>> locators = eyes.locate(VisualLocator.name("Twitter Logo"));

			List<Region> locatorRegions = locators.get("Twitter Logo");
			
			Region regionTwitterLogo = locatorRegions.get(0);

			int clickLocationX = regionTwitterLogo.getLeft() + regionTwitterLogo.getWidth() / 2;
			int clickLocationY = regionTwitterLogo.getTop() + regionTwitterLogo.getHeight() / 2;

			driver.get("https://demo.applitools.com/login-interactive.html");

			eyes.checkWindow("Login Window");

			new Actions(driver)
				.moveByOffset(clickLocationX, clickLocationY)
				.build()
				.perform();

			eyes.checkWindow("Twitter");

			eyes.closeAsync();
		} finally {
			driver.quit();
		}
	}

}
