package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfluxHomePage {
    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//h2[normalize-space()='Prodavnica drustvenih igara']")
    private WebElement homeTitleConflux;

    @FindBy(xpath = "//img[contains(@title,'Conflux')]")
    private WebElement returnToHomePageBtn;

    @FindBy(xpath = "//li[@class=\"menu-item icons-menu-item icons-menu-item-1 icon-menu-icon\"]")
    private WebElement facebookBtn;

    @FindBy(xpath = "//li[@class=\"menu-item icons-menu-item icons-menu-item-3 icon-menu-icon\"]")
    private WebElement instagramBtn;

    @FindBy(xpath = "/html/body/div[4]/footer")
    private WebElement footer;

    @FindBy(id = "language")
    private WebElement languageHoverBtn;

    @FindBy(xpath = "//span[@class='language-title-dropdown'][normalize-space()='Srpski']")
    private WebElement serbianLanguage;

    @FindBy(xpath = "//span[normalize-space()='English']")
    private WebElement englishLanguage;

    public ConfluxHomePage(WebDriver webDriver, Actions actions){
        this.webDriver = webDriver;
        this.actions = actions;
        PageFactory.initElements(this.webDriver, this);
    }

    public String checkIfOnHomePage(){
        return homeTitleConflux.getText();
    }

    public void returnToHomePage(){
        returnToHomePageBtn.click();
    }

    public void toFacebookPage(){
        facebookBtn.click();
    }
    public void toInstagramPage(){
        instagramBtn.click();
    }

    public void returnToConfluxTab(){
        webDriver.switchTo().window(webDriver.getWindowHandle());
    }
    public void changeLanguageToSerbian(){
        languageHoverBtn.click();
        serbianLanguage.click();
    }

    public void revertLanguageToEnglish(){
        languageHoverBtn.click();
        englishLanguage.click();
    }

    public String checkIfLanguageHasChangedToSerbian(){
       return languageHoverBtn.getText();
    }

    public String checkIfLanguageHasChangedToEnglish(){
        return languageHoverBtn.getText();
    }

    public String checkIfFacebookPageHasOpened(){
        return webDriver.getTitle().toString();
    }

    public String checkIfInstagramPageHasOpened(){
        return webDriver.getTitle().toString();
    }

}
