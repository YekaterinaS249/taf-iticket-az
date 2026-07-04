package az.iticket.ui.pages;

import az.iticket.ui.pages.basepage.BasePage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EventPage extends BasePage {
    private final String SESSION_PRICE = "//span[contains(@class,'session-tile__price')]";
    private final String FIRST_SESSION_PRICE = "(//span[contains(@class,'session-tile__price')])[1]";

    public EventPage() {
        super();

    }

    @Step("Get prices of all sessions")
    public List<String> getAllSessionPrices() {
        List<String> prices = driver.findElements(By.xpath(SESSION_PRICE))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        log.info("Session prices found: {}", prices);
        return prices;
    }

    @Step("Get price of first session")
    public String getFirstSessionPrice() {
        String price = waitForVisibility(FIRST_SESSION_PRICE).getText();
        log.info("Session price found: {}", price);
        return price;
    }

    @Step("Check first session price is displayed")
    public boolean isFirstSessionPriceDisplayed() {
        boolean isDisplayed = waitForVisibility(FIRST_SESSION_PRICE).isDisplayed();
        log.info("Session price found: {}", isDisplayed);
        return isDisplayed;
    }
}




