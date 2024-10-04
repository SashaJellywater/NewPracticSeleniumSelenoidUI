package Sem2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ElementPage {
    @FindBy(xpath = "//button")
    List<WebElement> allButtons;

    public ElementPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void deletePost(){
        allButtons.get(1).click();
    }
}






















