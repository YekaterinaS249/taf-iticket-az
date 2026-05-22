package az.iticket.basepage;

import az.iticket.core.DriverManager;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected final String BASE_URL = "https://iticket.az/ru";

    public BasePage() {
        this.driver = DriverManager.getDriver();
    }
}
