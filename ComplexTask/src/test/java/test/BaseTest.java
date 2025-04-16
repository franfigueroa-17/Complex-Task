package test;

import utils.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import models.LoginPage;

public class BaseTest {
    protected LoginPage loginPage;
    String URL = "https://www.saucedemo.com";

    @BeforeEach
    public void setUp() {
        WebDriver driver = DriverSingleton.getDriver();
        driver.get(URL);
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
