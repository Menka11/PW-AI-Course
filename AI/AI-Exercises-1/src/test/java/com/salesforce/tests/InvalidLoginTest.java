package com.salesforce.tests;

import com.salesforce.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class InvalidLoginTest {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private static final String LOGIN_URL = "https://login.salesforce.com/?locale=in";
    
    @BeforeTest
    public void setUp() throws Exception {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(LOGIN_URL);
            loginPage = new LoginPage(driver);
        } catch (Exception e) {
            throw new Exception("Test setup failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 1)
    public void verifyLoginWithInvalidUsername() throws Exception {
        try {
            loginPage.performLogin("invaliduser@test.com", "ValidPassword123");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid username");
            String errorText = loginPage.getErrorMessageText();
            Assert.assertFalse(errorText.isEmpty(), "Error message text is empty");
        } catch (Exception e) {
            throw new Exception("Invalid username test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 2)
    public void verifyLoginWithInvalidPassword() throws Exception {
        try {
            driver.get(LOGIN_URL);
            loginPage = new LoginPage(driver);
            loginPage.performLogin("validuser@salesforce.com", "WrongPassword123");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid password");
            String errorText = loginPage.getErrorMessageText();
            Assert.assertFalse(errorText.isEmpty(), "Error message text is empty");
        } catch (Exception e) {
            throw new Exception("Invalid password test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 3)
    public void verifyLoginWithEmptyUsername() throws Exception {
        try {
            driver.get(LOGIN_URL);
            loginPage = new LoginPage(driver);
            loginPage.performLogin("", "ValidPassword123");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for empty username");
        } catch (Exception e) {
            throw new Exception("Empty username test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 4)
    public void verifyLoginWithEmptyPassword() throws Exception {
        try {
            driver.get(LOGIN_URL);
            loginPage = new LoginPage(driver);
            loginPage.performLogin("validuser@salesforce.com", "");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for empty password");
        } catch (Exception e) {
            throw new Exception("Empty password test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 5)
    public void verifyLoginWithEmptyCredentials() throws Exception {
        try {
            driver.get(LOGIN_URL);
            loginPage = new LoginPage(driver);
            loginPage.performLogin("", "");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for empty credentials");
        } catch (Exception e) {
            throw new Exception("Empty credentials test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 6)
    public void verifyLoginWithSpecialCharactersInUsername() throws Exception {
        try {
            driver.get(LOGIN_URL);
            loginPage = new LoginPage(driver);
            loginPage.performLogin("user@#$%^&*.com", "ValidPassword123");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for special characters in username");
        } catch (Exception e) {
            throw new Exception("Special characters in username test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 7)
    public void verifyLoginWithSQLInjectionAttempt() throws Exception {
        try {
            driver.get(LOGIN_URL);
            loginPage = new LoginPage(driver);
            loginPage.performLogin("admin' OR '1'='1", "password' OR '1'='1");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for SQL injection attempt");
        } catch (Exception e) {
            throw new Exception("SQL injection test failed: " + e.getMessage());
        }
    }
    
    @AfterTest
    public void tearDown() throws Exception {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            throw new Exception("Test teardown failed: " + e.getMessage());
        }
    }
}
