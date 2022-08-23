package tests.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.pom.base.BasePage;
public class StorePage extends BasePage {
    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.xpath("//button[@value='Search']");
    private  final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");
    public StorePage(WebDriver driver) {
        super(driver);
    }

    public  StorePage enterTextInSearchField(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(text);
        return this;
    }

    public StorePage clickSearchButton() {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        return this;
    }


    public StorePage search(String text) {
        enterTextInSearchField(text).clickSearchButton();
        return this;
    }

    public String getTitle() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    private By getAddToCartBtnElement(String productName) {
           return By.xpath("//a[@aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddToCartButton(String productName) {
         By addToCartButton = getAddToCartBtnElement(productName);
           wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
           return this;
    }

    public CartPage clickViewCart() {
            wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
            return new CartPage(driver);
    }
}
