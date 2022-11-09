import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wdwait;
    public RegistrationPage registrationPage;

    @BeforeSuite
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));

        registrationPage = new RegistrationPage(driver, wdwait);
    }

    public void scrollIntoView(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitForElementVisibility(WebElement element){
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickability(WebElement element){
        wdwait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @AfterMethod
    public void refresh(){
        driver.navigate().refresh();
    }

    @AfterSuite
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @BeforeMethod
    public void refreshPage(){
        driver.navigate().refresh();
    }
}
