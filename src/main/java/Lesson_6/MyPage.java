package Lesson_6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyPage extends BaseView {

    public MyPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".edit-about-author .lmfont-edit")
    private WebElement editAboutAuthorButton;

    public MyPage clickEditAboutAuthorButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editAboutAuthorButton)).click();
        return this;
    }

    private static final String inputFormAboutAuthorByXpath = "//textarea[@class='form-control']";
    @FindBy(xpath = inputFormAboutAuthorByXpath)
    private WebElement inputFormAboutAuthor;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitProfileInfoButton;

    private static final String editedTextProfileByXpath = "//div[@class='bio-body note']/p[contains(text(), '')]";
    @FindBy(xpath = editedTextProfileByXpath)
    private WebElement editedTextProfile;

    public MyPage editedProfile(String text) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputFormAboutAuthorByXpath)))
                .sendKeys(text);
        submitProfileInfoButton.click();
        return this;
    }

    public void checkEditedProfile() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(editedTextProfileByXpath)));
        Assertions.assertTrue(editedTextProfile.isDisplayed());
    }
}
