package Page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;

public class Web extends Base{
	
	@FindBy(xpath="//div[@class='_3u328 copyable-text selectable-text']")
	WebElement textbox;
	
	@FindBy(xpath="//*[contains(text().'Keep your phone connected')]")
	WebElement heading;

	@FindBy(xpath="//input[@class='_2zCfw copyable-text selectable-text']")
	WebElement search;

	@FindBy(xpath = "//div[@class='_2UaNq']//div[@class='_2WP9Q']")
	WebElement firstOption;
	
	public Web() {
		PageFactory.initElements(driver, this);
	}
	
	public void spamMessageTo(String name,String message) throws InterruptedException {
		System.out.println("Waiting.............");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(heading));

		search.sendKeys(name);
		Thread.sleep(3000);
		firstOption.click();
		/*String xpath="//span[contains(text(),'"+name+"')]";
		driver.findElement(By.xpath(xpath)).click();*/
		
		for(int i=0;i<30;i++) {
			textbox.sendKeys(message+Keys.ENTER);
		}
		textbox.sendKeys("sorry for the inconvience caused. A new selenium bot is in action! :D"+Keys.ENTER);
		System.out.println("Message Entered");
	}

}
