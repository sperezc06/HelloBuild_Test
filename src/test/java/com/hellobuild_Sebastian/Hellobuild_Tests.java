package com.hellobuild_Sebastian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hellobuild_Tests {
	
	private WebDriver driver; 
	
	By toolsButton = By.id("hdtb-tls");

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");	
		
	}
	
	
	@Test
	public void searchTime() throws InterruptedException{
		
		WebElement searchbox = driver.findElement(By.name("q"));
		
		searchbox.clear();
		
		searchbox.sendKeys("Hello build");
		
		searchbox.submit();
		
		Thread.sleep(1000); 
	
		assertTrue(driver.findElement(By.id("result-stats")).getText().contains("segundos"));
		
	}

	@Test
	public void amountOfResults() throws InterruptedException{
	
		WebElement searchbox = driver.findElement(By.name("q"));
		
		searchbox.clear();
		
		searchbox.sendKeys("Hello build");
		
		searchbox.submit();
		
		Thread.sleep(1000); 	
		
		assertTrue(driver.findElement(By.id("result-stats")).getText().contains("Cerca de"));
		driver.quit();
	}
	
	@Test
	public void errorMessage() throws InterruptedException{
	
		WebElement searchbox = driver.findElement(By.name("q"));
		
		searchbox.clear();
		
		searchbox.sendKeys("qaqawwqwqaqaqwa");
		
		searchbox.submit();
		
		Thread.sleep(1000); 
	
		List<WebElement> fonts = driver.findElements(By.tagName("p"));
		assertEquals("Sugerencias:", fonts.get(1).getText());
		
	}
	
	
	@Test
	public void linkToTools() throws InterruptedException{
	
		WebElement searchbox = driver.findElement(By.name("q"));
		
		searchbox.clear();
		
		searchbox.sendKeys("Hello Build");
		
		searchbox.submit();
		
		Thread.sleep(1000); 
	
		driver.findElement(toolsButton).click();
		
		WebElement toolsButtonExpanded = driver.findElement(By.id("hdtb-tls"));
	
		String attr = toolsButtonExpanded.getAttribute("aria-expanded");
		
		System.out.println(attr);
	
		//assertEquals(driver.findElement(By.id("result-stats")).getText().contains("segundos"));
		assertEquals("true", attr);
	
	}
	
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
}
 