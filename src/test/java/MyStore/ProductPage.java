package MyStore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductPage {

    private static WebDriver driver;

    @FindBy(xpath = "//select[@id='group_1']//option")
    private List<WebElement> sizeValues;

    @FindBy(css = ".bootstrap-touchspin-up")
    private WebElement increaseQtyBtn;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//div[@class='cart-content-btn']//a")
    private WebElement proceedToCheckoutBtn;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //When the size value is entered into the method, the loop iterates through the elements in the list
    //and if the value from the list is equal to the entered one, the element is being clicked.
    //If no matching element found, method throws NoSuchElementException
    public void chooseSize(String size) {

        boolean found = false;

        for (WebElement s : sizeValues) {
            if (s.getText().equals(size)) {
                found = true;
                s.click();
            }
        }
        if (!found) {
            throw new NoSuchElementException();
        }
    }


    //When qty value is entered into the method, the loop is being executed
    // qty-1 times(Because 1 value is selected by default) and in every iteration
    //the increase qty button is being clicked.
    //The sleep method has been used to temporarily fix the issue with clicking
    // the element too fast

    public void chooseQuantity(int qty) {
        for(int i = 1; i < qty; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            increaseQtyBtn.click();
        }
    }

    public void addToCart(){
        addToCartBtn.click();
    }

    public void proceedToCheckout(){
        proceedToCheckoutBtn.click();
    }


}
