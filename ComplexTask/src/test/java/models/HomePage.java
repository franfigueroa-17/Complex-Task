package models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;
    private final By LogoHomePage = By.xpath("//div[@class=\"app_logo\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoHomePage() {
        return driver.findElement(LogoHomePage).getText();
    }


}
