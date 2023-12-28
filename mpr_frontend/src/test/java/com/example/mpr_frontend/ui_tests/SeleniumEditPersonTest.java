package com.example.mpr_frontend.ui_tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumEditPersonTest {
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
    public void shouldFillInAllFields(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.fillInName("Bob");
        editPersonTestPage.fillInEmail("bob@gmail.com");
        editPersonTestPage.fillInPassword("bob123");
        editPersonTestPage.fillInLogin("jambob");
        editPersonTestPage.fillInAge("55");
    }
    @Test
    public void shouldClickSubmitButton(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.clickSubmitButton();
    }
    @Test
    public void clickReturnLinkShouldNavigateToThemMainPage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.clickReturnLink();
        String expectedTitle = "index";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void clickSubmitButtonWhenFilledInAllFieldsShouldNavigateToTheMainPage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.fillInName("Bob");
        editPersonTestPage.fillInLogin("jambob");
        editPersonTestPage.fillInEmail("bob@gmail.com");
        editPersonTestPage.fillInPassword("bob123");
        editPersonTestPage.fillInAge("55");
        editPersonTestPage.clickSubmitButton();
        String expectedTitle = "index";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void whenNoFieldsChangedShouldStayAtTheSamePage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        String expectedTitle = "editPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void whenNameFilledWronglyShouldStayAtTheSamePage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.fillInName(" ");
        editPersonTestPage.fillInLogin("jambob");
        editPersonTestPage.fillInEmail("bob@gmail.com");
        editPersonTestPage.fillInPassword("bob123");
        editPersonTestPage.fillInAge("55");
        editPersonTestPage.clickSubmitButton();
        String expectedTitle = "editPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void whenLoginFilledWronglyShouldStayAtTheSamePage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.fillInName("Bob");
        editPersonTestPage.fillInLogin(" ");
        editPersonTestPage.fillInEmail("bob@gmail.com");
        editPersonTestPage.fillInPassword("bob123");
        editPersonTestPage.fillInAge("55");
        editPersonTestPage.clickSubmitButton();
        String expectedTitle = "editPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void whenEmailFilledWithSpaceShouldStayAtTheSamePage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.fillInName("Bob");
        editPersonTestPage.fillInLogin("jambob");
        editPersonTestPage.fillInEmail(" ");
        editPersonTestPage.fillInPassword("bob123");
        editPersonTestPage.fillInAge("55");
        editPersonTestPage.clickSubmitButton();
        String expectedTitle = "editPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void whenEmailFilledWronglyShouldStayAtTheSamePage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.fillInName("Bob");
        editPersonTestPage.fillInLogin("jambob");
        editPersonTestPage.fillInEmail("notemail");
        editPersonTestPage.fillInPassword("bob123");
        editPersonTestPage.fillInAge("55");
        editPersonTestPage.clickSubmitButton();
        String expectedTitle = "editPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void whenPasswordFilledWronglyShouldStayAtTheSamePage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.fillInName("Bob");
        editPersonTestPage.fillInLogin("jambob");
        editPersonTestPage.fillInEmail("bob@gmail.com");
        editPersonTestPage.fillInPassword(" ");
        editPersonTestPage.fillInAge("55");
        editPersonTestPage.clickSubmitButton();
        String expectedTitle = "editPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void whenAgeFilledWronglyShouldStayAtTheSamePage(){
        EditPersonTestPage editPersonTestPage = new EditPersonTestPage(driver);
        editPersonTestPage.open();
        editPersonTestPage.fillInName("Bob");
        editPersonTestPage.fillInLogin("jambob");
        editPersonTestPage.fillInEmail("bob@gmail.com");
        editPersonTestPage.fillInPassword("bob123");
        editPersonTestPage.fillInAge(" ");
        editPersonTestPage.clickSubmitButton();
        String expectedTitle = "editPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
}
