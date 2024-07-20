package rahulshettyacademy.pageobjects;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulsherryacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver){
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	//page factory
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderNames;
	
	
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = orderNames.stream().anyMatch(orderName ->orderName.getText().equalsIgnoreCase(productName));
		return match;
	}


	
	
	

}
