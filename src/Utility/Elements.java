package Utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Elements {
    public Elements(){
        PageFactory.initElements(BaseDriver.driver,this);
    }
    @FindBy(linkText = "Register")
    public WebElement registerButton;
    @FindBy(id = "gender-male")
    public WebElement genderMale;
    @FindBy(id = "FirstName")
    public WebElement firstName;
    @FindBy(id = "LastName")
    public WebElement lastName;
    @FindBy(name = "DateOfBirthDay")
    public WebElement dateOfBirthDay;
    @FindBy(name = "DateOfBirthMonth")
    public WebElement dateOfBirthMonth;
    @FindBy(name = "DateOfBirthYear")
    public WebElement dateOfBirthYear;
    @FindBy(id = "Email")
    public WebElement email;
    @FindBy(id = "Password")
    public WebElement password;
    @FindBy(id = "ConfirmPassword")
    public WebElement passwordConfirm;
    @FindBy(id = "register-button")
    public WebElement registerSubmitButton;
    @FindBy(xpath = "//div[@class='result']")
    public WebElement registerConfirmText;
    @FindBy(xpath = "//div[@class='message-error validation-summary-errors']/ul/li")
    public WebElement registerNotConfirmText;
}


