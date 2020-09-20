package Registration;

import org.testng.annotations.Test;


import java.sql.Driver;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;

public class Register{

	 static WebDriver driver;
	@BeforeTest
	public void settingAndLaunchingDriver() 
	{

		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Register.html");
		driver.manage().window().maximize();
		
	}
	
	@Test(priority=1)
	public void registerationTest()
	{
		//textBox
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Nithya");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("shree");
		driver.findElement(By.xpath("//textarea[@ng-model='Adress']")).sendKeys("Near Ashok hospital hosur");
		driver.findElement(By.xpath("//input[@ng-model='EmailAdress']")).sendKeys("Nithyashree24@gmail.com");
		driver.findElement(By.xpath("//input[@ng-model='Phone']")).sendKeys("6789345627");
		
		//Radio button
		WebElement radiobutton=driver.findElement(By.xpath("//div[@class='form-group']//div//label[2]//input"));
		if(radiobutton.isSelected()==false)
			radiobutton.click();
		
		//CheckBox
		WebElement checkbox=driver.findElement(By.xpath("//div[@class='form-group']//div//div[2]//input[@id='checkbox2']"));
		if(checkbox.isSelected()==false)
			checkbox.click();
		driver.findElement(By.id("msdd")).click();
		for(int i=1;i<=3;i++)
			driver.findElement(By.xpath("//multi-select//div[2]//ul")).click();
		
		//Select Dropdowns
		Select skills=new Select(driver.findElement(By.id("Skills")));
		skills.selectByValue("PHP");
		
		Select countries=new Select(driver.findElement(By.id("countries")));
		countries.selectByVisibleText("India");
		
		WebElement selectcountry=driver.findElement(By.xpath("//span[@aria-labelledby='select2-country-container']"));
		selectcountry.click();
		for(int i=1;i<=5;i++)
			selectcountry.sendKeys(Keys.ARROW_DOWN);		
		selectcountry.sendKeys(Keys.ENTER);
		
		//Selecting DOB
		Select year=new Select(driver.findElement(By.id("yearbox")));
		year.selectByValue("1996");
		Select month=new Select(driver.findElement(By.xpath("//select[@placeholder='Month']")));
		month.selectByVisibleText("August");
		Select date=new Select(driver.findElement(By.id("daybox")));
		date.selectByIndex(11);
		
		//Passwords
		driver.findElement(By.id("firstpassword")).sendKeys("Nithya@05");
		driver.findElement(By.id("secondpassword")).sendKeys("Nithu@05");
		
		//Registering details by clicking submit button
		WebDriverWait wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("submitbtn"))));
		driver.findElement(By.id("submitbtn")).click();
	}
	@Test(priority=2) // Handling child window
	public  void windowsHandling()
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("span.fa.fa-youtube-square")).click();
		Set<String> ids= driver.getWindowHandles();
		Iterator<String> windows=ids.iterator();
		String parent_window= windows.next();
		String child_Window = windows.next();
		WebDriver childwindow=driver.switchTo().window(child_Window);
		System.out.println("Child Window Title: "+childwindow.getTitle()+"\n");
		driver.close();
		
	}
	
	@AfterTest
	public void closingDriver()
	{
		driver.quit();
	}
}
