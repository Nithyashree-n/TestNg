import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class SecondProgram {
	static String chromePath, url;
	static ChromeDriver driver;

	
@BeforeMethod
public void beforeMethod() {
	System.out.println("google method is executing...");
	chromePath = System.getProperty("user.dir") + "\\chromedriver.exe";

	url = "http://www.google.com";
}

@Test(priority = 2)
public void google() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", chromePath);
	driver = new ChromeDriver();
	driver.get(url);
	driver.findElement(By.name("q")).sendKeys("java");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//input[@class='gNO89b']")).click();

}

@AfterMethod
public void exit() {
	System.out.println("google method execution Completed..!");
	driver.close();

}

@BeforeClass
public void beforeClass() {
	System.out.println("SecondProgram Class is ready to test...");
}

@AfterClass
public void afterClass() {
	System.out.println("SecondProgram Class is tested..!");
}

}