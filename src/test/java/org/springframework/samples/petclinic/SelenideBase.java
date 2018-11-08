/**
 * 
 */
package org.springframework.samples.petclinic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

/**
 * @author setupuser
 *
 */
public class SelenideBase {

	private static RemoteWebDriver driver = null;
	
	private static ResourceBundle rb = ResourceBundle.getBundle("application");
	
	@BeforeClass
	public static void setUp() throws MalformedURLException {
		
//		execLocal();
		execGridServer();
	}
	
	private static void execLocal() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		Configuration.browser = WebDriverRunner.CHROME;
//		Configuration.headless = true;
//		setProxy();
	}
	
	private static void execGridServer() throws MalformedURLException {
		String urlToRemoteWD = getEnvValue("GRID_URL");
		driver =new RemoteWebDriver(new URL(urlToRemoteWD),DesiredCapabilities.chrome());
		WebDriverRunner.setWebDriver(driver);
//		setProxy();
	}
	
	private static void setProxy() {
		Proxy webProxy = new Proxy();
		webProxy.setAutodetect(true);
		WebDriverRunner.setProxy(webProxy);
		System.setProperty("wdm.proxy", "http://host:port");    // url as "http://host:port"
		System.setProperty("wdm.proxyUser", "xxxxxx");
		System.setProperty("wdm.proxyPass", "xxxxxx");
	}
	
	public static String getEnvValue(String key) {
		return System.getenv(key);
	}
	
	@AfterClass
	public static void endUp() {
		if (driver != null) {
	 		driver.quit();
		}
	}

}
