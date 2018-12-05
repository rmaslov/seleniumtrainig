package seltests.tests;

import org.junit.Assert;
import org.junit.Test;
import seltests.Utilities.AppUtils;

public class SearchResultTest {
    @Test
    public void openFirstTestResult() {
        AppUtils utils = new AppUtils();
        utils.launchDriver();
        utils.frontPage.search();
        utils.searchPage.waitForSearchResult();

        String resultName = utils.searchPage.getFirstResultName();
        System.out.println(resultName);

        utils.searchPage.openFirstResult();
        utils.productPage.waitForProductImage();

        String productName = utils.productPage.getProductName();
        System.out.println(productName);

        Assert.assertEquals("Did not open same product", resultName, productName);

        utils.exitDriver();
    }  //test search, opens first result and compares name from PLP and PDP
}
