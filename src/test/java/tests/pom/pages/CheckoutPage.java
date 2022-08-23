package tests.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import tests.pom.base.BasePage;

public class CheckoutPage extends BasePage {
    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressLineOneField = By.id("billing_address_1");
    private final By billingCityField = By.id("billing_city");
    private final By billingPosterCodeField = By.id("billing_postcode");
    private final By billingEmailField = By.id("billing_email");

//    private final By placeOrderButton = By.cssSelector("#place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHereToLoginLink = By.xpath("//a[@class='showlogin']");
    private final By userNameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstname) {
       WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
       element.clear();
       element.sendKeys(firstname);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
        Select select = new Select(driver.findElement(countryDropDown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropDown));
        select.selectByVisibleText(countryName);
        return  this;
    }

    public CheckoutPage selectState(String stateName) {
        Select select = new Select(driver.findElement(stateDropDown));
        wait.until(ExpectedConditions.visibilityOfElementLocated(stateDropDown));
        select.selectByVisibleText(stateName);
        return  this;
    }

    public CheckoutPage enterlastName(String lastName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        element.clear();
        element.sendKeys(lastName);
        return this;
    }
    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        driver.findElement(addressLineOneField).clear();
        driver.findElement(addressLineOneField).sendKeys(addressLineOne);
        return this;
    }
    public CheckoutPage enterCity(String city) {
        driver.findElement(billingCityField).clear();
        driver.findElement(billingCityField).sendKeys(city);
        return this;
    }
    public CheckoutPage enterPostCode(String postCode) {
        driver.findElement(billingPosterCodeField).clear();
        driver.findElement(billingPosterCodeField).sendKeys(postCode);
        return this;
    }
    public CheckoutPage enterEmail(String email) {
        driver.findElement(billingEmailField).clear();
        driver.findElement(billingEmailField).sendKeys(email);
        return this;
    }
    public CheckoutPage placeOrder() {
       waitForOverlaysToDisappear(overlay);
        try {
            WebElement placeOrderBtn = driver.findElement(By.cssSelector("#place_order"));
            placeOrderBtn.click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement placeOrderBtn = driver.findElement(By.cssSelector("#place_order"));
            placeOrderBtn.click();
        }
        return this;
    }
    public String getNotice() {
       return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return this;
    }

    public CheckoutPage login(String username, String password) {
        return  enterUserName(username).enterPassword(password).clickLoginButton();
    }
}
