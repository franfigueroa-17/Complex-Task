package models;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By logoHomePage = By.xpath("//div[@class=\"app_logo\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoHomePage() {
        try {
            return driver.findElement(logoHomePage).getText();
        } catch (NoSuchElementException e) {
            return "Invalid Credentials for Login";
        }
    }

}
