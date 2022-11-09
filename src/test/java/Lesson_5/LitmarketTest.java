package Lesson_5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LitmarketTest {
    WebDriver driver;
    Actions actions;

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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions = new Actions(driver);
        driver.get("https://litmarket.ru/");
    }

    @Test
    void registrationOnSite() {
        driver.findElement(By.cssSelector(".header-login-btn")).click();
        String email = "mail" + RandomStringUtils.randomNumeric(5) + "@gmail.com";
        String password = "pass" + RandomStringUtils.randomNumeric(5);
        actions.sendKeys(driver.findElement(By.xpath(".//input[@placeholder ='Email']")), email)
                .sendKeys(driver.findElement(By.xpath(".//input[@placeholder ='Пароль']")), password)
                .click(driver.findElement(By.xpath(".//input[@value='Регистрация']")))
                .build()
                .perform();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".checkbox-material > .check")));
        driver.findElement(By.cssSelector(".checkbox-material > .check")).click();
        driver.findElement(By.id("approve_role")).click();
        Assertions.assertTrue(driver.findElement(By.xpath(".//p[@class = 'nickname-wrapper']")).isDisplayed());
    }



    @AfterEach
    void deleteUserAndtearDown() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//p[@class = 'nickname-wrapper']")));
        driver.findElement(By.xpath(".//p[@class = 'nickname-wrapper']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//span[contains(text(), 'Настройки')]")));
        driver.findElement(By.xpath(".//span[contains(text(), 'Настройки')]")).click();

        driver.findElement(By.xpath(".//a[contains(text(),'Профиль')]")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-md")));
        driver.findElement(By.cssSelector(".btn-md")).click();

        driver.findElement(By.cssSelector(".swal2-confirm")).click();

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@href='https://litmarket.ru/user-restore']")));
        Assertions.assertTrue(driver.findElement(By.xpath(".//a[@href='https://litmarket.ru/user-restore']")).isDisplayed());
        driver.quit();
    }

    @Test
    void addBook() {
        registrationOnSite();
        driver.findElement(By.cssSelector("#popularSlider .active .img")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-library > a")));
        driver.findElement(By.cssSelector(".btn-library > a")).click();

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@data-shelf-name = 'Читаю']")));
        driver.findElement(By.xpath(".//div[@data-shelf-name = 'Читаю']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//p[@class = 'nickname-wrapper']")));
        driver.findElement(By.xpath(".//p[@class = 'nickname-wrapper']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//span[contains(text(), 'Моя Библиотека')]")));
        driver.findElement(By.xpath(".//span[contains(text(), 'Моя Библиотека')]")).click();

        Assertions.assertTrue(driver.findElement(By.xpath(".//a[@class='img-wrap']")).isDisplayed());
    }

    @Test
    void addComment() {
        addBook();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class = 'item'][1]")));
        driver.findElement(By.xpath(".//div[@class = 'item'][1]")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".emojionearea-editor")));
        driver.findElement(By.cssSelector(".emojionearea-editor")).sendKeys("Какая интересная книга!");

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sendComment-big")));
        driver.findElement(By.cssSelector(".sendComment-big")).click();

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath(".//i[@class='lmfont-angle-double-down more']")));
        Assertions.assertTrue(driver.findElement(By.xpath(".//i[@class='lmfont-angle-double-down more']")).isDisplayed());
    }

    @Test
    void deleteComment() {
        addComment();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//i[@class='lmfont-angle-double-down more']")));
        driver.findElement(By.xpath(".//i[@class='lmfont-angle-double-down more']")).click();

        driver.findElement(By.xpath(".//div[@class='popover-content']/div/a[@title='Удалить']")).click();

        driver.findElement(By.xpath(".//button[@class='swal2-confirm swal2-styled']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@data-notify='message']")));
        Assertions.assertTrue(driver.findElement(By.xpath(".//span[@data-notify='message']")).isDisplayed());
    }

    @Test
    void deleteBook() {
        addBook();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='library-button-link settings-btn']")));
        driver.findElement(By.xpath(".//div[@class='library-button-link settings-btn']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class='btn btn-inverse library-delete']")));
        driver.findElement(By.xpath(".//div[@class='btn btn-inverse library-delete']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[@data-notify='message']")));
        Assertions.assertTrue(driver.findElement(By.xpath(".//span[@data-notify='message']")).isDisplayed());
    }

    @Test
    void editProfile() {
        registrationOnSite();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//p[@class = 'nickname-wrapper']")));
        driver.findElement(By.xpath(".//p[@class = 'nickname-wrapper']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//span[contains(text(), 'Моя страница')]")));
        driver.findElement(By.xpath(".//span[contains(text(), 'Моя страница')]")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".edit-about-author .lmfont-edit")));
        driver.findElement(By.cssSelector(".edit-about-author .lmfont-edit")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//textarea[@class='form-control']")));
        driver.findElement(By.xpath(".//textarea[@class='form-control']")).sendKeys("Моя страница");

        driver.findElement(By.xpath(".//input[@type='submit']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//p[contains(text(), 'Моя страница')]")));
        Assertions.assertTrue(driver.findElement(By.xpath(".//p[contains(text(), 'Моя страница')]")).isDisplayed());
    }
}


