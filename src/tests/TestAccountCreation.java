package tests;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AccountCreationPage;
import pages.LogInPageAndLogOut;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class TestAccountCreation {
    private WebDriver webDriver;
    private Actions actions;
    private AccountCreationPage accountCreationPage;

 //region BeforeMethod
    private LogInPageAndLogOut logInPageAndLogOut;
    @BeforeMethod
    public void config() throws IOException {
        System.setProperty("webdriver.chrome.driver", utils.PropertiesReader.getInstance().getValue("WEBDRIVER.CHROMEDRIVER"));
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(webDriver);
        accountCreationPage = new AccountCreationPage(webDriver, actions);
        logInPageAndLogOut = new LogInPageAndLogOut(webDriver, actions);
        webDriver.get("https://conflux.rs/");
        webDriver.manage().window().maximize();
    }
//endregion
//region DataProvider
    @DataProvider(name="InvalidUserCredentials")
    public Object[][] getInvalidUserCredentials(){
        return new Object[][]
                {
                        { "Imeimeimeimeimeimeimeimeimeimeime", "Prezine", "email@email.com", "060060060", "1234", "1234" },//predugacko ime
                        { "", "Prezine", "email@email.com", "060060060", "1234", "1234" },//prekratko ime
                        { "Ime", "Prezimepreprepreprepreprepreprepr", "email@email.com", "060060060", "1234", "1234" },//predugacko prezime
                        { "Ime", "", "email@email.com", "060060060", "1234", "1234" },//prekratko prezime
                        { "Ime", "Prezine", "emailemail.com", "060060060", "1234", "1234" },//invalid email
                        { "Ime", "Prezine", "email@email.com", "060123456789876543212345678987654", "1234", "1234" },//predugacak fon
                        { "Ime", "Prezine", "email@email.com", "06", "1234", "1234" },//prekratak fon
                        { "Ime", "Prezine", "email@email.com", "060060060", "123456789876543212345", "123456789876543212345" },//predugacak pass
                        { "Ime", "Prezine", "email@email.com", "060060060", "123", "123" },//prekratak pass
                        { "Ime", "Prezine", "email@email.com", "060060060", "1234", "" },//bez confrima
                        { "Ime", "Prezine", "email@email.com", "060060060", "1234", "1244" },//razlicit confirm
                        { "Ime", "Prezine", "oralee24@lmaritimen.com", "060060060", "1234", "1234" },//iskoriscen email
                };
    }
//endregion
//region Tests
    @Test (description = "variation of createAccountWithInvalidData, read from xml file")
    public void invalidDataAcountCreation() throws IOException {
        FileInputStream inputStream = accountCreationPage.getInputStream();
        XSSFWorkbook workBook = accountCreationPage.getWorkBook();
        XSSFSheet sheet = accountCreationPage.getSheet();
        int rowCount=accountCreationPage.getRowCount();
        for(int i=0;i<=rowCount;i++) {
            logInPageAndLogOut.clickOnAccountPageBtn();
            accountCreationPage.continueToRegistration();
            accountCreationPage.setInputFirstName(sheet.getRow(i).getCell(0).getStringCellValue());
            accountCreationPage.setInputLastName(sheet.getRow(i).getCell(1).getStringCellValue());
            accountCreationPage.setInputEmail(sheet.getRow(i).getCell(2).getStringCellValue());
            accountCreationPage.setInputPhoneNumber(sheet.getRow(i).getCell(3).getStringCellValue());
            accountCreationPage.setInputPassword(sheet.getRow(i).getCell(4).getStringCellValue());
            accountCreationPage.setConfirmPassword(sheet.getRow(i).getCell(5).getStringCellValue());
            accountCreationPage.confirmPrivacyPolicy();
            accountCreationPage.finishAccountCreation();
            Assert.assertEquals(true, accountCreationPage.getMessage_invalidUserCredentials(), "Message should be displayed");
        }
    }
    @Test(dataProvider = "InvalidUserCredentials", enabled = false)
    public void createAccountWithInvalidData(String firstName,String lastName,String email,String phoneNumber,String password,String confirmPassword){
        logInPageAndLogOut.clickOnAccountPageBtn();
        accountCreationPage.continueToRegistration();
        accountCreationPage.setInputFirstName(firstName);
        accountCreationPage.setInputLastName(lastName);
        accountCreationPage.setInputEmail(email);
        accountCreationPage.setInputPhoneNumber(phoneNumber);
        accountCreationPage.setInputPassword(password);
        accountCreationPage.setConfirmPassword(confirmPassword);
        accountCreationPage.confirmPrivacyPolicy();
        accountCreationPage.finishAccountCreation();
        Assert.assertTrue(accountCreationPage.getMessage_invalidUserCredentials(), "Message should be displayed");
    }
    @Test
    public void validUserCredentialsWithoutAcceptingPrivacyPolicy(){
        logInPageAndLogOut.clickOnAccountPageBtn();
        accountCreationPage.continueToRegistration();
        accountCreationPage.setInputFirstName("Ime");
        accountCreationPage.setInputLastName("Prezime");
        accountCreationPage.setInputEmail("email@email.com");
        accountCreationPage.setInputPhoneNumber("060060060");
        accountCreationPage.setInputPassword("1234");
        accountCreationPage.setConfirmPassword("1234");
        accountCreationPage.finishAccountCreation();
        Assert.assertTrue(accountCreationPage.getMessage_invalidUserCredentials(), "Message should be displayed");
    }
//endregion
    @Test (enabled = false)
    public void clickOnCaptcha(){
        accountCreationPage.clickOn_Captcha_CheckBox();
    }
    @AfterMethod
    public void closeWD(){
        webDriver.close();
    }
}
