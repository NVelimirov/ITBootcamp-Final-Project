package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearch {
    private WebDriver webDriver;

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")
    private WebElement googleSearchBar;

    @FindBy(xpath = "//a[@href='https://conflux.rs/']//h3[@class='LC20lb MBeuO DKV0Md'][normalize-space()='Conflux']")
    private WebElement confluxGoogleSearchLink;

    public GoogleSearch(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void googleSearchInput(){
        googleSearchBar.sendKeys("conflux");
        googleSearchBar.sendKeys(Keys.ENTER);
    }

    public void navigateToConfluxPage(){
        confluxGoogleSearchLink.click();
    }


}
