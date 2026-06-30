package az.iticket.ui.test;

import az.iticket.ui.basetest.BaseTest;
import az.iticket.ui.pages.SearchPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

@Epic("Search")
@Feature("Search Autocomplete")
@Owner("Silantyeva Yekaterina")
public class SearchTest extends BaseTest {
    private SearchPage searchPage;

    @BeforeEach
    public void initSearchPage() {
        searchPage = new SearchPage();
        searchPage.clickSearchIcon();
    }

    @DisplayName("Search input is displayed on the page -UI-SRH-001")
    @Story("Search input display")
    @Test
    public void searchInputIsDisplayedTest() {
        Assertions.assertTrue(searchPage.isSearchInputDisplayed());
    }

    @DisplayName("Search input placeholder contains correct text  -UI-SRH-002")
    @Story("Search input display")
    @Test
    public void searchInputPlaceholderTextTest() {
        Assertions.assertEquals("Ищите мероприятия или места", searchPage.getSearchInputPlaceholder());
    }

    @DisplayName("Search icon is displayed on the page -UI-SRH-003")
    @Story("Search input display")
    @Test
    public void searchIconIsDisplayedTest() {
        Assertions.assertTrue(searchPage.isSearchIconDisplayed());
    }

    @DisplayName("Clicking the search icon opens the dropdown  -UI-SRH-004")
    @Story("Search dropdown")
    @Test
    public void clickSearchIconOpenDropdownTest() {
        Assertions.assertTrue(searchPage.isSearchDropdownDisplayed());
    }

    @DisplayName("Clicking the search input opens the dropdown  -UI-SRH-005")
    @Story("Search dropdown")
    @Test
    public void clickSearchInputOpenDropdownTest() {
        searchPage.setSearchInput("");
        Assertions.assertTrue(searchPage.isSearchDropdownDisplayed());
    }

    @DisplayName("Dropdown contains the 'Top events' header -UI-SRH-006")
    @Story("Top events section")
    @Test
    public void dropdownContainsTopEventHeaderTest() {
        Assertions.assertTrue(searchPage.isTopEventHeaderDisplayed());
    }

    @DisplayName("Top events' header contains correct text -UI-SRH-007")
    @Story("Top events section")
    @Test
    public void topEventHeaderTextTest() {
        Assertions.assertEquals("Топ мероприятия", searchPage.getTopEventHeaderText());
    }

    @DisplayName("Dropdown contains the 'Top venues' header -UI-SRH-008")
    @Story("Top venues section")
    @Test
    public void dropdownContainsTopPlaceEventHeaderTest() {
        Assertions.assertTrue(searchPage.isTopPlaceHeaderDisplayed());

    }

    @DisplayName("'Top venues' header contains correct text -UI-SRH-009")
    @Story("Top venues section")
    @Test
    public void topPlaceHeaderTextTest() {
        Assertions.assertEquals("Топ площадки", searchPage.getTopPlaceHeaderText());
    }

    @DisplayName("Event items list is not empty -UI-SRH-010")
    @Story("Top events section")
    @Test
    public void eventItemsIsNotEmptyTest() {
        Assertions.assertFalse(searchPage.getEventItems().isEmpty());
    }

    @DisplayName("Clicking the first event item navigates to the event page -UI-SRH-011")
    @Story("Navigation from search results")
    @Test
    public void clickFirstEventItemNavigationsToEventPageTest() {
        String expectedHref = searchPage.getFirstEventItemsHref();
        searchPage.clickFirstEventItem();
        Assertions.assertTrue(searchPage.isUrlChanged(expectedHref));
    }

    @DisplayName("Clicking the first place item navigates to the venue page -UI-SRH-012")
    @Story("Navigation from search results")
    @Test
    public void clickFirstPlaceItemNavigationsToVenuePageTest() {
        String expectedHref = searchPage.getFirstPlaceItemsHref();
        searchPage.clickFirstPlaceItem();
        Assertions.assertTrue(searchPage.isUrlChangedToVenue(expectedHref));
    }

    @DisplayName("Search query returns results  -UI-SRH-013")
    @Story("Search results")
    @Test
    public void searchInputReturnResultsTest() {
        searchPage.setSearchInput("концерт");
        Assertions.assertFalse(searchPage.getEventItems().isEmpty());
    }

    @DisplayName("Clearing the search input resets the entered value  -UI-SRH-014")
    @Story("Clear search input")
    @Test
    public void searchInputClearResultsTest() {
        searchPage.setSearchInput("концерт");
        searchPage.clearSearchInput();
        Assertions.assertEquals("", searchPage.getSearchInputValue());
    }

    @DisplayName("Clicking outside the dropdown closes it -UI-SRH-015")
    @Story("Search dropdown")
    @Test
    public void clickOutsideClosesDropdownTest() {
        searchPage.clickOutsideDropdown();
        Assertions.assertTrue(searchPage.isSearchDropdownClosed());
    }

    @DisplayName("Search results contain the search query '{0}' -UI-SRH-016")
    @Story("Search results")
    @ParameterizedTest
    @ValueSource(strings = {"Jazz", "цирк", "театр", "cinema"})
    public void searchResultContainsSearchQueryTest(String query) {
        searchPage.setSearchInput(query);
        List<String> results = searchPage.getEventItemText();
        for (String result : results) {
            Assertions.assertTrue(result.toLowerCase().contains(query.toLowerCase()));
        }
    }

    @DisplayName("Search with a non-existent query returns no results -UI-SRH-017")
    @Story("Search results")
    @Test
    public void searchNoResultsTest() {
        searchPage.setSearchInput("qwerty");
        List<String> results = searchPage.getEventItemText();
        Assertions.assertTrue(results.isEmpty());
    }

    @DisplayName("Clearing the search input resets to the default state -UI-SRH-018")
    @Story("Clear search input")
    @Test
    public void clearSearchInputResetsToDefaultTest() {
        searchPage.setSearchInput("концерт");
        searchPage.clearSearchInput();
        Assertions.assertTrue(searchPage.isTopEventHeaderDisplayed());
    }

    @DisplayName("Search by partial word returns results -UI-SRH-019")
    @Story("Partial search")
    @Test
    public void searchByPartialWordTest() {
        String query = "теа";
        searchPage.setSearchInput(query);
        Assertions.assertFalse(searchPage.getFirstEventItemText().isEmpty());

    }
}

