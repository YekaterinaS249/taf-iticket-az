package az.iticket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final String SEARCH_BUTTON = "//button[contains(@class,'search')]";
    private final String CART_BUTTON = "//button[@class='cart ico-btn']";
    private final String AUTH_BUTTON = "//button[starts-with(@class,'profile')]";
    private final String COPY_RIGHTS = "//h1[contains(text(),'ITICKET')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        driver.get(BASE_URL);
    }

    public void clickSearchButton() {
        driver.findElement(By.xpath(SEARCH_BUTTON)).click();
    }

    public void clickCartButton() {
        driver.findElement(By.xpath(CART_BUTTON)).click();
    }

    public void clickAuthButton() {
        driver.findElement(By.xpath(AUTH_BUTTON)).click();
    }

    public String getCopyRights() {
        return driver.findElement(By.xpath(COPY_RIGHTS)).getText();
    }

    public boolean isDisplayedSearchButton() {
        return driver.findElement(By.xpath(SEARCH_BUTTON)).isDisplayed();
    }

    public boolean isDisplayedAuthButton() {
        return driver.findElement(By.xpath(AUTH_BUTTON)).isDisplayed();
    }

    public boolean isDisplayedCartButton() {
        return driver.findElement(By.xpath(CART_BUTTON)).isDisplayed();
    }

    public boolean isDisplayedCopyRights() {
        return driver.findElement(By.xpath(COPY_RIGHTS)).isDisplayed();
    }


}
