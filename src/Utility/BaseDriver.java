package Utility;

import TestCases.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseDriver {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void startDriver(){
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://demo.nopcommerce.com/");

        //loginWebsite();
    }
    @AfterClass
    public void closeDriver(){
        Tools.wait(2);
        driver.quit();
    }
    public void loginWebsite(){

        Elements elements = new Elements();

        elements.loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
        elements.email.sendKeys("xq1@gmail.com");
        elements.password.sendKeys("1234Abcd");
        elements.loginSubmitButton.click();

        wait.until(ExpectedConditions.urlToBe("https://demo.nopcommerce.com/"));
        Assert.assertTrue(elements.myAccount.isDisplayed());
    }

}
