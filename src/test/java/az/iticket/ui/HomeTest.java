package az.iticket.ui;

import az.iticket.basetest.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeTest extends BaseTest {
    @DisplayName("Get Copy Rights text")
    @Test
    public void getCopyRights() {
        homePage.getCopyRights();
        Assertions.assertEquals("© 2016–2026 iTicket.GLOBAL. Все права защищены.",homePage.getCopyRights());
    }

    @DisplayName("Сhek displayed login button")
    @Test
    public void loginButton() {
        Assertions.assertTrue(homePage.isDisplayedAuthButton());
    }

    @DisplayName("Search input displayed on thr Home Page")
    @Test
    public void searchInputDisplayedOnTheHomePage() {
        Assertions.assertTrue(homePage.searchInputIsDisplayed());
    }

    @DisplayName("Chek displayed copyrights")
    @Test
    public void copyrightsButton() {
        Assertions.assertTrue(homePage.isDisplayedCopyRights());

    }
}

