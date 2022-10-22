package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    public WebDriver webDriver;
    public WebDriverWait wdWait;

    public Actions actions;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", utils.PropertiesReader.getInstance().getValue("WEBDRIVER.CHROMEDRIVER"));
        webDriver = new ChromeDriver();
        wdWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        actions = new Actions(webDriver);
    }

    public void scrollIntoView(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView()", webElement);
    }

    public void waitForElementVisibility(WebElement webElement){
        wdWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementClickability(WebElement webElement){
        wdWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    @AfterClass
    public void tearDown(){
        webDriver.close();
        webDriver.quit();
    }
}
