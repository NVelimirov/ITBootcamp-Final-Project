package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductFilters {
    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//span[@class='irs-slider from']")
    private WebElement slider_From;

    @FindBy(xpath = "//span[@class='irs-slider to']")
    private WebElement slider_To;

    @FindBy(className = "range-slider")
    private WebElement sliderMovementPercentage;

    @FindBy(className = "filter-price-min")
    private WebElement priceMin;

    @FindBy(className = "filter-price-max")
    private WebElement priceMax;

    @FindBy(css = ".main-products.product-grid")
    private WebElement displayedProductGrid;

    @FindBy(xpath = "//div[contains(@class,'module-item module-item-q panel panel-active')]//label[2]//input[1]")
    private WebElement checkBox_OutOfStock;

    public ProductFilters(WebDriver webDriver, Actions actions){
        this.webDriver = webDriver;
        this.actions = actions;
        PageFactory.initElements(this.webDriver, this);
    }

    public void move_From_slider(){
        actions.moveToElement(slider_From);
        actions.pause(Duration.ofSeconds(1));
        actions.clickAndHold(slider_From);
        actions.pause(Duration.ofSeconds(1));
        actions.moveByOffset(50,0);
        actions.pause(Duration.ofSeconds(1));
        actions.release().perform();
    }

    public void move_To_slider(){
        actions.moveToElement(slider_To);
        actions.pause(Duration.ofSeconds(1));
        actions.clickAndHold(slider_To);
        actions.pause(Duration.ofSeconds(1));
        actions.moveByOffset(-50,0);
        actions.pause(Duration.ofSeconds(1));
        actions.release().perform();
    }

    public String getMinPrice(){
        return priceMin.getAttribute("value");
    }

    public String getMaxPrice(){
        return priceMax.getAttribute("value");
    }

    public int checkDisplayedItemsPrice(){
        List<WebElement> displayedItems = webDriver.findElements(By.cssSelector(".product-layout.has-extra-button"));
        int numberOfDisplayedProducts = 0;
        for (WebElement items : displayedItems){
            String stringCena = items.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/div/div[2]/div[4]")).getText().substring(0,2);
            int cena = Integer.parseInt(stringCena);
            if (cena > 13){//stavljamo 13 jer je cena uneta u fromatu 15,000 sa zarezom
                numberOfDisplayedProducts++;
            }
        }
        return numberOfDisplayedProducts;
    }

    public void clickOutOfStockCheckbox(){
        checkBox_OutOfStock.click();
    }

    public int checkIfDisplayedItemsAreOutOfStock(){
        int numberOfDisplayedItems = 0;
        List<WebElement> displayedItems = webDriver.findElements(By.cssSelector(".product-layout.out-of-stock.has-extra-button"));
        numberOfDisplayedItems += displayedItems.stream()
                .filter(item -> item.getAttribute("class").contains("out-of-stock"))
                .count();
        return numberOfDisplayedItems;
    }

}
