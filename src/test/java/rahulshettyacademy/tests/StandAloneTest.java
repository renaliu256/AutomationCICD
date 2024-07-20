package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		//create object of landing page 
		LandingPage landingPage = new LandingPage(driver);
		
		driver.get("https://rahulshettyacademy.com/client");
		
		//Step1-Login
		
		//enter username and password
		driver.findElement(By.id("userEmail")).sendKeys("rena30@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Renadai123");
		//click login
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
	   /* for(WebElement product:products) {
	    	if(product.findElement(By.cssSelector("b")).getText().contains("ZARA COAT 3")) {
	    		product.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
	    	}
	    } */
		
		WebElement prod = products.stream() 
				                  .filter(product->
				                   product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//wait loading animation disappear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart'")));
	
		//click cart button 
	      WebElement cartButton = driver.findElement(By.cssSelector("[routerlink*='cart']"));
	      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);
	      
	    //
	    List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	    Boolean match = cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
	    
	    Assert.assertTrue(match);
	    driver.findElement(By.cssSelector(".totalRow button")).click();
	    //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	    Thread.sleep(3000);
	    //checkout page 
	    
	    Actions a = new Actions(driver);
	   // WebElement countrySelect = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
	    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	    //wait drop down is available 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    
	    driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	    driver.findElement(By.cssSelector(".action__submit")).click();
	    
	    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	    
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    driver.close();
	    
		
		

	}

}
