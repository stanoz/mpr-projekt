package com.example.mpr_backend.ui_tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumIndexTest {
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
    public void clickEditLinkShouldNavigateToEditPersonPage(){
        IndexTestPage indexTestPage = new IndexTestPage(driver);
        indexTestPage.open();
        indexTestPage.clickEditLink("1");
        String expectedPageTitle = "editPerson";
        assertEquals(expectedPageTitle, driver.getTitle());
    }
    @Test
    public void clickDeleteLinkShouldNavigateToDeletePersonPage(){
        IndexTestPage indexTestPage = new IndexTestPage(driver);
        indexTestPage.open();
        indexTestPage.clickDeleteLink("1");
        String expectedTitle = "deletePerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void clickAddLinkShouldNavigateToAddPersonPage(){
        IndexTestPage indexTestPage = new IndexTestPage(driver);
        indexTestPage.open();
        indexTestPage.clickAddLink();
        String expectedTitle = "addPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
}
