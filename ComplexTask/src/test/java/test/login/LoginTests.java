package test.login;

import test.BaseTest;
import org.testng.annotations.Test;
import models.HomePage;
import utils.data.DataProviders;
import org.apache.logging.log4j.Logger;
import utils.logger.LoggerSingleton;
import static org.testng.Assert.*;

public class LoginTests extends BaseTest {

    private static final Logger log = LoggerSingleton.getLogger(LoginTests.class);

    @Test(dataProvider = "invalidCredentials", dataProviderClass = DataProviders.class)
    public void LoginWithEmptyCredentials(String username, String password) {

        log.info("Test: Login con credenciales vacías");

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clearFields();
        loginPage.clickLogin();

        String result = loginPage.getResult();
        log.info("Mensaje recibido: " + result);

        if (!result.equals("Epic sadface: Username is required")) {
            log.error("Resultado incorrecto. Se esperaba: 'Epic sadface: Username is required', pero se obtuvo: '" + result + "'");
        }
        assertEquals(result, "Epic sadface: Username is required");
    }

    @Test(dataProvider = "validCredentials", dataProviderClass = DataProviders.class)
    public void LoginWithUsernameOnly(String username, String password) {
        log.info("Test: Login solo con username");

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clearPassword();
        loginPage.clickLogin();

        String result = loginPage.getResult();
        log.info("Mensaje recibido: " + result);

        if (!result.equals("Epic sadface: Password is required")) {
            log.error("Resultado incorrecto. Se esperaba: 'Epic sadface: Password is required', pero se obtuvo: '" + result + "'");
        }
        assertEquals(result, "Epic sadface: Password is required");
    }

    @Test(dataProvider = "validCredentials", dataProviderClass = DataProviders.class)
    public void LoginWithUserAndPassword(String username, String password) {
        log.info("Test: Login con usuario y contraseña válidos");
        log.debug("Credenciales -> Usuario: " + username + ", Password: " + password);

        HomePage homepage = loginPage.login(username, password);
        String logo = homepage.getLogoHomePage();
        log.info("Mensaje recibido: " + logo);

        if (!logo.equals("Swag Labs")) {
            log.error("Resultado incorrecto. Se esperaba: 'Swag Labs'");
        }

        assertEquals(logo, "Swag Labs");
    }


}
