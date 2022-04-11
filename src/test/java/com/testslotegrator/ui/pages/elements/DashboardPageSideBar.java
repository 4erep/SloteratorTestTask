package com.testslotegrator.ui.pages.elements;

import com.testslotegrator.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPageSideBar extends BasePage {

    public DashboardPageSideBar(WebDriver driver) {
        super(driver);
    }

    public DashboardPageSideBar clickMenuTab(String optionName) {
        By menuOptionSelector = By.xpath("//ul[@id=\"nav\"]//a[span[contains(text(),\"" + optionName + "\")]]");
        WebElement menuOption = driver.findElement(menuOptionSelector);
        menuOption.click();

        return this;
    }

    public DashboardPageSideBar clickSubMenuTab(String subOptionName) {
        By subMenuTabSelector = By.xpath("//li[@class=\"active\"]//a[contains(text(),\"" + subOptionName + "\")]");
        WebElement subMenuTab = driver.findElement(subMenuTabSelector);
        subMenuTab.click();

        return this;
    }
}