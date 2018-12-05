package seltests.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import seltests.pageObjects.FrontPage;
import seltests.pageObjects.ProductPage;
import seltests.pageObjects.RegisterPage;
import seltests.pageObjects.SearchResultPage;

import java.time.Duration;

public class AppUtils {
    public WebDriver driver;
    public Wait<WebDriver> fluentWait;
    public FrontPage frontPage;
    public ProductPage productPage;
    public SearchResultPage searchPage;
    public RegisterPage registerPage;

    public AppUtils(){
        InternetExplorerOptions IEOptions = new InternetExplorerOptions()
                .requireWindowFocus()
                .ignoreZoomSettings();

        driver = new InternetExplorerDriver(IEOptions);
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(WebDriverException.class);

        frontPage = new FrontPage(driver);
        searchPage = new SearchResultPage(driver);
        productPage = new ProductPage(driver);
        registerPage = new RegisterPage(driver);
    }

    public void launchDriver(){
        driver.get("https://rozetka.com.ua");
        fluentWait.until(driver -> driver.findElement(By.xpath("//div[@class=\"logo\"]")).isDisplayed());
    }

    public void exitDriver(){
        driver.close();
        driver.quit();
    }

    public void clickClearType(WebElement textBox, String text){
        textBox.click();
        textBox.clear();
        textBox.sendKeys(text);
    }

    public int random(){
        return (int) (Math.random() * 1000);
    }
}
