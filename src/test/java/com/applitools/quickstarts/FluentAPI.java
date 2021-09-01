package com.applitools.quickstarts;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

public class FluentAPI {

	@Test
  public void test() {
		WebDriver driver = new ChromeDriver(new ChromeOptions());
		EyesRunner runner = new ClassicRunner();
		Eyes eyes = new Eyes(runner);
		Configuration config = new Configuration();
	
		config.setBatch(new BatchInfo("Demo Batch - Selenium for Java - Fluent API"));
		eyes.setConfiguration(config);
		
		try {
			eyes.open(driver, "Demo App - Selenium for Java - Fluent API", "Smoke Test - Selenium for Java - Fluent API", new RectangleSize(800, 600));

			driver.get("https://demo.applitools.com");

			eyes.checkWindow("Login Window");

			driver.findElement(By.id("log-in")).click();

			eyes.check(Target.window().fully().withName("App Window"));
			eyes.check("Transactions Table", Target.region(By.className("table")));

			eyes.closeAsync();
		} finally {
			driver.quit();
		}
	}

}
