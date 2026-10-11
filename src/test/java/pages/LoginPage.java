package pages;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Util;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(Util.BASE_URL);
    }

    public HomePage login(String username, String password) {
        // The login field is expected to become visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));
        driver.findElement(By.name("uid")).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        driver.findElement(By.name("password")).sendKeys(password);
        // Wait for the login button to become clickable, then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.name("btnLogin")));
        driver.findElement(By.name("btnLogin")).click();

        // After click ‘Login’, Selenium is already on the new page.
        // We create an object that describes this page and pass it to the test
        return new HomePage(driver);
    }

    public String getAlertText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}
