package org.example.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCartHandler {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public AddToCartHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    /**
     * Clica los botones "Add to cart" para mochila y bike-light,
     * y maneja alerts JS si aparecen (accept). No cierra el navegador.
     */
    public void addTwoProductsAndAcceptIfAlert() {
        try {
            System.out.println("🛍️ Agregando productos al carrito...");

            // 1) Mochila
            WebElement mochilaBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-backpack")));
            actions.moveToElement(mochilaBtn).pause(Duration.ofMillis(400)).click().perform();
            System.out.println(" - Mochila: click realizado");
            Thread.sleep(500);

            // Si aparece un alert JS, aceptarlo
            acceptAlertIfPresent();

            // 2) Bike Light
            WebElement bikeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("add-to-cart-sauce-labs-bike-light")));
            actions.moveToElement(bikeBtn).pause(Duration.ofMillis(400)).click().perform();
            System.out.println(" - Bike Light: click realizado");
            Thread.sleep(500);

            acceptAlertIfPresent();

            // 3) Validar badge carrito
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("shopping_cart_badge")));
            System.out.println("📦 Productos en carrito (badge): " + badge.getText());

        } catch (Exception e) {
            System.out.println("⚠️ Error en AddToCartHandler: " + e.getMessage());
        }
    }

    private void acceptAlertIfPresent() {
        try {
            // breve espera para el alert
            wait.withTimeout(Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println(" - Alert detectado: '" + alert.getText() + "' → aceptando");
            alert.accept();
            // restaurar timeout por defecto
            wait.withTimeout(Duration.ofSeconds(10));
        } catch (Exception ignored) {
            // no hay alert, restaurar timeout
            wait.withTimeout(Duration.ofSeconds(10));
        }
    }
}
