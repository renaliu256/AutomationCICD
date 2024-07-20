package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	
	@Given("I landed on ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		landingPage = launchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		landingPage.loginApplication(username,password);
		
	}
	
	@When("^I add product (.+) from cart$")
	public void i_add_product_to_cart(String productName) {
		
		productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^checkout (.+) and submit the order$")
	public void check_submit_order(String productName) throws InterruptedException {
		productCatalogue.goToCartPage();		   
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(productName);
	 	Assert.assertTrue(match);
	 	cartPage.goToCheckout();
	    
	    //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	    Thread.sleep(3000);
	    //checkout page 
	    
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    checkoutPage.selectCountry("india");
	    checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confimationPage")
	public void message_displayed_confirmationPage(String string) {
		
		   ConfirmationPage cp = new ConfirmationPage(driver);
		   String confirmMessage = cp.verifyConfirmationMessage();
		   Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		   driver.close();
		
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String strArg1) {
		
		Assert.assertEquals(strArg1, landingPage.getErrorMessgae());
		driver.close();
	}
	
	

}
