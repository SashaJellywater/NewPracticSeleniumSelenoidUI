package Sem2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriverWait wait;

    @FindBy(css = "form#login input[type='text']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordField;

    @FindBy(css = "form#login button")
    private WebElement loginButton;

    @FindBy(css = ".error-block")
    private WebElement errorBlock;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void loginInSystem(String myLogin, String myPassword){
        wait.until(ExpectedConditions.visibilityOf(loginField)).sendKeys(myLogin);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(myPassword);
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
    }

    public WebElement getErrorBlock(){
        return errorBlock;
    }
}





















