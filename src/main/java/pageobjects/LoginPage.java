package pageobjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import resources.Base;

public class LoginPage extends Base {

    private WebDriver driver;
    private String pageUrl ="https://www.saucedemo.com/";
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMessg = By.xpath(("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"));

    public LoginPage(WebDriver driver) {
        this.driver = driver;
            }

    public WebElement getUsername() {

        return driver.findElement(username);
    }

    public WebElement getPassword() {
        return driver.findElement(password);
    }

    public WebElement loginButton() {
        return driver.findElement(loginBtn);
    }
    public WebElement getAlertMessage()
    {
        return driver.findElement(errorMessg);
    }

    public boolean checkIfLoginPageIsOpen() {
        if(driver.getCurrentUrl().equalsIgnoreCase(pageUrl))
            return true;
        else {
            return false;
        }
    }
    public boolean checkWrongLoginCridentials()
    {
        if(getAlertMessage().isDisplayed())
            return true;
        else {
            return false;
        }
    }


}
