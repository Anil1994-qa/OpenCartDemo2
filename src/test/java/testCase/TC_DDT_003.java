package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_DDT_003 extends BaseClass{
	
	@Test(dataProvider="Logindata",dataProviderClass=DataProviders.class)//Getting data provider from the different class
	public void verifyLoginDDT(String email, String pwd, String result)
	{

		try
		{
			
			    //Home page
			 	HomePage hp=new HomePage(driver);
			 	hp.ClickonmyAccount();
	            hp.Clickonlogin();
			 	
		        //Login page--PO class
		        LoginPage log=new LoginPage(driver);
		        log.setEmail(email);
		        log.setPwd(pwd);
		        log.clickOnlogin();;
		        
		        
		MyAccountPage myacc= new MyAccountPage(driver);//This driver came from base class
		      boolean targetpage= myacc.isMsgHeading();
		            
	       if(result.equalsIgnoreCase("Valid"))
	       {
	    	    if(targetpage==true)
	    	    {
	    	    	myacc.clickOnlogout();;
	    	    	Assert.assertTrue(true); 	
	    	    }
	    	    else
	    	    {
	    	    	Assert.assertTrue(false);
	    	    }
	    	    if(result.equalsIgnoreCase("Invalid")) 
	    	    {
	    	    	myacc.clickOnlogout();;
	    	    	Assert.assertTrue(false);	 
	    	    }
	    	    else
	    	    {
	    	    	Assert.assertTrue(true);
	    	    }
	       }
		       }
		       catch(Exception e)
		       {
		           	Assert.fail();
		       }
		      
	}

		
	}