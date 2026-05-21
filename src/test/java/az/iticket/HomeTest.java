package az.iticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeTest extends BaseTest {
    @DisplayName("Get Copy Rights text")
    @Test
    public void getCopyRights() {
        homePage.getCopyRights();
        Assertions.assertEquals("ITICKET® - зарегистрированная торговая марка ООО «ITICKET».",homePage.getCopyRights());
    }

    @DisplayName("Сhek displayed login button")
    @Test
    public void loginButton() {
        Assertions.assertTrue(homePage.isDisplayedAuthButton());
    }

    @DisplayName("Chek displayed cart button")
    @Test
    public void cartButton() {
        Assertions.assertTrue(homePage.isDisplayedCartButton());
    }

    @DisplayName("Chek displayed search button")
    @Test
    public void searchButton() {
        Assertions.assertTrue(homePage.isDisplayedSearchButton());
    }

    @DisplayName("Chek displayed copyrights")
    @Test
    public void copyrightsButton() {
        Assertions.assertTrue(homePage.isDisplayedCopyRights());
    }
}
