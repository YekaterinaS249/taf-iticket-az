package az.iticket.ui.pages;

import az.iticket.basepage.BasePage;
import org.openqa.selenium.By;

public class SearchPage extends BasePage {
    private final String  SEARCH_INPUT = "//input[@id='desktop-search']";
    private final String CLOSE_BUTTON  = "//input[@id='desktop-search']/ancestor::div[contains(@class,'modal-dialog')]//button[contains(@class,'close')]";

    public SearchPage() {
        super();
    }

    public void setSearchInput(String searchInput) {
        driver.findElement(By.xpath(SEARCH_INPUT)).sendKeys(searchInput);
    }

    public void clickCloseButton() {
        driver.findElement(By.xpath(CLOSE_BUTTON)).click();
    }

}
