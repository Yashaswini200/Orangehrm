package org.example.orangeHRM;

import org.example.base.CommonToAllPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.example.utils.WaitHelpers.checkVisibility;
import static org.example.utils.WaitHelpers.waitJVM;


public class LoginPageOrangeHRM_POM extends CommonToAllPage {

    WebDriver driver;
    public LoginPageOrangeHRM_POM(WebDriver driver)
    {
        this.driver = driver;
    }


    // page locators

    private By username = By.xpath("//input[@placeholder='Username']");
    private By password = By.xpath("//input[@placeholder='Password']");
    private By submit_btn = By.xpath("//button[normalize-space()='Login']");


    public void loginToHRCreds(String user, String pwd) {
        openOrangeHRMUrl();

        checkVisibility(driver, username, 10);
        enterInput(username, user);
        enterInput(password, pwd);
        clickElement(submit_btn);
        By pimMenu = By.xpath("//span[text()='PIM']");
        checkVisibility(driver, pimMenu, 15);
    }







}
