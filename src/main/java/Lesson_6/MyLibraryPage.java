package Lesson_6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyLibraryPage extends BaseView {

    public MyLibraryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='img-wrap']")
    private WebElement firstBookToTheLibrary;

    public void checkBookToTheLibrary() {
        Assertions.assertTrue(firstBookToTheLibrary.isDisplayed());
    }

    public BookPage clickFirstBookToTheLibrary() {
        firstBookToTheLibrary.click();
        return new BookPage(driver);
    }

    @FindBy(xpath = "//div[@class='library-button-link settings-btn']")
    private WebElement bookSettingsButton;

    public MyLibraryPage clickBookSettingsButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(bookSettingsButton)).click();
        return this;
    }

    @FindBy(xpath = "//div[@class='btn btn-inverse library-delete']")
    private WebElement deleteBookButton;

    private static final String bookDeletedMessageByXpath = "//span[@data-notify='message']";
    @FindBy(xpath = bookDeletedMessageByXpath)
    private WebElement bookDeletedMessage;

    public MyLibraryPage deleteBook() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteBookButton)).click();
        return this;
    }

    public void checkDeletedBook() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(bookDeletedMessageByXpath)));
        Assertions.assertTrue(bookDeletedMessage.isDisplayed());
    }
}
