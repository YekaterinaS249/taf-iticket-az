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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void shouldDisplaySearchInput() {
        assertTrue(searchPage.isSearchInputDisplayed(),
                "Search input should be displayed");
    }

    @DisplayName("Search input placeholder contains correct text  -UI-SRH-002")
    @Story("Search input display")
    @Test
    public void shouldDisplayCorrectSearchInputPlaceholder() {
        assertEquals("Ищите мероприятия или места", searchPage.getSearchInputPlaceholder(),
                "Search input placeholder should match the expected text");
    }

    @DisplayName("Search icon is displayed on the page -UI-SRH-003")
    @Story("Search input display")
    @Test
    public void shouldDisplaySearchIcon() {
        assertTrue(searchPage.isSearchIconDisplayed(),
                "Search icon should be displayed");
    }

    @DisplayName("Clicking the search icon opens the dropdown  -UI-SRH-004")
    @Story("Search dropdown")
    @Test
    public void clickSearchIconOpenDropdownTest() {
        assertTrue(searchPage.isSearchDropdownDisplayed());
    }

    @DisplayName("Clicking the search input opens the dropdown  -UI-SRH-005")
    @Story("Search dropdown")
    @Test
    public void shouldOpenDropdownWhenClickingSearchIcon() {
        searchPage.setSearchInput("");
        assertTrue(searchPage.isSearchDropdownDisplayed(),
                "Search dropdown should be displayed");
    }

    @DisplayName("Dropdown contains the 'Top events' header -UI-SRH-006")
    @Story("Top events section")
    @Test
    public void shouldDisplayTopEventHeader() {
        assertTrue(searchPage.isTopEventHeaderDisplayed(),
              "Top Event header should be displayed");
    }

    @DisplayName("Top events' header contains correct text -UI-SRH-007")
    @Story("Top events section")
    @Test
    public void shouldDisplayCorrectTopEventHeaderText() {
        assertEquals("Топ мероприятия", searchPage.getTopEventHeaderText(),
                "Top Event header text should match the expected value");
    }

    @DisplayName("Dropdown contains the 'Top venues' header -UI-SRH-008")
    @Story("Top venues section")
    @Test
    public void shouldDisplayTopPlaceHeader() {
        assertTrue(searchPage.isTopPlaceHeaderDisplayed(),
                "Top Place header should be displayed");

    }

    @DisplayName("'Top venues' header contains correct text -UI-SRH-009")
    @Story("Top venues section")
    @Test
    public void shouldDisplayCorrectTopPlaceHeaderText() {
        assertEquals("Топ площадки", searchPage.getTopPlaceHeaderText(),
                "Top Place header text should match the expected value");
    }

    @DisplayName("Event items list is not empty -UI-SRH-010")
    @Story("Top events section")
    @Test
    public void shouldDisplayEventItems() {
        Assertions.assertFalse(searchPage.getEventItems().isEmpty(),
                "Event items list should not be empty");
    }

    @DisplayName("Clicking the first event item navigates to the event page -UI-SRH-011")
    @Story("Navigation from search results")
    @Test
    public void shouldNavigateToEventPageWhenClickingFirstEventItem() {
        String expectedHref = searchPage.getFirstEventItemsHref();
        searchPage.clickFirstEventItem();
        assertTrue(searchPage.isUrlChanged(expectedHref),
                "User should be redirected to the event page");
    }

    @DisplayName("Clicking the first place item navigates to the venue page -UI-SRH-012")
    @Story("Navigation from search results")
    @Test
    public void shouldNavigateToVenuePageWhenClickingFirstPlaceItem() {
        String expectedHref = searchPage.getFirstPlaceItemsHref();
        searchPage.clickFirstPlaceItem();
        assertTrue(searchPage.isUrlChangedToVenue(expectedHref),
                "User should be redirected to the venue page");
    }

    @DisplayName("Search query returns results  -UI-SRH-013")
    @Story("Search results")
    @Test
    public void shouldReturnSearchResults() {
        searchPage.setSearchInput("концерт");
        Assertions.assertFalse(searchPage.getEventItems().isEmpty(),
                "Search results should not be empty");
    }

    @DisplayName("Clearing the search input resets the entered value  -UI-SRH-014")
    @Story("Clear search input")
    @Test
    public void shouldClearSearchInput() {
        searchPage.setSearchInput("концерт");
        searchPage.clearSearchInput();
        assertEquals("", searchPage.getSearchInputValue(),
                "Search input should be empty");
    }

    @DisplayName("Clicking outside the dropdown closes it -UI-SRH-015")
    @Story("Search dropdown")
    @Test
    public void shouldCloseDropdownWhenClickingOutside() {
        searchPage.clickOutsideDropdown();
        assertTrue(searchPage.isSearchDropdownClosed(),
                "Search dropdown should be closed");
    }

    @DisplayName("Search results contain the search query '{0}' -UI-SRH-016")
    @Story("Search results")
    @ParameterizedTest
    @ValueSource(strings = {"Jazz", "цирк", "театр", "cinema"})
    public void shouldReturnResultsContainingSearchQuery(String query) {
        searchPage.setSearchInput(query);
        List<String> results = searchPage.getEventItemText();
        for (String result : results) {
            assertTrue(result.toLowerCase().contains(query.toLowerCase()),
                    "Search result should contain the entered query");
        }
    }

    @DisplayName("Search with a non-existent query returns no results -UI-SRH-017")
    @Story("Search results")
    @Test
    public void shouldReturnNoResultsForUnknownQuery() {
        searchPage.setSearchInput("qwerty");
        List<String> results = searchPage.getEventItemText();
        assertTrue(results.isEmpty(),
                "Search results should be empty");
    }

    @DisplayName("Clearing the search input resets to the default state -UI-SRH-018")
    @Story("Clear search input")
    @Test
    public void shouldResetSearchToDefaultStateAfterClearingInput() {
        searchPage.setSearchInput("концерт");
        searchPage.clearSearchInput();
        assertTrue(searchPage.isTopEventHeaderDisplayed(),
                "Top Event header should be displayed after clearing the search input");
    }

    @DisplayName("Search by partial word returns results -UI-SRH-019")
    @Story("Partial search")
    @Test
    public void shouldReturnResultsForPartialSearchQuery() {
        String query = "теа";
        searchPage.setSearchInput(query);
        Assertions.assertFalse(searchPage.getFirstEventItemText().isEmpty(),
                "Search should return results for a partial query");
    }
}


