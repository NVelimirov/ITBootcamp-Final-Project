package tests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.ConfluxHomePage;
import pages.GoogleSearch;
import org.testng.Assert;
import helpers.Page_TitlesTextsAndMessages;


import static helpers.URLs.GOOGLE_SEARCH;
import static helpers.URLs.HOME_PAGE;

public class TestConfluxHomePage extends BaseTest {
//region Fields
    private GoogleSearch googleSearch;
    private ConfluxHomePage confluxHomePage;

//endregion
//region BeforeTest
    @BeforeMethod
    public void config(){
        googleSearch = new GoogleSearch(webDriver);
        confluxHomePage = new ConfluxHomePage(webDriver,actions);
        webDriver.manage().window().maximize();
        webDriver.get(HOME_PAGE);
    }
//endregion
//region Test
    @Test (priority = 10)
    public void navigateToConfluxPage(){
        webDriver.navigate().to(GOOGLE_SEARCH);
        googleSearch.googleSearchInput();
        googleSearch.navigateToConfluxPage();
        Assert.assertEquals(confluxHomePage.checkIfOnHomePage(), Page_TitlesTextsAndMessages.HOME_PAGE_TEXT.getAbsolutePath(), "Text should match");
    }
    @Test (priority = 20)
    public void testFacebookLink(){
        confluxHomePage.toFacebookPage();
        Assert.assertEquals(confluxHomePage.checkIfFacebookPageHasOpened(), Page_TitlesTextsAndMessages.FACEBOOK_PAGE_TITLE.getAbsolutePath(), "Titles should match");
    }
    @Test (priority = 30)
    public void testInstagramLink(){
        confluxHomePage.toInstagramPage();
        Assert.assertEquals(confluxHomePage.checkIfInstagramPageHasOpened(), Page_TitlesTextsAndMessages.FACEBOOK_PAGE_TITLE.getAbsolutePath(), "Titles should match");
    }
    @Test (priority = 40)
    public void changePageLanguageToSerbian(){
        confluxHomePage.changeLanguageToSerbian();
        Assert.assertEquals(confluxHomePage.checkIfLanguageHasChangedToSerbian(), Page_TitlesTextsAndMessages.HOME_PAGE_LANGUAGE_SERBIAN.getAbsolutePath(), "It should say Srpski");
    }
    @Test (priority = 50)
    public void revertToEnglish(){
        confluxHomePage.revertLanguageToEnglish();
        Assert.assertEquals(confluxHomePage.checkIfLanguageHasChangedToEnglish(), Page_TitlesTextsAndMessages.HOME_PAGE_LANGUAGE_ENGLISH.getAbsolutePath(), "It should say English");
    }
//endregion
    @AfterMethod(dependsOnGroups = {"testFacebookLink", "testInstagramLink"})
    public void switchTo() {
        webDriver.switchTo().window(webDriver.getWindowHandle());
    }
}
