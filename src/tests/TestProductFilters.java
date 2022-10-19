package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BoardGamesPage;
import pages.ConfluxHomePage;
import pages.GoogleSearch;
import pages.ProductFilters;

import java.time.Duration;

//pomer jedan slider, prveri da li se proj prikazanih proizvoda smanjio, isto za drugi
//probaj jedan checkbox, proveri prikazane
//spusti jedan dropdown i probaj checkbox, proveri prikazane
//skroluj kroz gameboardScore i izaberi 10, proveri broj prikazanih
public class TestProductFilters {
    private WebDriver webDriver;

    private Actions actions;

    private ProductFilters productFilters;

    private ConfluxHomePage confluxHomePage;

    private BoardGamesPage boardGamesPage;

    @BeforeTest
    public void config(){
        System.setProperty("webdriver.chrome.driver", utils.PropertiesReader.getInstance().getValue("WEBDRIVER.CHROMEDRIVER"));
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(webDriver);
        confluxHomePage = new ConfluxHomePage(webDriver, actions);
        productFilters = new ProductFilters(webDriver, actions);
        boardGamesPage = new BoardGamesPage(webDriver, actions);
        webDriver.get("https://conflux.rs/Board-Games");
        webDriver.manage().window().maximize();
    }
    @Test
    public void moveSliderToChangePriceAndDisplayedProducts(){
        productFilters.move_From_slider();
        productFilters.move_To_slider();
        Assert.assertEquals(productFilters.getMinPrice(), "13074");
        Assert.assertEquals(productFilters.getMaxPrice(), "32425");
        Assert.assertEquals(productFilters.checkDisplayedItemsPrice(), 12);
        //ako je cena prikazanog proizvoda veca od 13k, povecavamo brojac za 1
        //ako je svim prikazanim proizvodima cena veca od 13k, broj prikazanih i broj izbrojanih bi trebalo da se poklapaju
    }


    @AfterTest
    public void closeWebDriver(){
        webDriver.close();
    }
}
