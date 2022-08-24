package tests.pom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tests.pom.base.BaseTest;
import tests.pom.pages.CartPage;
import tests.pom.pages.CheckoutPage;
import tests.pom.pages.HomePage;
import tests.pom.pages.StorePage;
import tests.pom.utils.ConfigLoader;

public class TestCases extends BaseTest {

    @Test
    public void guestPlaceOrder() {

        HomePage homePage = new HomePage(getDriver()).load();
        StorePage storePage = homePage.clickStoreMenuLink();

//      storePage.enterTextInSearchField("Blue").clickSearchButton();
        storePage.search("Blue");

//        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
//        Assert.assertTrue(storePage.getTitle().contains("Search results: “Blue”"));

        storePage.clickAddToCartButton("Blue Shoes");
        CartPage cartPage = storePage.clickViewCart();

//        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.enterFirstName("abdalla").
                enterlastName("mahmoud").
                enterAddressLineOne("nasr city").
                enterCity("nasr city").
                enterPostCode("117583").
                enterEmail("lerewa7361@wnpop.com").
                selectCountry("Egypt").
                selectState("Cairo").
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginUserPlaceOrder() {

        StorePage storePage = new HomePage(getDriver()).load().clickStoreMenuLink().search("Blue");
//        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
//        Assert.assertTrue(storePage.getTitle().contains("Search results: “Blue”"));


        storePage.clickAddToCartButton("Blue Shoes");
        CartPage cartPage = storePage.clickViewCart();
//        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.clickHereToLoginLink();

        checkoutPage.login(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword()).
                enterFirstName("abdalla").
                enterlastName("mahmoud").
                enterAddressLineOne("nasr city").
                enterCity("nasr city").
                enterPostCode("117583").
                enterEmail("lerewa7361@wnpop.com").
                selectCountry("Egypt").
                selectState("Cairo").
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
}
