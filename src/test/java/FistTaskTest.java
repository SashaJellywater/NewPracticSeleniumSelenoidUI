import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class FistTaskTest {
    static  WebDriver driver;
    String myLogin = "GB202311d632412";
    String myPassword = "d060a954e4";

    @BeforeAll
    static void initElements() throws InterruptedException{
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @Disabled
    void test() throws InterruptedException{
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://test-stand.gb.ru/login");
        Thread.sleep(5000l);
        driver.quit();
    }
    @Test
    @Disabled
    void authorization() throws InterruptedException{
        WebElement login = driver.findElement(By.xpath("//*[@type='text']"));
        WebElement password = driver.findElement((By.xpath("//*[@type='password']")));
        //WebElement button = driver.findElement(By.xpath("//*[@class='mdc-button_ripple']"));
        WebElement button = driver.findElement(By.cssSelector("form#login button"));

        login.sendKeys(myLogin);
        password.sendKeys(myPassword);
        button.click();

        List<WebElement> searchElement = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, searchElement.size());
    }
    @Test
    void authorization2() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='text']")));
        WebElement password = driver.findElement((By.xpath("//*[@type='password']")));
        //WebElement button = driver.findElement(By.xpath("//*[@class='mdc-button_ripple']"));
        WebElement button = driver.findElement(By.cssSelector("form#login button"));

        login.sendKeys(myLogin);
        password.sendKeys(myPassword);
        button.click();

        List<WebElement> searchElement = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, searchElement.size());

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(
                    "D:\\IT\\NewPracticSelenium\\src\\test\\resources\\1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterAll
    static void closeApp(){
        driver.quit();
    }
}
