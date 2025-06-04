// File: LoginTest.java
package TestNGPRacticewithSwagLab.TestNGPRacticewithSwagLab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional ("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(groups = {"sanity"})
    public void LoginWithValidUser() {
   
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"), "Login failed for valid user");
    }

    @Test(groups = {"regression"})
    public void LoginWithInvalidUser() {
        driver.findElement(By.id("user-name")).sendKeys("invalid_user");
        driver.findElement(By.id("password")).sendKeys("wrong_pass");
        driver.findElement(By.id("login-button")).click();
        boolean isErrorVisible = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        Assert.assertTrue(isErrorVisible, "Error message not displayed");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) driver.quit();
    }
}