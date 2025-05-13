package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AccountRigesterPage;
import PageObjects.HomePage;
import testBase.BaseClass;
public class TC_AR_001 extends BaseClass{
@Test
public void verity_account_registration()
{
	
	
	HomePage hp=new HomePage(driver);
	hp.ClickonmyAccount();
    hp.Clickonregister();

    AccountRigesterPage regpage= new AccountRigesterPage(driver);
    regpage.setFirstname(randomString().toUpperCase());
    regpage.setLastname(randomString().toUpperCase());
    regpage.setEmail(randomString() +"@gmail.com");
    regpage.setTelphone(randomNum());
    
    String password=randomAlphaNum();
    regpage.setPassword(password);
    regpage.conFirmpwd(password);
     
    regpage.privacyPolicy();
    regpage.continueCta();
    
  String confirmMsg=regpage.getConfirmation();
  Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");
  
 
}

}
