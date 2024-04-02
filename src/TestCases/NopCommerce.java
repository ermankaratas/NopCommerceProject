package TestCases;

import Utility.BaseDriver;
import Utility.Tools;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NopCommerce extends BaseDriver {
    @Test(priority = 1, groups = {"Smoke", "Registration"})
    public void TC501(){ // User Register
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
    }
    @Test(priority = 10, groups = {"NegativeRegistration"})
    public void TC501N(){  // Negative Register
        Elements elements = new Elements();
        if(elements.logout.isDisplayed())
            elements.logout.click();
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
    }
    @Test(priority = 2, groups = {"Smoke", "Login"}, dependsOnMethods = {"TC501"})
    public void TC502(){ // User Login
        Elements elements = new Elements();
        elements.loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
        elements.email.sendKeys("xq1@gmail.com");
        elements.password.sendKeys("1234Abcd");
        elements.loginSubmitButton.click();

        wait.until(ExpectedConditions.urlToBe("https://demo.nopcommerce.com/"));
        Assert.assertTrue(elements.myAccount.isDisplayed());

        elements.logout.click();
    }
    @Test(priority = 3, groups = {"Smoke", "Login"}, dataProviderClass = NopCommerce.class, dataProvider = "usersData")
    public void TC503(String username, String password){ // User Login Tries
        Elements elements = new Elements();

        elements.loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
        elements.email.sendKeys(username);
        elements.password.sendKeys(password);
        elements.loginSubmitButton.click();

        if(username.equals("xq1@gmail.com") & password.equals("1234Abcd")){
            wait.until(ExpectedConditions.urlToBe("https://demo.nopcommerce.com/"));
            Assert.assertTrue(elements.myAccount.isDisplayed());
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(elements.loginSubmitButton));
            Assert.assertTrue(elements.loginNotConfirmText.getText().contains("Login was unsuccessful."));
        }
    }
    @DataProvider
    Object[][] usersData(){
        Object[][] emailPassword =
                {
                        {"test1@test.com","1234"},
                        {"test2@test.com","1234"},
                        {"test3@gmail.com","1234"},
                        {"test4@test.com","1234"},
                        {"test5@test.com","1234"},
                        {"test6@test.com","1234"},
                        {"xq1@gmail.com","1234Abcd"},
                };
        return emailPassword;
    }
    @Test(priority = 4, groups = {"UITesting", "TabMenu"})
    public void TC504(){  // TabMenu
        Elements elements = new Elements();

        Actions actions = new Actions(driver);

        List<String> menuElements = Arrays.asList("Computers","Electronics","Apparel","Digital downloads",
                "Books","Jewelry","Gift Cards");

        for (int i = 0; i < menuElements.size(); i++)
            Assert.assertTrue(elements.tabMenus.get(i).getText().equals(menuElements.get(i)));

        Action action = actions.moveToElement(elements.computers).build();
        action.perform();
        actions.moveToElement(elements.desktops).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Desktops"));
        action.perform();
        actions.moveToElement(elements.notebooks).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Notebooks"));
        action.perform();
        actions.moveToElement(elements.software).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Software"));

        action = actions.moveToElement(elements.electronics).build();
        action.perform();
        actions.moveToElement(elements.camera).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Camera & photo"));
        action.perform();
        actions.moveToElement(elements.cellPhone).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Cell phones"));
        action.perform();
        actions.moveToElement(elements.others).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Others"));

        action = actions.moveToElement(elements.apparel).build();
        action.perform();
        actions.moveToElement(elements.shoes).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Shoes"));
        action.perform();
        actions.moveToElement(elements.clothing).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Clothing"));
        action.perform();
        actions.moveToElement(elements.accessories).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Accessories"));

        actions.moveToElement(elements.downloads).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Digital downloads"));

        actions.moveToElement(elements.books).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Books"));

        actions.moveToElement(elements.jewelry).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Jewelry"));

        actions.moveToElement(elements.giftCards).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Gift Cards"));
    }
    @Test(priority = 5, groups = {"UITesting", "Search", "TabMenu","Regression"})
    public void TC505(){  // TabMenu Products Control
        Elements elements = new Elements();

        Actions actions = new Actions(driver);

        List<String> tabMenuNames = new ArrayList<>();
        List<List<String>> subTabMenuNames = new ArrayList<>();
        List<List<String>> items = new ArrayList<>();

        for (WebElement element : elements.tabMenus)
            tabMenuNames.add(element.getText());

        for (String menuName : tabMenuNames) {
            List<String> subTabMenuName = new ArrayList<>();
            if(menuName.equals("Computers")){
                Action action = actions.moveToElement(elements.computers).build();
                action.perform();
                for (WebElement e : elements.subTabMenusComputers)
                    subTabMenuName.add(e.getText());
            }else if(menuName.equals("Electronics")){
                Action action = actions.moveToElement(elements.electronics).build();
                action.perform();
                for (WebElement e : elements.subTabMenusElectronics)
                    subTabMenuName.add(e.getText());
            }else if(menuName.equals("Apparel")){
                Action action = actions.moveToElement(elements.apparel).build();
                action.perform();
                for (WebElement e : elements.subTabMenusApparel)
                    subTabMenuName.add(e.getText());
            }
            subTabMenuNames.add(subTabMenuName);
        }

        Action action = actions.moveToElement(elements.computers).build();
        action.perform();
        actions.moveToElement(elements.desktops).click().build().perform();
        wait.until(ExpectedConditions.visibilityOf(elements.items.get(0)));

        List<String> desktopItems = new ArrayList<>();
        for (WebElement element : elements.items)
            desktopItems.add(element.getText());
        items.add(desktopItems);

        action.perform();
        actions.moveToElement(elements.notebooks).click().build().perform();
        wait.until(ExpectedConditions.visibilityOf(elements.items.get(0)));

        List<String> notebooksItems = new ArrayList<>();
        for (WebElement element : elements.items)
            notebooksItems.add(element.getText());
        items.add(notebooksItems);

        action.perform();
        actions.moveToElement(elements.software).click().build().perform();
        wait.until(ExpectedConditions.visibilityOf(elements.items.get(0)));

        List<String> softwareItems = new ArrayList<>();
        for (WebElement element : elements.items)
            softwareItems.add(element.getText());
        items.add(softwareItems);

        // Product is selected from Notebooks-Menu
        int randomSelect = Tools.randomGenerator(items.get(1).size());
        String product = items.get(1).get(randomSelect);

        elements.searchBox.sendKeys(product);
        elements.searchButton.click();

        wait.until(ExpectedConditions.visibilityOf(elements.items.get(0)));
        String foundProduct = elements.items.get(0).getText();
        boolean isFoundProductInList = false;
        for (int i = 0; i < items.get(1).size(); i++)
            if (foundProduct.equals(items.get(1).get(i)))
                isFoundProductInList = true;
        Assert.assertTrue(isFoundProductInList,"The product is not in the list");
    }
}
