package org.example.base;

import org.example.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.example.driver.DriverManager.driver;
import static org.example.driver.DriverManager.getDriver;
import static org.example.utils.WaitHelpers.waitJVM;


public class CommonToAllPage {
    public CommonToAllPage() {

    }

    public void openOrangeHRMUrl(){
        getDriver().get(PropertiesReader.readKey("ohr_url"));
    }

    public boolean isTextPresentInElements(By locator, String expectedText) {
        return driver.findElements(locator).stream()
                .anyMatch(element -> element.getText().contains(expectedText));
    }
    private By profileDropdown = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private By logoutLink = By.xpath("//a[text()='Logout']");

    public void logout() {
        clickElement(profileDropdown);
        waitJVM(2000);
        clickElement(logoutLink);
        waitJVM(3000);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void clickElement(By by) {
        getDriver().findElement(by).click();
    }
    public void clickElement(WebElement by) {
        by.click();
    }

    public void enterInput(By by, String key) {
        getDriver().findElement(by).sendKeys(key);
    }


    public String getText(By by){
        return getDriver().findElement(by).getText();
    }


}
