package com.example.mpr_frontend.ui_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditPersonTestPage {
    public static final String URL = "http://localhost:8081/editPerson/1";
    WebDriver webDriver;

    public EditPersonTestPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @FindBy(id = "name")
    WebElement nameInput;
    @FindBy(id = "login")
    WebElement loginInput;
    @FindBy(id = "email")
    WebElement emailInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(id = "age")
    WebElement ageInput;
    @FindBy(id = "submit-button")
    WebElement submitButton;
    @FindBy(id = "return-link")
    WebElement returnLink;
    public void open(){
        webDriver.get(URL);
        PageFactory.initElements(webDriver, this);
    }
    public void fillInName(String name){
        nameInput.clear();
        nameInput.sendKeys(name);
    }
    public void fillInLogin(String login){
        loginInput.clear();
        loginInput.sendKeys(login);
    }
    public void fillInEmail(String email){
        emailInput.clear();
        emailInput.sendKeys(email);
    }
    public void fillInPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    public void fillInAge(String age){
        ageInput.clear();
        ageInput.sendKeys(age);
    }
    public void clickSubmitButton(){
        submitButton.click();
    }
    public void clickReturnLink(){
        returnLink.click();
    }
}
