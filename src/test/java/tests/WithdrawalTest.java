package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewAccountPage;
import pages.NewCustomerPage;
import pages.WithdrawalPage;

public class WithdrawalTest extends BaseTest {
    @Test
    public void withdrawMoney() {

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
        homePage = newCustomerPage.continueToHome();

        // 3. Create a savings account
        NewAccountPage newAccountPage = homePage.openNewAccount();

        String initialDeposit = "5000";

        newAccountPage.createAccount(
                customerId,
                "Savings",
                initialDeposit
        );

        String accountId = newAccountPage.getAccountId();

        // 4. Return to the manager home page
        homePage = newAccountPage.continueToHome();

        // 5. Open the Withdrawal page
        WithdrawalPage withdrawalPage = homePage.openWithdrawal();

        String withdrawalAmount = "1000";

        // 6. Withdraw money from the account
        withdrawalPage.withdraw( accountId, withdrawalAmount, "Test");

        // 7. Verify the transaction details
        Assert.assertEquals(withdrawalPage.getSuccessMessage(), "Transaction details of Withdrawal");
        Assert.assertEquals(withdrawalPage.getAccountNumber(), accountId);

        String expectedBalance = "4000";
        Assert.assertEquals(withdrawalPage.getCurrentAmount(), expectedBalance,
                "Account balance should be updated after withdrawal"
        );
    }
}
