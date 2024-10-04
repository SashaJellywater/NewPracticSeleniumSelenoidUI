package HW2.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class StudentTableRow {
    private final WebElement root;

    public StudentTableRow(WebElement root) {
        this.root = root;
    }
    // Возвращает имя студента из строки-root
    public String getName() {
        return root.findElement(By.xpath("./td[2]")).getText();
    }
    // Возвращает имя студента из строки-root
    public String getStatus() {
        return root.findElement(By.xpath("./td[4]")).getText();
    }
    // Кликает на иконку трэш строки-root
    public void clickTrashIcon() {
        root.findElement(By.xpath("./td/button[text()='delete']")).click();
        ((Wait<WebElement>) new FluentWait<>(root)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class))
                .until(root -> root.findElement(By.xpath("./td/button[text()='restore_from_trash']")));
    }
    // Кликает на иконку антитрэш строки-root
    public void clickRestoreFromTrashIcon() {
        root.findElement(By.xpath("./td/button[text()='restore_from_trash']")).click();
        ((Wait<WebElement>) new FluentWait<>(root)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class))
                .until(root -> root.findElement(By.xpath("./td/button[text()='delete']")));
    }
}
