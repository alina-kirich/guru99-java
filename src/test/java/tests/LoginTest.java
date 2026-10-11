package tests;

import java.io.IOException;

import base.BaseTest;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HomePage;
import pages.LoginPage;
import utils.JsonUtils;
import utils.LoginData;
import utils.ScreenshotUtils;
import utils.Util;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    // Read test data from JSON and convert it to Object[][]
    // format required by the TestNG DataProvider
    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {

        String filePath = "src/test/resources/testdata/loginData.json";
        LoginData[] negativeData = JsonUtils.readLoginData(filePath);
        Object[][] result = new Object[negativeData.length + 1][3];

        // Successful login uses credentials from config.properties
        result[0][0] = Util.USER_NAME;
        result[0][1] = Util.PASSWORD;
        result[0][2] = "Login successful";

        // Negative login scenarios are read from JSON
        for (int i = 0; i < negativeData.length; i++) {
            result[i + 1][0] = negativeData[i].getUsername();
            result[i + 1][1] = negativeData[i].getPassword();
            result[i + 1][2] = negativeData[i].getExpectedResult();
        }

        return result;
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) throws IOException {
        // Open the browser and navigate to the application URL
        loginPage.open();

        // Enter the username and password, then click Login
        HomePage homePage = loginPage.login(username, password);

        // Check the expected result
        // If login is successful, verify the page title and manager information
        if (expectedResult.equals("Login successful")) {

            Assert.assertEquals(driver.getTitle(), Util.EXPECTED_TITLE);

            String managerText = homePage.getManagerText();
            Assert.assertTrue(managerText.startsWith("Manger Id"));

            String managerId = homePage.getManagerId();
            Assert.assertFalse(managerId.isEmpty());

            // Take a screenshot after verifying successful login
            // ScreenshotUtils contains the screenshot implementation
            ScreenshotUtils.takeScreenshot(driver, "loginSuccess");

        } else {
            // If login fails, an alert appears
            // Verify the alert message and accept it
            Assert.assertEquals(loginPage.getAlertText(), expectedResult);
            loginPage.acceptAlert();

        }
    }
}
