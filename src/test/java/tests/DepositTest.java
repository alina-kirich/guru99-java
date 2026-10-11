package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DepositPage;
import pages.HomePage;
import pages.NewAccountPage;
import pages.NewCustomerPage;

public class DepositTest extends BaseTest {
    @Test
    public void depositMoney() {

        // 1. Log in as manager
        HomePage homePage = loginAsManager();

        // 2. Create a new customer
        NewCustomerPage newCustomerPage = homePage.openNewCustomer();

        String email = "anna" + System.currentTimeMillis() + "@example.com";

        newCustomerPage.createCustomer(
                "Anna",
                "female",
                "01/20/1995",
                "London Street",
                "London",
                "London",
                "123456",
                "1234567890",
                email,
                "Password123!"
        );

        String customerId = newCustomerPage.getCustomerId();

        // Return to manager home
        homePage = newCustomerPage.continueToHome();

        // 3. Open a savings account
        NewAccountPage newAccountPage = homePage.openNewAccount();

        String initialDeposit = "5000";

        newAccountPage.createAccount( customerId, "Savings", initialDeposit);

        String accountId = newAccountPage.getAccountId();

        // 4. Return to the manager home page
        homePage = newAccountPage.continueToHome();

        // 5. Open the Deposit page
        DepositPage depositPage = homePage.openDeposit();

        // 6. Deposit money into the account
        String depositAmount = "1000";

        depositPage.deposit( accountId, depositAmount, "Test");

        // 7. Verify the transaction details
        Assert.assertEquals( depositPage.getSuccessMessage(), "Transaction details of Deposit");
        Assert.assertEquals(depositPage.getAccountNumber(), accountId);

        String expectedBalance = "6000";
        Assert.assertEquals(depositPage.getCurrentAmount(), expectedBalance,
                "Account balance should be updated after deposit"
        );
    }
}
