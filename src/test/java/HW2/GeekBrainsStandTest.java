package HW2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GeekBrainsStandTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPageAdmin loginPageAdmin;
    private MainPageAdmin mainPageAdmin;

    private String USERNAME;
    private String PASSWORD;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://test-stand.gb.ru/login");
        loginPageAdmin = new LoginPageAdmin(driver, wait);
    }

    @Test
    public void testLoginWithEmptyFields() {
        loginPageAdmin.clickLoginButton();
        Assertions.assertEquals("401 Invalid credentials.", loginPageAdmin.getErrorBlockText());
    }

    @Test
    public void testAddingGroupOnMainPageAdmin() {
        loginPageAdmin.login("Student-14", "386001db06");
        mainPageAdmin = new MainPageAdmin(driver, wait);
        Assertions.assertTrue(mainPageAdmin.getUsernameLabelText().contains(USERNAME));

        String groupTestName = "New Group" + System.currentTimeMillis();
        mainPageAdmin.createGroup(groupTestName);
        Assertions.assertTrue(mainPageAdmin.waitAndGetGroupTitleByText(groupTestName).isDisplayed());
    }

    @Test
    void testArchiveGroupOnMainPageAdmin() {
        loginPageAdmin.login("Student-14", "386001db06");
        mainPageAdmin = new MainPageAdmin(driver, wait);
        //Assertions.assertTrue(mainPageAdmin.getUsernameLabelText().contains(USERNAME));

        String groupTestName = "New Test Group" + System.currentTimeMillis();
        mainPageAdmin.createGroup(groupTestName);
        mainPageAdmin.closeCreateGroupModalWindow();

        Assertions.assertEquals("active", mainPageAdmin.getStatusOfGroupWithTitle(groupTestName));
        mainPageAdmin.clickTrashIconOnGroupWithTitle(groupTestName);
        Assertions.assertEquals("inactive", mainPageAdmin.getStatusOfGroupWithTitle(groupTestName));
        mainPageAdmin.clickRestoreFromTrashIconOnGroupWithTitle(groupTestName);
        Assertions.assertEquals("active", mainPageAdmin.getStatusOfGroupWithTitle(groupTestName));
    }

    @Test
    void testBlockingStudentInTableOnMainPageAdmin() throws InterruptedException {
        loginPageAdmin.login("Student-14", "386001db06");
        mainPageAdmin = new MainPageAdmin(driver, wait);

        String groupTestName = "New Test Group" + System.currentTimeMillis();
        mainPageAdmin.createGroup(groupTestName);
        mainPageAdmin.closeCreateGroupModalWindow();

        mainPageAdmin.clickAddStudentsIconOnGroupWithTitle(groupTestName);
        mainPageAdmin.typeAmountOfStudentsInCreateStudentsForm(3);
        mainPageAdmin.clickSaveButtonOnCreateStudentsForm();
        mainPageAdmin.closeCreateStudentsModalWindow();
        mainPageAdmin.clickZoomInIconOnGroupWithTitle(groupTestName);

        String firstGeneratedStudentName = mainPageAdmin.getFirstGeneratedStudentName();
        Assertions.assertEquals("active", mainPageAdmin.getStatusOfStudentWithName(firstGeneratedStudentName));
        mainPageAdmin.clickTrashIconOnStudentWithName(firstGeneratedStudentName);
        Assertions.assertEquals("block", mainPageAdmin.getStatusOfStudentWithName(firstGeneratedStudentName));
        mainPageAdmin.clickRestoreFromTrashIconOnStudentWithName(firstGeneratedStudentName);
        Assertions.assertEquals("active", mainPageAdmin.getStatusOfStudentWithName(firstGeneratedStudentName));
    }

    @AfterEach
    public void close(){
        driver.quit();
    }
}