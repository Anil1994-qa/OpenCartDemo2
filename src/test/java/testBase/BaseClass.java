package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
public WebDriver driver;
public Properties p;
@BeforeClass
@Parameters({"os","br"})
public void Setup(String os, String br) throws IOException, InterruptedException
{   	
	
	FileReader file= new FileReader(".//src//test//resources//config.properties");
	p=new Properties();
	p.load(file);

	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		//OS --this is coming from xml
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WIN11);
		}
		else if (os.equalsIgnoreCase("mac"))
		{
			capabilities.setPlatform(Platform.MAC);
		}
		else if(os.equalsIgnoreCase("Linux"))
		{
			capabilities.setPlatform(Platform.LINUX);
		}
		else
		{
			System.out.println("No matching OS");
			return;
		}
		
	    //browser
	     switch(br.toLowerCase())
	     {
	     case "chrome": capabilities.setBrowserName("chrome"); break;
	     case "edge": capabilities.setBrowserName("Microsoftedge");break;
	     case "firefox": capabilities.setBrowserName("firefox");break;
	     default: System.out.println("No matching browser");
	     }
	      driver=new RemoteWebDriver(new URL(" http://172.26.96.1:4444/wd/hub"),capabilities);
	}
	
	     if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	     {
	    	 //This below code is run in local system
	    		switch(br.toLowerCase())
	    		{
	    		case "chrome":driver=new ChromeDriver();
	    		break;
	    		case "edge":driver=new EdgeDriver();
	    		break;
	    		case "firefox":driver=new FirefoxDriver();
	    		break;
	    		default :System.out.println("Invalid Browser");
	    		return;
	    		}
	       }
	       driver.get(p.getProperty("appURL"));
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	       driver.manage().window().maximize();
	       Thread.sleep(3000);
	      }

@AfterClass	
public void tearDown()
{
  driver.quit();
}

public String randomString()  //created method to generate random Strings {
{	
         String genratedString= RandomStringUtils.randomAlphabetic(5);
	 	 return genratedString;
}
public String randomNum()//created method to generate random Numbers
{
	 	 String genratedNum= RandomStringUtils.randomNumeric(10);
	 	 return genratedNum;
}
public String randomAlphaNum()//created method to generate random Alpha numeric methods
{
	 	 String genratedString= RandomStringUtils.randomAlphabetic(3);
	 	 String genratedNum= RandomStringUtils.randomAlphabetic(3);
	 	 return genratedNum+"@"+genratedString;
}
public String captureScreen(String tname) throws IOException {
    // Ensure driver is initialized and not null
    if (driver == null) {
        throw new IllegalStateException("WebDriver is not initialized.");
    }

    // Get the current timestamp in a proper format
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  // Corrected format for timestamp

    // Cast driver to TakesScreenshot
    TakesScreenshot takesScreenShot = (TakesScreenshot) driver;

    // Take screenshot
    File sourceFile = takesScreenShot.getScreenshotAs(OutputType.FILE);

    // Define the target file path (ensure screenshots directory exists)
    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";  // Add .png extension
    File targetFile = new File(targetFilePath);

    // Create the screenshots directory if it doesn't exist
    File screenshotDir = new File(System.getProperty("user.dir") + "\\screenshots\\");
    if (!screenshotDir.exists()) {
        screenshotDir.mkdir();
    }

    // Rename the screenshot file
    sourceFile.renameTo(targetFile);
	return targetFilePath;
    
}
}