package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProductFilters {
    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//span[@class='irs-slider from']")
    private WebElement slider_From;

    @FindBy(xpath = "//span[@class='irs-slider to']")
    private WebElement slider_To;

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
        actions.moveByOffset(-125,0);
        actions.pause(Duration.ofSeconds(1));
        actions.release().perform();

    }

    public void move_To_slider(){

    }

}
