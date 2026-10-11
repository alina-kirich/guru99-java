package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewCustomerPage;


public class NewCustomerTest extends BaseTest {

    @Test
    public void createNewCustomer()  {

        HomePage homePage = loginAsManager();

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

        Assert.assertEquals(newCustomerPage.getSuccessMessage(), "Customer Registered Successfully!!!");
        Assert.assertEquals(newCustomerPage.getRegisteredCustomerName(), "Anna");
        Assert.assertEquals(newCustomerPage.getRegisteredCustomerEmail(), email);
    }

}
