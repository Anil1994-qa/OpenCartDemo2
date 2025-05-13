package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BasePage;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement headermsg;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logoputbtn;
	
	
	
	public boolean isMsgHeading ()
	{
		try {
		     return headermsg.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public void clickOnlogout()
	{
		logoputbtn.click();
	}
	

}
