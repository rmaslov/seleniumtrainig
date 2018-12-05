package seltests.tests;

import org.junit.Test;
import seltests.Utilities.AppUtils;

public class LoginTest {


    @Test
    public void login(){
        AppUtils utils = new AppUtils();
        utils.launchDriver();
        try {
            LogoutTest loTest = new LogoutTest();
            loTest.logoutSteps(utils);
        } catch (Exception e){
            System.out.println("User is not logged in");
        }
        utils.frontPage.clickSignIn();
        utils.frontPage.typeLogin("testuser@yahoo.at");
        utils.frontPage.typePassword("Test1234");
        utils.frontPage.submitAuthentication();
        utils.frontPage.waitForProfileName();
        utils.exitDriver();
    }  //test login with pre-defined data
}
