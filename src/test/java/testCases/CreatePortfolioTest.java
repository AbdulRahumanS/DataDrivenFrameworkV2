package testCases;

import org.testng.annotations.Test;

import keywords.ApplicationKeywords;

public class CreatePortfolioTest extends ApplicationKeywords{
	
	@Test
	public void CreatePortfolioTest() throws InterruptedException {
		
		/*1. Open Website URL
		2. Click on Signinbutton / link
		3. Enter Login Details
		4. Click on Submit button
		5. Verify you are in portfolio page after login
		6. Click create portfolio link
		7. Enter the portfolio link 
		8. Click on create portfolio link
		*/
			
		
	
		ApplicationKeywords app = new ApplicationKeywords();
		
		
		
		app.OpenBrowser("browser_name");
		app.OpenURL("URL");
		app.Click("signIn_linkText");
		app.type("userName_id", "abdulrahuman0495@gmail.com");
		app.type("password_xpath", "Abdul@007");
		app.enterCaptcha("captcha_css");
		app.ClickSubmitButton("submitButton_name");
		app.validateTitle("Sign In to Portfolio - Rediff Moneywiz ultimate Stock Market Portfolio Tracker");
//		app.Click();
		app.quitDriver();
		
		
		
	}
	
	
	


}
