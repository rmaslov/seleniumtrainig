package seltests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver wd){
        driver = wd;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "title")
    private WebElement title;

    @FindBy(name = "login")
    private WebElement login;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//div[@class=\"signup-submit\"]//*[@class=\"btn-link-i\"]")
    private WebElement signupSubmit;

    public void typeTitle(String userTitle){
        title.click();
        title.clear();
        title.sendKeys(userTitle);
    }

    public void typeLogin(String userLogin){
        login.click();
        login.clear();
        login.sendKeys(userLogin);
    }

    public void typePassword(String userPassword){
        title.click();
        title.clear();
        title.sendKeys(userPassword);
    }

    public void submit(){
        signupSubmit.click();
    }

    public void waitRegistration(int userID){
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(WebDriverException.class);
        fluentWait.until(driver -> driver.findElement(By.xpath(
                "//div[@class=\"addit-f-i-field\"]/*[contains(text(),\"testuser"
                        + userID + "@yahoo.at\")]")).isDisplayed());
    }
}
