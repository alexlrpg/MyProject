package Lesson_6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigationBlock extends BaseView {
    public NavigationBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[@class = 'nickname-wrapper']")
    private WebElement nicknameWrapperButton;

    @FindBy(xpath = "//span[contains(text(), 'Моя страница')]")
    private WebElement myPageButton;

    @FindBy(xpath = "//span[contains(text(), 'Моя Библиотека')]")
    private WebElement myLibraryButton;

    @FindBy(xpath = "//span[contains(text(), 'Настройки')]")
    private WebElement settingsButton;

    public MyPage clickMyPageButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(nicknameWrapperButton)).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(myPageButton)).click();
        return new MyPage(driver);
    }

    public MyLibraryPage clickMyLibraryButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(nicknameWrapperButton)).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(myLibraryButton)).click();
        return new MyLibraryPage(driver);
    }

    public SettingsPage clickSettingsButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(nicknameWrapperButton)).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(settingsButton)).click();
        return new SettingsPage(driver);
    }

    public WebElement getNicknameWrapperButton() {
        return nicknameWrapperButton;
    }
}
