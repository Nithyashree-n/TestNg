package PhpTravels;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


import com.google.errorprone.annotations.Var;

public class Phptravels {

	WebDriver driver;
	Properties Locators;
	Properties Data;

	@BeforeTest
	public void site() throws IOException {
		String chromeDriverPath = System.getProperty("user.dir") + "\\chromedriver.exe";

		FileInputStream locator = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//java//SeleniumProject//Project//phpTravelsLocators.properties");
		Locators = new Properties();
		Locators.load(locator);
		FileInputStream data = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//java//SeleniumProject//Project//phpTravelsData.properties");
		Data = new Properties();
		Data.load(data);
		driver = new ChromeDriver();
		driver.navigate().to("https://www.phptravels.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,350)");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void login() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.switchTo().frame(Locators.getProperty("Frame_Id"));
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.getProperty("ChatWidget"))));

		WebElement button = driver.findElement(By.xpath(Locators.getProperty("ChatWidget")));
		js.executeScript("arguments[0].click();", button);

		WebElement name = driver.findElement(By.id(Locators.getProperty("Name")));
		name.sendKeys(Data.getProperty("Name"));
		WebElement whatsapp = driver.findElement(By.xpath(Locators.getProperty("WhatsApp")));
		whatsapp.sendKeys(Data.getProperty("WhatsAppNo"));
		WebElement mail = driver.findElement(By.id(Locators.getProperty("Email")));
		mail.sendKeys(Data.getProperty("Email"));
		WebElement choice = driver.findElement(By.xpath(Locators.getProperty("Dropdown")));
		Select choose = new Select(choice);
		choose.selectByValue(Locators.getProperty("value"));
		WebElement click = driver.findElement(By.xpath(Locators.getProperty("StartChat")));
		js.executeScript("arguments[0].click();", click);
		driver.switchTo().defaultContent();

	}

	@AfterTest
	public void close() {
		driver.close();
	}
}