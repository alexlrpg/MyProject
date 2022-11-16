package Lesson_6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookPage extends BaseView {

    public NavigationBlock navigationBlock;

    public BookPage(WebDriver driver) {
        super(driver);
        navigationBlock = new NavigationBlock(driver);
    }

    @FindBy(css = ".btn-library > a")
    private WebElement addedLibraryButton;

    @FindBy(xpath = "//div[@data-shelf-name = 'Читаю']")
    private WebElement readingBookButton;

    public BookPage addBook() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addedLibraryButton)).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(readingBookButton)).click();
        return this;
    }

    @FindBy(css = ".emojionearea-editor")
    private WebElement commentField;

    @FindBy(css = ".sendComment-big")
    private WebElement sendCommentButton;

    @FindBy(xpath = "//i[@class='lmfont-angle-double-down more']")
    private WebElement commentActionsButton;

    public BookPage addComment() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(commentField)).sendKeys("Очень интересная книга");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(sendCommentButton)).click();
        return this;
    }

    public void checkAddedComment() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(commentActionsButton));
        Assertions.assertTrue(commentActionsButton.isDisplayed());
    }

    private static final String deleteCommentButtonByXpath = "//div[@class='popover-content']/div/a[@title='Удалить']";
    @FindBy(xpath = deleteCommentButtonByXpath)
    private WebElement deleteCommentButton;

    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled']")
    private WebElement confirmDeleteCommentButton;

    private static final String deleteMessageByXpath = "//span[@data-notify='message']";
    @FindBy(xpath = deleteMessageByXpath)
    private WebElement deleteMessage;

    public BookPage deleteComment() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(commentActionsButton)).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteCommentButtonByXpath))).click();
        confirmDeleteCommentButton.click();
        return this;
    }

    public void checkDeletedComment() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(deleteMessageByXpath)));
        Assertions.assertTrue(deleteMessage.isDisplayed());
    }
}
