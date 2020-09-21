package demoblaze;

import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Contact {
	Properties Locators;
	Properties Data;
	String chromePath;
	ChromeDriver driver;

	@BeforeMethod
	public void beforeMethod() throws IOException {

		FileInputStream locator = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//java//SeleniumProject//blazeLocators.properties");
		Locators = new Properties();
		Locators.load(locator);
		FileInputStream data = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//java//SeleniumProject//blazeData.properties");
		Data = new Properties();
		Data.load(data);

		chromePath = System.getProperty("user.dir") + "\\chromedriver.exe";

		driver = new ChromeDriver();
		driver.navigate().to("https://www.demoblaze.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 3)
	public void contact() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath(Locators.getProperty("ContactLink"))).click();
		driver.findElement(By.cssSelector(Locators.getProperty("Recipient_Email")))
				.sendKeys(Data.getProperty("Recipient_Mail"));
		driver.findElement(By.cssSelector(Locators.getProperty("Recipient_name")))
				.sendKeys(Data.getProperty("Recipient_Name"));
		driver.findElement(By.id(Locators.getProperty("Messageid"))).sendKeys(Data.getProperty("Message"));
		driver.findElement(By.xpath(Locators.getProperty("SendMessage"))).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		driver.close();

	}

}