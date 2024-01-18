package com.example.mpr_frontend.ui_tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumDeletePersonTest {
    WebDriver driver;
    @BeforeEach
    public void setUp(){
        driver = new EdgeDriver();
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
    @Test
    @Order(1)
    public void clickReturnLinkShouldNavigateToTheMainPage(){
        DeletePersonTestPage deletePersonTestPage = new DeletePersonTestPage(driver);
        deletePersonTestPage.open();
        deletePersonTestPage.clickReturnLink();
        String expectedTitle = "index";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    @Order(2)
    public void clickSubmitButtonShouldNavigateToTheMainPage(){
        DeletePersonTestPage deletePersonTestPage = new DeletePersonTestPage(driver);
        deletePersonTestPage.open();
        deletePersonTestPage.clickSubmitButton();
        String expectedTitle = "index";
        assertEquals(expectedTitle, driver.getTitle());
    }
}
