package test.login;

import org.openqa.selenium.NoSuchElementException;

import test.BaseTest;
import org.testng.annotations.Test;
import models.HomePage;
import utils.data.DataProviders;
import org.slf4j.Logger;
import utils.logger.LoggerSingleton;
import static org.testng.Assert.*;

public class LoginTests extends BaseTest {

    private static final Logger log = LoggerSingleton.getLogger(LoginTests.class);

    @Test(dataProvider = "invalidCredentials", dataProviderClass = DataProviders.class)
    public void LoginWithEmptyCredentials(String username, String password) {

        log.info("Test: Login with empty credentials");

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clearFields();
        loginPage.clickLogin();

        String result = loginPage.getResult();
        log.info("Received message: " + result);

        if (!result.equals("Epic sadface: Username is required")) {
            log.error("Wrong Result. Expected: 'Epic sadface: Username is required', but obtained: '" + result + "'");
        }
        assertEquals(result, "Epic sadface: Username is required");
    }

    @Test(dataProvider = "validCredentials", dataProviderClass = DataProviders.class)
    public void LoginWithUsernameOnly(String username, String password) {
        log.info("Test: Login with username only");

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clearPassword();
        loginPage.clickLogin();

        String result = loginPage.getResult();
        log.info("Received message: " + result);

        if (!result.equals("Epic sadface: Password is required")) {
            log.error("Wrong Result. Expected: 'Epic sadface: Password is required', but obtained: '" + result + "'");
        }
        assertEquals(result, "Epic sadface: Password is required");
    }

    @Test(dataProvider = "validCredentials", dataProviderClass = DataProviders.class)
    public void LoginWithUserAndPassword(String username, String password) {
        log.info("Test: Login with valid username and password");
        log.debug("Credentials -> Username: " + username + ", Password: " + password);

        try {
            HomePage homepage = loginPage.login(username, password);
            String logo = homepage.getLogoHomePage();
            log.info("Homepage Info: " + logo);
            assertEquals(logo, "Swag Labs");
        } catch (NoSuchElementException e) {
            log.error("Logo Element not found in the homepage");
            fail("Login failed: credentials did not allow to log in into the homepage");
            }
    }


}
