package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import Resources.TestUtil;
import Resources.WebEventListener;



public class Base {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static Logger log=LogManager.getLogger(Base.class.getName());
	
	public Base(){
		try {
			prop = new Properties();
			log.info("Reading property file.........");
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/Resources/properties.properties");
			prop.load(ip);
			log.info("Property file loaded successfully!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialize(){
		String browserName = prop.getProperty("Browser");
		log.info("Getting browser from property file");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",OsPath.getPath(browserName));	
			log.info("Loading ChromeDriver");
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver",OsPath.getPath(browserName));	
			log.info("Loading firefox driver");
			driver = new FirefoxDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		log.info("Triggering url");
		driver.get(prop.getProperty("url"));
		log.info("Triggered "+prop.getProperty("url"));
		
	}

}
