package TestCases;

import Utility.Elements;
import Utility.Tools;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static Utility.BaseDriver.driver;
import static Utility.BaseDriver.wait;

public class US503_UserLoginTries {
    @Test(dataProvider = "usersData")
    public void TC503_Tries(String username, String password, int userTries){
        startDriver();

        Elements elements = new Elements();
        elements.loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
        elements.email.sendKeys(username);
        elements.password.sendKeys(password);
        elements.loginSubmitButton.click();

        if(userTries == 7){
            wait.until(ExpectedConditions.urlToBe("https://demo.nopcommerce.com/"));
            Assert.assertTrue(elements.myAccount.isDisplayed());
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(elements.loginSubmitButton));
            Assert.assertTrue(elements.loginNotConfirmText.getText().contains("Login was unsuccessful."));
        }

        closeDriver();
    }
    @DataProvider
    Object[][] usersData(){
        Object[][] emailPassword =
                {
                        {"test1@test.com","1234",1},
                        {"test2@test.com","1234",2},
                        {"test3@gmail.com","1234",3},
                        {"test4@test.com","1234",4},
                        {"test5@test.com","1234",5},
                        {"test6@test.com","1234",6},
                        {"xq1@gmail.com","1234Abcd",7},
                };
        return emailPassword;
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
