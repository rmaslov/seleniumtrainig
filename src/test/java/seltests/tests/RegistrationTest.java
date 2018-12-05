package seltests.tests;

import org.junit.Test;
import seltests.Utilities.AppUtils;

public class RegistrationTest {


    @Test
    public void register(){
        AppUtils utils = new AppUtils();
        utils.launchDriver();
        try {
            LogoutTest loTest = new LogoutTest();
            loTest.logoutSteps(utils);
        } catch (Exception logoutException) {
            System.out.println("User is not logged in");
        }
        
        utils.frontPage.clickSignIn();
        utils.frontPage.register();
        utils.frontPage.waitForLogo();
        int randomNumber = utils.random();
        utils.registerPage.typeTitle("testuser" + randomNumber);
        utils.registerPage.typeLogin("testuser" + randomNumber + "@yahoo.at");

        System.out.println("Login:    testuser" + randomNumber + "@yahoo.at");
        System.out.println("Password: Test1234");

        utils.registerPage.typePassword("Test1234");

        utils.registerPage.submit();

        utils.registerPage.waitRegistration(randomNumber);

        utils.exitDriver();
    }  //test registration
}
