package com.example.mpr_frontend.ui_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexTestPage {

    public static final String URL = "http://localhost:8080/showAll";
    WebDriver webDriver;

    public IndexTestPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @FindBy(id = "add-link")
    WebElement addLink;

    public WebElement getEditLinkById(String id) {
        return webDriver.findElement(By.id("edit-link-" + id));
    }
    public WebElement getDeleteLinkById(String id) {
        return webDriver.findElement(By.id("delete-link-" + id));
    }
    public void open(){
        webDriver.get(URL);
        PageFactory.initElements(webDriver,this);
    }
    public void clickEditLink(String id){
        getEditLinkById(id).click();
    }
    public void clickDeleteLink(String id){
        getDeleteLinkById(id).click();
    }
    public void clickAddLink(){
        addLink.click();
    }
}
