package ZadanieWarsztatowe1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewAddressPage {

    private static WebDriver driver;
    @FindBy(id="field-alias")
    private WebElement aliasInput;
    @FindBy(id="field-address1")
    private WebElement addressInput;
    @FindBy(id="field-city")
    private WebElement cityInput;
    @FindBy(id="field-postcode")
    private WebElement postalCodeInput;
    @FindBy(id="field-phone")
    private WebElement phoneInput;
    @FindBy(id="field-id_country")
    private WebElement countrySelect;
    @FindBy(xpath = "//footer//button")
    private WebElement formSubmitButton;




    public NewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOutTheAddressForm(String alias, String address, String city,
                                      String postalcode, String country, String phone) {

        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.clear();
        addressInput.sendKeys(address);

        cityInput.clear();
        cityInput.sendKeys(city);

        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalcode);

        Select dropdown = new Select(countrySelect);
        dropdown.selectByVisibleText(country);

        phoneInput.clear();
        phoneInput.sendKeys(phone);

    }

    public void saveAddress() {
        formSubmitButton.click();
    }
}
