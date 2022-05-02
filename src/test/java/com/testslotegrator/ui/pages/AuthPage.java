package com.testslotegrator.ui.pages;

import com.testslotegrator.ui.config.UiCommonProperties;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage extends BasePage {

    UiCommonProperties properties = ConfigFactory.create(UiCommonProperties.class, System.getProperties());

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    private final By loginInput = By.id("UserLogin_username");
    private final By passwordInput = By.id("UserLogin_password");
    private final By signInButton = By.xpath("//input[@type='submit']");

    public AuthPage enterLogin() {
        driver.findElement(loginInput).sendKeys(properties.UserLogin());
        return this;
    }

    public AuthPage enterPassword() {
        driver.findElement(passwordInput).sendKeys(properties.UserPassword());
        return this;
    }

    public AuthPage signInButtonClick() {
        driver.findElement(signInButton).click();
        return this;
    }

}