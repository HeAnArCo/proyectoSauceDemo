package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginHandler {
    private final WebDriver driver;

    private final WebDriverWait wait;

    public LoginHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String usernameText, String passwordText) {
        System.out.println("🔐 Iniciando login...");

        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
        username.clear();
        username.sendKeys(usernameText);

        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        password.clear();
        password.sendKeys(passwordText);

        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
        loginBtn.click();

        wait.until(ExpectedConditions.urlContains("inventory"));
        System.out.println("✅ Login completado");
    }
}
