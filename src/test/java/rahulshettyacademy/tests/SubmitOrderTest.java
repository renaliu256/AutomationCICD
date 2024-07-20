package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups="Purchase")

	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
					
		//Step1-Login
		
		//create object of landing page 
		//LandingPage landingPage = launchApplication();

		landingPage.loginApplication(input.get("email"), input.get("password"));
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		
		
	   /* for(WebElement product:products) {
	    	if(product.findElement(By.cssSelector("b")).getText().contains("ZARA COAT 3")) {
	    		product.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
	    	}
	    } */
		
		//add product with a certain name to cart
		
		productCatalogue.addProductToCart(input.get("product"));
		productCatalogue.goToCartPage();	
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		
		//wait loading animation disappear
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart'")));
	
		//click cart button 
	     // WebElement cartButton = driver.findElement(By.cssSelector("[routerlink*='cart']"));
	     // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);
	      
	    //
	   
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
	 	Assert.assertTrue(match);
	 	cartPage.goToCheckout();
	    
	    //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	    Thread.sleep(3000);
	    //checkout page 
	    
	    CheckoutPage checkoutPage = new CheckoutPage(driver);
	    checkoutPage.selectCountry("india");
	    checkoutPage.submitOrder();
	    
	    
	 
	    ConfirmationPage cp = new ConfirmationPage(driver);
	    String confirmMessage = cp.verifyConfirmationMessage();
	    
	    AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    
	    
		
		

	}
	
	//to verify ZARA coat 3 is displayed on order page 
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		
		landingPage.loginApplication("rena30@gmail.com", "Renadai123");
		landingPage.goToOrdersPage();
		OrderPage orderPage = new OrderPage(driver);
		Boolean result = orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(result);
		
		
	}
		
	//Extent reports
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "rena30@gmail.com");
//		map.put("password", "Renadai123");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "rena29@gmail.com");
//		map1.put("password", "Renaliu123");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}
	
	

}
