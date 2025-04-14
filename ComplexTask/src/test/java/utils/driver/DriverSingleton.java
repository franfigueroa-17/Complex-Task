package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.login.LoginTests;

public class DriverSingleton {
    private static WebDriver driver;
    private static final Logger log = LogManager.getLogger(DriverSingleton.class);


    private DriverSingleton(){}

    public static WebDriver getDriver() {
        if(null == driver) {
            switch (System.getProperty("browser", "chrome")) {
                case "firefox": {
                    try{
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        log.info("Firefox Driver Setup Complete");
                    } catch (Exception e){
                        log.error("Firefox Driver Setup Failed");
                        e.printStackTrace();
                    }
                    break;
                }
                case "edge": {
                    try{
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        log.info("Edge Driver Setup Complete");
                    } catch (Exception e){
                        log.error("Edge Driver Setup Failed");
                        e.printStackTrace();
                    }
                    break;

                }
                default: {

                    try{
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        log.info("Chrome Driver Setup Complete");
                    } catch (Exception e){
                        log.error("Chrome Driver Setup Failed");
                        e.printStackTrace();
                    }
                    break;

                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
        log.info("Test Driver Closed");
    }
}
