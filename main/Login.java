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

public class Login {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("User is on Home Page")
    public void user_is_on_home_page() {
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @When("User navigate to Login Page")
    public void user_navigate_to_login_page() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Then("User enters {string} and {string}")
    public void user_enters_username_and_password(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user-name")));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
    }

    @Then("Keeping case as {string}")
    public void keeping_case_as(String string) {
        if (string.equalsIgnoreCase("Invalid")) {
            WebElement loginButton = driver.findElement(By.name("login-button"));
            loginButton.click();
            wait.until(ExpectedConditions.urlContains("error"));
            System.out.println("Login Unsuccessful");
            return;
        }
    }

    @Then("User should get logged in")
    public void user_should_get_logged_in() {
        WebElement loginButton = driver.findElement(By.name("login-button"));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("inventory"));
    }

    @Then("Message displayed Login Successfully")
    public void message_displayed_login_successfully() {
        System.out.println("Login Successful");
    }

    // Close the browser after all scenarios
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
