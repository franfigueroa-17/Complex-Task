package test.login;

import models.HomePage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import test.BaseTest;
import utils.logger.LoggerSingleton;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    private static final Logger log = LoggerSingleton.getLogger(LoginTests.class);

    @ParameterizedTest
    @MethodSource("utils.data.DataProviders#invalidCredentials")
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
        assertEquals("Epic sadface: Username is required", result);
    }

    @ParameterizedTest
    @MethodSource("utils.data.DataProviders#validCredentials")
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
        assertEquals("Epic sadface: Password is required", result);
    }

    @ParameterizedTest
    @MethodSource("utils.data.DataProviders#validCredentials")
    public void LoginWithUserAndPassword(String username, String password) {
        log.info("Test: Login with valid username and password");
        log.debug("Credentials -> Username: " + username + ", Password: " + password);

        try {
            HomePage homepage = loginPage.login(username, password);
            String logo = homepage.getLogoHomePage();
            log.info("Homepage Info: " + logo);
            assertEquals("Swag Labs", logo);
        } catch (NoSuchElementException e) {
            log.error("Logo Element not found in the homepage");
            fail("Login failed: credentials did not allow to log in into the homepage");
        }
    }
}
