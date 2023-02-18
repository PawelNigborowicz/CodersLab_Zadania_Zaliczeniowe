package MyStore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

    private static WebDriver driver;
    @FindBy(xpath = "//div//h3[@class='h1 card-title']")
    private WebElement orderConfirmationMessage;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getOrderConfirmationMessage(){
        return orderConfirmationMessage.getText();
    }
}
