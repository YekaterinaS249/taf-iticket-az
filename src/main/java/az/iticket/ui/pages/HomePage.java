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
    final String SEARCH_INPUT = "//input[@id='search-input']";
    final String AUTH_BUTTON = "//button[.//span[text()='Войти']]";
    final String COPY_RIGHTS = "//span[contains(text(),'© 2016–2026 iTicket.GLOBAL. Все права защищены.')]";

    public HomePage() {
        super();
    }

    public void openHomePage() {
        driver.get(BASE_URL);
    }

    @Step("Click on search input")
    public void clickSearchInput() {
        driver.findElement(By.xpath(SEARCH_INPUT)).click();
        log.info("Search input clicked");
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

    @Step("Auth button is displayed on the Home page")
    public boolean isDisplayedAuthButton() {
       boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AUTH_BUTTON))).isDisplayed();
       log.info("Auth button is displayed: {} " ,isDisplayed);
       return isDisplayed;
    }

    @Step("Search input is displayed on the Home page")
    public boolean searchInputIsDisplayed() {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_INPUT))).isDisplayed();
        log.info("Search input is displayed: {} " ,isDisplayed);
        return isDisplayed;
    }

    @Step("Copy rights text has on home page")
    public boolean isDisplayedCopyRights() {
        boolean isDisplayedText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(COPY_RIGHTS))).isDisplayed();
        log.info("Copy rights text:{} ",isDisplayedText);
        return isDisplayedText;
    }
}

