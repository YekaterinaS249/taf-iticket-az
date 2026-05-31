package az.iticket.basetest;

import az.iticket.ui.core.DriverManager;
import az.iticket.ui.pages.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

@Slf4j
public abstract  class BaseTest {
    protected HomePage homePage;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        log.info("Test stared: {}.{}",
                testInfo.getTestClass().orElseThrow().getSimpleName(),
                testInfo.getTestMethod().orElseThrow().getName());

        homePage = new HomePage();
        homePage.openHomePage();
    }
    @AfterEach
        public void tearDown(TestInfo testInfo) {
        log.info("Test finished: {}.{}",
            testInfo.getTestClass().orElseThrow().getSimpleName(),
                testInfo.getTestMethod().orElseThrow().getName());
            DriverManager.quitDriver();
        }
    }

