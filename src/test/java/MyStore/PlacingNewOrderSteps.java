package MyStore;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.NoSuchElementException;

public class PlacingNewOrderSteps {

    MainPage mainPage;

    LogInPage loginPage;

    YourAccountPage yourAccountPage;

    ProductListPage productListPage;

    ProductPage productPage;

    WebDriver driver;
    @Given("I'm on the {string} page")
    public void imOnTheMyStore(String url) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

    }
}
