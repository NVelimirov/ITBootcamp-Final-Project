package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ConfluxHomePage;
import pages.LogInPageAndLogOut;
import helpers.Page_TitlesTextsAndMessages;


import static helpers.URLs.HOME_PAGE;


public class TestLogInLogOut extends BaseTest {
//region Fields
    private LogInPageAndLogOut logInPage;

    private ConfluxHomePage confluxHomePage;

//endregion
//region BeforeMethod
    @BeforeMethod
    public void config(){
        logInPage = new LogInPageAndLogOut(webDriver, actions);
        confluxHomePage = new ConfluxHomePage(webDriver, actions);
        webDriver.manage().window().maximize();
        webDriver.get(HOME_PAGE);
    }
//endregion
//region DataProvider
    @DataProvider(name = "invalidUserCredentials")
    public Object[][] getInvalidUserCredentials(){
        return new Object[][]
                {
                        {"", ""},
                        {"", "1234"},
                        {"oralee24@lmaritimen.com", ""},
                        {"oralee24lmaritimen.com", "1234"},
                        {"oralee24@lmaritimen.com", "124"},
                };
    }
//endregionr
//region Tests
    @Test(dataProvider = "invalidUserCredentials")
    public void invalidCredentials(String userEmail, String userPassword){
        logInPage.clickOnAccountPageBtn();
        Assert.assertEquals(logInPage.checkIfOnLogInPage(), Page_TitlesTextsAndMessages.LOG_IN_PAGE_TITLE.getAbsolutePath(), "Text should match");
        logInPage.enterUserEmail(userEmail);
        logInPage.enterUserPassword(userPassword);
        logInPage.clickOnLogInBtn();
        Assert.assertEquals(logInPage.checkForWarningPopUp(), Page_TitlesTextsAndMessages.LOG_IN_WARNING.getAbsolutePath(), "Text should match");
    }

    @Test
    public void happyPath(){
        logInPage.clickOnAccountPageBtn();
        Assert.assertEquals(logInPage.checkIfOnLogInPage(), Page_TitlesTextsAndMessages.LOG_IN_PAGE_TITLE.getAbsolutePath(), "Text should match");
        logInPage.enterUserEmail("oralee24@lmaritimen.com");
        logInPage.enterUserPassword("1234");
        logInPage.clickOnLogInBtn();
        Assert.assertEquals(logInPage.checkIfLogInIsSuccesfull(), Page_TitlesTextsAndMessages.ACCOUNT_PAGE_TITLE.getAbsolutePath(), "Text should match");
    }

    @Test
    public void happyPathLogOut(){
        logInPage.clickOnAccountPageBtn();
        Assert.assertEquals(logInPage.checkIfOnLogInPage(), Page_TitlesTextsAndMessages.LOG_IN_PAGE_TITLE.getAbsolutePath(), "Text should match");
        logInPage.enterUserEmail("oralee24@lmaritimen.com");
        logInPage.enterUserPassword("1234");
        logInPage.clickOnLogInBtn();
        Assert.assertEquals(logInPage.checkIfLogInIsSuccesfull(), Page_TitlesTextsAndMessages.ACCOUNT_PAGE_TITLE.getAbsolutePath(), "Text should match");
        logInPage.clickOnAccountPageBtnPopUpLogOut();
        Assert.assertEquals(logInPage.checkIfOnLogOutPage(), Page_TitlesTextsAndMessages.LOG_OUT_PAGE_TITLE.getAbsolutePath(), "Text should match");
        logInPage.continueToHomePage();
        Assert.assertEquals(confluxHomePage.checkIfOnHomePage(), Page_TitlesTextsAndMessages.HOME_PAGE_TEXT.getAbsolutePath(), "Text should match");
    }

    @Test
    public void logInWithPoPUpScreen(){
        logInPage.clickOnAccountPageBtnPopUpLogIn();//neki put ga pronadje, a neki ne. Ne znam u cemu je konkretan problem
        logInPage.enterUserEmailPopUp("oralee24lmaritimen.com");
        logInPage.enterUserPasswordPopUp("1234");
        logInPage.clickLogInPopUpBtn();
    }
//endregion

    @AfterMethod
    public void pageCleanup() {
        webDriver.manage().deleteAllCookies();
    }
}
