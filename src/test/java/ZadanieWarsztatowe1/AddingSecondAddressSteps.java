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
}
