package com.testslotegrator.ui.pages.elements;

import com.google.common.collect.Ordering;
import com.testslotegrator.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayersTable extends BasePage {
    public PlayersTable(WebDriver driver) {
        super(driver);
    }

    private final By tableHeader = By.xpath("//div[@class='panel-heading']");
    private final By tableBody = By.xpath("//div[@id='payment-system-transaction-grid']");
    private final By loader = By.xpath("//div[contains(@class, 'grid-view-load')]");
    int sortedIndex;


    public PlayersTable checkPlayersTableIsLoaded() {
        waitElementToBeVisible(driver.findElement(tableHeader));
        waitElementToBeVisible(driver.findElement(tableBody));

        return this;
    }

    public PlayersTable sortTable(String column) {
        List<WebElement> columns = driver.findElements(By.xpath("//table/thead/tr//th"));
        for (int i = 0; i < columns.size(); i++) {
            WebElement col = columns.get(i);
            if (col.getText().equals(column)) {
                col.findElement(By.xpath("./a")).click();
                sortedIndex = i + 1;
                waitElementToBeNotVisible(driver.findElement(loader));
                return this;
            }
        }
        return null;
    }

    public void checkIsTableSorted() {
        List<WebElement> sortedCells = driver.findElements(By.xpath("//table./tbody//td[" + sortedIndex + "]"));
        List<String> sortedValues = new ArrayList<>();
        sortedCells.forEach(cell -> sortedValues.add(cell.getText()));
        assertTrue(Ordering.natural().isOrdered(sortedValues));
    }

}