package seltests.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class FrontPage {
    private WebDriver driver;


    public FrontPage(WebDriver wd){
        driver = wd;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id=\"rz-search\"]//input[@type=\"text\"]")
    private WebElement searchBar;

    @FindBy(name = "profile")
    private WebElement profile;

    @FindBy(name = "signout")
    private WebElement signout;

    @FindBy(className = "logo")
    private WebElement logo;

    @FindBy(name = "signin")
    private WebElement signin;

    @FindBy(name = "login")
    private WebElement login;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "auth_submit")
    private WebElement auth_submit;

    @FindBy(className = "auth-f-signup")
    private WebElement register;

    public void search() {
        searchBar.click();
        searchBar.clear();
        searchBar.sendKeys("Play station 4 pro" + Keys.ENTER);
    }

    public void hoverOverProfile(){
        Actions action = new Actions(driver);
        action.moveToElement(profile)
                .build()
                .perform();
    }

    public void clickSignout() {
        signout.click();
    }

    public void waitForLogo(){
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(WebDriverException.class);
        fluentWait.until(driver -> logo.isDisplayed());
    }

    public void clickSignIn(){
        signin.click();
    }

    public void typeLogin(String userLogin){
        login.click();
        login.clear();
        login.sendKeys(userLogin);
    }

    public void typePassword(String userPassword){
        password.click();
        password.clear();
        password.sendKeys(userPassword);
    }

    public void submitAuthentication(){
        auth_submit.click();
    }

    public void waitForProfileName(){
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(WebDriverException.class);
        fluentWait.until(driver -> profile.isDisplayed());
    }

    public void register(){
        register.click();
    }
}
