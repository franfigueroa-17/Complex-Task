package base.login;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.DataProviders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.testng.Assert.*;

public class LoginTests extends BaseTest {

    private static final Logger log = LogManager.getLogger(LoginTests.class);

    @Test(dataProvider = "invalidCredentials", dataProviderClass = DataProviders.class)
    public void LoginWithEmptyCredentials(String username, String password) {
        log.info("Test: Login con credenciales vacías");

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clearFields();
        loginPage.clickLogin();

        String result = loginPage.getResult();
        log.info("Mensaje recibido: " + result);

        assertEquals(result, "Epic sadface: Username is required");
    }

    @Test
    public void LoginWithUsernameOnly() {
        log.info("Test: Login solo con username");

        String username = "username";
        loginPage.setUsername(username);
        loginPage.clickLogin();

        String result = loginPage.getResult();
        log.info("Mensaje recibido: " + result);

        assertEquals(result, "Epic sadface: Password is required");
    }

    @Test(dataProvider = "validCredentials", dataProviderClass = DataProviders.class)
    public void LoginWithUserAndPassword(String username, String password) {
        log.info("Test: Login con usuario y contraseña válidos");
        log.debug("Credenciales -> Usuario: " + username + ", Password: " + password);

        HomePage homepage = loginPage.login(username, password);
        String logo = homepage.getLogoHomePage();
        log.info("Logo recibido: " + logo);

        assertEquals(logo, "Swag Labs");
    }
}
