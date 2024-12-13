package com.saad.tpwebdriver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnterpriseSearchTest {

    private WebDriver mockDriver;
    private WebDriverWait mockWait;

    @BeforeEach
    void setUp() {
        mockDriver = Mockito.mock(WebDriver.class);
        mockWait = Mockito.mock(WebDriverWait.class);
    }

    @Test
    void testSearchResultsFound() {

        WebElement mockSearchSector = mock(WebElement.class);
        WebElement mockSearchRegion = mock(WebElement.class);
        WebElement mockSearchButton = mock(WebElement.class);
        WebElement mockList = mock(WebElement.class);
        WebElement mockEnterprise = mock(WebElement.class);
        List<WebElement> mockEnterprises = Arrays.asList(mockEnterprise);


        when(mockDriver.findElement(By.name("quoiqui"))).thenReturn(mockSearchSector);
        when(mockDriver.findElement(By.name("ou"))).thenReturn(mockSearchRegion);
        when(mockDriver.findElement(By.cssSelector("#findId"))).thenReturn(mockSearchButton);
        when(mockDriver.findElements(By.cssSelector(".bi-list"))).thenReturn(Arrays.asList(mockList));
        when(mockDriver.findElements(By.cssSelector(".bi-generic"))).thenReturn(mockEnterprises);


        when(mockEnterprise.findElement(By.cssSelector(".bi-denomination"))).thenReturn(mock(WebElement.class));
        when(mockEnterprise.findElement(By.cssSelector(".bi-denomination")).getText()).thenReturn("Test Enterprise");


        when(mockWait.until(any())).thenReturn(mockList);


        mockSearchButton.click();
        List<WebElement> enterprises = mockDriver.findElements(By.cssSelector(".bi-generic"));


        assertNotNull(enterprises);
        assertEquals(1, enterprises.size());
        assertEquals("Test Enterprise", enterprises.get(0).findElement(By.cssSelector(".bi-denomination")).getText());
    }

    @Test
    void testNoResultsFound() {

        when(mockDriver.findElements(By.cssSelector(".bi-list"))).thenReturn(Arrays.asList());


        WebElement mockSearchButton = mock(WebElement.class);
        when(mockDriver.findElement(By.cssSelector("#findId"))).thenReturn(mockSearchButton);
        mockSearchButton.click();


        List<WebElement> enterprises = mockDriver.findElements(By.cssSelector(".bi-list"));
        assertTrue(enterprises.isEmpty());
    }

    @Test
    void testInvalidElementHandling() {

        WebElement mockSearchSector = mock(WebElement.class);
        WebElement mockSearchRegion = mock(WebElement.class);
        WebElement mockSearchButton = mock(WebElement.class);
        when(mockDriver.findElement(By.name("quoiqui"))).thenReturn(mockSearchSector);
        when(mockDriver.findElement(By.name("ou"))).thenReturn(mockSearchRegion);
        when(mockDriver.findElement(By.cssSelector("#findId"))).thenReturn(mockSearchButton);


        when(mockDriver.findElements(By.cssSelector(".bi-list"))).thenReturn(Arrays.asList());


        mockSearchButton.click();
        List<WebElement> enterprises = mockDriver.findElements(By.cssSelector(".bi-list"));


        assertTrue(enterprises.isEmpty());
    }
}
