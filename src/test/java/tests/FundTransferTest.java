package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FundTransferPage;
import pages.HomePage;
import pages.NewAccountPage;
import pages.NewCustomerPage;

public class FundTransferTest extends BaseTest {
    @Test
    public void transferMoney() {

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

        // 3. Create the payer account
        NewAccountPage newAccountPage = homePage.openNewAccount();

        newAccountPage.createAccount(
                customerId,
                "Savings",
                "5000"
        );

        String payerAccount = newAccountPage.getAccountId();

        homePage = newAccountPage.continueToHome();

        // 4. Create the payee account
        newAccountPage = homePage.openNewAccount();

        newAccountPage.createAccount(
                customerId,
                "Savings",
                "3000"
        );

        String payeeAccount = newAccountPage.getAccountId();

        homePage = newAccountPage.continueToHome();

        // 5. Open the Fund Transfer page
        FundTransferPage fundTransferPage = homePage.openFundTransfer();

        // 6. Transfer money between accounts
        fundTransferPage.transfer(
                payerAccount,
                payeeAccount,
                "1000",
                "Test"
        );

        // 7. Verify the transfer details
        Assert.assertEquals(fundTransferPage.getSuccessMessage(), "Fund Transfer Details");
        Assert.assertEquals(fundTransferPage.getPayerAccount(), payerAccount);
        Assert.assertEquals(fundTransferPage.getPayeeAccount(), payeeAccount);
        Assert.assertEquals(fundTransferPage.getTransferAmount(), "1000");
    }
}
