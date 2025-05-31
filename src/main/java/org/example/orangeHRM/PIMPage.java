package org.example.orangeHRM;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.example.utils.WaitHelpers.checkVisibility;

public class PIMPage extends CommonToAllPage {

    WebDriver driver;

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By addEmployeeButton = By.xpath("//a[contains(text(),'Add Employee')]");
    private final By firstNameField = By.name("firstName");
    private final By lastNameField = By.name("lastName");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By employeeListTab = By.xpath("//a[text()='Employee List']");
    private final By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");
    private final By tableRows = By.xpath("//div[@class='oxd-table-body']//div[@role='row']");

    // Actions
    public void navigateToPIM() {
        checkVisibility(driver, pimMenu, 10);
        clickElement(pimMenu);
    }

    public void addEmployee(String firstName, String lastName) {
        checkVisibility(driver, addEmployeeButton, 10);
        clickElement(addEmployeeButton);

        checkVisibility(driver, firstNameField, 10);
        enterInput(firstNameField, firstName);

        checkVisibility(driver, lastNameField, 10);
        enterInput(lastNameField, lastName);

        checkVisibility(driver, saveButton, 10);
        clickElement(saveButton);

    }

    public void openEmployeeList() {
        checkVisibility(driver, employeeListTab, 10);
        clickElement(employeeListTab);
    }

    public void searchEmployee(String fullName) {
        checkVisibility(driver, employeeNameInput, 10);
        WebElement input = driver.findElement(employeeNameInput);
        input.click();
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);

        // Wait a moment for clearing to be recognized by UI
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        input.sendKeys(fullName);

        checkVisibility(driver, searchButton, 10);
        clickElement(searchButton);

        checkVisibility(driver, tableRows, 10);
    }

    public boolean isEmployeePresentInTable(String firstName, String lastName) {
        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            scrollToElement(row);

            List<WebElement> cells = row.findElements(By.xpath(".//div[@role='cell']"));

            boolean firstNameFound = false;
            boolean lastNameFound = false;

            for (WebElement cell : cells) {
                String cellText = cell.getText().trim();
                if (cellText.equalsIgnoreCase(firstName)) {
                    firstNameFound = true;
                }
                if (cellText.equalsIgnoreCase(lastName)) {
                    lastNameFound = true;
                }
            }
            if (firstNameFound && lastNameFound) {
                return true;
            }
        }
        return false;
    }
}
