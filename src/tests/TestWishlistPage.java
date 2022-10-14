package tests;

import utils.Page_TitlesTextsAndMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.time.Duration;

public class TestWishlistPage {
//region fields
    private WebDriver webDriver;

    private Actions actions;

    private WishlistPage wishlistPage;

    private LogInPageAndLogOut logInPage;

    private ConfluxHomePage confluxHomePage;

    private BoardGamesPage boardGamesPage;

//endregion
//region BeforeTest
    @BeforeTest
    public void config(){
        System.setProperty("webdriver.chrome.driver", utils.PropertiesReader.getInstance().getValue("WEBDRIVER.CHROMEDRIVER"));
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(webDriver);
        wishlistPage = new WishlistPage(webDriver,actions);
        logInPage = new LogInPageAndLogOut(webDriver, actions);
        confluxHomePage = new ConfluxHomePage(webDriver, actions);
        boardGamesPage = new BoardGamesPage(webDriver,actions);
        webDriver.get("https://conflux.rs/");
        webDriver.manage().window().maximize();
    }
//endregion
//region Tests
    @Test (priority = 1)
    public void ToWishlistWithoutLoggingIn(){
        wishlistPage.toWishlistPage();
        Assert.assertEquals(logInPage.checkIfOnLogInPage(), Page_TitlesTextsAndMessages.LOG_IN_PAGE_TITLE.getAbsolutePath(), "texts should match");
    }

    @Test (priority = 2, enabled = true)
    public void AddItemToWishlistfromHomePage(){
        Assert.assertEquals(logInPage.checkIfOnLogInPage(), Page_TitlesTextsAndMessages.LOG_IN_PAGE_TITLE.getAbsolutePath(), "texts should match");
        logInPage.enterUserEmail("oralee24@lmaritimen.com");
        logInPage.enterUserPassword("1234");
        logInPage.clickOnLogInBtn();
        Assert.assertEquals(wishlistPage.checkIfOnWishlistPage(), Page_TitlesTextsAndMessages.WISHLIST_PAGE_TITLE.getAbsolutePath(), "Titles should match");
        confluxHomePage.returnToHomePage();
        wishlistPage.addToWishlistFromLatestProducts();
        wishlistPage.toWishlistPage();
        Assert.assertEquals(wishlistPage.checkIfOnWishlistPage(), Page_TitlesTextsAndMessages.WISHLIST_PAGE_TITLE.getAbsolutePath(), "Titles should match");
        Assert.assertEquals(wishlistPage.checkNumberOfWishlistItems(), 1, "Numbers should match");
    }

    @Test (priority = 3)
    public void AddSecondItemToWishListFromBoardGamesPage(){
        confluxHomePage.returnToHomePage();
        boardGamesPage.clickOnBoardGamesPageBtn();
        wishlistPage.addToWishlistFromBoardgames();
        wishlistPage.toWishlistPage();
        Assert.assertEquals(wishlistPage.checkNumberOfWishlistItems(), 2, "Numbers should match");
    }


    @Test (priority = 4)
    public void RemoveOneItemFromWishlist(){
        wishlistPage.removeOneItemFromWishlist();
        Assert.assertEquals(wishlistPage.checkNumberOfWishlistItems(), 1, "Numbers should match");
    }

    @Test(priority = 5)
    public void AddOneItemFromRecentlyViewed(){
        wishlistPage.addFromRecentlyViewedList();
        webDriver.navigate().refresh();
        Assert.assertEquals(wishlistPage.checkNumberOfWishlistItems(), 2, "Numbers should match");
    }

    @Test(priority = 6)
    public void CheckWishlistAfterLogout(){
        logInPage.clickOnAccountPageBtnPopUpLogOut();
        wishlistPage.toWishlistPage();
        Assert.assertNotEquals(logInPage.checkIfOnLogInPage(), Page_TitlesTextsAndMessages.WISHLIST_PAGE_TITLE.getAbsolutePath(), "Titles shouldnt match");
    }
//region
    @AfterTest
    public void closeWebDriver(){
        webDriver.close();
    }
}
