package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.logger.LoggerSingleton;

public class DriverSingleton {
    private static WebDriver driver;
    private static final Logger log = LoggerSingleton.getLogger(DriverSingleton.class);

    private DriverSingleton(){}

    public static WebDriver getDriver() {
        if(null == driver) {
            String browserType = System.getProperty("browser", "chrome");
            log.info("Iniciando configuración del driver para: " + browserType);

            try {
                switch (browserType) {
                    case "firefox": {
                        log.debug("Configurando Firefox WebDriver...");
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        log.info("Firefox Driver configurado exitosamente");
                        break;
                    }
                    case "edge": {
                        log.debug("Configurando Edge WebDriver...");
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        log.info("Edge Driver configurado exitosamente");
                        break;
                    }
                    default: {
                        log.debug("Configurando Chrome WebDriver (navegador por defecto)...");
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        log.info("Chrome Driver configurado exitosamente");
                        break;
                    }
                }

                log.debug("Maximizando ventana del navegador");
                driver.manage().window().maximize();
                log.info("Configuración del WebDriver completada");

            } catch (Exception e) {
                log.error("Error al configurar el driver para " + browserType + ": " + e.getMessage());
                log.debug("Detalles del error:", e);
                throw new RuntimeException("No se pudo inicializar el WebDriver: " + e.getMessage(), e);
            }
        } else {
            log.debug("Usando instancia existente del WebDriver");
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            log.debug("Cerrando sesión del WebDriver");
            try {
                driver.quit();
                log.info("WebDriver cerrado exitosamente");
            } catch (Exception e) {
                log.error("Error al cerrar el WebDriver: " + e.getMessage());
            } finally {
                driver = null;
            }
        } else {
            log.debug("No hay instancia de WebDriver para cerrar");
        }
    }
}