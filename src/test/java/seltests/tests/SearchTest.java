package seltests.tests;

import org.junit.Test;
import seltests.Utilities.AppUtils;

public class SearchTest {
    //test search and compares quantity of test results to actual one
    @Test
    public void searchTest() throws InterruptedException {
        AppUtils utils = new AppUtils();
        utils.launchDriver();
        utils.frontPage.search();
        utils.searchPage.waitForSearchResult();
        utils.searchPage.compareResults();
        Thread.sleep(1000);
        utils.searchPage.printArticles();
        utils.exitDriver();
    }
}
