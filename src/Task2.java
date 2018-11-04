import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*** Task 2 Automation script for end to end Test 
 * 
 * @author Dipendar
 *
 */


public class Task2 {
	WebDriver driver;
	WebDriverWait wait;
	

	/** Task 2 end to end test script ***/
	@Test
	public void test() 
	{
		/** Open the url **/
		driver.get("https://openweathermap.org/");
		maxBrowser();
		/** Enter the invalid city name **/
		inputCityName("ABCD");
		
		/** click on search button **/
		searchBtn();
		
		/** verify the result not found text ***/
		verifyResultNotFound("Not found");
		
	}
	
	/**  To select the browser ***/
	@BeforeTest
	public void setUp()
	{
		selectBrowser("Chrome");
	}
	
	/*** To quit the browser ****/
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	/** To select the browser ****/
	public void selectBrowser(String browser)
	{
		switch (browser) 
		{
		case "Chrome":
			String chromedriver=System.getProperty("user.dir")+"\\lib\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromedriver);
			driver =new ChromeDriver();
			break;

		case "Firefox":
			String firefoxdriver=System.getProperty("user.dir")+"\\lib\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxdriver);
			driver =new FirefoxDriver();
			break;
		default:
			System.out.println("No Browser selected");
			break;
		}
		
		
	}
	
	/*** To maximize the broswer and wait for page load ***/
	public void maxBrowser()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	/** Enter the invalid City Name ***/
	public void inputCityName(String city)
	{
		wait= new WebDriverWait(driver, 30);
		WebElement cityName=driver.findElement(By.xpath("//form[@id='searchform']//input[@id='q']"));
		wait.until(ExpectedConditions.visibilityOf(cityName)).isDisplayed();
		cityName.sendKeys(city);
		
	}
	
	/** click the SearchButton ***/
	public void searchBtn()
	{
		wait= new WebDriverWait(driver, 20);
		WebElement btnSearch=driver.findElement(By.xpath("//form[@id='searchform']//button[@type='submit']"));
		wait.until(ExpectedConditions.visibilityOf(btnSearch)).isDisplayed();
		btnSearch.click();
	}
	
	/** Verify Result not found text ***/
	public void verifyResultNotFound(String result)
	{
		WebElement resultText=driver.findElement(By.xpath("//div[@class='alert alert-warning']"));
		if(resultText.getText().contains(result))
		{
			System.out.println("Result not found is displayed ");
		}
	}
}