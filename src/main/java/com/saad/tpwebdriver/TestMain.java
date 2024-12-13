package com.saad.tpwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestMain {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\S9\\management_de_la_qualite\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("http://127.0.0.1:5500/connexion.html");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

