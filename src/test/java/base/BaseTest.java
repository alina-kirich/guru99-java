package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import utils.Util;

public class BaseTest {
    protected WebDriver driver;

    public WebDriver createDriver() {
        return new ChromeDriver();
    }

    @BeforeMethod
    public void setUp() {
        driver = createDriver();
    }

    protected HomePage loginAsManager() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        return loginPage.login(
                Util.USER_NAME,
                Util.PASSWORD
        );
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
