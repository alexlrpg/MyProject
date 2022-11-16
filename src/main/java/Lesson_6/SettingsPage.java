package Lesson_6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SettingsPage extends BaseView {

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Профиль')]")
    private WebElement profileButton;

    public SettingsPage clickProfileButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
        return this;
    }

    @FindBy(css = ".btn-md")
    private WebElement deleteAccountButton;

    @FindBy(css = ".swal2-confirm")
    private WebElement confirmDeleteAccountButton;

    public MainPage deleteUser() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteAccountButton)).click();
        confirmDeleteAccountButton.click();
        return new MainPage(driver);
    }
}
