package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final By newCustomerLink = By.linkText("New Customer");
    private final By newAccountLink = By.linkText("New Account");
    private final By depositLink = By.linkText("Deposit");
    private final By withdrawalLink = By.linkText("Withdrawal");
    private final By fundTransferLink = By.linkText("Fund Transfer");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getManagerText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),'Manger Id')]")
                )
        ).getText();
    }

    public String getManagerId() {
        return getManagerText().split(":")[1].trim();
    }

    public NewCustomerPage openNewCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(newCustomerLink));
        driver.findElement(newCustomerLink).click();

        return new NewCustomerPage(driver);
    }

    public NewAccountPage openNewAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(newAccountLink));
        driver.findElement(newAccountLink).click();
        return new NewAccountPage(driver);
    }

    public DepositPage openDeposit() {
        wait.until(ExpectedConditions.elementToBeClickable(depositLink));
        driver.findElement(depositLink).click();
        return new DepositPage(driver);
    }

    public WithdrawalPage openWithdrawal() {
        wait.until(ExpectedConditions.elementToBeClickable(withdrawalLink));
        driver.findElement(withdrawalLink).click();
        return new WithdrawalPage(driver);
    }

    public FundTransferPage openFundTransfer() {
        wait.until(ExpectedConditions.elementToBeClickable(fundTransferLink));
        driver.findElement(fundTransferLink).click();
        return new FundTransferPage(driver);
    }
}
