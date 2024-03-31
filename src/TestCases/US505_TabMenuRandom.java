package TestCases;

import Utility.BaseDriver;
import Utility.Elements;
import Utility.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US505_TabMenuRandom extends BaseDriver {
    @Test
    public void TC505(){
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

        int randomSelect = Tools.randomGenerator(items.get(1).size());   // selected from Computers
        String product = items.get(1).get(randomSelect);

        elements.searchBox.sendKeys(product);
        elements.searchButton.click();
    }
}
