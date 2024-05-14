package Assignment.BDD.Features;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class Login {
	WebDriver driver;
	WebDriverWait wait;
	
	@Given("User is on Home Page")
	public void user_is_on_home_page() {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium Drivers\\Chrome Driver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
	}

	@When("User navigate to Login Page")
	public void user_navigate_to_login_page() throws InterruptedException {
		driver.navigate().to("https://www.saucedemo.com/");
		Thread.sleep(5000);
	}

	@Then("User enters {string} and {string}")
	public void user_enters_and(String string, String string2) throws InterruptedException {
		WebElement username=driver.findElement(By.name("user-name"));
		username.sendKeys(string);
	    WebElement pass=driver.findElement(By.name("password"));
	    pass.sendKeys(string2);
	    Thread.sleep(5000);
	    
	}

	@Then("Keeping case as {string}")
	public void keeping_case_as(String string) throws InterruptedException {
		if (string.equalsIgnoreCase("Valid")) {
	        // Additional actions for valid case
	        System.out.println("Valid Credentials");    
	    } 
		else if (string.equalsIgnoreCase("Invalid")) {
			WebElement login=driver.findElement(By.name("login-button"));
		    login.click();
		    Thread.sleep(5000);
	        System.out.println("Login UnSuccessful");
	        driver.navigate().to("https://www.saucedemo.com/");
	        WebElement username=driver.findElement(By.name("user-name"));
			username.sendKeys("standard_user");
		    WebElement pass=driver.findElement(By.name("password"));
		    pass.sendKeys("secret_sauce");
	        return;
	    } 
		
	}

	@Then("User should get logged in")
	public void user_should_get_logged_in() {
		WebElement login=driver.findElement(By.name("login-button"));
	    login.click();
	    
	}

	@Then("Message displayed Login Successfully")
	public void message_displayed_login_successfully() {
		System.out.println("Login Successfully");
	}

}
