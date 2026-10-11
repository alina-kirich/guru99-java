package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewAccountPage;
import pages.NewCustomerPage;

public class NewAccountTest extends BaseTest {

    @Test
    public void createNewAccount() {

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

        // 3. Get the customer ID from the registration result
        String customerId = newCustomerPage.getCustomerId();

        // 4. Open the New Account page
        homePage = newCustomerPage.continueToHome();
        NewAccountPage newAccountPage = homePage.openNewAccount();

        // 5. Create an account for the new customer
        newAccountPage.createAccount(
                customerId,
                "Savings",
                "5000"
        );

        // 6. Verify the account details
        Assert.assertEquals(newAccountPage.getSuccessMessage(), "Account Generated Successfully!!!");
        Assert.assertEquals(newAccountPage.getRegisteredCustomerId(), customerId);
        Assert.assertEquals(newAccountPage.getRegisteredCustomerName(), "Anna");
        Assert.assertEquals(newAccountPage.getRegisteredAccountType(), "Savings");
        Assert.assertEquals(newAccountPage.getCurrentAmount(), "5000");
        Assert.assertFalse(newAccountPage.getAccountId().trim().isEmpty(), "Account ID should not be empty");
    }
}
