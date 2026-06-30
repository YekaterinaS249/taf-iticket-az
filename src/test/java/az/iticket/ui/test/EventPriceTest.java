package az.iticket.ui.test;

import az.iticket.ui.basetest.BaseTest;
import az.iticket.ui.pages.EventPage;
import az.iticket.ui.pages.SearchPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Epic("Event Details")
@Feature("Session Pricing")
@Owner("Silantyeva Yekaterina")
public class EventPriceTest extends BaseTest {
    private SearchPage searchPage;
    private EventPage eventPage;

    @BeforeEach
    public void initEventPage() {
        searchPage = new SearchPage();
        eventPage = new EventPage();
    }

    @DisplayName("First session price is displayed on the event page -UI-PRC-004")
    @Story("Price display")
    @Test
    public void searchResultPriceIsDisplayedTest() {
        searchPage.clickSearchIcon();
        searchPage.setSearchInput("театр");
        searchPage.clickFirstEventItem();
        Assertions.assertTrue(eventPage.isFirstSessionPriceDisplayed());
    }

    @DisplayName("First session price contains the currency symbol -UI-PRC-002")
    @Story("Price currency")
    @Test
    public void searchResultPriceContainsCurrencyTest() {
        searchPage.clickSearchIcon();
        searchPage.setSearchInput("театр");
        searchPage.clickFirstEventItem();
        Assertions.assertTrue(eventPage.getFirstSessionPrice().contains("₼"));
    }

    @DisplayName("All session prices contain the currency symbol -UI-PRC-003")
    @Test
    public void searchResultAllPricesContainCurrencyTest() {
        searchPage.clickSearchIcon();
        searchPage.setSearchInput("театр");
        searchPage.clickFirstEventItem();
        List<String> prices = eventPage.getAllSessionPrice();
        for (String price : prices) {
            Assertions.assertTrue(price.contains("₼"));

        }
    }
}
