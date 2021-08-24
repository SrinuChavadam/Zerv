package com.zerv.pages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.zerv.api.utils.ReadProperty;
import com.zerv.web.utils.Webutils;



public class Dashboard extends Webutils{
	Logger log = Logger.getLogger(Dashboard.class);
	private By link_User=By.xpath("//li[@class='mt-3']//b[contains(text(),'Users')]");
	private By btn_AddUser=By.xpath("//span[text()='Add User']");
	private By input_Firstname=By.xpath("//input[@name='firstName']");
	private By input_Lastname=By.xpath("//input[@name='lastName']");
	private By input_Phone=By.xpath("//input[@id='phone']");
	private By radio_cardFormat=By.xpath("//select[@name='cardFormat']");
	private By input_Email=By.xpath("//input[@name='email']");
	private By input_AccessCode=By.xpath("//input[@name='accessCode']");
	private By input_TimeAccess=By.xpath("input_TimeAccess");
	private By btn_Save=By.xpath("//button[text()='Save']");
	private By btn_AddNewGroup=By.xpath("//button[text()='Add New Group']");
	private By dd_SelectAGroup=By.xpath("//span[text()='Select a Group']");
	private By dd_Devices=By.xpath("//b[text()='Devices']");
	private By lnk_EditUser=By.xpath("(//a[@ptooltip='Edit'])[1]");
	
	
	
	
	
	
	
	
	public Dashboard(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	

}
