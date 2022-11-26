package Lesson_6;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
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

    private static final String emailButtonByXpath = "//input[@placeholder ='Email']";
    @FindBy(xpath = emailButtonByXpath)
    private WebElement emailButton;

    private static final String passwordButtonByXpath = "//input[@placeholder ='Пароль']";
    @FindBy(xpath = passwordButtonByXpath)
    private WebElement passwordButton;

    @FindBy(xpath = "//input[@value='Регистрация']")
    private WebElement registrationButton;

    @FindBy(xpath = "//input[@id='eula_agree']/following-sibling::span[@class='checkbox-material']")
    private WebElement checkbox;

    @FindBy(id = "approve_role")
    private WebElement approveRoleButton;

    @FindBy(xpath = "//input[@id='eula_18_years']/following-sibling::span[@class='checkbox-material']")
    private WebElement checkbox18Years;

    public MainPage registrationOnSite() {
        String email = "mail" + RandomStringUtils.randomNumeric(5) + "@gmail.com";
        String password = "pass" + RandomStringUtils.randomNumeric(5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(headerLoginButton)).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailButtonByXpath))).sendKeys(email);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordButtonByXpath))).sendKeys(password);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(registrationButton)).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(checkbox18Years)).click();
        approveRoleButton.click();
        return this;
    }

    public void checkRegistrationOnSite() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(navigationBlock.getNicknameWrapperButton()));
        Assertions.assertTrue(navigationBlock.getNicknameWrapperButton().isDisplayed());
    }

    @FindBy(xpath = "//div[@data-show='newBooks']//div[@class='item active']")
    private WebElement newBook;

    public BookPage clickNewBook() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(newBook)).click();
        return new BookPage(driver);
    }

    private final static String userRestoreButtonByXpath = "//a[@href='https://litmarket.ru/user-restore']";
    @FindBy(xpath = userRestoreButtonByXpath)
    private WebElement userRestoreButton;

    @FindBy(xpath = "//span[@data-notify='message']")
    private WebElement alertSessionExite;

    public void checkDeleteUser() {
        Assertions.assertTrue(alertSessionExite.isDisplayed());
    }


}