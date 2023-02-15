package ZadanieWarsztatowe1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourAddressesPage {

    private static WebDriver driver;


    @FindBy(xpath = "//div[@class='addresses-footer']//a")
    private WebElement createNewAddressButton;

    public YourAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCreateNewAddressButton() {
        createNewAddressButton.click();
    }
}
