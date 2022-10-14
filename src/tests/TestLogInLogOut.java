package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ConfluxHomePage;
import pages.LogInPageAndLogOut;
import utils.Page_TitlesTextsAndMessages;

import java.time.Duration;


public class TestLogInLogOut {
//region Fields
    private WebDriver webDriver;

    private Actions actions;

    private LogInPageAndLogOut logInPage;

    private ConfluxHomePage confluxHomePage;

//endregion
//region BeforeMethod
    @BeforeMethod
    public void config(){
        System.setProperty("webdriver.chrome.driver", utils.PropertiesReader.getInstance().getValue("WEBDRIVER.CHROMEDRIVER"));
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(webDriver);
        logInPage = new LogInPageAndLogOut(webDriver, actions);
        confluxHomePage = new ConfluxHomePage(webDriver, actions);
        webDriver.get("https://conflux.rs/");
        webDriver.manage().window().maximize();
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
        logInPage.clickOnAccountPageBtnPopUpLogIn();
        logInPage.enterUserEmailPopUp("oralee24lmaritimen.com");
        logInPage.enterUserPasswordPopUp("1234");
        logInPage.clickLogInPopUpBtn();
    }
//endregion

    @AfterMethod
    public void closeWebDriver(){
        webDriver.close();
    }
}
