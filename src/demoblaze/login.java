package demoblaze;

import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeMethod;

public class login {

	Properties Locators;
	Properties Data;
	String chromePath;
	ChromeDriver driver;

	@BeforeMethod
	public void beforeMethod() throws IOException {

		FileInputStream locator = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//java//SeleniumProject//Project//blazeLocators.properties");
		Locators = new Properties();
		Locators.load(locator);
		FileInputStream data = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//java//SeleniumProject//Project//blazeData.properties");
		Data = new Properties();
		Data.load(data);

		chromePath = System.getProperty("user.dir") + "\\chromedriver.exe";

		driver = new ChromeDriver();
		driver.navigate().to("https://www.demoblaze.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 2)

	public  void login() throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id(Locators.getProperty("LoginId"))).click();
		driver.findElement(By.cssSelector(Locators.getProperty("Login_User")))
				.sendKeys(Data.getProperty("UsernameLogin"));
		driver.findElement(By.xpath(Locators.getProperty("Login_Password")))
				.sendKeys(Data.getProperty("PasswordLogin"));
		driver.findElement(By.xpath(Locators.getProperty("LoginButton"))).click();
		driver.close();

	}

}