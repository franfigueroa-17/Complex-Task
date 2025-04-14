package base;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

public class BaseTest {
    protected LoginPage loginPage;
    String url = "https://www.saucedemo.com";

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverSingleton.getDriver();
        driver.get(url);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
