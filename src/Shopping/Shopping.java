package Shopping;

import org.testng.annotations.Test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopping {


WebDriver driver;
String url1;
String dpath;


@BeforeTest
public void SetDriver() {

url1="https://www.amazon.com/";

     dpath="C:\\Drivers\\chromedriver.exe";
     
     System.out.println("before Test annotation");



}

@Test
public void verifyTitle()
{
//driver.get("https://www.amazon.com");
	

System.setProperty("webdriver.chrome.driver",dpath);

 driver= new ChromeDriver();

driver.get(url1);

String Expectedtitle="Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
String Actualtitle=driver.getTitle();

Assert.assertEquals(Actualtitle, Expectedtitle);
System.out.println(Actualtitle);



}


@AfterTest
public void closedriver(){


driver.close();

}

}