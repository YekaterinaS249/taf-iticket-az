package az.iticket.basepage;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected final String BASE_URL = "https://iticket.az/ru";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
