package com.saad.tpwebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PriceComparator {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\S9\\management_de_la_qualite\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            String product = "laptop";

            driver.get("https://www.amazon.fr");
            acceptCookies(driver);
            WebElement searchBoxAmazon = driver.findElement(By.id("twotabsearchtextbox"));
            searchBoxAmazon.sendKeys(product);
            searchBoxAmazon.submit();

            List<WebElement> amazonPrices = driver.findElements(By.cssSelector(".a-price-whole"));
            System.out.println("Prix trouvés sur Amazon :");
            for (int i = 0; i < Math.min(5, amazonPrices.size()); i++) {
                System.out.println("- " + amazonPrices.get(i).getText() + " €");
            }

            driver.get("https://www.ebay.fr");
            WebElement searchBoxEbay = driver.findElement(By.id("gh-ac"));
            searchBoxEbay.sendKeys(product);
            searchBoxEbay.submit();

            List<WebElement> ebayPrices = driver.findElements(By.cssSelector(".s-item__price"));
            System.out.println("\nPrix trouvés sur eBay :");
            for (int i = 0; i < Math.min(5, ebayPrices.size()); i++) {
                System.out.println("- " + ebayPrices.get(i).getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void acceptCookies(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#sp-cc-accept"))).click();
        } catch (Exception e) {
            System.out.println("Cookie popup not found.");
        }
    }
}
