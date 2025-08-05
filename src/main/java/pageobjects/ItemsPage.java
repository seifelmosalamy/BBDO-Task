package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Base;

import java.time.Duration;

public class ItemsPage extends Base {
    private String itemsUrl ="https://www.saucedemo.com/inventory.html";
    private By checkoutButton = By.id("checkout");
    private By thankYouText = By.xpath("//*[@id=\"checkout_complete_container\"]/h2");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private WebDriver driver;
    private String expectedFooterText ="© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";
    private By footer = By.xpath(("//*[@id=\"page_wrapper\"]/footer/div"));
    private By cart = By.xpath("/html/body/div[1]/div/div/div[1]/div[1]/div[3]/a");
    private    By redItemButton = By.name("add-to-cart-test.allthethings()-t-shirt-(red)");
    private    By redItemName = By.xpath("//div[contains(text(),'Test.allTheThings() T-Shirt (Red)')]");
    private    By cartItemName = By.xpath("//div[@class='inventory_item_name']");

    public ItemsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebElement getFooter() {

        return driver.findElement(footer);
    }

    public WebElement getRedItemButton() throws InterruptedException {
        Thread.sleep(2000);
        return driver.findElement(redItemButton);
    }
    
    public String getRedItemName() {
        return driver.findElement(redItemName).getText();
    }
    
    public String getCartItemName() {
        return driver.findElement(cartItemName).getText();
    }
    public WebElement getCart()
    {
        return driver.findElement(cart);
    }
    public WebElement getFinishButton()
    {
        return driver.findElement(finishButton);    }
    public WebElement getThankYou()
    {
        return driver.findElement(thankYouText);
    }
    public WebElement getFirstName()
    {
        return driver.findElement(firstName);
    }
    public WebElement getLastName()
    {
        return driver.findElement(lastName);
    }
    public WebElement getcontinueButton()
    {
        return driver.findElement(continueButton);
    }
    public WebElement getPostalCode()
    {
        return driver.findElement(postalCode);
    }
    public WebElement getCheckout()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        return driver.findElement(checkoutButton);
    }


    public void scrollUntilElementIsInView(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public String getExpectedFooterText() {
        return expectedFooterText;
    }


    public boolean checkIfItemsPageIsOpen() {
        if(driver.getCurrentUrl().equalsIgnoreCase(itemsUrl))
            return true;
        else {
            return false;
        }
    }
}
