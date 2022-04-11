package com.testslotegrator.ui.steps;

import com.testslotegrator.ui.base.CommonActions;
import com.testslotegrator.ui.pages.AuthPage;
import com.testslotegrator.ui.pages.BasePage;
import com.testslotegrator.ui.pages.DashboardPage;
import com.testslotegrator.ui.pages.elements.DashboardPageSideBar;
import com.testslotegrator.ui.pages.elements.PlayersTable;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.testslotegrator.ui.config.DriverProperties.CLEAR_COOKIES_AND_STORAGE;

public class BaseSteps {

    protected WebDriver driver = CommonActions.setupDriver();
    protected BasePage basePage = new BasePage(driver);
    protected AuthPage authPage = new AuthPage(driver);
    protected DashboardPage dashboardPage = new DashboardPage(driver);
    protected DashboardPageSideBar dashboardPageSideBar = new DashboardPageSideBar(driver);
    protected PlayersTable playersTable = new PlayersTable(driver);


    @AfterEach
    public void clearCookiesAndLocalStorage() {
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

}