package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)

	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("rena30@gmail.com", "Rena123");
		
		//.ng-tns-c4-54.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		
		Assert.assertEquals("Incorrect email or+ password.", landingPage.getErrorMessgae());
		
		

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";


		landingPage.loginApplication("rena30@gmail.com", "Renadai123");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
	    productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();
		
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));	   
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
	 	Assert.assertFalse(match);

	    
	    
		
		

	}

}
