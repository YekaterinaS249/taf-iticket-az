package az.iticket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected final String BASE_URL = "https://iticket.az/ru";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
