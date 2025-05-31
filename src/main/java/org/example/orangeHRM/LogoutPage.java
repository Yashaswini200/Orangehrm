package org.example.orangeHRM;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.example.utils.WaitHelpers.waitJVM;

public class LogoutPage extends CommonToAllPage {

    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By profileDropdown = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private final By logoutLink = By.xpath("//a[text()='Logout']");

    // Action: Logout
    public void logoutFromOrangeHRM() {
        clickElement(profileDropdown);
        waitJVM(2000);
        clickElement(logoutLink);
        waitJVM(3000);
    }
}
