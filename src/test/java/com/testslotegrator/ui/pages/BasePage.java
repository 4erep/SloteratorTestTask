package com.testslotegrator.ui.pages;

import com.testslotegrator.ui.config.UiCommonProperties;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.testslotegrator.ui.config.DriverProperties.TimeoutVariable.EXPLICIT_WAIT;

public class BasePage {
    public WebDriver driver;

    UiCommonProperties properties = ConfigFactory.create(UiCommonProperties.class, System.getProperties());

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl() {
        driver.get(properties.UiBaseURI());
    }

    public WebElement waitElementToBeVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOf(element));
        return element;

    }
    public WebElement waitElementToBeNotVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.invisibilityOf(element));
        return element;
    }
}