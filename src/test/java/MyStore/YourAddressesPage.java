package MyStore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YourAddressesPage {

    private static WebDriver driver;


    @FindBy(xpath = "//div[@class='address-body']")
    private List<WebElement> addresses;

    @FindBy(xpath = "//a[@data-link-action='delete-address']")
    private List<WebElement> deleteButtons;


    @FindBy(xpath = "//div[@class='addresses-footer']//a")
    private WebElement createNewAddressButton;

    public YourAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCreateNewAddressButton() {
        createNewAddressButton.click();
    }

    //The method checks if added address is visible by checking the size of the
    //addresses list. Before the test, there is one address addded by default
    //so the size of the list is 1 on the beginning. After adding the new
    //address, the size of the list should change to 2
    public boolean isAddressVisible() {
        return addresses.size() == 2;
    }

    //The method returns the text of the first address from the list (So
    //the second address, because the address added by default has 0 index
    public String getSavedAddress() {
        return addresses.get(1).getText();
    }

    public void deleteAddress() {
        deleteButtons.get(1).click();
    }

    //The method is checking if the new address has been removed.If yes,
    //the addresses list should have 1 size again(default address added only)
    public boolean isAddressRemoved() {
        return addresses.size() == 1;
    }


}
