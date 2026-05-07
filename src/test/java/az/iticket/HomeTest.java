package az.iticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomeTest extends BaseTest {
    @DisplayName("Chek clickable search button")
    @Test
    public void searchButtonTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickSearchButton();
        driver.quit();
    }
    @DisplayName("Chek clickable cart button")
    @Test
    public void cartButtonTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickCartButton();
        driver.quit();
    }
    @DisplayName("Chek clickable Auth button")
    @Test
    public void authButtonTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickAuthButton();
        driver.quit();
    }
    @DisplayName("Get Copy Rights text")
    @Test
    public void getCopyRights() {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.getCopyRights();
        driver.quit();
    }
}
