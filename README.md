# Selenium WebDriver Automation Framework
This repository contains a robust and scalable Selenium WebDriver automation framework using Java, JUnit, and integrates with TestRail for test case management. It is designed to provide a solid foundation for automating web application testing while allowing for test result synchronization and visibility with TestRail. 

It will include example Sanitized Test Cases and demonstrate how to organize code into different packages and call classes from one package in another using a Page Object Model (POM) pattern.

## Features

* **Modular and Reusable:** The framework promotes code reusability through page object models and utility classes.
* **JUnit Integration:** Utilizes JUnit for test management and execution.
* **WebDriver Management:** Handles WebDriver initialization and browser management efficiently.
* **Configuration Management:** Uses properties files for easy configuration of browsers, URLs, and other settings.
* **TestRail Integration:** Seamlessly integrates with TestRail to update test results and manage test cases.
* **Reporting:** Generates detailed reports using JUnit's built-in reporting or external reporting libraries.
* **Logging:** Provides comprehensive logging for debugging and troubleshooting.
* **Data Driven Testing:** Examples of Data driven testing using external files.
* **Explicit Waits:** Implements explicit waits to handle dynamic web elements.
* **Screenshot Capture:** Automatically captures screenshots on test failures.
* **Page Object Model (POM):** Demonstrates best practices for organizing test code using the POM pattern.
* **Sanitized Test Cases:** Provides examples of test cases with sensitive data removed or replaced.

## Prerequisites

* Java Development Kit (JDK) 8 or higher
* Maven
* An Integrated Development Environment (IDE) like IntelliJ IDEA
* Browser drivers (ChromeDriver, GeckoDriver, etc.)
* TestRail Account and API Key
* TestRail Java API library (included as a dependency)

  ## Getting Started

1.  **Clone the repository:**

    ```bash
    git clone [repository URL]
    cd [repository directory]
    ```

2.  **Import the project into your IDE:**

    * For IntelliJ IDEA: File -> Open -> Select the `pom.xml` file.

3.  **Install dependencies:**

    ```bash
    mvn clean install
    ```

4.  **Configure the `config.properties` file:**

    * Locate the `src/test/resources/config.properties` file.
    * Modify the properties to match your environment (browser, URL, TestRail credentials, etc.).
    * Add your TestRail URL, username, API key, project ID, and other required TestRail settings.

5.  **Place your browser drivers in a location accessible by your system's PATH variable, or configure the path directly in your config.properties file.**

## Project Structure

Selenium-WebDriver-Automation-Framework/

    ├── src/
    │     ├── main/
    │     │   └── java/
    │     │       └── com/
    │     │           └── yourCompany/
    │     │               ├── pages/                          // Page object classes
    │     │               │   ├── HomePage.java
    │     │               │   └── LoginPage.java
    │     │               ├── testrail/                       // TestRail integration
    │     │               │   └── TestRailIntegration.java
    │     │               └── utilities/                      // Utility classes
    │     │                   └── WebDriverManager.java
    │     └── test/
    │         ├── java/
    │         │   └── com/
    │         │       └── yourcompany/
    │         │           ├── base/                           // Base test class
    │         │           │   └── BaseTest.java
    │         │           └── tests/                          // Test classes
    │         │               ├── HomePageTest.java
    │         │               └── LoginPageTest.java
    │         └── resources/                                  // Configuration file
    │             └── config.properties
    └── README.md


## TestRail Integration

* The framework includes classes within the `com.yourcompany.testrail` package that handle communication with the TestRail API.
* You will need to annotate your JUnit test methods with the corresponding TestRail test case IDs.
* After each test execution, the framework will automatically update the test results in TestRail.
* Ensure the TestRail API library is correctly included in your pom.xml file.
* TestRail configuration is done within the config.properties file.

## Running Tests

* **Using Maven:**

    ```bash
    mvn test
    ```

* **From your IDE:**

    * Right-click on a test class or test suite and select "Run as JUnit Test".

## Reporting

JUnit generates reports that can be customized. TestRail will also contain the results of the tests.

## Configuration

The `config.properties` file in `src/test/resources/` allows you to configure various settings, including:

* `browser`: The browser to use (e.g., Chrome, Firefox, Safari).
* `baseUrl`: The base URL of the application under test.
* `implicitWait`: Implicit wait timeout.
* `explicitWait`: Explicit wait timeout.
* `driverPath`: Path to the browser driver.
* `testrail.url`: Your TestRail URL.
* `testrail.username`: Your TestRail username.
* `testrail.apikey`: Your TestRail API key.
* `testrail.projectid`: The ID of your TestRail project.
* `testrail.runid`: The ID of the TestRail test run.

## Page Object Model (POM) and Package Structure

* The `com.example.automationFramework.pages` package contains page object classes, each representing a web page or component.
* The `com.example.automationFramework.tests` contains the test classes that call methods from the page object classes to interact with the application.
* This demonstrates how to call methods from page object classes to perform actions and validations.

## Sanitized Test Cases

* Example test cases are provided with sensitive data replaced with placeholder values or generated data.
* This ensures that no real user data is exposed in the repository.
* Example: instead of using real user credentials, the tests may use `"testuser"` and `"password123"`.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.
