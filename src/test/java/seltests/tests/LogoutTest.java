package seltests.tests;

import org.junit.Test;
import seltests.Utilities.AppUtils;

public class LogoutTest {

    @Test
    public void logout() throws InterruptedException {
        AppUtils utils = new AppUtils();
        utils.launchDriver();
        try {
            logoutSteps(utils);
        } catch (Exception e) {
            System.out.println("User is not logged in");
        }
        utils.exitDriver();
    }

    public void logoutSteps(AppUtils utils) throws InterruptedException {
        Thread.sleep(2000);
        utils.frontPage.hoverOverProfile();
        utils.frontPage.clickSignout();
        utils.frontPage.waitForLogo();
        Thread.sleep(300);
    }
}
