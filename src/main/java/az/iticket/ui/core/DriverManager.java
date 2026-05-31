package az.iticket.ui.core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
public class DriverManager {
    private static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {
            log.info("Browser is open");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return  driver;
    }
    public static void quitDriver() {
        if (driver != null) {
            log.info("Browser is quitting");
            driver.quit();
        }
        driver = null;
    }
}

