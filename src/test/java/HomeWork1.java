import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HomeWork1 {
    static WebDriver driver;
    String myLogin = "GB202311d632412";
    String myPassword = "d060a954e4";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeAll
    static void initElements() throws InterruptedException{
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @BeforeEach
    void authorization2() throws InterruptedException{
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='text']")));
        WebElement password = driver.findElement((By.xpath("//*[@type='password']")));
        WebElement button = driver.findElement(By.cssSelector("form#login button"));

        login.sendKeys(myLogin);
        password.sendKeys(myPassword);
        button.click();
    }
    @Test
    void createDummyTest() throws InterruptedException{
        //WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                //By.id("create-btn")));
        WebElement addButton = wait.until(ExpectedConditions.presenceOfElementLocated(
        By.cssSelector("#create-btn")));
        addButton.click();

        WebElement firstNameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[contains(., 'Fist Name')]")));
        firstNameField.sendKeys("Dum-0");

        WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//label[contains(., 'Login')]")));
        //WebElement loginField = driver.findElement(By.xpath("//label[contains(., 'Login')]"));
        loginField.sendKeys("Dum-085gsggfg3");

        //List<WebElement> saveButton = driver.findElements(By.cssSelector("div[class='submit'] span.mdc-button__label"));
        //Assertions.assertEquals(1, saveButton.size());
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div[class='submit'] span.mdc-button__label")));
        saveButton.click();


        List<WebElement> elements = driver.findElements(By.xpath("//td[contains(., 'Dum-0')]"));
        Assertions.assertEquals(3, elements.size());

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(
                    "D:\\IT\\NewPracticSelenium\\src\\test\\resources\\hw1.png"));
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    @AfterEach
    void closeApp(){
        driver.quit();
    }
}
