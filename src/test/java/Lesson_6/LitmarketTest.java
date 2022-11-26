package Lesson_6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

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
    @DisplayName("Регистрация пользователя на сайте")
    @Severity(SeverityLevel.CRITICAL)
    void registrationOnSiteTest() {
        mainPage.registrationOnSite()
                .checkRegistrationOnSite();
    }

    @Test
    @DisplayName("Добавление книги на полку - Читаю")
    @Severity(SeverityLevel.NORMAL)
    void addBookTest() {
        mainPage.registrationOnSite()
                .clickNewBook()
                .addBook()
                .navigationBlock.clickMyLibraryButton()
                .checkBookToTheLibrary();
    }

    @Test
    @DisplayName("Добавление комментария к книге")
    @Severity(SeverityLevel.NORMAL)
    void addCommentTest() {
        mainPage.registrationOnSite()
                .clickNewBook()
                .addComment()
                .checkAddedComment();
    }

    @Test
    @DisplayName("Удаление комментария к книге")
    @Severity(SeverityLevel.NORMAL)
    void deleteCommentTest() {
        mainPage.registrationOnSite()
                .clickNewBook()
                .addComment()
                .deleteComment()
                .checkDeletedComment();
    }

    @Test
    @DisplayName("Удаление книги из библиотеки")
    @Severity(SeverityLevel.NORMAL)
    void deleteBookTest() {
        mainPage.registrationOnSite()
                .clickNewBook()
                .addBook()
                .navigationBlock.clickMyLibraryButton()
                .clickBookSettingsButton()
                .deleteBook()
                .checkDeletedBook();
    }

    @Test
    @DisplayName("Редактирование профиля")
    @Severity(SeverityLevel.NORMAL)
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
                .navigationBlock.clickExitButton()
                .checkDeleteUser();
        driver.navigate().refresh();
        getLogs();
        driver.quit();
    }

    void getLogs() {
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
        if (allLogRows.size() > 0 ) {
            allLogRows.forEach(logEntry -> {
                System.out.println(logEntry.getMessage());
            });
        }
    }
}
