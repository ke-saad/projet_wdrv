package com.saad.tpwebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class FormTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\S9\\management_de_la_qualite\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("http://127.0.0.1:5500/connexion.html");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            usernameField.sendKeys("user");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("password");

            WebElement submitButton = driver.findElement(By.xpath("//button[text()='Se connecter']"));
            submitButton.click();

            WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginMessage")));
            String messageText = messageElement.getText();

            if (messageText.equals("Connexion réussie !")) {
                System.out.println("Test réussi : " + messageText);
            } else if (messageText.equals("Nom d'utilisateur ou mot de passe incorrect.")) {
                System.out.println("Test échoué : " + messageText);
            } else {
                System.out.println("Message inattendu : " + messageText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
