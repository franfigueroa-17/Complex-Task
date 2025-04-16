package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
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
            log.info("Starting driver configuration for: " + browserType);

            try {
                switch (browserType) {
                    case "firefox": {
                        log.debug("Configuring Firefox WebDriver...");
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        log.info("Firefox Driver successfully configured");
                        break;
                    }
                    case "edge": {
                        log.debug("Configuring Edge WebDriver...");
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        log.info("Edge Driver successfully configured");
                        break;
                    }
                    default: {
                        log.debug("Configuring Chrome WebDriver (default browser)...");
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        log.info("Chrome Driver successfully configured");
                        break;
                    }
                }

                log.debug("Maximizing browser window");
                driver.manage().window().maximize();
                log.info("WebDriver configuration completed");

            } catch (Exception e) {
                log.error("Error configuring driver for " + browserType + ": " + e.getMessage());
                log.debug("Error details:", e);
                throw new RuntimeException("Could not initialize WebDriver: " + e.getMessage(), e);
            }
        } else {
            log.debug("Using existing WebDriver instance");
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            log.debug("Closing WebDriver session");
            try {
                driver.quit();
                log.info("WebDriver successfully closed");
            } catch (Exception e) {
                log.error("Error closing WebDriver: " + e.getMessage());
            } finally {
                driver = null;
            }
        } else {
            log.debug("No WebDriver instance to close");
        }
    }
}