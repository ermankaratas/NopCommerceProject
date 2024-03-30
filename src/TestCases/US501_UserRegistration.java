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

public class US501_UserRegistration {
    @Test
    public void TC501_Pozitive(){
        startDriver();

        Elements elements = new Elements();
        elements.registerButton.click();
        wait.until(ExpectedConditions.urlContains("register"));
        elements.genderMale.click();
        elements.firstName.sendKeys("Xqw");
        elements.lastName.sendKeys("Xqwlastname");
        Select dateOfBDMenu = new Select(elements.dateOfBirthDay);
        dateOfBDMenu.selectByValue("2");
        Select dateOfBMMenu = new Select(elements.dateOfBirthMonth);
        dateOfBMMenu.selectByValue("2");
        Select dateOfBYMenu = new Select(elements.dateOfBirthYear);
        dateOfBYMenu.selectByValue("2000");
        elements.email.sendKeys("xq1@gmail.com");
        elements.password.sendKeys("1234Abcd");
        elements.passwordConfirm.sendKeys("1234Abcd");
        elements.registerSubmitButton.click();

        wait.until(ExpectedConditions.urlContains("registerresult"));
        Assert.assertTrue(elements.registerConfirmText.getText().equals("Your registration completed"));

        closeDriver();
    }
    @Test
    public void TC502_Negative_SameEMail(){
        startDriver();

        Elements elements = new Elements();
        elements.registerButton.click();
        wait.until(ExpectedConditions.urlContains("register"));
        elements.genderMale.click();
        elements.firstName.sendKeys("Xqw");
        elements.lastName.sendKeys("Xqwlastname");
        Select dateOfBDMenu = new Select(elements.dateOfBirthDay);
        dateOfBDMenu.selectByValue("2");
        Select dateOfBMMenu = new Select(elements.dateOfBirthMonth);
        dateOfBMMenu.selectByValue("2");
        Select dateOfBYMenu = new Select(elements.dateOfBirthYear);
        dateOfBYMenu.selectByValue("2000");
        elements.email.sendKeys("xq1@gmail.com");
        elements.password.sendKeys("1234Abcd");
        elements.passwordConfirm.sendKeys("1234Abcd");
        elements.registerSubmitButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(elements.registerSubmitButton));
        Assert.assertTrue(elements.registerNotConfirmText.getText().equals("The specified email already exists"));

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
