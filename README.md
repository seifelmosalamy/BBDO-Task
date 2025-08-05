# BBDO Task - Selenium TestNG Framework

A simple automated test framework for the Sauce Demo website using Selenium WebDriver and TestNG.

## Project Structure

```
BBDO Task/
├── src/
│   ├── main/java/
│   │   ├── pageobjects/          # Page Object Model classes
│   │   │   ├── LoginPage.java    # Login page elements and methods
│   │   │   └── ItemsPage.java    # Items/cart page elements and methods
│   │   └── resources/
│   │       ├── Base.java         # Base class with WebDriver setup
│   │       └── data.properties   # Configuration properties
│   └── test/
│       ├── java/
│       │   └── HappyFlowTestNGTest.java    # Main test class
│       └── resources/
│           └── happy-flow-testng.xml       # TestNG configuration
├── reports/                      # Generated test reports
├── screenshots/                  # Test execution screenshots
├── pom.xml                      # Maven configuration
└── README.md                    # This file
```

## Prerequisites

- Java 18 or higher
- Maven 3.6 or higher
- Chrome browser installed

## How to Run the Tests

### Simple Command

```bash
mvn clean test
```

This single command will:
- Clean the project
- Compile the code
- Run the test suite
- Generate reports and screenshots

### What the Test Does

The `HappyFlowTestNGTest` performs a complete e-commerce workflow:

1. **Login** to Sauce Demo website
2. **Add an item** to the shopping cart
3. **Navigate to checkout**
4. **Enter user information**
5. **Complete the purchase**
6. **Verify success message**

## Test Results

After running the tests, you can find:

- **Test Reports**: `target/test-output/html/index.html`
- **Screenshots**: `target/screenshots/` directory
- **Test Results**: `target/test-output/testng-results.xml`

## Screenshots Generated

The test automatically captures screenshots at key steps:
- `user_adds_an_item_to_the_cart.png`
- `user_goes_to_checkout.png`
- `user_entered_his_info.png`
- `checkout_page_is_displayed.png`
- `Finished checkout.png`

## Dependencies

The project uses:
- Selenium WebDriver 4.19.1
- TestNG 7.4.0
- ReportNG 1.1.4 (for HTML reports)
- WebDriverManager 5.8.0 (automatic driver management) 