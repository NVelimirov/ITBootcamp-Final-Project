package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WishlistPage {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//a[@class='btn btn-wishlist']")
    private WebElement toWishlistFromFirstLatestProduct;

    @FindBy(xpath = "//div[@class='product-layout swiper-slide has-extra-button swiper-slide-visible swiper-slide-active']")
    private WebElement latestProductsFirstItem;

    @FindBy(xpath = "//a[@href='https://conflux.rs/index.php?route=account/wishlist']")
    private WebElement wishlistPageBtn;

    @FindBy(xpath = "//*[@id=\"content\"]/h1")
    private WebElement wishlistTitlePage;

    @FindBy(css = ".btn.btn-wishlist")
    private WebElement toWishlistButton;

    @FindBy(xpath = "//tbody/tr[1]/td[6]/a[1]/i[1]")
    private WebElement removeOneFromBoardgames;

    @FindBy(xpath = "//div[@class='product-layout swiper-slide swiper-slide-visible swiper-slide-active']//a[@class='btn btn-wishlist']")
    private WebElement addRecentlyViewedItem;

    public WishlistPage(WebDriver webDriver, Actions actions){
        this.webDriver = webDriver;
        this.actions = actions;
        PageFactory.initElements(this.webDriver, this);
    }

    public void addToWishlistFromLatestProducts(){
            actions.moveToElement(latestProductsFirstItem).perform();
            actions.moveToElement(toWishlistFromFirstLatestProduct).click().perform();
    }

    public void toWishlistPage(){
        webDriver.navigate().refresh();
        actions.moveToElement(wishlistPageBtn).click().perform();
    }

    public String checkIfOnWishlistPage(){
        return wishlistTitlePage.getText();
    }

    public void addToWishlistFromBoardgames(){
        actions.moveToElement(toWishlistButton).click().perform();
    }

    public int checkNumberOfWishlistItems(){
        int numberOfItems = 0;
        WebElement table_Wishlist = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table"));
        List<WebElement> itemi = table_Wishlist.findElements(By.tagName("tr"));
        for (WebElement i : itemi){
            numberOfItems++;
        }

    }

    public void removeOneItemFromWishlist(){
        actions.moveToElement(removeOneFromBoardgames).click().perform();
    }

    public void addFromRecentlyViewedList(){
        actions.moveToElement(addRecentlyViewedItem).click().perform();
        webDriver.navigate().refresh();
    }

}
