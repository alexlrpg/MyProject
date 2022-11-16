package Lesson_6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LitmarketTest {
    WebDriver driver;
    MainPage mainPage;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://litmarket.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    void registrationOnSiteTest() {
        mainPage.registrationOnSite()
                .checkRegistrationOnSite();
    }

    @Test
    void addBookTest() {
        mainPage.registrationOnSite()
                .clickPopularBook()
                .addBook()
                .navigationBlock.clickMyLibraryButton()
                .checkBookToTheLibrary();
    }

    @Test
    void addCommentTest() {
        mainPage.registrationOnSite()
                .clickPopularBook()
                .addComment()
                .checkAddedComment();
    }

    @Test
    void deleteCommentTest() {
        mainPage.registrationOnSite()
                .clickPopularBook()
                .addComment()
                .deleteComment()
                .checkDeletedComment();
    }

    @Test
    void deleteBookTest() {
        mainPage.registrationOnSite()
                .clickPopularBook()
                .addBook()
                .navigationBlock.clickMyLibraryButton()
                .clickBookSettingsButton()
                .deleteBook()
                .checkDeletedBook();
    }

    @Test
    void editeProfileTest() {
        mainPage.registrationOnSite()
                .navigationBlock.clickMyPageButton()
                .clickEditAboutAuthorButton()
                .editedProfile("Моя страница")
                .checkEditedProfile();
    }

    @AfterEach
    void tearDownAndDeleteUser() {
        mainPage.navigationBlock.clickSettingsButton()
                .clickProfileButton()
                .deleteUser()
                .checkDeleteUser();
        driver.quit();
    }
}
