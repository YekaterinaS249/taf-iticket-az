package az.iticket.ui.pages.basepage;

import az.iticket.ui.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = "https://iticket.az/ru";

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForVisibility(String xpath) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    protected WebElement waitForClickable(String xpath) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    protected void click(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }
}



