package az.iticket.ui.pages;
import az.iticket.ui.pages.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;


public class EventPage extends BasePage {
    private final String SESSION_PRICE = "//span[contains(@class,'session-tile__price')]";
    private final String FIRST_SESSION_PRICE = "(//span[contains(@class,'session-tile__price')])[1]";

    public EventPage() {
        super();

    }

    public List<String> getAllSessionPrice() {
        return driver.findElements(By.xpath(SESSION_PRICE))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getFirstSessionPrice() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FIRST_SESSION_PRICE))).getText();
    }

    public boolean isFirstSessionPriceDisplayed() {
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_SESSION_PRICE))).isDisplayed();

    }
}


