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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.util.List;
import java.util.Map;

public class VisualLocators {

	static WebDriver driver;
	static EyesRunner runner;
	static Eyes eyes;
	static Configuration config;

	@BeforeAll
	public static void init(){
		driver = new ChromeDriver(new ChromeOptions());
		runner = new ClassicRunner();
		eyes = new Eyes(runner);
		config = eyes.getConfiguration();

		config.setBatch(new BatchInfo("Demo Batch - Selenium for Java - Visual Locators"));
		eyes.setConfiguration(config);
	}

	@Test
  public void test() {
		driver.get("https://demo.applitools.com/login-interactive.html");
		
		eyes.open(driver, "Applitools.com - Selenium for Java - Visual Locators", "Smoke Test - Selenium for Java - Visual Locators", new RectangleSize(800, 600));

		Map<String, List<Region>> locators = eyes.locate(VisualLocator.name("linkedin-logo"));

		List<Region> locatorRegions = locators.get("linkedin-logo");
		
		Region regionLinkedInLogo = locatorRegions.get(0);

		int clickLocationX = regionLinkedInLogo.getLeft() + regionLinkedInLogo.getWidth() / 2;
		int clickLocationY = regionLinkedInLogo.getTop() + regionLinkedInLogo.getHeight() / 2;

		eyes.check("Login Window", Target.window().fully());

		new Actions(driver)
			.moveByOffset(clickLocationX, clickLocationY)
			.click()
			.perform();

		eyes.check("LinkedIn", Target.window().fully());

		eyes.closeAsync();
	}

	@AfterAll
	public static void cleanUp(){
		eyes.abortAsync();
		driver.quit();
	}
}
