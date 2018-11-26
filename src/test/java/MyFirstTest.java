import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class MyFirstTest {
    private WebDriver driver = new InternetExplorerDriver();

    private Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(WebDriverException.class);

    private void clickClearType(WebElement textBox, String text){
        textBox.click();
        textBox.clear();
        textBox.sendKeys(text);
    }

    //@Before
    public void launchDriverViaGoogle(){
        driver.navigate().to("https://www.google.com");
        WebElement googleSearchTextbox = driver.findElement(By.xpath("//input[@type=\"text\"]"));

        clickClearType(googleSearchTextbox, "rozetka" + Keys.ENTER); //Search in Google


        wait.until(driver1 -> driver.findElement(By.xpath("//a[contains(@href,\"rozetka.com.ua\")]/h3")).isDisplayed()); //wait until search result is present
        driver.findElement(By.xpath("//a[contains(@href,\"rozetka.com.ua\")]/h3")).click();
        wait.until(driver1 -> driver1.findElement(By.xpath("//div[@class=\"logo\"]")).isDisplayed()); //wait until logo is displayed
    }

    @Before
    public void launchDriver(){
        driver.navigate().to("https://rozetka.com.ua");
        wait.until(driver1 -> driver1.findElement(By.xpath("//div[@class=\"logo\"]")).isDisplayed()); //wait until logo is displayed
    }

    @After
    public void exitDriver(){
        driver.close();
        driver.quit();
    }

    @Test
    public void login(){
        try {
            logout();
        } catch (Exception e){
            System.out.println("User is not logged in");
        }
        driver.findElement(By.name("signin")).click();
        clickClearType(driver.findElement(By.name("login")),"testuser@yahoo.at");
        clickClearType(driver.findElement(By.name("password")), "Test1234");
        driver.findElement(By.name("auth_submit")).click();

        wait.until(driver1 -> driver1.findElement(By.name("profile")).isDisplayed()); //wait until logo is displayed
    }

    @Test
    public void logout(){
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(By.name("profile")))
                    .build()
                    .perform();
            driver.findElement(By.name("signout")).click();
            wait.until(driver1 -> driver1.findElement(By.xpath("//div[@class=\"logo\"]")).isDisplayed()); //wait until logo is displayed
            Thread.sleep(300);

        } catch (Exception e){
            System.out.println("User is not logged in");
        }
    }

    @Test
    public void searchTest() {
        try {
            int results;
            int resultsCount;

            WebElement searchBar = driver.findElement(By.xpath("//div[@id=\"rz-search\"]//input[@type=\"text\"]"));
            clickClearType(searchBar, "Play station 4 pro" + Keys.ENTER);

            By searchCount = By.xpath("//i[@class=\"m-cat-l-i-all-count\"]");
            wait.until(driver1 -> driver1.findElement(searchCount).isDisplayed()); //wait until search count is displayed
            Thread.sleep(500);
            results = Integer.parseInt(driver.findElement(searchCount).getText().substring(1, driver.findElement(searchCount).getText().length()-1));
            List<WebElement> resultElements = driver.findElements(By.xpath("(//div[@class=\"g-i-tile-i-box\"])"));

            resultsCount = resultElements.size();

            if (results==resultsCount) {
                System.out.println("Count matches!");
            } else {
                System.out.println("Count differs.");
            }

            Thread.sleep(1000);
            for (int i = 0; i < resultElements.size(); i++) {
                int elementNumber = i + 1;
                System.out.print("[Name: "+driver.findElement(By.xpath("(//div[@class=\"g-i-tile-i-title clearfix\"])["+elementNumber+"]/a")).getText()+"]");
                System.out.println("[Price: "+driver.findElement(By.xpath("(//div[@class=\"inline\"]/div[@name=\"price\"]//span[1])["+elementNumber+"]")).getText()+"]");
            }
        }
        catch (Exception e) {
            System.out.println("exception: "+ e);
        }
    }

    @Test
    public void openFirstTestResult() {
        try {
            WebElement searchBar = driver.findElement(By.xpath("//div[@id=\"rz-search\"]//input[@type=\"text\"]"));
            clickClearType(searchBar, "Play station 4 pro" + Keys.ENTER);

            By searchCount = By.xpath("//i[@class=\"m-cat-l-i-all-count\"]");
            wait.until(driver1 -> driver1.findElement(searchCount).isDisplayed()); //wait until search count is displayed
            Thread.sleep(500);

            WebElement firstResultName = driver.findElement(By.xpath("(//div[@class=\"g-i-tile-i-title clearfix\"])[1]/a"));
            String resultName = firstResultName.getText().trim();
            System.out.println(resultName);

            firstResultName.click();
            wait.until(driver -> driver.findElement(By.className("responsive-img")).isDisplayed());
            String productName = driver.findElement(By.xpath("//*[@class=\"detail-title h1\"]")).getText().trim();
            System.out.println(productName);
            Assert.assertEquals("Did not open same product", resultName, productName);
        }
        catch (Exception e) {
            System.out.println("exception: "+ e);
        }
    }

    @Test
    public void register(){
        try {
            logout();
        } catch (Exception logoutException) {
            System.out.println(logoutException);
        }

        try {
            driver.findElement(By.name("signin")).click();
            driver.findElement(By.className("auth-f-signup")).click();
            wait.until(driver1 -> driver1.findElement(By.xpath("//div[@class=\"logo\"]")).isDisplayed()); //wait until logo is displayed
            int randomNumber = (int)(Math.random()*1000);
            clickClearType(driver.findElement(By.name("title")),"testuser"+randomNumber);
            clickClearType(driver.findElement(By.name("login")), "testuser"+randomNumber+"@yahoo.at");
            System.out.println("Login:    testuser"+randomNumber+"@yahoo.at");
            System.out.println("Password: Test1234");
            clickClearType(driver.findElement(By.name("password")), "Test1234");
            driver.findElement(By.xpath("//div[@class=\"signup-submit\"]//*[@class=\"btn-link-i\"]")).click();
            wait.until(driver1 -> driver1.findElement(By.xpath("//div[@class=\"addit-f-i-field\"]/*[contains(text(),\"testuser"+randomNumber+"@yahoo.at\")]")).isDisplayed()); //wait until login is displayed
            Thread.sleep(1000);


        } catch (Exception e) {
            System.out.println("exception: "+ e);
        }
    }
}
