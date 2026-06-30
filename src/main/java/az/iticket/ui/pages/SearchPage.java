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
        WebElement searchInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_INPUT)));
        log.info("Entering '{}' into search input" , searchInput);
        searchInputElement.sendKeys(searchInput);
    }

    @Step("Clear search input")
    public void clearSearchInput() {
        driver.findElement(By.xpath(SEARCH_INPUT)).clear();
        log.info("Clearing search input");
    }

    @Step("Get search input placeholder")
    public String getSearchInputPlaceholder() {
        String placeholder = driver.findElement(By.xpath(SEARCH_INPUT)).getAttribute("placeholder");
        log.info("Search input placeholder: '{}'", placeholder);
        return placeholder;
    }

    @Step("Get search input value")
    public String getSearchInputValue() {
        String value = driver.findElement(By.xpath(SEARCH_INPUT)).getAttribute("value");
        log.info("Search input value: '{}'", value);
        return value;
    }

    @Step("Click outside the search dropdown")
    public void clickOutsideDropdown(){
        driver.findElement(By.tagName("body")).click();
        log.info("Clicking outside dropdown");
    }

    @Step("Check if search input is displayed")
    public boolean isSearchInputDisplayed() {
        boolean isDisplayed = driver.findElement(By.xpath(SEARCH_INPUT)).isDisplayed();
        log.info("Search input isDisplayed: '{}'", isDisplayed);
        return isDisplayed;
    }

    @Step("Click search icon")
    public void clickSearchIcon() {
        driver.findElement(By.xpath(SEARCH_ICON)).click();
        log.info("Clicking search icon");
    }

    @Step("Check if search icon is displayed")
    public boolean isSearchIconDisplayed() {
        boolean isDisplayed = driver.findElement(By.xpath(SEARCH_ICON)).isDisplayed();
        log.info("Search icon isDisplayed: '{}'", isDisplayed);
        return isDisplayed;
    }

    @Step("Check if search dropdown is displayed")
    public boolean isSearchDropdownDisplayed() {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_DROPDOWN))).isDisplayed();
        log.info("Search dropdown isDisplayed: '{}'", isDisplayed);
        return isDisplayed;
    }

    @Step("Check if search dropdown is closed")
    public boolean isSearchDropdownClosed() {
        boolean dropdownClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(SEARCH_DROPDOWN)));
        log.info("Search dropdown isClosed: '{}'", dropdownClosed);
        return dropdownClosed;
    }

    @Step("Check if 'Top  event' header is displayed")
    public boolean isTopEventHeaderDisplayed() {
        boolean eventHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOP_EVENT_HEADER))).isDisplayed();
        log.info("Top event header isDisplayed: '{}'", eventHeader);
        return eventHeader;
    }

    @Step("Get 'Top event' header text")
    public String getTopEventHeaderText() {
        String text = driver.findElement(By.xpath(TOP_EVENT_HEADER)).getText();
        log.info("Top event header text: '{}'", text);
        return text;
    }

    @Step("Check if 'Top place' header is displayed")
    public boolean isTopPlaceHeaderDisplayed() {
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOP_PLACES_HEADER))).isDisplayed();
        log.info("Top place header isDisplayed: '{}'", isDisplayed);
        return isDisplayed;
    }

    @Step("Get 'Top place' header text")
    public String getTopPlaceHeaderText() {
        String text = driver.findElement(By.xpath(TOP_PLACES_HEADER)).getText();
        log.info("Top place header text: '{}'", text);
        return text;
    }

    @Step("Get list of event items")
    public List<WebElement> getEventItems() {
        List<WebElement> items = driver.findElements(By.xpath(EVENT_ITEMS));
        log.info("Found {} event items", items.size());
        return items;
    }

    @Step("Click first event item")
    public void clickFirstEventItem() {
        WebElement firstEvent = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EVENT_ITEMS)));
        log.info("First event item is clicked: '{}'", firstEvent);
        firstEvent.click();
    }

    @Step("Check  URL changed to '{expectedUrl}'")
    public boolean isUrlChanged(String expectedUrl) {
        boolean result = wait.until(ExpectedConditions.urlToBe(expectedUrl));
        log.info("Url changed to expected value: '{}'", result);
        return result;
    }

    @Step("Check  URL changed to venue page '{expectedUrl}'")
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
        List<String> texts = driver.findElements(By.xpath(EVENT_ITEMS))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        log.info("Event item texts: {}" , texts);
        return texts;
    }

    @Step("Get href of first event item")
    public String getFirstEventItemsHref() {
        String href = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EVENT_ITEMS))).getAttribute("href");
        log.info("First event item href: '{}'", href);
        return href;
    }

    @Step("Click first place item")
    public void clickFirstPlaceItem() {
        log.info("Clicking first place item");
        driver.findElement(By.xpath(PLACE_ITEMS)).click();
    }

    @Step("Get href of first place item")
    public String getFirstPlaceItemsHref() {
        String href = driver.findElement(By.xpath(PLACE_ITEMS)).getAttribute("href");
        log.info("First place item href: '{}'", href);
        return href;

    }
}


