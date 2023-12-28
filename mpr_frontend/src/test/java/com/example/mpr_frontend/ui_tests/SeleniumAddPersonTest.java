package com.example.mpr_frontend.ui_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumAddPersonTest {
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
    public void fillInAllFields(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.fillInName("Piotr");
        addPersonTestPage.fillInLogin("jamPiotr");
        addPersonTestPage.fillInEmail("piotr@piotr.com");
        addPersonTestPage.fillInPassword("piotr123");
        addPersonTestPage.fillInAge("30");
    }
    @Test
    public void clickSubmitButton(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.clickSubmitButton();
    }
    @Test
    public void filledAllFiledClickSubmitButtonShouldNavigateToMainPage(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.fillInName("Henryk");
        addPersonTestPage.fillInLogin("jamhenryk");
        addPersonTestPage.fillInEmail("henryk@gmail.com");
        addPersonTestPage.fillInPassword("henryk123");
        addPersonTestPage.fillInAge("30");
        addPersonTestPage.clickSubmitButton();
        String expectedTitle = "index";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void notFilledAllFieldsClickSubmitButtonShouldStayAtTheSamePage(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.clickSubmitButton();
        String expectedTitle = "addPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void nameNotFilledClickSubmitButtonShouldStayAtTheSamePage(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.fillInLogin("jamhenryk");
        addPersonTestPage.fillInEmail("henryk@gmail.com");
        addPersonTestPage.fillInPassword("henryk123");
        addPersonTestPage.fillInAge("30");
        addPersonTestPage.clickSubmitButton();
        String expectedTitle = "addPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void loginNotFilledClickSubmitButtonShouldStayAtTheSamePage(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.fillInName("Henryk");
        addPersonTestPage.fillInEmail("henryk@gmail.com");
        addPersonTestPage.fillInPassword("henryk123");
        addPersonTestPage.fillInAge("30");
        addPersonTestPage.clickSubmitButton();
        String expectedTitle = "addPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void emailNotFilledClickSubmitButtonShouldStayAtTheSamePage(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.fillInName("Henryk");
        addPersonTestPage.fillInLogin("jamhenryk");
        addPersonTestPage.fillInPassword("henryk123");
        addPersonTestPage.fillInAge("30");
        addPersonTestPage.clickSubmitButton();
        String expectedTitle = "addPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void emailFilledWronglyClickSubmitButtonShouldStayAtTheSamePage(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.fillInName("Henryk");
        addPersonTestPage.fillInLogin("jamhenryk");
        addPersonTestPage.fillInEmail("notemail");
        addPersonTestPage.fillInPassword("henryk123");
        addPersonTestPage.fillInAge("30");
        addPersonTestPage.clickSubmitButton();
        String expectedTitle = "addPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void ageNotFilledClickSubmitButtonShouldStayAtTheSamePage(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.fillInName("Henryk");
        addPersonTestPage.fillInLogin("jamhenryk");
        addPersonTestPage.fillInEmail("henryk@gmail.com");
        addPersonTestPage.fillInPassword("henryk123");
        addPersonTestPage.fillInAge(" ");
        addPersonTestPage.clickSubmitButton();
        String expectedTitle = "addPerson";
        assertEquals(expectedTitle, driver.getTitle());
    }
    @Test
    public void clickReturnLinkShouldNavigateToTheMainPage(){
        AddPersonTestPage addPersonTestPage = new AddPersonTestPage(driver);
        addPersonTestPage.open();
        addPersonTestPage.clickReturnLink();
        String expectedTitle = "index";
        assertEquals(expectedTitle, driver.getTitle());
    }
}
