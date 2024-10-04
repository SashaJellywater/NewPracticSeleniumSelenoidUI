package HW2;

import HW2.elements.GroupTableRow;
import HW2.elements.StudentTableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageAdmin {
    private final WebDriverWait wait;

    @FindBy(css = "nav li.mdc-menu-surface--anchor a")
    private WebElement usernameLinkInNavBar;

    // Create Group Modal Window
    @FindBy(id = "create-btn")
    private WebElement createGroupButton;
    @FindBy(xpath = "//form//span[contains(text(), 'Group name')]/following-sibling::input")
    private WebElement groupNameField;
    @FindBy(css = "form div.submit button")
    private WebElement submitButtonOnModalWindow;
    @FindBy(xpath = "//span[text()='Creating Study Group']" +
            "//ancestor::div[contains(@class, 'form-modal-header')]//button")
    private WebElement closeCreateGroupIcon;

    // Create Students Modal Window
    @FindBy(css = "div#generateStudentsForm-content input")
    private WebElement createStudentsFormInput;
    @FindBy(css = "div#generateStudentsForm-content div.submit button")
    private WebElement saveCreateStudentsForm;
    @FindBy(xpath = "//h2[@id='generateStudentsForm-title']/../button")
    private WebElement closeCreateStudentsFormIcon;

    @FindBy(xpath = "//table[@aria-label='Tutors list']/tbody/tr")
    private List<WebElement> rowsInGroupTable;
    @FindBy(xpath = "//table[@aria-label='User list']/tbody/tr")
    private List<WebElement> rowsInStudentTable;

    public MainPageAdmin(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
    //Метод ожидания- ждет пока в таблице не появится новая строка группы с (именем группы)
    public WebElement waitAndGetGroupTitleByText(String title) {
        String xpath = String.format("//table[@aria-label='Tutors list']/tbody//td[text()='%s']", title);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    // Переводит в текст содержание нав.бара - "Hello, Student 14"
    public String getUsernameLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(usernameLinkInNavBar))
                .getText().replace("\n", " ");
    }

    // Group
    // Создает группу с (именем группы)
    public void createGroup(String groupName) {
        wait.until(ExpectedConditions.visibilityOf(createGroupButton)).click();
        wait.until(ExpectedConditions.visibilityOf(groupNameField)).sendKeys(groupName);
        submitButtonOnModalWindow.click();
        waitAndGetGroupTitleByText(groupName);
    }
    // Закрывает окно создания группы
    public void closeCreateGroupModalWindow() {
        closeCreateGroupIcon.click();
        wait.until(ExpectedConditions.invisibilityOf(closeCreateGroupIcon));
    }
    // Возвращает статус группы по (названию группы)
    public String getStatusOfGroupWithTitle(String title) {
        return getGroupRowByTitle(title).getStatus();
    }
    // Возвращает строку из таблицы групп по (имени группы)
    private GroupTableRow getGroupRowByTitle(String title) {
        return rowsInGroupTable.stream()
                .map(GroupTableRow::new)
                .filter(row -> row.getTitle().equals(title))
                .findFirst().orElseThrow();
    }
    // Кликает на иконку трэш в строке с группой по (имени группы)
    public void clickTrashIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickTrashIcon();
    }
    // Кликает на иконку антитрэш в строке с группой по (имени группы)
    public void clickRestoreFromTrashIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickRestoreFromTrashIcon();
    }

    //Student
    // Кликает на иконку +студента в строке с группой по (имени группы)
    public void clickAddStudentsIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickAddStudentsIcon();
    }
    // Добавляет студентов в группу(в счетчик) в соответствии с количеством(например 3)
    public void typeAmountOfStudentsInCreateStudentsForm(int amount) {
        wait.until(ExpectedConditions.visibilityOf(createStudentsFormInput))
                .sendKeys(String.valueOf(amount));
    }
    // Сохраняет и добавляет студентов в группу по кнопке save
    public void clickSaveButtonOnCreateStudentsForm() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(saveCreateStudentsForm)).click();
        // Использование подобного ожидания - плохая практика, желательно стараться избегать
        // Но в некоторых очень редких случаях, при условии что вы, например, обсудили это
        // с участниками команды, можно оставить и так. Это может зависеть от соотношения
        // потраченных усилий на реализацию без sleep и частоты вызовы этого метода
        Thread.sleep(5000);
    }
    // Закрывает модальное окно добавления студентов в группу
    public void closeCreateStudentsModalWindow() {
        closeCreateStudentsFormIcon.click();
        wait.until(ExpectedConditions.invisibilityOf(closeCreateStudentsFormIcon));
    }
    // Открывает таблицу-список студентов в группе по (имени группы)
    public void clickZoomInIconOnGroupWithTitle(String title) {
        getGroupRowByTitle(title).clickZoomInIcon();
    }
    // Возвращает имя студента из первой строки таблицы студентов
    public String getFirstGeneratedStudentName() {
        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable.stream()
                .map(StudentTableRow::new)
                .findFirst().orElseThrow().getName();
    }
    // Возвращает статус студента по (имени)
    public String getStatusOfStudentWithName(String name) {
        return getStudentRowByName(name).getStatus();
    }
    // Возвращет строку студента из таблицы по (имени) студента
    private StudentTableRow getStudentRowByName(String name) {
        wait.until(ExpectedConditions.visibilityOfAllElements(rowsInStudentTable));
        return rowsInStudentTable.stream()
                .map(StudentTableRow::new)
                .filter(row -> row.getName().equals(name))
                .findFirst().orElseThrow();
    }
    // Кликает на кнопку трэш строки студента в таблице по (имени студента)
    public void clickTrashIconOnStudentWithName(String name) {
        getStudentRowByName(name).clickTrashIcon();
    }
    // Кликает на кнопку антитрэш строки студента в таблице по (имени студента)
    public void clickRestoreFromTrashIconOnStudentWithName(String name) {
        getStudentRowByName(name).clickRestoreFromTrashIcon();
    }
}
