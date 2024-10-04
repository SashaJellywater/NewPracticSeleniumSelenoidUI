package Sem2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriverWait wait;

    @FindBy(css="#create-btn")
    WebElement createNewPostButton;
    @FindBy(xpath="//*[@id=\"app\"]/main/nav/ul/li[3]/a")
    WebElement openHelloButton;
    @FindBy(xpath="//*[@id=\"app\"]/main/nav/ul/li[3]/div/ul/li[1]/span[2]")
    WebElement openProfileButton;


    public MainPage(WebDriver driver, WebDriverWait wait){
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void openProfilePage(){
        wait.until(ExpectedConditions.visibilityOf(openHelloButton)).click();
        wait.until(ExpectedConditions.visibilityOf(openProfileButton)).click();
    }
    public void createNewPost(){
        wait.until(ExpectedConditions.visibilityOf(createNewPostButton)).click();
    }
}
