package TestCases;

import Utility.BaseDriver;
import Utility.Elements;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class US504_TabMenu extends BaseDriver {
    @Test
    public void TC504(){
        Elements elements = new Elements();

        Actions actions = new Actions(driver);

        List<String> menuElements = Arrays.asList("Computers","Electronics","Apparel","Digital downloads",
                "Books","Jewelry","Gift Cards");

        for (int i = 0; i < menuElements.size(); i++)
            Assert.assertTrue(elements.tabMenus.get(i).getText().equals(menuElements.get(i)));

        actions.moveToElement(elements.computers).build().perform();
        actions.moveToElement(elements.desktops).click().build().perform();
        wait.until(ExpectedConditions.urlContains("desktops"));
        Assert.assertTrue(elements.title.getText().equals("Desktops"));
        actions.moveToElement(elements.computers).build().perform();
        actions.moveToElement(elements.notebooks).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Notebooks"));
        actions.moveToElement(elements.computers).build().perform();
        actions.moveToElement(elements.software).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Software"));
        actions.moveToElement(elements.electronics).build().perform();
        actions.moveToElement(elements.camera).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Camera & photo"));
        actions.moveToElement(elements.electronics).build().perform();
        actions.moveToElement(elements.cellPhone).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Cell phones"));
        actions.moveToElement(elements.electronics).build().perform();
        actions.moveToElement(elements.others).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Others"));
        actions.moveToElement(elements.apparel).build().perform();
        actions.moveToElement(elements.shoes).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Shoes"));
        actions.moveToElement(elements.apparel).build().perform();
        actions.moveToElement(elements.clothing).click().build().perform();
        Assert.assertTrue(elements.title.getText().equals("Clothing"));
        actions.moveToElement(elements.apparel).build().perform();
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
}
