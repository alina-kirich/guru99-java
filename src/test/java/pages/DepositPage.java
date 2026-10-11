package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DepositPage extends BasePage {
    private final By accountNumber = By.name("accountno");
    private final By amount = By.name("ammount");
    private final By description = By.name("desc");
    private final By submit = By.name("AccSubmit");
    private final By reset = By.name("res");

    // Possible locators for a successful deposit
    private final By successMessage =
            By.xpath("//p[contains(@class,'heading3')]");
    private final By accountNumberResult =
            By.xpath("//tr[td[1]='Account No']/td[2]");
    private final By currentAmount =
            By.xpath("//tr[td[1]='Current Balance']/td[2]");

    public DepositPage(WebDriver driver) {
        super(driver);
    }

    public void deposit(
            String accountNumberValue,
            String amountValue,
            String descriptionValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumber));
        driver.findElement(accountNumber).sendKeys(accountNumberValue);

        driver.findElement(amount).sendKeys(amountValue);

        driver.findElement(description).sendKeys(descriptionValue);

        wait.until(ExpectedConditions.elementToBeClickable(submit));
        driver.findElement(submit).click();
    }

    // Possible methods
    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    public String getAccountNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumberResult)).getText();
    }

    public String getCurrentAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentAmount)).getText();
    }

    public void reset() {
        wait.until(ExpectedConditions.elementToBeClickable(reset));
        driver.findElement(reset).click();
    }
}
