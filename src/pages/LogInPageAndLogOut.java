package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LogInPageAndLogOut {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "/html/body/div[4]/header/div[1]/div[2]/div[3]/div/div/ul/li[1]")
    private WebElement accountPageBtn;

    @FindBy(xpath = "//li[@class='menu-item top-menu-item-2']//a[@href='javascript:open_login_popup()']//span[@class='links-text'][normalize-space()='Login']")
    private WebElement accoutPageBtnPopUpLogIn;

    @FindBy(xpath = "//h1[@class='title page-title']")
    private WebElement logInPageTitle;

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement inputEmailAddress;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement logInBtn;

    @FindBy(xpath = "//h1[@class='title page-title']")
    private WebElement accountPageTitle;

    @FindBy(xpath = "//li[@class='menu-item top-menu-item-5']//a")
    private WebElement accoutPageBtnPopUpLogOut;

    @FindBy(xpath = "//h1[@class='title page-title']")
    private WebElement logOutPageTitle;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement continueToHomePageFromLogOut;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement warning_NoMatchForEmailOrPassword;

    @FindBy(xpath = "//span[normalize-space()='Login']")
    private WebElement popUpLoginInBtn;

    @FindBy(xpath = "//iframe[@src='index.php?route=account/login&popup=login']")
    private WebElement frame_logInPopUpWindow;

    @FindBy(xpath = "//*[@id=\"input-email\"]")
    private WebElement inputEmailAddressPopUp;

    @FindBy(xpath = "//*[@id=\"input-password\"]")
    private WebElement inputPasswordPopUp;

    @FindBy(xpath = "/html/body/div/form/div[3]/div/button")
    private WebElement logInBtnPopUp;

    public LogInPageAndLogOut(WebDriver webDriver, Actions actions){
        this.webDriver = webDriver;
        this.actions = actions;
        PageFactory.initElements(this.webDriver, this);
    }

    public void clickOnAccountPageBtn(){
        accountPageBtn.click();
    }

    public void clickOnAccountPageBtnPopUpLogIn(){
        actions.moveToElement(accountPageBtn).perform();
        accoutPageBtnPopUpLogIn.click();
    }

    public String checkIfOnLogInPage(){
        return logInPageTitle.getText();
    }

    public void enterUserEmail(String email){
        inputEmailAddress.sendKeys(email);
    }

    public void enterUserPassword(String password){
        inputPassword.sendKeys(password);
    }

    public void clickOnLogInBtn(){
        logInBtn.click();
    }

    public String checkIfLogInIsSuccesfull(){
        return accountPageTitle.getText();
    }

    public void clickOnAccountPageBtnPopUpLogOut(){
        actions.moveToElement(accountPageBtn).perform();
        accoutPageBtnPopUpLogOut.click();
    }

    public String checkIfOnLogOutPage(){
        return logOutPageTitle.getText();
    }

    public void continueToHomePage(){
        continueToHomePageFromLogOut.click();
    }

    public String checkForWarningPopUp(){
        return warning_NoMatchForEmailOrPassword.getText();
    }

    public void clickLogInPopUpBtn(){
        popUpLoginInBtn.click();
    }

    public void enterUserEmailPopUp(String email){
            webDriver.switchTo().frame(frame_logInPopUpWindow);
            actions.moveToElement(inputEmailAddressPopUp).click().sendKeys(email).perform();
    }

    public void enterUserPasswordPopUp(String password){
        actions.moveToElement(inputPasswordPopUp).click().sendKeys(password).perform();
    }

    public void clickOnLogInBtnPopUp(){
        actions.moveToElement(inputPasswordPopUp).click().perform();
    }


}
