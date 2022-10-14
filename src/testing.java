import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testing {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", utils.PropertiesReader.getInstance().getValue("WEBDRIVER.CHROMEDRIVER"));
        WebDriver webDriver = new ChromeDriver();
        WebElement tabela = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody"));
        System.out.println(tabela.getText());
    }
}
