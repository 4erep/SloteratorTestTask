package com.testslotegrator.ui.pages;

import com.testslotegrator.ui.config.UiCommonProperties;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardPage extends BasePage {
    UiCommonProperties properties = ConfigFactory.create(UiCommonProperties.class, System.getProperties());

    private final By header = By.xpath("//img[@id='header-logo']");
    private final By dashboardMenuButton = By.xpath("//i[@class='fa fa-dashboard']");
    private final By userNameSpan = By.xpath("//li[contains(@class, 'nav-profile')]//span");


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage checkThatUserIsLogin() {
        assertTrue(driver.findElement(userNameSpan).getText().contains(properties.UserLogin()));
        return this;
    }

    public DashboardPage checkPageIsLoaded() {
        waitElementToBeVisible(driver.findElement(header));
        waitElementToBeVisible(driver.findElement(dashboardMenuButton));
        return this;
    }

}