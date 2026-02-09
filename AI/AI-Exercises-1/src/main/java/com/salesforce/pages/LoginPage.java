package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;
    
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;
    
    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;
    
    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//div[@class='loginError']")
    private WebElement loginErrorContainer;
    
    public LoginPage(WebDriver driver) throws Exception {
        try {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
            throw new Exception("Failed to initialize LoginPage: " + e.getMessage());
        }
    }
    
    public void enterUsername(String username) throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            usernameField.clear();
            usernameField.sendKeys(username);
        } catch (Exception e) {
            throw new Exception("Failed to enter username: " + e.getMessage());
        }
    }
    
    public void enterPassword(String password) throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            passwordField.clear();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            throw new Exception("Failed to enter password: " + e.getMessage());
        }
    }
    
    public void clickLoginButton() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (Exception e) {
            throw new Exception("Failed to click login button: " + e.getMessage());
        }
    }
    
    public void selectRememberMe() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
            if (!rememberMeCheckbox.isSelected()) {
                rememberMeCheckbox.click();
            }
        } catch (Exception e) {
            throw new Exception("Failed to select remember me checkbox: " + e.getMessage());
        }
    }
    
    public void performLogin(String username, String password) throws Exception {
        try {
            enterUsername(username);
            enterPassword(password);
            clickLoginButton();
        } catch (Exception e) {
            throw new Exception("Login operation failed: " + e.getMessage());
        }
    }
    
    public void performLoginWithRememberMe(String username, String password) throws Exception {
        try {
            enterUsername(username);
            enterPassword(password);
            selectRememberMe();
            clickLoginButton();
        } catch (Exception e) {
            throw new Exception("Login with remember me failed: " + e.getMessage());
        }
    }
    
    public boolean isErrorMessageDisplayed() throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            throw new Exception("Failed to verify error message: " + e.getMessage());
        }
    }
    
    public String getErrorMessageText() throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            throw new Exception("Failed to retrieve error message text: " + e.getMessage());
        }
    }
    
    public boolean isUsernameFieldDisplayed() throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            return usernameField.isDisplayed();
        } catch (Exception e) {
            throw new Exception("Failed to verify username field: " + e.getMessage());
        }
    }
    
    public boolean isPasswordFieldDisplayed() throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            return passwordField.isDisplayed();
        } catch (Exception e) {
            throw new Exception("Failed to verify password field: " + e.getMessage());
        }
    }
    
    public boolean isLoginButtonDisplayed() throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return loginButton.isDisplayed();
        } catch (Exception e) {
            throw new Exception("Failed to verify login button: " + e.getMessage());
        }
    }
    
    public boolean isRememberMeCheckboxDisplayed() throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(rememberMeCheckbox));
            return rememberMeCheckbox.isDisplayed();
        } catch (Exception e) {
            throw new Exception("Failed to verify remember me checkbox: " + e.getMessage());
        }
    }
}
