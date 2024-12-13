package com.saad.tpwebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EnterpriseSearch {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\S9\\management_de_la_qualite\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("https://www.pagesjaunes.fr");

            acceptCookies(driver);

            WebElement searchSector = driver.findElement(By.name("quoiqui"));
            searchSector.sendKeys("informatique");

            WebElement searchRegion = driver.findElement(By.name("ou"));
            searchRegion.sendKeys("Paris");

            driver.findElement(By.cssSelector("#findId")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bi-list")), ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-results"))));

            if (!driver.findElements(By.cssSelector(".bi-list")).isEmpty()) {
                List<WebElement> entreprises = driver.findElements(By.cssSelector(".bi-generic"));
               

                System.out.println("Entreprises trouvées :");
                for (int i = 0; i < Math.min(30, entreprises.size()); i++) {
                    WebElement entreprise = entreprises.get(i);

                    String nomEntreprise = entreprise.findElement(By.cssSelector(".bi-denomination")).getText();
                    String adresse = (entreprise.findElements(By.cssSelector(".bi-address a")).size()> 0) ? entreprise.findElement(By.cssSelector(".bi-address a")).getText().replace("Voir le plan", "").trim() : "Adresse non disponible";


                     String phoneNumber = (entreprise.findElements(By.cssSelector(".number-contact .annonceur")).size() > 0)?
                             entreprise.findElement(By.cssSelector(".number-contact .annonceur")).getText() : "Not Available";
                    String websiteUrl = (entreprise.findElements(By.cssSelector(".bi-content a[href^='http']")).size() > 0)?
                            entreprise.findElement(By.cssSelector(".bi-content a[href^='http']")).getAttribute("href"):"Not Available";

                    String activity = (entreprise.findElements(By.cssSelector(".bi-activity-unit")).size() > 0) ?
                        entreprise.findElement(By.cssSelector(".bi-activity-unit")).getText() : "Not Available";

                    String rating = (entreprise.findElements(By.cssSelector(".bi-rating")).size() > 0) ?
                    entreprise.findElement(By.cssSelector(".bi-rating")).getText() : "Not Available";
                    System.out.println("--------------------------------------------");

                    System.out.println("- " + nomEntreprise );
                    System.out.println("  Adresse: " + adresse);
                    System.out.println("  Num Tel: " + phoneNumber);
                    System.out.println("  Site Web: " + websiteUrl);
                    System.out.println("  Activité: " + activity);
                    System.out.println("  Evaluations: " + rating);
                    System.out.println("--------------------------------------------");

                }

            } else {
                System.out.println("Aucun résultat trouvé");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void acceptCookies(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#didomi-notice-agree-button"))).click();
        } catch (Exception e) {
            System.out.println("Pas de cookies.");
        }
    }
}