package Sem2;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProfilePage {

    private final WebDriverWait wait;

    @FindBy(xpath = "//button[contains(@class, 'mdc-icon-button') and @title='More options']")
    WebElement editProfileButton;
            //Изменение аватара
    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[2]/div[2]/div/div[2]/div/form/div[3]/label/input")
    WebElement editAvatarField;
            // Изменение даты рождения
    @FindBy(xpath = "//*[@id=\"update-item\"]/div[3]/label/input")
    WebElement editBirthField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveProfileEditsButton;

    @FindBy(xpath = "/html/body/div[1]/main/div/div/div[1]/div/div[2]/div/div[2]/div[2]")
    WebElement birthDateValueRow;

    @FindBy(xpath = "//button[@data-mdc-dialog-action='close']")
    WebElement closeButton;

    //button[@data-mdc-dialog-action='close']

    String filePath;
    //

    public ProfilePage(WebDriver driver, WebDriverWait wait){
        PageFactory.initElements(driver, this);
        this.wait = wait;

    }

    public void editProfile(){
        wait.until(ExpectedConditions.visibilityOf(editProfileButton)).click();
    }

    public void editAvatar(String filePath){
        wait.until(ExpectedConditions.visibilityOf(editAvatarField)).sendKeys(filePath);
    }

    public String getAvatarFileName(){
        return editAvatarField.getAttribute("value");
    }

    public void editBirthDate(String birthDate){
        editBirthField.click();
        editBirthField.clear();
        editBirthField.sendKeys(Keys.HOME);
        editBirthField.sendKeys(birthDate);
    }

    public String getBirthDateValue(){
        return birthDateValueRow.getText();
    }

    public void saveEdits(){
        wait.until(ExpectedConditions.visibilityOf(saveProfileEditsButton)).click();
    }

    public void clickCloseButton(){
        closeButton.click();
    }
}






















