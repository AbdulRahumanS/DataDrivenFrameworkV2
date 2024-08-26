package keywords;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.text.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericKeywords {

	public WebDriver driver = null;
	public Properties prop;

	public void OpenBrowser(String browserNameKey) {
			String browserName = prop.getProperty(browserNameKey);
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized", "--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("ignore-certificate-errors");

			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//geckodriver");
			FirefoxOptions options = new FirefoxOptions();

			ProfilesIni profiles = new ProfilesIni();
			FirefoxProfile ffprofile = profiles.getProfile("TestUser");
			ffprofile.setPreference("dom.webnotifications.enabled", false);
			ffprofile.setAcceptUntrustedCertificates(true);
			ffprofile.setAssumeUntrustedCertificateIssuer(false);
			options.setProfile(ffprofile);
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			driver = new ChromeDriver();
		}

		// Maximize Browser Window
		driver.manage().window().maximize();

	}

	public void OpenURL(String URLKey) {
		System.out.println("Opening Web URL :" + prop.getProperty(URLKey));
		driver.get(prop.getProperty(URLKey));

	}

	public void Click(String locatorKey) {

		System.out.println("Perform Click on the Locator: " + prop.getProperty(locatorKey));
		driver.findElement(By.linkText(prop.getProperty(locatorKey))).click();
	}

	public void ClickSubmitButton(String locatorKey) {

		System.out.println("Perform Click on the Locator: " +getElement(locatorKey));
		getElement((locatorKey)).click();
	}

	public void type(String locatorKey, String value) {

		System.out.println("Typing text : " + value + "In locator : " + getElement(locatorKey));
		getElement((locatorKey)).sendKeys(value);
//		driver.findElement(By.id(locator)).sendKeys(value);

	}

	public void enterCaptcha(String locatorKey) throws InterruptedException {

		System.out.println("Given Captcha locator : " + getElement(locatorKey));
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Captcha to fill in the textbox :");
		String inputText = scanner.nextLine();
		getElement((locatorKey)).sendKeys(inputText);
//		driver.findElement(By.id(locator)).sendKeys(inputText);
		Thread.sleep(5000);
	}

	public WebElement getElement(String locatorKey) {

		//Element is present
		if(!isElementPresent(locatorKey)) {
			//Report Error
			System.out.println("Element is not Present : "+ locatorKey);
		}
		
		//Element is visible
		if(!isElementvisible(locatorKey)){
			//Report Error
			System.out.println("Element is not Visible : "+ locatorKey);
		}
		//Create Webelement and Return Webelement
		WebElement element = driver.findElement(getLocator(locatorKey));
		return element;
	}

	public List<WebElement> getElements(String locatorKey) {

		
		//Create Webelement and Return Webelement
		List<WebElement> element = driver.findElements(getLocator(locatorKey));
		return element;
	}
	public boolean isElementPresent(String locatorKey) { // finding the element using iselementpresent method

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator(locatorKey)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isElementvisible(String locatorKey) { // finding the element using iselementpresent method

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public By getLocator(String locatorKey) {
		By by = null;
		
		if(locatorKey.endsWith("_id")) {
			by= By.id(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_xpath")) {
			by = By.xpath(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_css")) {
			by = By.cssSelector(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_linkText")) {
			by = By.linkText(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_partialLinkText")) {
			by = By.partialLinkText(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_name")) {
			by = By.name(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_className")) {
			by = By.className(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_tagName")) {
			by = By.tagName(prop.getProperty(locatorKey));
		}
		return by;
		
	}

	public void select() {

	}

	public void getText() {

	}

	public void navigate() {

	}

	public void acceptAlert() {

	}

	public void dismissAlert() {

	}
}
