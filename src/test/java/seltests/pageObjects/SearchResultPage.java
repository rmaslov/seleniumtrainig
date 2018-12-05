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
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;

    public SearchResultPage(WebDriver wd){
        driver = wd;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "m-cat-l-i-all-count")
    private WebElement searchCount;

    @FindBy(xpath = "(//div[@class=\"g-i-tile-i-title clearfix\"])[1]/a")
    private WebElement firstResult;

    public int result() {
        return Integer.parseInt(searchCount.getText()
                .substring(1, searchCount.getText().length()-1));
    }

    public void waitForSearchResult(){
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(WebDriverException.class);
        fluentWait.until(driver -> searchCount.isDisplayed());
    }

    public int resultCount(){
        List<WebElement> resultElements = driver.findElements(By.className("g-i-tile-i-box"));
        return resultElements.size();
    }

    public void printArticles(){
        int resultsCount = resultCount();
        for (int i = 0; i < resultsCount; i++) {
            int elementNumber = i + 1;
            System.out.print("[Name: "+ driver.findElement(
                    By.xpath("(//div[@class=\"g-i-tile-i-title clearfix\"])["+elementNumber+"]/a")).getText()+"]");
            System.out.println("[Price: "+ driver.findElement(
                    By.xpath("(//div[@class=\"inline\"]/div[@name=\"price\"])["+elementNumber+"]")).getText()+"]");
        }
    }

    public void compareResults(){
        int results = result();
        int resultsCount = resultCount();
        if (results==resultsCount) {
            System.out.println("Count matches!");
        } else {
            System.out.println("results: "+results);
            System.out.println("resultsCount: "+resultsCount);
            System.out.println("Count differs.");
        }
    }

    public String getFirstResultName(){
        return firstResult.getText().trim();
    }

    public void openFirstResult(){
        firstResult.click();
    }
}
