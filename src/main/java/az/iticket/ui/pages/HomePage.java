package az.iticket.ui.pages;

import az.iticket.basepage.BasePage;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomePage extends BasePage {
    final String SEARCH_BUTTON = "//button[contains(@class,'search')]";
    final String CART_BUTTON = "//button[@class='cart ico-btn']";
    final String AUTH_BUTTON = "//button[starts-with(@class,'profile')]";
    final String COPY_RIGHTS = "//h1[contains(text(),'ITICKET')]";

    public HomePage() {
        super();
    }

    public void openHomePage() {
        driver.get(BASE_URL);
    }

    @Step("Click on search button")
    public void clickSearchButton() {
        driver.findElement(By.xpath(SEARCH_BUTTON)).click();
        log.info("Search button clicked");
    }

    @Step("Click cart button")
    public void clickCartButton() {
        driver.findElement(By.xpath(CART_BUTTON)).click();
        log.info("Cart button clicked");
    }

    @Step("Click on auth button")
    public void clickAuthButton() {
        driver.findElement(By.xpath(AUTH_BUTTON)).click();
        log.info("Auth button clicked");
    }

    @Step("Get copyrights text")
    public String getCopyRights() {
       String text = driver.findElement(By.xpath(COPY_RIGHTS)).getText();
       log.info("Copy rights text:{} ",text);
       return text;
    }

    @Step("Search button is displayed on the Home page")
    public boolean isDisplayedSearchButton() {
        boolean isDisplayed = driver.findElement(By.xpath(SEARCH_BUTTON)).isDisplayed();
        log.info("Search button is displayed: {} " ,isDisplayed);
        return isDisplayed;
    }

    @Step("Auth button is displayed on the Home page")
    public boolean isDisplayedAuthButton() {
       boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AUTH_BUTTON))).isDisplayed();
       log.info("Auth button is displayed: {} " ,isDisplayed);
       return isDisplayed;
    }

    @Step("Cart button is displayed on the Home page")
    public boolean isDisplayedCartButton() {
       boolean isDisplayed = driver.findElement(By.xpath(CART_BUTTON)).isDisplayed();
       log.info("Cart button is displayed: {} " ,isDisplayed);
       return isDisplayed;
    }

    @Step("Copy rights text has on home page")
    public boolean isDisplayedCopyRights() {
        boolean isDisplayedText = driver.findElement(By.xpath(COPY_RIGHTS)).isDisplayed();
        log.info("Copy rights text:{} ",isDisplayedText);
        return isDisplayedText;
    }
}

