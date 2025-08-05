package test.java;

import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.ItemsPage;
import pageobjects.LoginPage;
import resources.Base;

import java.io.IOException;

public class HappyFlowTestNGTest extends Base {

    private LoginPage loginPage;
    private ItemsPage itemsPage;
    
    // Test data variables
    private static final String WEBSITE_URL = "https://www.saucedemo.com/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";
    private static final String FIRST_NAME = "seif";
    private static final String LAST_NAME = "elmosalamy";
    private static final String POSTAL_CODE = "123";
    private static final String EXPECTED_INVENTORY_URL = "https://www.saucedemo.com/inventory.html";

    @BeforeClass
    public void setUp() throws IOException {
        // Initialize driver using Base class method
        driver = initializeDriver();
        
        // Initialize page objects
        loginPage = new LoginPage(driver);
        itemsPage = new ItemsPage(driver);
    }

    @Test(description = "Happy Flow: Login, Choose Item, Checkout and Verify")
    public void testHappyFlow() throws InterruptedException {
        // Step 1: Login
        driver.get(WEBSITE_URL);
        loginPage.getUsername().sendKeys(USERNAME);
        loginPage.getPassword().sendKeys(PASSWORD);
        loginPage.loginButton().click();
        
        // Verify successful login
        Assert.assertEquals(driver.getCurrentUrl(), EXPECTED_INVENTORY_URL, "Login failed");
        
        // Step 2: Add item to cart
        Thread.sleep(2000);
        
        // Get the item name before adding to cart
        String selectedItemName = itemsPage.getRedItemName();
        
        itemsPage.getRedItemButton().click();
        
        // Take screenshot of adding item to cart
        saveScreenshot("user_adds_an_item_to_the_cart", takeScreenshot());
        
        // Step 3: Go to cart and checkout
        itemsPage.getCart().click();
        saveScreenshot("user_goes_to_checkout", takeScreenshot());
        
        // Verify the chosen item is in the cart
        String cartItemName = itemsPage.getCartItemName();
        Assert.assertEquals(cartItemName, selectedItemName, "The chosen item should be the same as the item in cart");
        
        itemsPage.getCheckout().click();
        
        // Step 4: Enter user information
        itemsPage.getFirstName().sendKeys(FIRST_NAME);
        itemsPage.getLastName().sendKeys(LAST_NAME);
        itemsPage.getPostalCode().sendKeys(POSTAL_CODE);
        saveScreenshot("user_entered_his_info", takeScreenshot());
        
        itemsPage.getcontinueButton().click();
        
        // Step 5: Verify checkout page and finish
        saveScreenshot("checkout_page_is_displayed", takeScreenshot());
        itemsPage.scrollUntilElementIsInView(itemsPage.getFinishButton());
        Assert.assertTrue(itemsPage.getFinishButton().isDisplayed(), "Finish button should be displayed");
        itemsPage.getFinishButton().click();
        
        // Step 6: Verify checkout completion
        Assert.assertTrue(itemsPage.getThankYou().isDisplayed(), "Thank you message should be displayed");
        saveScreenshot("Finished checkout", takeScreenshot());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
} 