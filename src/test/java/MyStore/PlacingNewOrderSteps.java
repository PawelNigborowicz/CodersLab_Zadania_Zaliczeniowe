package MyStore;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

public class PlacingNewOrderSteps {

    MainPage mainPage;

    LogInPage loginPage;

    YourAccountPage yourAccountPage;

    ProductListPage productListPage;

    ProductPage productPage;

    ShoppingCartPage shoppingCartPage;

    CheckoutPage checkoutPage;

    OrderConfirmationPage orderConfirmationPage;

    OrderHistoryPage orderHistoryPage;

    String orderTotal;

    WebDriver driver;
    @Given("I'm on the {string} page")
    public void imOnTheMyStore(String url) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        driver.get(url);
    }

    @When("I log in using email {string} and password {string}")
    public void iSignInUsingEmailAndPassword(String email, String password) {

        mainPage = new MainPage(driver);
        mainPage.clickSignInButton();

        loginPage = new LogInPage(driver);
        loginPage.enterEmailAndPassword(email, password);
        loginPage.submitLogin();
    }

    @And("I go to the Clothes product list page")
    public void iGoToClothesPLP() {
        yourAccountPage = new YourAccountPage(driver);
        yourAccountPage.goToClothesPDP();
    }

    @And("I see that Hummingbird Printed Sweater is 20% discounted")
    public void iSeeThatHummingbirdIs20PercentDiscounted(){
        productListPage = new ProductListPage(driver);
        Assertions.assertEquals("-20%", productListPage.getHummingbirdDiscount());
    }

    @Then("I add {int} units of size {string} to my basket")
    public void iAddItemsToMyBasket(int qty, String size) {
        productListPage.clickHummingbirdTitle();

        productPage = new ProductPage(driver);

        try {
            productPage.chooseSize(size);
        } catch (NoSuchElementException e) {
            Assertions.fail();
        }

        productPage.chooseQuantity(qty);

        productPage.addToCart();

    }

    @When(("I proceed to checkout"))
    public void iProceedToCheckout(){
        productPage.proceedToCheckout();

        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.proceedToCheckout();

    }

    @And("I confirm my address")
    public void iConfirmMyAddress(){

        checkoutPage = new CheckoutPage(driver);
        checkoutPage.confirmAddress();

    }

    @And("I choose PrestaShop pick up in store delivery method")
    public void iChoosePrestaShopDeliveryMethod(){
        checkoutPage.chooseDeliveryMethod();
    }

    @And("I choose Pay by Check payment method")
    public void iChoosePayByCheckPaymentMethod(){
        checkoutPage.choosePaymentMethod();
    }

    @And("I check the term of service checkbox")
    public void iCheckTermsOfServiceCheckbox(){
        checkoutPage.agreeTermsOfService();
    }

    @And("I place an order")
    public void iPlaceAnOrder(){
        checkoutPage.placeOrder();
    }

    @Then("I can see {string} message")
    public void iCanSeeMsg(String msg){
        orderConfirmationPage = new OrderConfirmationPage(driver);
        orderTotal = orderConfirmationPage.getOrderTotal(); // holding the value for the future step
        Assertions.assertEquals(msg, orderConfirmationPage.getOrderConfirmationMessage());
    }

    @And("I make a screenshot of the order confirmation page")
    public void iMakeScreenShot(){

        //Konwersja WebDriver do TakesScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) driver);

        //Wykonanie screenshotu i zapis do pliku
        File scrFile = scrShot.getScreenshotAs(OutputType.FILE);

        try{
            FileHandler.copy(scrFile, new File("src/screenshot/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @And("I go to the Order history and details page")
    public void iGoToOrderHistory(){
        orderConfirmationPage.goToMyAccount();
        yourAccountPage.clickOrderHistoryIcon();
    }

    @And("I see that my order has {string} status")
    public void iSeeMyOrderStatus(String status) {

        orderHistoryPage = new OrderHistoryPage(driver);
        Assertions.assertEquals(status, orderHistoryPage.getLastOrderStatus());
    }

    @And("I see that my order's total price is the same as on the order confirmation page")
    public void iSeeThatMyOrdersTotalIsTheSameAsOnTheOrderConfirmationPage() {
        Assertions.assertEquals(orderTotal, orderHistoryPage.getLastOrderPrice());
    }

    @And("I close my browser")
    public void iCloseMyBrowser(){
        driver.quit();
    }
}
