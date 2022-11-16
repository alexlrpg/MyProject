package Lesson_6;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BaseView {

    public NavigationBlock navigationBlock;

    public MainPage(WebDriver driver) {
        super(driver);
        navigationBlock = new NavigationBlock(driver);
    }

    @FindBy(css = ".header-login-btn")
    private WebElement headerLoginButton;

    @FindBy(xpath = "//input[@placeholder ='Email']")
    private WebElement emailButton;

    @FindBy(xpath = "//input[@placeholder ='Пароль']")
    private WebElement passwordButton;

    @FindBy(xpath = "//input[@value='Регистрация']")
    private WebElement registrationButton;

    @FindBy(css = ".checkbox-material > .check")
    private WebElement checkbox;

    @FindBy(id = "approve_role")
    private WebElement approveRoleButton;

    public MainPage registrationOnSite() {
        String email = "mail" + RandomStringUtils.randomNumeric(5) + "@gmail.com";
        String password = "pass" + RandomStringUtils.randomNumeric(5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(headerLoginButton)).click();
        actions.sendKeys(emailButton, email)
                .sendKeys(passwordButton, password)
                .click(registrationButton)
                .build()
                .perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
        approveRoleButton.click();
        return this;
    }

    public void checkRegistrationOnSite() {
        Assertions.assertTrue(navigationBlock.getNicknameWrapperButton().isDisplayed());
    }

    @FindBy(css = "#popularSlider .active .img")
    private WebElement popularBook;

    public BookPage clickPopularBook() {
        popularBook.click();
        return new BookPage(driver);
    }

    @FindBy(xpath = "//a[@href='https://litmarket.ru/user-restore']")
    private WebElement userRestoreButton;

    public void checkDeleteUser() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(userRestoreButton));
        Assertions.assertTrue(userRestoreButton.isDisplayed());
    }
}