# Guru99 Bank Automation Framework

A learning project for web UI test automation using Java, Selenium WebDriver, TestNG, and Maven. The project follows the Page Object Model (POM) design pattern.

## Tech Stack

- Java 21
- Selenium WebDriver 4.48.0
- TestNG 7.12.0
- Maven
- Chrome / ChromeDriver
- Jackson for JSON test data

## Project Structure

```text
src/test/java/
├── base/
│   ├── BasePage.java
│   └── BaseTest.java
├── pages/
│   ├── LoginPage.java
│   ├── HomePage.java
│   ├── NewCustomerPage.java
│   ├── NewAccountPage.java
│   ├── DepositPage.java
│   ├── WithdrawalPage.java
│   └── FundTransferPage.java
├── tests/
│   ├── LoginTest.java
│   ├── NewCustomerTest.java
│   ├── NewAccountTest.java
│   ├── DepositTest.java
│   ├── WithdrawalTest.java
│   └── FundTransferTest.java
└── utils/
    ├── Util.java
    ├── JsonUtils.java
    ├── LoginData.java
    └── ScreenshotUtils.java

src/test/resources/
├── config.example.properties
└── testdata/
    └── loginData.json
```

## Implemented Features

- Page Object Model (POM)
- Shared `BasePage` and `BaseTest` classes
- Positive and negative login scenarios
- TestNG `DataProvider`
- JSON-based login test data
- Explicit waits using `WebDriverWait`
- Test scenarios for customer creation, account creation, deposit, withdrawal, and fund transfer
- Timestamped screenshots

## Configuration

1. Copy `config.example.properties` to `config.properties`.
2. Set the Guru99 Bank URL, valid login credentials, and expected page title in `config.properties`.
3. Keep `config.properties` out of version control. Do not commit real credentials.

## Running Tests

Run the tests with Maven:

```bash
mvn test
```

Make sure Java 21, Maven, and Google Chrome are installed and configured.

## Notes

This is a learning project built against the Guru99 Bank demo application. Test results may depend on the availability and behavior of the demo site.