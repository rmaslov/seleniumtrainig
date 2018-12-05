package seltests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver wd){
        driver = wd;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "responsive-img")
    private WebElement productImage;

    @FindBy(xpath = "//*[@class=\"detail-title h1\"]")
    private WebElement productName;

    public void waitForProductImage(){
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(WebDriverException.class);
        fluentWait.until(driver -> productImage.isDisplayed());
    }

    public String getProductName(){
        return productName.getText().trim();
    }
}
