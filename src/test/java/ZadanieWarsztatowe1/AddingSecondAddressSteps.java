package ZadanieWarsztatowe1;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AddingSecondAddressSteps {

    MainPage mainPage;

    LogInPage loginPage;

    YourAccountPage yourAccountPage;

    YourAddressesPage yourAddressesPage;

    NewAddressPage newAddressPage;
    WebDriver driver;

    @Given("Im'm on the {string}")
    public void imOnTheMyStore(String url) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(url);
    }

    @When("I sign in using email {string} and password {string}")
    public void iSignInUsingEmailAndPassword(String email, String password) {

        mainPage = new MainPage(driver);
        mainPage.clickSignInButton();

        loginPage = new LogInPage(driver);
        loginPage.enterEmailAndPassword(email, password);
        loginPage.submitLogin();
    }

    @And("I click on the Addresses icon in Your account section")
    public void iClickOnTheAddressesIcon() {
        yourAccountPage = new YourAccountPage(driver);
        yourAccountPage.clickAdressesIcon();
    }

    @Then("I land on the {string} page")
    public void iLandOnTheAdressesPage(String url) {
        Assertions.assertEquals(url, driver.getCurrentUrl());
    }

    @When("I click at the Create new address button")
    public void iClickAtTheCreateNewAddressButton() {
        yourAddressesPage = new YourAddressesPage(driver);
        yourAddressesPage.clickCreateNewAddressButton();
    }

    @And("I enter new address {string}, {string}, {string}, {string}, {string}, {string}")
    public void iEnterNewAddress(String alias, String address, String city,
                                 String postalcode, String country, String phone) {
        newAddressPage = new NewAddressPage(driver);
        newAddressPage.fillOutTheAddressForm(alias, address, city, postalcode, country, phone);
        newAddressPage.saveAddress();
    }

    @Then("I can see new address")
    public void iCanSeeNewAddress(){
        Assertions.assertTrue(yourAddressesPage.isAddressVisible());
    }

    @And("I check if the new address contains {string}, {string}, {string}, {string}, {string} and {string}")
    public void iCheckIfTheNewAddressIsCorrect(String alias, String address, String city,
                                               String postalcode, String country, String phone) {
        Assertions.assertEquals(newAddressPage.getEnteredAddress(), yourAddressesPage.getSavedAddress());
    }

    @And("I remove the address")
    public void iRemoveTheAddress() {
        yourAddressesPage.deleteAddress();
    }

    @And("I can see the address has been removed")
    public void ICanSeeNoAddress(){
        Assertions.assertTrue(yourAddressesPage.isAddressRemoved());
    }

    @And("I close the browser")
    public void IcloseTheBrowser() {
        driver.quit();
    }
}
