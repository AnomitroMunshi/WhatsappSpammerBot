package Whatsapp.WhatsappBot;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.Base;
import Page.Web;

public class Test1 extends Base{
	Web web;
	
	public Test1() {
		super();
	}
	
	
@BeforeMethod
public void setup() {
	initialize();
	web=new Web();
	
}

@Test
public void Spam() throws InterruptedException {
	
	web.spamMessageTo("Gayatiri Jio", "Botted Spam message from selenium!");
}

@AfterMethod
public void teardown() {
	
	//driver.close();
}
	
}
