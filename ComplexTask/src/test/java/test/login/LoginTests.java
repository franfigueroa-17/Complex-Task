package test.login;

import models.HomePage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import test.BaseTest;
import utils.logger.LoggerSingleton;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginTests extends BaseTest {

    private static final Logger log = LoggerSingleton.getLogger(LoginTests.class);

    /**
     * UC-1: Test Login with empty credentials
     * Given I am on the login page
     * When I type any credentials into username and password fields
     * And I clear the inputs
     * And I click on login
     * Then I should see the error message: "Username is required"
     */
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

        assertThat("Wrong Result", result, is("Epic sadface: Username is required"));
    }

    /**
     * UC-2: Test Login with only username
     * Given I am on the login page
     * When I enter a valid username
     * And I clear the password input
     * And I click on login
     * Then I should see the error message: "Password is required"
     */
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

        assertThat("Wrong Result", result, is("Epic sadface: Password is required"));
    }

    /**
     * UC-3: Test Login with valid credentials
     * Given I am on the login page
     * When I enter a valid username and password
     * And I click on login
     * Then I should be redirected to homepage and see the title "Swag Labs"
     */
    @ParameterizedTest
    @MethodSource("utils.data.DataProviders#validCredentials")
    public void LoginWithUserAndPassword(String username, String password) {
        log.info("Test: Login with valid username and password");
        log.debug("Credentials -> Username: " + username + ", Password: " + password);

        try {
            HomePage homepage = loginPage.login(username, password);
            String logo = homepage.getLogoHomePage();
            log.info("Homepage Info: " + logo);

            assertThat("Wrong page title after login", logo, is("Swag Labs"));
        } catch (NoSuchElementException e) {
            log.error("Logo Element not found in the homepage");
            throw new AssertionError("Login failed: credentials did not allow to log in into the homepage", e);
        }
    }
}
