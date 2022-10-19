package pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AccountCreationPage {
//region Fields
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//iframe[@title='reCAPTCHA']")
    private WebElement captcha_CheckBox;

    @FindBy(xpath = "//*[@id=\"rc-anchor-container\"]/div[3]")
    private WebElement captchaIFrame;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement continueToAccounCreationPage;

    @FindBy(xpath = "//input[@id='input-firstname']")
    private WebElement inputFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    private WebElement inputLastName;

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    private WebElement inputPhoneNumber;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement confirmPrivacyPolicy;

    @FindBy(xpath = "//span[normalize-space()='Continue']")
    private WebElement finishAccountCreationBtn;

    @FindBy(xpath = "//div[@class='text-danger']")
    private WebElement message_invalidUserCredentials;

    private FileInputStream inputStream = new FileInputStream("src\\utils\\ListOfInvalidUserCredentials.xlsx");

    private XSSFWorkbook workBook = new XSSFWorkbook(inputStream);

    private XSSFSheet sheet = workBook.getSheet("Sheet1");

    private int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
//endregion
    public AccountCreationPage(WebDriver webDriver, Actions actions) throws IOException {
        this.webDriver = webDriver;
        this.actions = actions;
        PageFactory.initElements(this.webDriver, this);
    }
    public void continueToRegistration(){
        continueToAccounCreationPage.click();
    }
    public void setInputFirstName(String input){
        inputFirstName.clear();
        inputFirstName.sendKeys(input);
    }
    public void setInputLastName(String input){
        inputLastName.clear();
        inputLastName.sendKeys(input);
    }
    public void setInputEmail(String input){
        inputEmail.clear();
        inputEmail.sendKeys(input);
    }
    public void setInputPhoneNumber(String input){
        inputPhoneNumber.clear();
        inputPhoneNumber.sendKeys(input);
    }
    public void setInputPassword(String input){
        inputPassword.clear();
        inputPassword.sendKeys(input);
    }
    public void setConfirmPassword(String input){
        confirmPassword.clear();
        confirmPassword.sendKeys(input);
    }
    public void confirmPrivacyPolicy(){
        actions.moveToElement(confirmPrivacyPolicy).click().perform();
    }
    public void finishAccountCreation(){
        finishAccountCreationBtn.click();
    }
    public boolean getMessage_invalidUserCredentials(){
        return message_invalidUserCredentials.isDisplayed();
    }
    public FileInputStream getInputStream(){
        return this.inputStream;
    }
    public XSSFWorkbook getWorkBook(){
        return this.workBook;
    }
    public XSSFSheet getSheet(){
        return this.sheet;
    }
    public int getRowCount(){
        return this.rowCount;
    }

}
