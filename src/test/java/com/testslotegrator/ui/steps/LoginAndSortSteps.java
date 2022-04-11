package com.testslotegrator.ui.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginAndSortSteps extends BaseSteps {

    @Given("Открыть стартовую страницу")
    public void openBasePage() {
        basePage.openUrl();
    }

    @When("Ввести логин")
    public void enterProvidedLogin() {
        authPage.enterLogin();
    }

    @And("Ввести пароль")
    public void enterProvidedPassword() {
        authPage.enterPassword();
    }

    @And("Нажать кнопку Sign In")
    public void clickSingInButton() {
        authPage.signInButtonClick();
    }

    @Given("Зайти на сайт и залогиниться")
    public void openUriAndLogin() {
        basePage.openUrl();
        authPage.enterLogin();
        authPage.enterPassword();
        authPage.signInButtonClick();
        dashboardPage.checkThatUserIsLogin();
        dashboardPage.checkPageIsLoaded();
    }

    @Then("Юзер залогинен и страница загрузилась")
    public void checkUserIsLoginAndPageIsLoaded() {
        dashboardPage.checkThatUserIsLogin();
        dashboardPage.checkPageIsLoaded();
    }

    @When("Нажать на опцию {string} в боковом меню")
    public void clickOptionInSidebar(String optionName) {
        dashboardPageSideBar.clickMenuTab(optionName);
    }

    @When("Нажать на подопцию {string} в боковом меню")
    public void clickSubOptionInSidebar(String subOptionName) {
        dashboardPageSideBar.clickSubMenuTab(subOptionName);
    }

    @Then("Таблица с игроками загрузилась")
    public void checkPlayersTableIsLoaded() {
        playersTable.checkPlayersTableIsLoaded();
    }

    @When("Отсортировать таблицу по столбцу {string}")
    public void sortColumn(String column) {
        playersTable.sortTable(column);
    }

    @Then("Проверить, что таблица отсортирована")
    public void checkTableIsSorted() {
        playersTable.checkIsTableSorted();

    }

    @When("Зайти в таблицу с игроками")
    public void openPlayersTable() {
        dashboardPageSideBar.clickMenuTab("Users");
        dashboardPageSideBar.clickSubMenuTab("Players");
        playersTable.checkPlayersTableIsLoaded();
    }

    @After
    public void afterScenario() {
        driver.quit();
    }

}