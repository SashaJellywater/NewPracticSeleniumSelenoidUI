package Sem2;
import HW2.LoginPageAdmin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class LoginPageTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private String USERNAME = "Emmanuel.Cole";
    private String PASSWORD = "01d8c15f0a";

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://test-stand.gb.ru/login");

    }

    @Test
    void test1teshomework() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver, wait);
        MainPage mainPage = new MainPage(driver, wait);
        ProfilePage profilePage = new ProfilePage(driver, wait);

        String myAvatar = "D:\\IT\\NewPracticSelenium — копия\\src\\test\\resources\\bb.jpg";

        loginPage.loginInSystem(USERNAME,PASSWORD);
        mainPage.openProfilePage();
        profilePage.editProfile();
        profilePage.editAvatar(myAvatar);

        System.out.println(profilePage.getAvatarFileName());

        Assertions.assertTrue(profilePage.getAvatarFileName().contains("bb"));
        profilePage.saveEdits();
    }

    @Test
    void test2teshomework() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver, wait);
        MainPage mainPage = new MainPage(driver, wait);
        ProfilePage profilePage = new ProfilePage(driver, wait);

        String myDate = "11.12.2003";
        loginPage.loginInSystem(USERNAME,PASSWORD);
        mainPage.openProfilePage();
        profilePage.editProfile();
        Thread.sleep(3000);
        profilePage.editBirthDate(myDate);
        profilePage.saveEdits();
        profilePage.clickCloseButton();
        Thread.sleep(3000);
        Assertions.assertEquals(myDate, profilePage.getBirthDateValue());
    }

//    @Test
//    void loginTest() throws InterruptedException {
//
//        LoginPage loginPage = new LoginPage(driver);
//
//        loginPage.loginInSystem(USERNAME,PASSWORD);
//
//        Thread.sleep(5000l);
//
//        List<WebElement> searchElement = driver.findElements(By.partialLinkText("Hello"));
//        Assertions.assertEquals(1, searchElement.size());
//
//        MainPage mainPage = new MainPage(driver);
//        mainPage.createNewPost();
//
//        List<WebElement> searchTitle = driver.findElements(By.xpath("//*[@type='text']"));
//        Assertions.assertEquals(1, searchTitle.size());
//
//        CreatePostPage createPostPage = new CreatePostPage(driver);
//        createPostPage.savePost("WoW", "Ohhh","Lol");
//
//        Thread.sleep(5000l);
//        ElementPage elementPage = new ElementPage(driver);
//        elementPage.deletePost();
//    }
//
//    @Test
//    void failLoginTest() throws InterruptedException {
//
//        LoginPage loginPage = new LoginPage(driver);
//
//        loginPage.loginInSystem("","");
//
//        WebElement error = loginPage.getErrorBlock();
//
//        Thread.sleep(5000l);
//        Assertions.assertEquals("401\n" + "Invalid credentials.", error.getText());
//    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}





































