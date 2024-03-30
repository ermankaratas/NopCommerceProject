package TestCases;

import Utility.Elements;
import Utility.Tools;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static Utility.BaseDriver.driver;
import static Utility.BaseDriver.wait;

public class US502_UserLogin {
    @Test
    public void TC502_Pozitive(){
        startDriver();

        Elements elements = new Elements();
        elements.loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
        elements.email.sendKeys("xq1@gmail.com");
        elements.password.sendKeys("1234Abcd");
        elements.loginSubmitButton.click();

        wait.until(ExpectedConditions.urlToBe("https://demo.nopcommerce.com/"));
        Assert.assertTrue(elements.myAccount.isDisplayed());

        closeDriver();
    }
    public void startDriver(){
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://demo.nopcommerce.com/");
    }
    public void closeDriver(){
        Tools.wait(2);
        driver.quit();
    }
}
