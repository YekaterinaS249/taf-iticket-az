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
public class SearchPage extends BasePage {
    private final String SEARCH_INPUT = "//input[@id='search-input']";
    private final String SEARCH_ICON = "//div[contains(@class,'form-input') and contains(@class,'dropdown')]//div[@class='icon']";
    private final String SEARCH_DROPDOWN = "//div[contains(@class,'dropdown-panel')]";
    private final String TOP_EVENT_HEADER = "//div[contains(@class,'dropdown-panel')]//div[text()='Топ мероприятия']";
    private final String TOP_PLACES_HEADER = "//div[contains(@class,'dropdown-panel')]//div[text()='Топ площадки']";
    private final String EVENT_ITEMS = "//div[contains(@class,'dropdown-panel')]//ul[1]/li/a";
    private final String PLACE_ITEMS = "//div[contains(@class,'dropdown-panel')]//div[text()='Топ площадки']/following-sibling::ul/li[1]/a";


    public SearchPage() {
        super();
    }

    @Step("Enter '{searchInput}' into search input")
    public void setSearchInput(String searchInput) {
        WebElement searchInputElement = waitForVisibility(SEARCH_INPUT);
        log.info("Entering '{}' into search input", searchInput);
        searchInputElement.sendKeys(searchInput);
    }

    @Step("Clear search input")
    public void clearSearchInput() {
        waitForVisibility(SEARCH_INPUT).clear();
        log.info("Clearing search input");
    }

    @Step("Get search input placeholder")
    public String getSearchInputPlaceholder() {
        String placeholder = waitForVisibility(SEARCH_INPUT).getAttribute("placeholder");
        log.info("Search input placeholder: '{}'", placeholder);
        return placeholder;
    }

    @Step("Get search input value")
    public String getSearchInputValue() {
        String value = waitForVisibility(SEARCH_INPUT).getAttribute("value");
        log.info("Search input value: '{}'", value);
        return value;
    }

    @Step("Click outside the search dropdown")
    public void clickOutsideDropdown() {
        driver.findElement(By.tagName("body")).click();
        log.info("Clicking outside dropdown");
    }

    @Step("Verify search input is displayed")
    public boolean isSearchInputDisplayed() {
        boolean isDisplayed = waitForVisibility(SEARCH_INPUT).isDisplayed();
        log.info("Search input isDisplayed: '{}'", isDisplayed);
        return isDisplayed;
    }

    @Step("Click search icon")
    public void clickSearchIcon() {
        click(SEARCH_ICON);
        log.info("Clicking search icon");
    }

    @Step("Verify search icon is displayed")
    public boolean isSearchIconDisplayed() {
        boolean isDisplayed = waitForVisibility(SEARCH_ICON).isDisplayed();
        log.info("Search icon isDisplayed: '{}'", isDisplayed);
        return isDisplayed;
    }

    @Step("Verify search dropdown is displayed")
    public boolean isSearchDropdownDisplayed() {
        boolean isDisplayed = waitForVisibility(SEARCH_DROPDOWN).isDisplayed();
        log.info("Search dropdown isDisplayed: '{}'", isDisplayed);
        return isDisplayed;
    }

    @Step("Verify search dropdown is closed")
    public boolean isSearchDropdownClosed() {
        boolean dropdownClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(SEARCH_DROPDOWN)));
        log.info("Search dropdown isClosed: '{}'", dropdownClosed);
        return dropdownClosed;
    }

    @Step("Verify Top Event header is displayed")
    public boolean isTopEventHeaderDisplayed() {
        boolean eventHeader = waitForVisibility(TOP_EVENT_HEADER).isDisplayed();
        log.info("Top event header isDisplayed: '{}'", eventHeader);
        return eventHeader;
    }

    @Step("Get 'Top event' header text")
    public String getTopEventHeaderText() {
        String text = waitForVisibility(TOP_EVENT_HEADER).getText();
        log.info("Top event header text: '{}'", text);
        return text;
    }

    @Step("Verify Top Places header is displayed")
    public boolean isTopPlaceHeaderDisplayed() {
        boolean isDisplayed = waitForVisibility(TOP_PLACES_HEADER).isDisplayed();
        log.info("Top place header isDisplayed: '{}'", isDisplayed);
        return isDisplayed;
    }

    @Step("Get 'Top place' header text")
    public String getTopPlaceHeaderText() {
        String text = waitForVisibility(TOP_PLACES_HEADER).getText();
        log.info("Top place header text: '{}'", text);
        return text;
    }

    @Step("Get event items")
    public List<WebElement> getEventItems() {
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(EVENT_ITEMS)));
        log.info("Found {} event items", items.size());
        return items;
    }

    @Step("Click first event item")
    public void clickFirstEventItem() {
        click(EVENT_ITEMS);
        log.info("User clicked first event item");
    }

    @Step("Verify URL is '{expectedUrl}'")
    public boolean isUrlChanged(String expectedUrl) {
        boolean isUrlChanged = wait.until(ExpectedConditions.urlToBe(expectedUrl));
        log.info("URL changed to '{}': {}", expectedUrl, isUrlChanged);
        return isUrlChanged;
    }

    @Step("Verify URL is '{expectedUrl}''")
    public boolean isUrlChangedToVenue(String expectedUrl) {
        boolean result = wait.until(ExpectedConditions.urlToBe(expectedUrl));
        log.info("URL changed to venue page: {}", result);
        return result;
    }

    @Step("Get text of first event item")
    public String getFirstEventItemText() {
        String text = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(EVENT_ITEMS))).getText();
        log.info("First event item text: '{}'", text);
        return text;
    }

    @Step("Get texts of all event items")
    public List<String> getEventItemText() {
        List<String> texts = getEventItems()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        log.info("Event item texts: {}", texts);
        return texts;
    }

    @Step("Get href of first event item")
    public String getFirstEventItemsHref() {
        String href = waitForVisibility(EVENT_ITEMS).getAttribute("href");
        log.info("First event item href: '{}'", href);
        return href;
    }

    @Step("Click first place item")
    public void clickFirstPlaceItem() {
        log.info("Clicking first place item");
        click(PLACE_ITEMS);
    }

    @Step("Get href of first place item")
    public String getFirstPlaceItemsHref() {
        String href = waitForVisibility(PLACE_ITEMS).getAttribute("href");
        log.info("First place item href: '{}'", href);
        return href;

    }
}



