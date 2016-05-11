package log4jsample;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;



public class log4j {
	
	WebDriver driver;
	public String BaseURL="http://healthunify.com/bmicalculator/";
	Logger log = Logger.getLogger("devpinoyLogger");
	@BeforeSuite
    public void chromeConfig()
    {
		   DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		   ChromeOptions options = new ChromeOptions();
		   capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		   System.setProperty("webdriver.chrome.driver","C:\\Users\\Manpreet Kaur\\Documents\\chromedriver.exe");
		   driver = new ChromeDriver(capabilities); 
		   
		   
	}
	
	@BeforeTest
	public void launchBrowser()
	{
		driver.manage().window().maximize();
		driver.get(BaseURL);
		
		
	}
  @Test(priority=0)
  public void  weight() {
	  log.debug("entering weight");
	  driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/p[1]/input")).sendKeys("68");
	  log.debug("selecting KG");
	  WebElement wieghtDropdown=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/p[1]/select"));
      Select kg=new Select(wieghtDropdown);
      kg.selectByValue("kilograms");
  }
  @Test(priority=1)
  public void  height()
  {
	  log.debug("entering height in feet");
	   WebElement feetDropdown=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/p[2]/select[1]"));
      Select feet=new Select(feetDropdown);
      feet.selectByValue("5");
      log.debug("entering height in inches");
      WebElement inchesDropdown=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/p[2]/select[2]"));
      Select inch=new Select(inchesDropdown);
      inch.selectByValue("7");
  
  }
  @Test(priority=2)
  public void calculate()
  {
	  log.debug("clicking on Calculate");
	  driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/p[3]/input[2]")).click();
	  
  }
  @Test(priority=3)
  
  public void gettingUnits()
  {
	  log.debug("getting SI Units");
	  String SI=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/table[1]/tbody/tr[1]/td[2]/input")).getAttribute("value");
      log.debug("getting US Units");
      String US=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/table[1]/tbody/tr[2]/td[2]/input")).getAttribute("value");
      log.debug("getting UK Units");
      String UK=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/table[1]/tbody/tr[3]/td[2]/input")).getAttribute("value");
      log.debug("getting description");
     String description=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/section/article/div/form/table[2]/tbody/tr/td/input")).getAttribute("value");
     
     System.out.println("SI units are " + SI);
     System.out.println("US units are " +US);
     System.out.println("UK units are " +UK);
     System.out.println("message is "+ description);
     
  }
  
  @Test(priority=4)
  
  public void screenshot()throws Exception
  {
	  try
	  {
		 driver.findElement(By.name("manpreet"));
	  }
  
  
	  catch (Exception e)
	  {
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          
             FileUtils.copyFile(scrFile, new File ("C:\\Users\\Manpreet Kaur\\Documents\\GIT_Manpreet.png"));
		  
	  }
  }
  
@AfterSuite

public void quitBrowser()

{
	driver.quit();
}
  
}
