package az.iticket.basetest;

import az.iticket.ui.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract  class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeEach
    public void setup() {
        homePage = new HomePage();
        homePage.openHomePage();
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

