package com.saad.tpwebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class GoogleSearchTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\S9\\management_de_la_qualite\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("https://www.google.com");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            searchBox.sendKeys("FC Barcelona");

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
            searchButton.click();

            boolean resultsDisplayed = driver.findElements(By.cssSelector("#search")).size() > 0;
            System.out.println(resultsDisplayed ? "Résultats affichés avec succès." : "Aucun résultat trouvé.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
