package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoardGamesPage {
    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//span[normalize-space()='Board Games']")
    private WebElement boardGamesBtn;

    public BoardGamesPage(WebDriver webDriver, Actions actions){
        this.webDriver = webDriver;
        this.actions = actions;
        PageFactory.initElements(this.webDriver, this);
    }

    public void clickOnBoardGamesPageBtn(){
        actions.moveToElement(boardGamesBtn).click().perform();
    }

}
