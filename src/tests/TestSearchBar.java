package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ConfluxHomePage;
import utils.Page_TitlesTextsAndMessages;
import pages.SearchPageConflux;

import java.time.Duration;

public class TestSearchBar {
//region Fields
    private WebDriver webDriver;

    private Actions actions;

    private ConfluxHomePage confluxHomePage;

    private SearchPageConflux searchPageConflux;

//endregion
//region BeforeTest
    @BeforeMethod
    public void config(){
        System.setProperty("webdriver.chrome.driver", utils.PropertiesReader.getInstance().getValue("WEBDRIVER.CHROMEDRIVER"));
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        confluxHomePage = new ConfluxHomePage(webDriver, actions);
        searchPageConflux = new SearchPageConflux(webDriver, actions);
        webDriver.get("https://conflux.rs/");
        webDriver.manage().window().maximize();
    }
//endregion
//region DataProvider
    @DataProvider(name = "listOfBoardGamesForSearch")
    public Object[][] getListOfBoardGamesForSearch(){
        return new Object[][]{
                {"Fallout", 4},
                {"Dark Souls", 2},
                {"Waterdeep", 6},
        };
    }
//endregion
//region Test
    @Test(dataProvider = "listOfBoardGamesForSearch")
    public void testSearchBar(String boardGame, int expectedNumberOfDisplayedItems){
        searchPageConflux.inputIntoSearchBar(boardGame);
        Assert.assertEquals(searchPageConflux.checkIfOnSearchPage(), Page_TitlesTextsAndMessages.SEARCH_PAGE_TEXT.getAbsolutePath(), "Text should match");
        searchPageConflux.moveToProductsGrid();
        Assert.assertEquals(searchPageConflux.checkNumberOfSearchedItemsThatMatchKeywords(boardGame), expectedNumberOfDisplayedItems, "Numbers of displayed items should match");
    }

    @Test (dependsOnMethods = "testSearchBar", enabled = false)
    public void testReturnToHomePage(){
    confluxHomePage.returnToHomePage();
        Assert.assertEquals(confluxHomePage.checkIfOnHomePage(), Page_TitlesTextsAndMessages.HOME_PAGE_TEXT.getAbsolutePath(), "Text should match");
    }
//endregion
    @AfterMethod
    public void closeWebDriver(){
        webDriver.close();
    }
}

