package org.example.orangeHRM;

import io.qameta.allure.Description;
import org.example.base.CommonToAllTest;
import org.example.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.driver.DriverManager.getDriver;
import static org.example.utils.WaitHelpers.waitJVM;

public class TestOrangeHR_POM extends CommonToAllTest {

    @Test
    @Description("Add and verify multiple employees in OrangeHRM")
    public void testAddAndVerifyEmployees() {
        // Login
        LoginPageOrangeHRM_POM loginHRPage = new LoginPageOrangeHRM_POM(getDriver());
        loginHRPage.loginToHRCreds(PropertiesReader.readKey("ohr_username"), PropertiesReader.readKey("ohr_password"));

        // Add Employees
        PIMPage pimPage = new PIMPage(getDriver());
        pimPage.navigateToPIM();

        String[][] employeeData = {
                {"John", "Doe"},
                {"Jane", "Smith"},
                {"Michael", "Jordan"},
                {"Alice", "Brown"}
        };

        for (String[] employee : employeeData) {
            pimPage.addEmployee(employee[0], employee[1]);
        }

        // Open Employee List
        pimPage.openEmployeeList();

        for (String[] employee : employeeData) {
            String fullName = employee[0] + " " + employee[1];
            pimPage.searchEmployee(fullName);

            boolean found = pimPage.isEmployeePresentInTable(employee[0], employee[1]);
            Assert.assertTrue(found, "Employee not found in list: " + fullName);
            System.out.println("Name Verified: " + fullName);

            // Clear the search input after verifying to avoid UI collapse
            WebElement input = getDriver().findElement(By.xpath("//input[@placeholder='Type for hints...']"));
            input.clear();
            waitJVM(500);
        }

        // Logout
        LogoutPage logoutPage = new LogoutPage(getDriver());
        logoutPage.logoutFromOrangeHRM();
    }
}
