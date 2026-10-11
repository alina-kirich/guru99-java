package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewCustomerPage extends BasePage {

    // locators
    private final By customerName = By.name("name");
    private final By maleGender = By.cssSelector("input[value='m']");
    private final By femaleGender = By.cssSelector("input[value='f']");
    private final By dob = By.id("dob");
    private final By address = By.name("addr");
    private final By city = By.name("city");
    private final By state = By.name("state");
    private final By pin = By.name("pinno");
    private final By phone = By.name("telephoneno");
    private final By email = By.name("emailid");
    private final By password = By.name("password");
    private final By submit = By.name("sub");

    private final By successMessage =
            By.cssSelector("#customer .heading3");

    private final By registeredCustomerName =
            By.xpath("//table[@id='customer']//tr[td[1]='Customer Name']/td[2]");

    private final By registeredCustomerEmail =
            By.xpath("//table[@id='customer']//tr[td[1]='Email']/td[2]");

    private final By customerId =
            By.xpath("//table[@id='customer']//tr[td[1]='Customer ID']/td[2]");

    private final By continueLink = By.linkText("Continue");


    public NewCustomerPage(WebDriver driver) {
        super(driver);
    }

    // methods
    public void createCustomer(
            String name,
            String gender,
            String birthDate,
            String addressText,
            String cityName,
            String stateName,
            String pinCode,
            String phoneNumber,
            String emailAddress,
            String passwordValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(customerName));
        driver.findElement(customerName).sendKeys(name);

        if (gender.equalsIgnoreCase("male")) {
            driver.findElement(maleGender).click();
        } else {
            driver.findElement(femaleGender).click();
        }

        driver.findElement(dob).sendKeys(birthDate);
        driver.findElement(address).sendKeys(addressText);
        driver.findElement(city).sendKeys(cityName);
        driver.findElement(state).sendKeys(stateName);
        driver.findElement(pin).sendKeys(pinCode);
        driver.findElement(phone).sendKeys(phoneNumber);
        driver.findElement(email).sendKeys(emailAddress);
        driver.findElement(password).sendKeys(passwordValue);

        wait.until(ExpectedConditions.elementToBeClickable(submit));
        driver.findElement(submit).click();
    }
    public String getSuccessMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        ).getText();
    }

    public String getRegisteredCustomerName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(registeredCustomerName)
        ).getText();
    }

    public String getRegisteredCustomerEmail() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(registeredCustomerEmail)
        ).getText();
    }

    public String getCustomerId() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(customerId)
        ).getText();
    }

    public HomePage continueToHome(){
            wait.until(ExpectedConditions.elementToBeClickable(continueLink));
            driver.findElement(continueLink).click();
            return new HomePage(driver);
        }
}