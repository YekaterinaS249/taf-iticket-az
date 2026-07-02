package az.iticket.ui.test;

import az.iticket.ui.basetest.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Home Page")
@Feature("Home Page UI")
@Owner("Silantyeva Yekaterina")
public class HomeTest extends BaseTest {

    @DisplayName("Verify copyright text -UI-HMP-001")
    @Story("Verify copyright text")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldReturnCopyrightTextTest() {
        assertEquals("© 2016–2026 iTicket.GLOBAL. Все права защищены.",
                homePage.getCopyrightText(),
                "Copyright text should match the expected value.");
    }

    @DisplayName("Verify login button is displayed -UI-HMP-002")
    @Story("Verify login button visibility")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldDisplayLoginButtonTest() {
        assertTrue(homePage.isAuthButtonDisplayed(),
                "Login button should be displayed on the Home page.");
    }

    @DisplayName("Verify search input is displayed -UI-HMP-003")
    @Story("Verify search input is displayed")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void shouldDisplaySearchInputTest() {
        assertTrue(homePage.isSearchInputDisplayed(),
                "Search input should be displayed on the Home page.");
    }

    @DisplayName("Verify copyright text is displayed -UI-HMP-004")
    @Story("Verify copyright visibility")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void shouldDisplayCopyrightTextTest() {
        assertTrue(homePage.isCopyrightDisplayed(),
                "Copyright text should be displayed on the Home page.");

    }
}





