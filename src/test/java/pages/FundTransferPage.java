package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FundTransferPage extends BasePage {
    private final By payerAccount = By.name("payersaccount");
    private final By payeeAccount = By.name("payeeaccount");
    private final By amount = By.name("ammount");
    private final By description = By.name("desc");
    private final By submit = By.name("AccSubmit");
    private final By reset = By.name("res");

    // Possible locators for a successful transfer
    private final By successMessage = By.xpath("//p[contains(@class,'heading3')]");
    private final By payerAccountResult = By.xpath("//tr[td[1]='Payers Account Number']/td[2]");
    private final By payeeAccountResult = By.xpath("//tr[td[1]='Payees Account Number']/td[2]");
    private final By transferAmount = By.xpath("//tr[td[1]='Amount']/td[2]");

    public FundTransferPage(WebDriver driver) {
        super(driver);
    }

    public void transfer(
            String payerAccountValue,
            String payeeAccountValue,
            String amountValue,
            String descriptionValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(payerAccount));

        driver.findElement(payerAccount).sendKeys(payerAccountValue);
        driver.findElement(payeeAccount).sendKeys(payeeAccountValue);
        driver.findElement(amount).sendKeys(amountValue);
        driver.findElement(description).sendKeys(descriptionValue);

        wait.until(ExpectedConditions.elementToBeClickable(submit));
        driver.findElement(submit).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    public String getPayerAccount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(payerAccountResult)).getText();
    }

    public String getPayeeAccount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(payeeAccountResult)).getText();
    }

    public String getTransferAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(transferAmount)).getText();
    }

    public void reset() {
        wait.until(ExpectedConditions.elementToBeClickable(reset));
        driver.findElement(reset).click();
    }
}
