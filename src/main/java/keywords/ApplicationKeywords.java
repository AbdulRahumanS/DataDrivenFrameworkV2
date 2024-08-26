package keywords;

import java.io.FileInputStream;
import java.util.Properties;

public class ApplicationKeywords extends ValidationKeywords {

	public ApplicationKeywords() {
		prop = new Properties();

		try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Project.properties");
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Calling the ApplicationKeyword Constructor");

		}
	}

	public void login() {

	}

	public void selectDate() {

	}

	public void quitDriver() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(5000);
		driver.quit();
	}
}
