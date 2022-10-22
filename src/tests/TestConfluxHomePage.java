package tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import pages.ConfluxHomePage;
import pages.GoogleSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import helpers.Page_TitlesTextsAndMessages;


import java.time.Duration;

public class TestConfluxHomePage {
//region Fields
    private WebDriver webDriver;

    private Actions actions;

    private GoogleSearch googleSearch;

    private ConfluxHomePage confluxHomePage;

//endregion
//region BeforeTest
    @BeforeTest
    public void config(){
        System.setProperty("webdriver.chrome.driver", utils.PropertiesReader.getInstance().getValue("WEBDRIVER.CHROMEDRIVER"));
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(webDriver);
        googleSearch = new GoogleSearch(webDriver);
        confluxHomePage = new ConfluxHomePage(webDriver,actions);
        webDriver.get("https://www.google.com/");
        webDriver.manage().window().maximize();
    }
//endregion
//region Test
    @Test
    public void navigateToConfluxPage(){
        googleSearch.googleSearchInput();
        googleSearch.navigateToConfluxPage();
        Assert.assertEquals(confluxHomePage.checkIfOnHomePage(), Page_TitlesTextsAndMessages.HOME_PAGE_TEXT.getAbsolutePath(), "Text should match");
    }
    @Test (dependsOnMethods = "navigateToConfluxPage")
    public void testFacebookLink(){
        confluxHomePage.toFacebookPage();
        Assert.assertEquals(confluxHomePage.checkIfFacebookPageHasOpened(), Page_TitlesTextsAndMessages.FACEBOOK_PAGE_TITLE.getAbsolutePath(), "Titles should match");
        webDriver.switchTo().window(webDriver.getWindowHandle());
    }
    @Test (dependsOnMethods = "navigateToConfluxPage")
    public void testInstagramLink(){
        confluxHomePage.toInstagramPage();
        Assert.assertEquals(confluxHomePage.checkIfInstagramPageHasOpened(), Page_TitlesTextsAndMessages.FACEBOOK_PAGE_TITLE.getAbsolutePath(), "Titles should match");
        webDriver.switchTo().window(webDriver.getWindowHandle());
    }
    @Test(dependsOnMethods = "navigateToConfluxPage")
    public void changePageLanguageToSerbian(){
        confluxHomePage.changeLanguageToSerbian();
        Assert.assertEquals(confluxHomePage.checkIfLanguageHasChangedToSerbian(), Page_TitlesTextsAndMessages.HOME_PAGE_LANGUAGE_SERBIAN.getAbsolutePath(), "It should say Srpski");
    }


    //hocemo da se ovo izvrsi samo posle odredjenih metoda
    @AfterMethod(dependsOnMethods = {"testInstagramLink", "testFacebookLink"})
    public void switchTo() {
        webDriver.switchTo().window(webDriver.getWindowHandle());
    }
    @Test(dependsOnMethods = "changePageLanguageToSerbian")
    public void revertToEnglish(){
        confluxHomePage.revertLanguageToEnglish();
        Assert.assertEquals(confluxHomePage.checkIfLanguageHasChangedToEnglish(), Page_TitlesTextsAndMessages.HOME_PAGE_LANGUAGE_ENGLISH.getAbsolutePath(), "It should say English");
    }
//endregion
    @AfterTest
    public void closeWebDriver(){
        webDriver.quit();
    }
}
