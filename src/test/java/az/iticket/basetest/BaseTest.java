package az.iticket.basetest;

import az.iticket.ui.core.DriverManager;
import az.iticket.ui.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract  class BaseTest {
    protected HomePage homePage;

    @BeforeEach
    public void setup() {
        homePage = new HomePage();
        homePage.openHomePage();
    }
    @AfterEach
        public void tearDown() {
            DriverManager.quitDriver();
        }
    }

