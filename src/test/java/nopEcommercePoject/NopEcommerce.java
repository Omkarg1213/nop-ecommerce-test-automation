package nopEcommercePoject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.data.setUp.*;

import java.time.Duration;
import java.util.NoSuchElementException;


@Listeners(NopEcommerceTestListener.class)
public class NopEcommerce extends CommonDataSetUp1 {
	
	
	@Test(priority = 0)
	public void launchWebsite() {
		 driver.get("https://demo.nopcommerce.com/register");
		 extentTest.info("Navigated to the Url.");
	}

    @Test(priority = 1, dataProvider = "registrationData", dataProviderClass = TestDataExtractor.class)
    public void registerNewUser(String gender, String FirstName, String LastName,
    		String day, String month, String year, String email, String company,boolean newsletter,
    		String password) throws InterruptedException {
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Adjusted implicit wait time

        // Fill registration form
		
		  if(gender.contains("Male")) {
		  driver.findElement(By.id("gender-male")).click(); 
		  } else if(gender.contains("Female")) {
		  driver.findElement(By.id("gender-female")).click(); }
        
        driver.findElement(By.id("FirstName")).sendKeys(FirstName);
        driver.findElement(By.id("LastName")).sendKeys(LastName);

        // Select date of birth
        Select dayOfBirth = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dayOfBirth.selectByVisibleText(day);
        Select monthOfBirth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        monthOfBirth.selectByVisibleText(month);
        Select yearOfBirth = new Select(driver.findElement(By.name("DateOfBirthYear")));
        yearOfBirth.selectByVisibleText(year);
        
        // Fill remaining fields
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys(company);
        if(newsletter == true) {
        driver.findElement(By.id("Newsletter")).click();
        }
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        // Submit registration form
        driver.findElement(By.id("register-button")).click();
        
        extentTest.info("Provided the datas from dataProvider() i,e registrationData() to registerNewUser()");
        
       // Thread.sleep(3000);
        
        toLogin(email,password);
        //driver.findElement(By.linkText("Register")).click();
    }
    
    
    public void toLogin(String email, String password) throws InterruptedException {
    	 WebElement alreadyRegisteredMessage = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/form/div[1]/ul/li"));
    	 WebElement youreRegisteredMessage = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]"));

         if (alreadyRegisteredMessage.isDisplayed()) {
             // If the message is displayed, click the login button
             WebElement loginButton = driver.findElement(By.linkText("Log in")); // Change the locator accordingly
             loginButton.click();
             
             driver.findElement(By.id("Email")).sendKeys(email);
             driver.findElement(By.id("Password")).sendKeys(password);
             //driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
  
             addTocart();
             
         } else if (youreRegisteredMessage.isDisplayed()){
             // If the message is not displayed, continue with other actions, like filling out the registration form and clicking the continue button
        	 addTocart(); 
         }
         
         extentTest.info("Provided the datas from dataProvider() i,e loginData() to toLogin()");
    }
    
    @Test(priority = 2)
    public void addTocart() throws InterruptedException {
    	WebElement bookElement = driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[5]/a"));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", bookElement);
        
        WebElement addToCartButton = driver.findElement(By.xpath("(//button[@type='button'])[1]"));
        js.executeScript("arguments[0].scrollIntoView()", addToCartButton);
        js.executeScript("arguments[0].click()", addToCartButton);
        
        js.executeScript("window.scrollTo(0, 0)");
        
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement closeButton = driver.findElement(By.xpath("//span[@class='close']"));
        //wait.until(ExpectedConditions.visibilityOf(closeButton)).click();
        //Thread.sleep(2000);
        //WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='close']")));
        closeButton.click();
        
        WebElement addToCartElement = driver.findElement(By.xpath("//span[@class='cart-label']")); 
        Actions action = new Actions(driver);
        action.moveToElement(addToCartElement).perform();             
        
        WebElement goToCart = driver.findElement(By.xpath("(//button[@type='button'])[1]"));
        goToCart.click();
        
        extentTest.info("Added the desired book to the cart");

    }
    
    @Test(priority = 3)
    public void checkOut() throws InterruptedException {	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	WebElement checkoutOption = driver.findElement(By.id("termsofservice"));
        js.executeScript("arguments[0].scrollIntoView()", checkoutOption);
        js.executeScript("arguments[0].click()", checkoutOption);
        
        driver.findElement(By.id("checkout")).click(); 
        //driver.findElement(By.xpath("//button[@type='button'])[2]")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[1]/div[3]/button[1]")).click();
	  
        extentTest.info("Performed the chekout operations");

    }
    
    @Test(priority = 4, dataProvider = "billingAddressData", dataProviderClass = TestDataExtractor.class)
    public void billingAddress(String firstName, String lastName,String email, String company, String countryId, String stateProvinceId,
    		String city, String address1, String address2, String zipPostalCode, String phoneNumber, String faxNumber) {
    	
    	if (!isFieldNotEmpty(By.name("BillingNewAddress.FirstName")) &&
    	        !isFieldNotEmpty(By.name("BillingNewAddress.LastName")) &&
    	        !isFieldNotEmpty(By.name("BillingNewAddress.Email")) &&
    	        !isFieldNotEmpty(By.name("BillingNewAddress.Company"))) {
    	        
    	        // Fill billing address form only if the fields are empty
    	        driver.findElement(By.name("BillingNewAddress.FirstName")).sendKeys(firstName);
    	        driver.findElement(By.name("BillingNewAddress.LastName")).sendKeys(lastName);
    	        driver.findElement(By.name("BillingNewAddress.Email")).sendKeys(email);
    	        driver.findElement(By.name("BillingNewAddress.Company")).sendKeys(company);
    	    }
    	Select selectCountry = new Select(driver.findElement(By.name("BillingNewAddress.CountryId")));
    	selectCountry.selectByVisibleText(countryId);
    	Select selectSstate = new Select(driver.findElement(By.name("BillingNewAddress.StateProvinceId")));
    	selectSstate.selectByVisibleText(stateProvinceId);
    	driver.findElement(By.name("BillingNewAddress.City")).sendKeys(city);
    	driver.findElement(By.name("BillingNewAddress.Address1")).sendKeys(address1);
    	driver.findElement(By.name("BillingNewAddress.Address2")).sendKeys(address2);
    	driver.findElement(By.name("BillingNewAddress.ZipPostalCode")).sendKeys(zipPostalCode);
    	driver.findElement(By.name("BillingNewAddress.PhoneNumber")).sendKeys(phoneNumber);
    	driver.findElement(By.name("BillingNewAddress.FaxNumber")).sendKeys(faxNumber);
    	
    	driver.findElement(By.xpath("(//button[@name='save'])[1]")).click();
    	
		/*
		 * Alert simpleAlert = driver.switchTo().alert();
		 * System.out.println(simpleAlert.getText()); simpleAlert.accept();
		 */
    	driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
    	driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    	driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	WebElement confirmButton = driver.findElement(By.xpath("(//button[@type='button'])[13]"));
    	js.executeScript("arguments[0].scrollIntoView()", confirmButton);
    	confirmButton.click();

        WebElement thankYouMessage = driver.findElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        System.out.println(thankYouMessage);
        
        extentTest.info("Performed the billing operations and displayed : Your order has been successfully processed!");

    }

	private boolean isFieldNotEmpty(By locator) {
		// TODO Auto-generated method stub
		 try {
	            String fieldValue = driver.findElement(locator).getAttribute("value");
	            return fieldValue != null && !fieldValue.isEmpty();
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }
	}

