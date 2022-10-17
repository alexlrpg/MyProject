package Lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AutoLitmarket {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://litmarket.ru/");

        //1) Registration on site

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header-login-btn")));
        driver.findElement(By.cssSelector(".header-login-btn")).click();

        new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@placeholder ='Email']")));
        String email = "mail"+ RandomStringUtils.randomNumeric(5) + "@gmail.com";
        driver.findElement(By.xpath(".//input[@placeholder ='Email']")).sendKeys(email);

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//input[@placeholder ='Пароль']")));
        String password = "pass" + RandomStringUtils.randomNumeric(5);
        driver.findElement(By.xpath(".//input[@placeholder ='Пароль']")).sendKeys(password);

        driver.findElement(By.xpath(".//input[@value='Регистрация']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".checkbox-material > .check")));
        driver.findElement(By.cssSelector(".checkbox-material > .check")).click();

        driver.findElement(By.id("approve_role")).click();


        //2) Add a book to the library

        WebElement popularSlader = driver.findElement(By.cssSelector("#popularSlider .active .img"));
        popularSlader.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-library > a")));
        driver.findElement(By.cssSelector(".btn-library > a")).click();

        new WebDriverWait(driver,15).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@data-shelf-name = 'Читаю']")));
        driver.findElement(By.xpath(".//div[@data-shelf-name = 'Читаю']")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//p[@class = 'nickname-wrapper']")));
        driver.findElement(By.xpath(".//p[@class = 'nickname-wrapper']")).click();

        driver.findElement(By.xpath(".//span[contains(text(), 'Моя Библиотека')]")).click();


        //3) Add a comment

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class = 'item'][1]")));
        driver.findElement(By.xpath(".//div[@class = 'item'][1]")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".emojionearea-editor")));
        driver.findElement(By.cssSelector(".emojionearea-editor")).sendKeys("Какая интересная книга!");

        driver.findElement(By.cssSelector(".sendComment-big")).click();


        //4) Delete a user

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(".//p[@class = 'nickname-wrapper']")));
        driver.findElement(By.xpath(".//p[@class = 'nickname-wrapper']")).click();

        driver.findElement(By.xpath(".//span[contains(text(), 'Настройки')]")).click();

        driver.findElement(By.xpath(".//a[contains(text(),'Профиль')]")).click();

        driver.findElement(By.cssSelector(".btn-md")).click();

        driver.findElement(By.cssSelector(".swal2-confirm")).click();

        //driver.quit();
    }
}
