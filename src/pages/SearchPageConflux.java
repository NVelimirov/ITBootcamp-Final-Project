package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPageConflux {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//*[@id=\"content\"]/h2[1]")
    private WebElement searchBarTitle;

    @FindBy(xpath = "//input[@placeholder='Search here...']")
    private WebElement searchBar;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div[2]/div[1]/div/div[2]/div[2]/a")
    private WebElement articleName;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div[2]")
    private WebElement productsGrid;

    @FindBy(xpath = "//div[contains(@class,'main-products product-grid')]//div[2]//div[1]//div[1]//a[1]//div[1]")
    private WebElement middleItem;


    public SearchPageConflux(WebDriver webDriver, Actions actions){
        this.webDriver = webDriver;
        this.actions = actions;
        PageFactory.initElements(this.webDriver, this);
    }

    public String checkIfOnSearchPage(){
        return searchBarTitle.getText();
    }

    public void inputIntoSearchBar(String text){
        searchBar.click();
        searchBar.sendKeys(text);
        searchBar.sendKeys(Keys.ENTER);
    }

    public void moveToProductsGrid(){
        JavascriptExecutor js = (JavascriptExecutor)webDriver ;
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, productsGrid);
    }
    public int checkNumberOfSearchedItemsThatMatchKeywords(String searchInputText){
        int numberOfDisplayedItems = 0;
        WebElement searchProductsInStockTable = webDriver.findElement(By.xpath("//div[@class='main-products product-grid']"));
        List<WebElement> searchedDisplayedItemsInStock = searchProductsInStockTable.findElements(By.xpath("//div[@class='product-layout  has-extra-button']"));
        //dve varijante
        numberOfDisplayedItems += searchedDisplayedItemsInStock.stream()
                .filter(item -> item.getText().contains(searchInputText))
                .count();

        WebElement searchProductsOutOfStockTable = webDriver.findElement(By.xpath("//div[@class='main-products product-grid']"));
        List<WebElement> searchedDisplayedItemsOutOfStock = searchProductsOutOfStockTable.findElements(By.xpath("//div[@class='product-layout  has-extra-button']"));
        for (WebElement i : searchedDisplayedItemsOutOfStock){
            if (i.getText().contains(searchInputText)) {
                numberOfDisplayedItems++;
            }
        }
        return numberOfDisplayedItems;
    }

    public String checkIfSearchContainsKeyword(){
        return articleName.getText();
    }
}
