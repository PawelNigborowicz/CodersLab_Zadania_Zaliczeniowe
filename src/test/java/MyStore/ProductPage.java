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

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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
}
