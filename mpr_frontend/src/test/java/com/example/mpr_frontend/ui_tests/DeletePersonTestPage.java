package com.example.mpr_frontend.ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeletePersonTestPage {
    public static final String URL = "http://localhost:8081/deletePerson/1";
    WebDriver webDriver;

    public DeletePersonTestPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @FindBy(id = "delete-button")
    WebElement submitButton;
    @FindBy(id = "return-link")
    WebElement returnLink;
    public void open(){
        webDriver.get(URL);
        PageFactory.initElements(webDriver, this);
    }
    public void clickSubmitButton(){
        submitButton.click();
    }
    public void clickReturnLink(){
        returnLink.click();
    }
}
