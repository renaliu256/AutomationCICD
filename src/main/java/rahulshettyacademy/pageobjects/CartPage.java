package rahulshettyacademy.pageobjects;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulsherryacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver){
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//page factory

	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = productTitles.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public void goToCheckout() {
		checkoutButton.click();
	}
	
	
	
	

}
