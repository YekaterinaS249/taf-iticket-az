package az.iticket.ui.pages;

import az.iticket.ui.pages.basepage.BasePage;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


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

    @Step("Click authentication button")
    public void clickAuthButton() {
        waitForClickable(AUTH_BUTTON).click();
        log.info("Authentication button clicked");
    }

    @Step("Get copyright text")
    public String getCopyrightText() {
        String text = waitForVisibility(COPY_RIGHTS).getText();
        log.info("Copyright text: {} ", text);
        return text;
    }

    @Step("Verify authentication button is displayed")
    public boolean isAuthButtonDisplayed() {
        boolean isDisplayed = waitForVisibility(AUTH_BUTTON).isDisplayed();
        log.info("Authentication button is displayed: {} ", isDisplayed);
        return isDisplayed;
    }

    @Step("Verify search input is displayed")
    public boolean isSearchInputDisplayed() {
        boolean isDisplayed = waitForVisibility(SEARCH_INPUT).isDisplayed();
        log.info("Search input is displayed: {} ", isDisplayed);
        return isDisplayed;
    }

    @Step("Verify copyright text is displayed")
    public boolean isCopyrightDisplayed() {
        boolean isDisplayed = waitForVisibility(COPY_RIGHTS).isDisplayed();
        log.info("Copyright text is displayed: {} ", isDisplayed);
        return isDisplayed;
    }
}




