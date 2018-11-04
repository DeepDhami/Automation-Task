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

/*** Task 1 Automation script for end to end Test 
 * 
 * @author Dipendar
 *
 */

public class Task1 {
	WebDriver driver;
	

	/*** Task1 end to end test ***/
	@Test
	public void test() 
	{
		/** Open the url **/
		driver.get("https://openweathermap.org/");
		maxBrowser();
		
		/** verify the topmenu **/
		verifyTopMenu("Weather");
		verifyTopMenu("Maps");
		verifyTopMenu("API");
		verifyTopMenu("Price");
		verifyTopMenu("Partners");
		verifyTopMenu("Stations");
		verifyTopMenu("Widgets");
		verifyTopMenu("Blog");
		
		/** verify the pageTitle **/
		verifyPageTitle();
		
		
		
		
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
	/** To verify the page title ***/
	public void verifyPageTitle() 
	{
		String pageTitle="Сurrent weather and forecast - OpenWeatherMap";
		WebElement title=driver.findElement(By.xpath("//title"));
		System.out.println("The title" + title);
		if(title.getAttribute("innerText").contains(pageTitle))
		{
			System.out.println("Page title matches");
		}
		else
		{
			System.out.println("Page title does not matches");
		}
	}
	/** to verify topmenu **/
	public void verifyTopMenu(String menu)
	{
		List<WebElement> topmenulist=driver.findElements(By.xpath("//ul[contains(@class,'navbar-right')]/li[starts-with(@class,'nav__item')]/a"));
		for (WebElement menulist : topmenulist)
		{
			
			if(menulist.getAttribute("innerText").contains(menu))
			{
				System.out.println("The menu matches " + menulist);
				break;
			}
		}
	}
}
