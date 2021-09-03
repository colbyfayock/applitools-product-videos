package com.applitools.quickstarts;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

public class FluentAPI {

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

		config.setBatch(new BatchInfo("Demo Batch - Selenium for Java - Fluent API"));
		eyes.setConfiguration(config);
	}

	@Test
  public void test() {
		eyes.open(driver, "Demo App - Selenium for Java - Fluent API", "Smoke Test - Selenium for Java - Fluent API", new RectangleSize(800, 600));

		driver.get("https://demo.applitools.com");

		eyes.check("Login Window", Target.window().fully());

		driver.findElement(By.id("log-in")).click();

		eyes.check(Target.window().fully().withName("App Window"));
		eyes.check("Transactions Table", Target.region(By.className("table")));
		eyes.check("Transactions Table without Header", Target.region(By.className("table")).ignore(By.tagName("thead")));

		eyes.closeAsync();
	}

	@AfterAll
	public static void cleanUp(){
		eyes.abortAsync();
		driver.quit();
	}
}
