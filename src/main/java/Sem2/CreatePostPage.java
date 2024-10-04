package Sem2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePostPage {
    @FindBy(css = "[type='text']")
    private WebElement titleField;
    @FindBy(css = "textarea[maxlength='100']")
    private WebElement descriptionField;
    @FindBy(css = "[aria-controls]")
    private WebElement contentField;
    @FindBy(css = ".mdc-button__label")
    private WebElement savePostButton;

    public CreatePostPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void savePost(String title, String description, String content){
        titleField.sendKeys(title);
        descriptionField.sendKeys(description);
        contentField.sendKeys(content);
        savePostButton.click();
    }
}
