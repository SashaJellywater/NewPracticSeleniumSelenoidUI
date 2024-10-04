package HW2.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class GroupTableRow {
    private final WebElement root;

    public GroupTableRow(WebElement root){ this.root = root; }
    //Берет строку-root- и возвращает из неё имя группы
    public String getTitle(){
        return root.findElement(By.xpath("./td[2]")).getText();
    }
    //Берет строку-root- и возвращает из неё статус группы
    public String getStatus() {
        return root.findElement(By.xpath("./td[3]")).getText();
    }
    //Берет строку-root- и кликает иконку трэш
    public void clickTrashIcon() {
        root.findElement(By.xpath("./td/button[text()='delete']")).click();
        ((Wait<WebElement>) new FluentWait<>(root)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class))
                .until(root -> root.findElement(By.xpath("./td/button[text()='restore_from_trash']")));
    }
    //Берет строку-root- и кликает иконку антитрэш
    public void clickRestoreFromTrashIcon() {
        root.findElement(By.xpath("./td/button[text()='restore_from_trash']")).click();
        ((Wait<WebElement>) new FluentWait<>(root)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class))
                .until(root -> root.findElement(By.xpath("./td/button[text()='delete']")));
    }
    //Берет строку-root- и кликает иконку +студентов
    public void clickAddStudentsIcon() {
        root.findElement(By.cssSelector("td button i.material-icons")).click();
    }
    //Берет строку-root- и кликает иконку zoom открывающую таблицу студентов
    public void clickZoomInIcon() {
        root.findElement(By.xpath(".//td/button[contains(., 'zoom_in')]")).click();
    }
}
