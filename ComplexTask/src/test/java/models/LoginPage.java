package models;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.xpath("//input[@id='login-button']");
    private final By result = By.tagName("h3");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clearFields() {
        driver.findElement(usernameField).
                sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        driver.findElement(passwordField).
                sendKeys(Keys.CONTROL + "a", Keys.DELETE);
    }

    public String getResult() {
        return driver.findElement(result).getText();
    }

    public HomePage login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
        return new HomePage(driver);
    }

}
