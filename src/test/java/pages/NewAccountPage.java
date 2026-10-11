package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class NewAccountPage extends BasePage {
    // locators
    private final By customerId = By.name("cusid");
    private final By accountType = By.name("selaccount");
    private final By initialDeposit = By.name("inideposit");
    private final By submit = By.name("button2");

    private final By successMessage =
            By.xpath("//p[contains(@class,'heading3') and normalize-space()='Account Generated Successfully!!!']");
    private final By accountId =
            By.xpath("//tr[td[1]='Account ID']/td[2]");
    private final By registeredCustomerId =
            By.xpath("//tr[td[1]='Customer ID']/td[2]");
    private final By registeredCustomerName =
            By.xpath("//tr[td[1]='Customer Name']/td[2]");
    private final By registeredAccountType =
            By.xpath("//tr[td[1]='Account Type']/td[2]");
    private final By currentAmount =
            By.xpath("//tr[td[1]='Current Amount']/td[2]");

    private final By continueLink = By.linkText("Continue");

    public NewAccountPage(WebDriver driver) {super(driver);}

    public void createAccount(
            String customerIdValue,
            String accountTypeValue,
            String initialDepositValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(customerId));

        driver.findElement(customerId).sendKeys(customerIdValue);

        Select selectAccountType = new Select(driver.findElement(accountType));

        selectAccountType.selectByVisibleText(accountTypeValue);

        driver.findElement(initialDeposit).sendKeys(initialDepositValue);

        wait.until(ExpectedConditions.elementToBeClickable(submit));
        driver.findElement(submit).click();
    }

    public String getAccountId() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountId)).getText();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    public String getRegisteredCustomerId() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registeredCustomerId)).getText();
    }

    public String getRegisteredCustomerName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registeredCustomerName)).getText();
    }

    public String getRegisteredAccountType() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registeredAccountType)).getText();
    }

    public String getCurrentAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentAmount)).getText();
    }

    public HomePage continueToHome() {
        wait.until(ExpectedConditions.elementToBeClickable(continueLink));
        driver.findElement(continueLink).click();

        return new HomePage(driver);
    }
}
