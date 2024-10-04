package HW2;
import Sem2.LoginPage;
import Sem2.MainPage;
import Sem2.ProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.module.Configuration;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfilePageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private static final String USERNAME = "Emmanuel.Cole";
    private static final String PASSWORD = "01d8c15f0a";
    private static final String SELENOID_URL = "http://localhost:4444/wd/hub";

    @BeforeEach
    public void setupTest() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        Map<String, Boolean> optionss = new HashMap<>();
        optionss.put("enableVNC", true);
        capabilities.setCapability("selenoid:options", optionss);

        driver = new RemoteWebDriver(new URL(SELENOID_URL), capabilities);
        driver.manage().window().maximize();
        driver.get("https://test-stand.gb.ru/login");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver, wait);
        mainPage = new MainPage(driver, wait);
        profilePage = new ProfilePage(driver, wait);
    }
    @Test
    void loginTest() throws InterruptedException {
        loginPage.loginInSystem(USERNAME, PASSWORD);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Hello")));

        List<WebElement> searchElement = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1, searchElement.size());

            Thread.sleep(10000); // Задержка на 10 секунд, чтобы успеть увидеть тест
    }

    @Test
    void editBirthDateTest() throws InterruptedException {

        String myDate = "01.01.2010";

        loginPage.loginInSystem(USERNAME, PASSWORD);
        mainPage.openProfilePage();
        profilePage.editProfile();
        Thread.sleep(5000);
        profilePage.editBirthDate(myDate);
        profilePage.saveEdits();
        profilePage.clickCloseButton();

        Thread.sleep(5000);
        System.out.println(profilePage.getBirthDateValue());
        Assertions.assertEquals(myDate, profilePage.getBirthDateValue());

        Thread.sleep(10000); // Задержка на 10 секунд, чтобы успеть увидеть тест

    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}