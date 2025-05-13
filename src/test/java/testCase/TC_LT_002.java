package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LT_002 extends BaseClass{
	
	@Test
	public void testlogin()
	{
		try
		{
		HomePage hp=new HomePage(driver);
		hp.ClickonmyAccount();
        hp.Clickonlogin();


        LoginPage lp=new LoginPage(driver);
        lp.setEmail("anilgowdan67@gmail.com");
        lp.setPwd("Anil@1122");
        lp.clickOnlogin();
        
       MyAccountPage ap=new MyAccountPage(driver);
        Assert.assertTrue(ap.isMsgHeading(),"Invalid Credentials");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}

}
